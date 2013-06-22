package eu.ist.astra.am.view;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * Implementation of our personal table model, in order to have our custom
 * widgets in the cells.
 * 
 * Based on: http://java.sun.com/docs/books/tutorial/uiswing/components/table.html
 *  
 * @author David Rozas
 *
 */
public class ApplicationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 1L;
	private String[] columnNames = null;
	private String[] identifiers = null;
	private Object[][] data = null;
	
	
	/**
	 * 
	 * Creates a new table model.
	 * 
	 * @author David Rozas
	 * 
	 */
	public ApplicationTableModel(String[] columnNames, String[] identifiers, Object[][] data)
	{
		this.columnNames = columnNames;
		this.identifiers = identifiers;
		this.data = data;
	}
	
    /**
     * 
     * Returns the identifier which belongs to that row.
     * 
     * @author David Rozas
     * 
     */
	public String getIdentifierAt(int row)
	{
		if(this.identifiers!=null)
			return this.identifiers[row];
		else
			return "";
	}

    /**
     * 
     * Returns the number of columns.
     * 
     * @author David Rozas
     * 
     */
    public int getColumnCount() {
    	if(this.columnNames!=null)
    		return columnNames.length;
    	else
    		return 0;
    }

    /**
     * 
     * Returns the number of rows.
     * 
     * @author David Rozas
     * 
     */
    public int getRowCount() {
    	if (this.data!=null)
    		return data.length;
    	else
    		return 0;
    }

    /**
     * 
     * It returns the name of the indicated column
     * 
     * @author David Rozas
     * 
     */
    public String getColumnName(int col) {
    	if (this.columnNames!=null)
        	return columnNames[col];
    	else
    		return "";
    }

    /**
     * 
     * It returns the value from the indicated cell
     * 
     * @author David Rozas
     * 
     */
    public Object getValueAt(int row, int col) {
    	if (this.data!=null)
    		return data[row][col];
    	else 
    		return null;
    }

    /**
     * 
     * JTable uses this method to determine the default renderer for each cell.
     * 
     * For instance in our case, if we did not implement this method the last column
     * will show a text "false"/"true" instead of the checkbox.
     * 
     * @author David Rozas
     * 
     * 
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /**
     * 
     * Only the last cell will be editable
     * 
     * @author David Rozas
     * 
     */
    public boolean isCellEditable(int row, int col) {
        return col==this.getColumnCount()-1;
    }

    /**
     * Sets the value internally and propagates it to the cell.
     * 
     * @author David Rozas
     * 
     */
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);

    }


}
