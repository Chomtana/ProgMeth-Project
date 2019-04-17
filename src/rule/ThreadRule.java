package rule;

/**
 * Rule is similar to a variable
 * But update value in real time, and can listen for value change event
 * 
 * For example	; RealSpeed = Speed if not hit by obstacle else 0
 * 				; Quest1Completed = wood>=5
 * 
 * @author Chomtana
 *
 * @param <T> Type of variable
 */
public abstract class ThreadRule<T> extends Thread implements Runnable, RuleGetter<T>, RuleOnChange<T> {
	private T currVal = null;
	/*private RuleGetter<T> rg;
	private RuleOnChange<T> roc = (T curr,T prev)->{};
	
	public Rule(RuleGetter<T> RG) {
		rg = RG;
		start();
	}
	
	public Rule(RuleGetter<T> RG,RuleOnChange<T> ROC) {
		rg = RG;
		roc = ROC;
		start();
	}*/
	
	public ThreadRule() {
		start();
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			T prevVal = currVal;
			currVal = get();
			try {
				if (!prevVal.equals(currVal)) {
					onChange(currVal,prevVal);
				}
			} catch (NullPointerException e) {
				
			} catch (Exception e) {
				System.err.println("Error in rule");
				System.err.println(e.getMessage());
			}
			yield();
		}
	}
}
