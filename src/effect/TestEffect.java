package effect;

import entity.Entity;

public class TestEffect extends AutokillEffect {

	public TestEffect(int row, int col) {
		super(row, col,5000);
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