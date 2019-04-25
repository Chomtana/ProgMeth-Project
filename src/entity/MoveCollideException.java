package entity;

public class MoveCollideException extends UnmoveableException {
	private Entity with;
	
	public MoveCollideException(Entity with) {
		super("Collided with "+with.getClass().getName());
		this.with = with;
	}
	
	public MoveCollideException(Entity with, String msg) {
		super(msg);
		this.with = with;
	}
	
	public MoveCollideException(Entity with, String msg, Runnable action) {
		super(msg, action);
		this.with = with;
	}
	
	public MoveCollideException(Entity with, Runnable action) {
		super(action);
		this.with = with;
	}
	
	public Entity getWith() {
		return with;
	}
}
