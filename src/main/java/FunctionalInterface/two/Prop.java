package FunctionalInterface.two;

import java.util.Objects;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Prop<T> extends P<T> implements MView<T> {
	
	private Lock lock = new Lock();
	
	public Prop() {}
	
	public Prop(T value) {
		set(value);
	}
	
	public Prop<T> orElseGet(Supplier<T> other) {
		super.orElseGet(other);
		return this;
	}
	
	public Prop<T> orElse(T defaultValue) {
		super.orElse(defaultValue);
		return this;
	}
	
	@Override
	public Prop<T> as(String name) {
		super.as(name);
		return this;
	}
	
	@Override
	public Prop<T> limit(BiPredicate<T, T> predicate) {
		super.limit(predicate);
		return this;
	}
	
	public boolean isLock() {
		return lock.isLock();
	}
	
	public void replace(Function<T, T> function) {
		set(Objects.requireNonNull(function).apply(get()));
	}
	
	public void changer(Consumer<T> consumer) {
		Objects.requireNonNull(consumer).accept(get());
		set(get());
	}
	
	public void bindBidirectional(MView<T> mView) {
		Objects.requireNonNull(mView);
		String key = UUID.randomUUID().toString();
		bind(key, mView);
		mView.addChangeListener(() -> set(mView.get(), key));
	}
	
	public void bindBidirectional(Write<T> write, Read<T> read, View model) {
		Objects.requireNonNull(write);
		Objects.requireNonNull(read);
		Objects.requireNonNull(model);
		
		String key = UUID.randomUUID().toString();
		bind(key, write);
		model.addChangeListener(() -> set(read.get(), key));
	}
	
	@Override
	public void set(T value) {
		lock.sync(() -> change(value));
	}
	
	private void set(T value, String key) {
		lock.sync(() -> change(value, key));
	}
	
}

class Lock {
	private boolean lock = false;
	
	public boolean isLock() {
		return lock;
	}
	
	public void sync(ChangeListener changeListener) {
		if (lock) return;
		lock = true;
		changeListener.change();
		lock = false;
	}
}
