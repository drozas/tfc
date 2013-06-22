package eu.ist.astra.rm.searchEngine;

/**
 * 
 * Customized exception for the Search engine
 * 
 * @author David Rozas
 *
 */
public class SearchEngineException extends Exception {

	private static final long serialVersionUID = 1L;

	public SearchEngineException() {
	  }

	  public SearchEngineException(String msg) {
	    super(msg);
	  }

}
