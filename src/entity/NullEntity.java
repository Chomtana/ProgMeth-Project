package entity;

public class NullEntity extends Entity {
	
	private int row = 0;
	private int col = 0;

	public NullEntity(int row, int col) {
		super(row,col);
		this.row = row;
		this.col = col;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public int getRow() {
		// TODO Auto-generated method stub
		return row;
	}

	@Override
	public int getCol() {
		// TODO Auto-generated method stub
		return col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}

}
