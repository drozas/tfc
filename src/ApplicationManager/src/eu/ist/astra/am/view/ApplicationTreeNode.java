package eu.ist.astra.am.view;

import java.util.Collections;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

/**
 * 
 * Extends a DefaultMutableTreeNode to implement Comparable
 * and overrides insert so that the relevant nodes are sorted 
 * after each insert
 * 
 * 
 * @author David Rozas 
 *
 */
public class ApplicationTreeNode extends DefaultMutableTreeNode implements Comparable {
    public ApplicationTreeNode(String name) {
        super(name);
    }

    
    public void insert(final MutableTreeNode newChild, final int childIndex) {
        super.insert(newChild, childIndex);
        Collections.sort(this.children);
    }
    public int compareTo(final Object o) {
        return this.toString().compareToIgnoreCase(o.toString());
    }
}
