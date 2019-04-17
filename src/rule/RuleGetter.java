package rule;

public interface RuleGetter<T> {
	/**
	 * Get current value of this rule
	 * 
	 * @return Current value of this rule
	 */
	public T get();
}
