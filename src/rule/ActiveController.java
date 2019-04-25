package rule;

import java.util.ArrayList;

public class ActiveController {
	private RuleGetter<Boolean> isActive;
	private ArrayList<ThreadRule> rules = new ArrayList<ThreadRule>();
	
	private ThreadRule<Boolean> activeUpdater = new ThreadRule<Boolean>() {
		
		@Override
		public void onChange(Boolean curr, Boolean prev) {
			// TODO Auto-generated method stub
			if (curr.equals(false)) {
				for(ThreadRule r: rules) {
					r.kill();
				}
			} else {
				ThreadRule r = rules.get(0);
			}
		}
		
		@Override
		public Boolean get() {
			// TODO Auto-generated method stub
			return isActive.get();
		}
	};
	
	public ActiveController(RuleGetter<Boolean> isActive) {
		this.isActive = isActive;
	}
	
	public void register(ThreadRule r) {
		rules.add(r);
	}
	
	
}
