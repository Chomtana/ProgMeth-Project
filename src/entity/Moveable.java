package entity;



public interface Moveable {
	public boolean canMoveTo(int row,int col) throws UnmoveableException;
	public boolean moveTo(int row,int col) throws UnmoveableException;
	public double getRenderRow();
	public double getRenderCol();
	public int getRow();
	public int getCol();
}
