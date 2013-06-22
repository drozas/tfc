package eu.ist.astra.rm.searchEngine;

import java.io.IOException;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
//import org.apache.lucene.search.Hits;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Searcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;

import eu.ist.astra.rm.impl.SharedApplication;

/**
 * 
 * This class implements a search engine to perform queries about
 * the applications in the repository.
 * 
 * 
 * @author David Rozas
 *
 */
public class SearchEngine {
	
	// Directory where the index is created
	private RAMDirectory indexDirectory = new RAMDirectory();
	
	private IndexWriter indexWriter = null;

	/**
	 * 
	 * It creates a SearchEngine object, storing the index data in the
	 * given indexDirectory (in local memory - more efficient) 
	 * 
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public SearchEngine() throws SearchEngineException{
		try {
			this.indexWriter = new IndexWriter(this.indexDirectory, new StandardAnalyzer(), true, IndexWriter.MaxFieldLength.UNLIMITED);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to create the index because it is corrupted");
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to create the index because it is locked");
		} catch (IOException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to create the index");
		}
	}
	
	/**
	 * 
	 * It adds a SharedApplication to the index
	 * 
	 * @param app	Application to be added
	 * @param tags	List of tags associated to that application
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized void addSharedApplicationToIndex(SharedApplication app, String[] tags) throws SearchEngineException{

		//We create a document
		Document doc = new Document();
			
		doc.add(new Field("id", app.getAppID(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("description", app.getDescription(), Field.Store.YES, Field.Index.ANALYZED));
		doc.add(new Field("type", app.getType(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		doc.add(new Field("owner", app.getUid(), Field.Store.YES, Field.Index.NOT_ANALYZED));
		
		//Process and add the list of tags
		String tagAux = "";
		for (int i=0; i<tags.length; i++)
			tagAux += tags[i] + " ";
		doc.add(new Field("tags", tagAux, Field.Store.YES, Field.Index.ANALYZED));

		
		try {
			this.indexWriter.addDocument(doc);
			this.indexWriter.optimize();
			this.indexWriter.commit();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while adding a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while adding a document.");
		}

	}
	
	
	/**
	 * 
	 * It deletes an application from the index
	 * 
	 * @param app	Application to be deleted
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized void deleteSharedApplicationFromIndex(String appId) throws SearchEngineException{


		try {
			this.indexWriter.deleteDocuments(new Term("id", appId));
			this.indexWriter.optimize();
			this.indexWriter.commit();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while deleting a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while deleting a document.");
		}

	}
	
	
	/**
	 * It makes a search based on one of the fields
	 * 
	 * @param q	String with the query
	 * @param field	String with the field to search for
	 * @param userId	Identifier of the user who is performing the query to filter apps
	 * 					own by him
	 * @param verbose 	It shows the result also for the standard output (just for debugging)
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results
	 * 
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized String[] search(String q, String field, String userId, boolean verbose) throws SearchEngineException{
		
		String [] filteredResults = null;
		
		try {
			// We have to create an object which searches on the directory
			Searcher searcher;
			
			searcher = new IndexSearcher(this.indexDirectory);
	
	
			QueryParser singleParser = new QueryParser(field, new StandardAnalyzer());
			Query query = singleParser.parse(q);
		
			// We perform the query on the searcher
			TopDocs results = searcher.search(query, null, 100);
			
			//And proceed to filter the results
			filteredResults = filterResults(searcher, results, userId);
			
			if(verbose)
			{
				System.out.println("The following query " + q + " in the field " + field + " has been performed");
				System.out.println(results.totalHits + " applications found. Query performed by " + userId);
				
				if (filteredResults!=null)
					System.out.println("Results filtered (same user) = " + (results.totalHits - filteredResults.length));
				else
					System.out.println("No valid results after filtering");
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to parse the query ");
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while searching for a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while searching for a document.");
		}
		
		return filteredResults;
	}
	
	/**
	 * It makes a search in different fields
	 * 
	 * @param q	String with the query
	 * @param fields	String array with all the fields to search in
	 * @param verbose 	It shows the result also for the standard output (just for debugging)
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results.
	 * 
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized String[] search(String q, String[] fields, String userId, boolean verbose) throws SearchEngineException {

		String [] filteredResults = null;
		
		try{
			// We have to create an object which searches on the directory
			Searcher searcher = new IndexSearcher(this.indexDirectory);
	
			MultiFieldQueryParser multiquery = new MultiFieldQueryParser(fields, new StandardAnalyzer());
			Query query = multiquery.parse(q);
			
			// We perform the query on the searcher
			TopDocs results = searcher.search(query, null, 100);
			
			//And proceed to filter the results
			filteredResults = filterResults(searcher, results, userId);
			
			//Just for debugging purposes, it will not be called in deployment
			if(verbose)
			{
				System.out.print("The following query " + q + " in the fields ");
				for(int i=0; i<fields.length; i++)
					System.out.print(fields[i] + " ");
				System.out.println(" has been performed");
				
				
				System.out.println(results.totalHits + " applications found. Query performed by " + userId);
				if (filteredResults!=null)
					System.out.println("Results filtered (same user) = " + (results.totalHits - filteredResults.length));
				else
					System.out.println("No valid results after filtering");
				
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to parse the query ");
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while searching for a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while searching for a document.");
		}
		
		return filteredResults;
		
	}
	
	/**
	 * 
	 * Filters the set of initial results, eliminating the applications that
	 * belong to that user and storing the application identifiers in filteredResults
	 * 
	 * @param searcher	Searcher, necessary to access the documents.
	 * @param initialResults	Set of initial results
	 * @param userId	Identifier of the user whose application will be filtered.
	 * 
	 * @return 	 String array with the application identifiers after filtering process, 
	 * 			null if there are not valid results.
	 * 
	 * @throws CorruptIndexException
	 * @throws IOException
	 * 
	 * @author David Rozas
	 * 
	 */
	private String[] filterResults(Searcher searcher, TopDocs initialResults, String userId) throws CorruptIndexException, IOException 
	{
	
		String[] filteredResults = null;
		
		//We calculate the number total of hits after filtering
		int realHits = 0;
		for(ScoreDoc result : initialResults.scoreDocs)
		{
			//Eliminating hits which belongs to this user
			Document doc = searcher.doc(result.doc);
			if (!doc.getField("owner").stringValue().equals(userId) /*&& result.score>0.1*/)
				realHits++;
		}
		
		//If there are any results, add them to the filtered results.
		if (realHits>0)
		{
			filteredResults = new String[realHits];
			int i = 0;
			for(ScoreDoc result : initialResults.scoreDocs)
			{
				//Eliminating hits which belongs to this user
				Document doc = searcher.doc(result.doc);
				if (!doc.getField("owner").stringValue().equals(userId) /*&& result.score>0.1*/)
				{
					filteredResults[i] = doc.getField("id").stringValue();
					i++;
				}
			}
		}
		
		return filteredResults;
		
	}
	
