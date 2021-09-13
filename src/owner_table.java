import javax.swing.table.AbstractTableModel;

public class owner_table extends AbstractTableModel {

	private String[] columns;
	private Object[][] rows;
	public owner_table() {}
	
	public owner_table(Object[][] data, String[] columnsName) {
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
