/*
 * Copyright 2007 UbiCollab.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package eu.ist.astra.tmbe;

/**
 * 
 * Interface for the TagManagerBackEnd
 * Provides functionality for handling public and community tags.
 * The methods provided will be available through web service calls.
 * 
 * Adapted for Astra
 * 
 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
 * @version 0.3
 */
public interface ITagManagerBackEnd {
	
	public final String SOAP_SERVICE_NAME = "TagManagerBackEnd";
	
	
	/**
	 * Creates a new tag. This method is overloaded, when it is invoked with
	 * these parameters (no community) a public tag is created 
	 *
	 * Adapted for Astra.
	 *
	 * @param name Tag name
	 * @param appId Application identifier
	 * @param userId User identifier
	 * 
	 * @return True if the operation was successful, false otherwise.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 */
	public boolean addTag(String name, String appId, String userId);
	
	/**
	 * Creates a new tag by community. 
	 *
	 * Adapted for Astra.
	 *
	 * @param name Tag name
	 * @param appId Application identifier
	 * @param userId User identifier
	 * @param communityId	Community identifier
	 * 
	 * @return True if the operation was successful, false otherwise.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 */
	public boolean addTagByCommunity(String name, String appId, String userId, String communityId);
	

	/**
	 * Finds and returns all tags for the given application. This method is overloaded, when it is invoked with
	 * these parameters (no community) the public tags are retrieved.
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of public tags resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTags(String appId, int limit);
	
	/**
	 * Finds and returns all tags for the given application and community. 
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * @param communityId	Community identifier
	 * 
	 * @return List of tags which belong to that community resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTagsByCommunity(String appId, int limit, String communityId);
	
	
	/**
	 * Finds and returns all tags for the given application which are public or are from a community
	 * where the user is part of. 
	 * The tags returned are unique (if many users chose the same name, it will only appears once)
	 * and it is sorted by popularity (number of occurrences).
	 * 
	 * 
	 * @param appId Application identifier
	 * @param userId	User identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of tags which belong to that community resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author David Rozas
	 * 
	 */
	public String[] getTagsByApplication(String appId, String userId, int limit);

	
	/**
	 * Add a listener that will be notified whenever a tag is created
	 * or modified.
	 * @param String url Url of the service that implements the interface IEventsNotifier in the form http://server/
	 * @param String service Service that implements the interface IEventsNotifier i.e. EventsManager
	 */
	
	public void addTagListener(String url, String service);
	
	/**
	 * 
	 * Deletes a public tag
	 * 
	 * @param name	Tag name
	 * @param appId	Application identifier in standard ASTRA format
	 * @param userId	User identifier
	 * 
	 * @return	True if the process was successful, false otherwise.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deleteTag(String name, String appId, String userId);
	
	/**
	 * 
	 * Deletes a tag for a given community
	 * 
	 * @param name	Tag name
	 * @param appId	Application identifier in standard ASTRA format
	 * @param userId	User identifier
	 * @param communityId	Community Identifier
	 * 
	 * @return	True if the process was successful, false otherwise.
	 * 
	 * 
	 * @author David Rozas
	 * 
	 */
	public boolean deleteTag(String name, String appId, String userId, String communityId);
	
}
