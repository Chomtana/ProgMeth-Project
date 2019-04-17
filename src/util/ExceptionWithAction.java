package util;

public class ExceptionWithAction extends Exception {
	protected String message = "";
	protected Runnable action = null;
	
	public ExceptionWithAction() {
		super();
	}
	
	public ExceptionWithAction(String msg) {
		super(msg);
		this.message = msg;
	}
	
	public ExceptionWithAction(String msg, Runnable action) {
		super(msg);
		this.message = msg;
		this.action = action;
	}
	
	public ExceptionWithAction(Runnable action) {
		super();
		this.action = action;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean canPerformAction() {
		return action != null;
	}
	
	public void performAction() {
		if (canPerformAction()) action.run();
	}
	
	public Runnable getAction() {
		return action;
	}
	
	public void setAction(Runnable action) {
		this.action = action;
	}
}
