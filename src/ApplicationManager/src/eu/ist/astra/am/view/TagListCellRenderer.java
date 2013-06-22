package eu.ist.astra.am.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


/**
 * 
 * Extends the cell renderer to show different tag icons depending on 
 * the scope 
 * 
 * 
 * @author David Rozas
 *
 */
public class TagListCellRenderer extends JLabel implements ListCellRenderer {
	
	private static final long serialVersionUID = 1L;
	private ImageIcon publicIcon = null;
    private ImageIcon communitiesIcon = null;
    private ImageIcon privateIcon = null;
	
    public TagListCellRenderer(ImageIcon publicIcon, ImageIcon communitiesIcon, ImageIcon privateIcon) {
        this.publicIcon = publicIcon;
        this.communitiesIcon = communitiesIcon;
        this.privateIcon = privateIcon;
    }
    
    // Set the attributes of the 
    //class and return a reference
    public Component  getListCellRendererComponent(JList list, Object value, int index, boolean iss, boolean chf)
    {
         //Set the text and icon for rendering
    	this.setText(((TagListItem)value).getValue() );
    	
    	
 		if(((TagListItem)value).getType().equalsIgnoreCase("public"))
 		{
 			this.setIcon(this.publicIcon);
 			this.setToolTipText("Public tag");
		}else if(((TagListItem)value).getType().equalsIgnoreCase("communities")){
			this.setIcon(this.communitiesIcon);
			this.setToolTipText("Tag in community: " + ((TagListItem)value).getCommunityName());
		}else if(((TagListItem)value).getType().equalsIgnoreCase("private")){
			this.setIcon(this.privateIcon);
			this.setToolTipText("Private tag");
		}
 		
 		// Set a border if the listitem is selected 
       if (iss) 
       {
    	   this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
       } else {
    	   this.setBorder(BorderFactory.createLineBorder(list.getBackground(), 1));
       }

       return this;
           
     }


}
