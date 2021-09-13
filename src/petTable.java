import javax.swing.table.AbstractTableModel;

public class petTable extends AbstractTableModel {

	private String[] columns;
	private Object[][] rows;
	public petTable() {}
	
	public petTable(Object[][] data, String[] columnsName) {
		this.columns = columnsName;
		this.rows = data;
	}
	
	
	@Override
	public int getRowCount() {
		return this.rows.length;
	}

	@Override
	public int getColumnCount() {
		return this.columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.rows[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int col) {
		
		return this.columns[col];
	}
}