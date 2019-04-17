package entity;

import util.ExceptionWithAction;

public class UnmoveableException extends ExceptionWithAction {
	public UnmoveableException() {
		super();
	}
	
	public UnmoveableException(String msg) {
		super(msg);
	}
	
	public UnmoveableException(String msg, Runnable action) {
		super(msg, action);
	}
	
	public UnmoveableException(Runnable action) {
		super(action);
	}
}
