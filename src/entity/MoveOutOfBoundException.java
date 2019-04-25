package entity;

public class MoveOutOfBoundException extends UnmoveableException {
	public MoveOutOfBoundException() {
		super("Cannot move out of bound");
	}
	
	public MoveOutOfBoundException(String msg) {
		super(msg);
	}
	
	public MoveOutOfBoundException(String msg, Runnable action) {
		super(msg, action);
	}
	
	public MoveOutOfBoundException(Runnable action) {
		super(action);
	}
}
