package eu.ist.astra.am.view;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import eu.ist.astra.am.controller.ApplicationManagerController;

/**
 * 
 * Extension of the class DefaultTreeCellRendeder, in order to show
 * different icons in the tree depending on the type of application.
 * 
 * @author David Rozas
 *
 */
class TreeRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;
	ImageIcon nimbusIcon = null;
    ImageIcon focusIcon = null;
    ApplicationManagerController controller = null;
  
    /**
     * 
     * TreeRenderer constructor
     * 
     * @param nimbusIcon	Icon for a nimbus application
     * @param focusIcon		Icon for a focus application
     * @param controller	Reference to the controller (to ask remotely for the type through it)
     * 
     * @author David Rozas
     */
    public TreeRenderer(ImageIcon nimbusIcon, ImageIcon focusIcon, ApplicationManagerController controller) {
        this.nimbusIcon = nimbusIcon;
        this.focusIcon = focusIcon;
        this.controller = controller;
    }

    /**
     * 
     * Display a different icon depending on the type of application.
     * 
     * @author David Rozas
     * 
     */
    public Component getTreeCellRendererComponent(
                        JTree tree,
                        Object value,
                        boolean sel,
                        boolean expanded,
                        boolean leaf,
                        int row,
                        boolean hasFocus) {

        super.getTreeCellRendererComponent(
                        tree, value, sel,
                        expanded, leaf, row,
                        hasFocus);

        //drozas: display a different icon depending on the type of application (focus or nimbus),
        //or standard one if is an unknown type (this should not happen, but just in case)
        if (this.controller.isEligibleNode((DefaultMutableTreeNode)value) )
        {
        	String type = this.controller.getApplicationType(((DefaultMutableTreeNode)value));
        	
        	
        	if (type!=null && type.equalsIgnoreCase("nimbus"))
        		setIcon(this.nimbusIcon);
        	else if(type!=null && type.equalsIgnoreCase("focus"))
        		setIcon(this.focusIcon);
        	else
        		setIcon(leafIcon);
        }

        return this;
    }

}
