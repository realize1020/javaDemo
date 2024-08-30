package FunctionalInterface.two;

public interface ChangeListener {
	
	void change();
	
	default ChangeListener andThen(ChangeListener changeListener) {
		return () -> {
			this.change();
			changeListener.change();
		};
	}
	
	static ChangeListener EMPTY = () -> {};
}
