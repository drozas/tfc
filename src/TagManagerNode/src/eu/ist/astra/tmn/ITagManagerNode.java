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

package eu.ist.astra.tmn;

/**
 * 
 * Interface for the TagManagerNode
 * Provides functionality for private tags.
 * The methods provided will be available through web service calls.
 * 
 * Adapted for Astra
 * 
 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
 * @version 0.3
 */
public interface ITagManagerNode {	
	
	public final String SOAP_SERVICE_NAME = "TagManagerNode";
	
	/**
	 * Creates a new private tag 
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
	 * Finds and returns all the private tags for the given application for this user.
	 * 
	 * 
	 * @param appId Application identifier
	 * @param userId	User Identifier
	 * @param limit Limit on number of results returned. If <=0, then all the results are returned
	 * 
	 * @return List of private tags resulting from query, empty array if there no tags for it, 
	 * 			or null if there was any problem with the DB.
	 * 
	 * @author Christian Laverton, modified by David Rozas & Alfredo Perez
	 * 
	 */
	public String[] getTags(String appId, String userId, int limit);
	
	/**
	 * 
	 * Deletes a private tag
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
	
}
