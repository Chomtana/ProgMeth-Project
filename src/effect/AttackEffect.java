package effect;

import entity.Entity;

public class AttackEffect extends AutokillEffect {

	public AttackEffect(int row, int col) {
		super(row, col,100);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCollideWith(Entity target) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

}
