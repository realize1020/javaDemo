package FunctionalInterface.two;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;


public class Comp<T> extends Prop<T> {
	
	public Comp(Supplier<T> supplier, String name, View... properties) {
		super(Objects.requireNonNull(supplier).get());
		Arrays.stream(Objects.requireNonNull(properties)).forEach(p -> p.addChangeListener(() -> set(supplier.get())));
	}
	
	@Override
	public Comp<T> as(String name) {
		super.as(name);
		return this;
	}
	
	@Override
	public void bind(Write<T> write) {
		super.bind(write);
	}
	
	public Comp<T> addSet(Consumer<T> consumer) {
		super.addChangeListener(() -> consumer.accept(get()));
		return this;
	}
	
}
