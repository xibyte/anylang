package mlang.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LazyList<E> implements Iterable<E> {

	protected List<E> list = null;
	private final int hint;
		
	public LazyList() {
		this(10);
	}
	
	public LazyList(int hint) {
		this.hint = hint;
	}
	
	public List<E> list() {
		if (list == null) {
			return Collections.emptyList();
		} else {
			return list;
		}
	}
	
	public int size() {
		if (list == null) {
			return 0;
		}
		return list.size();
	}

	public boolean isEmpty() {
		if (list == null) {
			return true;
		}
		return list.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		if (list == null) {
			return EmptyIterator.INSTANCE;
		}
		return list.iterator();
	}

	public Object[] toArray() {
		if (list == null) {
			return EmptyArrays.OBJECT;
		}
		return list.toArray();
	}

	public <T> T[] toArray(T[] a) {
		if (list == null) {
			return a;
		}
		return list.toArray(a);
	}

	private void ensure() {
		if (list == null) {
			list = new ArrayList<E>(hint);
		}
	}
	
	public boolean add(E e) {
		ensure();
		return list.add(e);
	}

	public void add(int index, E e) {
		ensure();
		list.add(index, e);
	}
	
	public void addArray(E[] array) {
		ensure();
		for (E e : array) {
			list.add(e);
		}
	}
	
	public boolean remove(Object o) {
		if (list == null) {
			return false;
		}
		return list.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		if (list == null) {
			return false;
		}
		return list.containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c) {
		ensure();
		return list.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends E> c) {
		ensure();
		return list.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		if (list == null) {
			return false;
		}
		return list.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		if (list == null) {
			return false;
		}
		return list.retainAll(c);
	}

	public void clear() {
		if (list == null) {
			return;
		}
		list.clear();
	}


	public E get(int index) {
		if (list == null) {
			throw new IndexOutOfBoundsException("Index: "+index+", Size: 0");
		}
		return list.get(index);
	}


	public int indexOf(Object o) {
		if (list == null) {
			return -1;
		}
		return list.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		if (list == null) {
			return -1;
		}
		return list.lastIndexOf(o);
	}
	@SuppressWarnings({ "rawtypes" })
	public static class EmptyIterable implements Iterable {

		public static EmptyIterable INSTANCE = new EmptyIterable();
		
		@Override
		public Iterator iterator() {
			return EmptyIterator.INSTANCE;
		}
	}

	@SuppressWarnings("rawtypes")
	public static class EmptyIterator implements Iterator {

		public static EmptyIterator INSTANCE = new EmptyIterator();
		
		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Object next() {
			return null;
		}

		@Override
		public void remove() {
			throw new IllegalStateException();
		}
	}
}