	/**
	 * 
	 * It performs a query to search for similar applications.
	 * 
	 * 
	 * Internally, we will compose the query based on the application metadata, and we
	 * make use of the standard multifield search
	 * 
	 * @param userId	User identifier
	 * @param appDescription	Description of the compared application
	 * @param tags	List of tags visible for this user.
	 * @param verbose	It shows the results also for the standard output if it is true
	 * 
	 * @return Array with all the application identifiers (in standard ASTRA format) sorted by score, 
	 * 			null if there were not results
	 * 
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized String[] searchBySimilarity(String userId, String appDescription, String[] tags, boolean verbose) throws SearchEngineException{

		//We compose a query based on the description and the public and community tags, which will be compared
		//with description + tags fields for every application in the repository
		
		String query = appDescription + " ";
		for (int i=0; i<tags.length; i++)
			query += tags[i] + " ";
		
		//Search by similarity is performed in fields description and tags
		String[] fields = {"description", "tags"};
		return search(query, fields, userId, verbose);
		
	}
	
	/**
	 * 
	 * Add a new tag to the document. Since in Lucene it is not possible to update
	 * the document, we need to:
	 * 	- Perform a query by appId
	 *  - Recover all the information about that document
	 *  - Delete it
	 *  - Create it with the new information.
	 *  
	 *  If the event belongs to an application which has not been shared, the event is 
	 *  listened but it is ignored (there are no actions with respect to it)
	 * 
	 * @param appId	Application identifier in the standard ASTRA format
	 * @param tag	Tag to be added
	 * @param verbose	It shows the results also for the standard output
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized void addTagToDocument(String appId, String tag, boolean verbose) throws SearchEngineException{
		
	
		try {
			// We have to create an object which searches on the directory
			Searcher searcher;
			
			searcher = new IndexSearcher(this.indexDirectory);
			
			//Recover the document (application) with that id. Since there are some
			//special characters in the identifier, we have to create a TermQuery w/o parsing
			TermQuery my_query = new TermQuery(new Term("id", appId));
		
			// We perform the query on the searcher
			TopDocs results = searcher.search(my_query, null, 100);
			
			// This should return only one, but just in case
			for(ScoreDoc result : results.scoreDocs)
			{
				//We recover the old document
				Document doc = searcher.doc(result.doc);
				
				//Create a new document with the old data plus the new tag
				Document newDoc = new Document();
					
				newDoc.add(new Field("id", doc.getField("id").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				newDoc.add(new Field("description", doc.getField("description").stringValue(), Field.Store.YES, Field.Index.ANALYZED));
				newDoc.add(new Field("type", doc.getField("type").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				newDoc.add(new Field("owner", doc.getField("owner").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				newDoc.add(new Field("tags", doc.getField("tags").stringValue() + tag + " ", Field.Store.YES, Field.Index.ANALYZED));
				
				
				//Delete the old document
				this.deleteSharedApplicationFromIndex(appId);

				//And add the new one
				this.indexWriter.addDocument(newDoc);
				this.indexWriter.optimize();
				this.indexWriter.commit();

				if (verbose)
					System.out.println("Document to be modified:");
					System.out.println(this.documentToString(doc));
					System.out.println("New document:");
					System.out.println(this.documentToString(newDoc));
					System.out.println("The operation was performed sucessfully");
				
			}
			
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while updating a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while updating a document.");
		}
		
	}
	
	
	/**
	 * 
	 * Remove a tag from the document. Since in Lucene it is not possible to update
	 * the document, we need to:
	 * 	- Perform a query by appId
	 *  - Recover all the information about that document
	 *  - Delete it
	 *  - Create it with the new information.
	 *  
	 *  If the event belongs to an application which has not been shared, the event is 
	 *  listened but it is ignored (there are no actions with respect to it)
	 * 
	 * @param appId	Application identifier in the standard ASTRA format
	 * @param tag	Tag to be deleted
	 * @param verbose	It shows the results also for the standard output
	 * @throws SearchEngineException
	 * 
	 * @author David Rozas
	 */
	public synchronized void removeTagFromDocument(String appId, String tag, boolean verbose) throws SearchEngineException{
		
	
		try {
			// We have to create an object which searches on the directory
			Searcher searcher;
			
			searcher = new IndexSearcher(this.indexDirectory);
			
			//Recover the document (application) with that id. Since there are some
			//special characters in the identifier, we have to create a TermQuery w/o parsing
			TermQuery my_query = new TermQuery(new Term("id", appId));
		
			// We perform the query on the searcher
			TopDocs results = searcher.search(my_query, null, 100);
			
			// This should return only one, but just in case
			for(ScoreDoc result : results.scoreDocs)
			{
				//We recover the old document
				Document doc = searcher.doc(result.doc);
				
				//Create a new document with the old data plus the new tag
				Document newDoc = new Document();
					
				newDoc.add(new Field("id", doc.getField("id").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				newDoc.add(new Field("description", doc.getField("description").stringValue(), Field.Store.YES, Field.Index.ANALYZED));
				newDoc.add(new Field("type", doc.getField("type").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				newDoc.add(new Field("owner", doc.getField("owner").stringValue(), Field.Store.YES, Field.Index.NOT_ANALYZED));
				
				//Replace the tag
				String newTags = this.replace(doc.getField("tags").stringValue() + " ", tag, "");
				newDoc.add(new Field("tags", newTags, Field.Store.YES, Field.Index.ANALYZED));
				
				
				//Delete the old document
				this.deleteSharedApplicationFromIndex(appId);

				//And add the new one
				this.indexWriter.addDocument(newDoc);
				this.indexWriter.optimize();
				this.indexWriter.commit();

				if (verbose)
					System.out.println("Document to be modified:");
					System.out.println(this.documentToString(doc));
					System.out.println("New document:");
					System.out.println(this.documentToString(newDoc));
					System.out.println("The operation was performed sucessfully");
				
			}
			
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while updating a document because it is corrupt");
		} catch (IOException e) {
			throw new SearchEngineException("Search Engine: It was not possible to access to the index " +
											"while updating a document.");
		}
		
	}
	
	
	/**
	 * 
	 * Returns a printable version of the document in a string.
	 * This should be used only in the verbose mode.
	 * 
	 * @param doc	Document
	 * @return	String in printable version
	 * 
	 * @author David Rozas
	 * 
	 */
	private String documentToString(Document doc)
	{
		String res = "";
		res += "Field id = " + doc.getField("id").stringValue() + "\n";
		res += "Field description = " + doc.getField("description").stringValue() + "\n";
		res += "Field type = " + doc.getField("type").stringValue() + "\n";
		res += "Field owner = " + doc.getField("owner").stringValue() + "\n";
		res += "Field tags = " + doc.getField("tags").stringValue() + "\n";
		
		return res;
	}
	
	/**
	 * 
	 * Replace the substring in the pattern by replace
	 *
	 * @param str	String to be analyzed
	 * @param pattern	Patter to search for
	 * @param replace	Replacement
	 * 
	 * @return	New string with the replacement
	 */
    private String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
    
        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e+pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }


}
