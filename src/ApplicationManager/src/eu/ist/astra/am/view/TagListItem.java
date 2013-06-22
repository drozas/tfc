package eu.ist.astra.am.view;

/**
 * 
 * Class to model the tag items in the list. Stores the type and the name, and
 * the community information if necessary.
 * 
 * @author David Rozas
 *
 */
public class TagListItem 
{
	private String type = null;
	private String value = null;
	private String communityId = null;
	private String communityName = null;

	public TagListItem(String t, String s) {
		this.type = t;
		this.value = s;
	}
	public TagListItem(String t, String s, String communityId, String communityName) {
		this.type = t;
		this.value = s;
		this.communityId = communityId;
		this.communityName = communityName;
	}

	public String getType() {
		return this.type;
	}
	public String getValue() {
		return this.value;
	}
	public String getCommunityId() {
		return this.communityId;
	}
	public String getCommunityName() {
		return this.communityName;
	}
    


}
