package entity;

import rule.ThreadRule;

public interface Moveable {
	public boolean canMoveTo(int row,int col) throws UnmoveableException;
	public boolean moveTo(int row,int col) throws UnmoveableException;
	public int getRow();
	public int getCol();
}
