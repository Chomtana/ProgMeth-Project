package rule;

public interface RuleOnChange<T> {
	public void onChange(T curr,T prev);
}
