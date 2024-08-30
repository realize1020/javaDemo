package FunctionalInterface.two;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Supplier;



public class P<T> implements RView<T> {
	
	private boolean log;
	
	private T value;
	
	private Map<String, BiConsumer<T, T>> writeMap = new HashMap<>();
	
	private List<ChangeListener> changeListenerList = new ArrayList<>();
	
	private String name;
	
	private BiPredicate<T, T> predicate = (o, n) -> true;
	
	private T defaultValue;
	
	private Supplier<T> other = () -> defaultValue;
	
	public P<T> as(String name) {
		this.name = Objects.requireNonNull(name);
		this.log = true;
		return this;
	}
	
	public P<T> orElseGet(Supplier<T> other) {
		this.other = Objects.requireNonNull(other);
		this.value = other.get();
		return this;
	}
	
	public P<T> orElse(T defaultValue) {
		this.defaultValue = defaultValue;
		this.value = defaultValue;
		return this;
	}
	
	public P<T> limit(BiPredicate<T, T> predicate) {
		this.predicate = Objects.requireNonNull(predicate);
		return this;
	}
	
	public T get() {
		return value;
	}
	
	public void bind(Write<T> write) {
		bind(UUID.randomUUID().toString(), write);
	}
	
	public void bind(String key, Write<T> write) {
		Objects.requireNonNull(write);
		this.writeMap.put(key, (o, n) -> write.set(n));
	}
	
	public void watch(BiConsumer<T, T> biConsumer) {
		watch(UUID.randomUUID().toString(), biConsumer);
	}
	
	public void watch(String key, BiConsumer<T, T> biConsumer) {
		Objects.requireNonNull(biConsumer);
		this.writeMap.put(key, biConsumer);
	}
	
	public void bindExe(Write<T> write) {
		bindExe(UUID.randomUUID().toString(), write);
	}
	
	public void bindExe(String key, Write<T> write) {
		Objects.requireNonNull(write);
		write.set(value);
		this.writeMap.put(key, (o, n) -> write.set(n));
	}
	
	public void clearBind() {
		this.writeMap.clear();
		this.value = other.get();
	}
	
	public void clearListener() {
		this.changeListenerList.clear();
	}
	
	private void _change(T value) {
		if (log) System.out.println(name + "  --source:" + this.value + "  --change:" + value);
		this.value = Optional.ofNullable(value).orElseGet(other);
		this.changeListenerList.forEach(ChangeListener::change);
	}
	
	protected void change(T value) {
		T old = this.value;
		if (!predicate.test(old, value)) return;
		_change(value);
		this.writeMap.values().stream().forEach(c -> c.accept(old, get()));
	}
	
	protected void change(T value, String key) {
		T old = this.value;
		if (!predicate.test(old, value)) return;
		_change(value);
		this.writeMap.entrySet().stream().filter(e -> !e.getKey().equals(key)).map(e -> e.getValue()).forEach(c -> c.accept(old, get()));
	}
	
	public boolean isBind() {
		return !writeMap.isEmpty();
	}
	
	@Override
	public void addChangeListener(ChangeListener changeListener) {
		this.changeListenerList.add(Objects.requireNonNull(changeListener));
	}
	
	@Override
	public String toString() {
		return "P:" + Objects.toString(value);
	}
	
}
