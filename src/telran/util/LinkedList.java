package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private int size;

	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> head; // reference to the first element
	private Node<T> tail; // reference to the last element

	@Override
	public void add(T element) {
		Node<T> newNode = new Node<>(element);
		if (head == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;

	}

	private Node<T> getNode(int index) {
		Node<T> res = null;
		if (isValidIndex(index)) {
			res = index <= size / 2 ? getNodefromLeft(index) : getNodeFromRight(index);
		}
		return res;
	}

	private Node<T> getNodeFromRight(int index) {
		Node<T> current = tail;
		int ind = size - 1;
		while (ind != index) {
			ind--;
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodefromLeft(int index) {
		Node<T> current = head;
		int ind = 0;
		while (ind != index) {
			ind++;
			current = current.next;
		}
		return current;
	}

	private boolean isValidIndex(int index) {

		return index >= 0 && index < size;
	}

	@Override
	public boolean add(int index, T element) {
		boolean res = false;
		if (index == size) {
			add(element);
			res = true;
		} else if (isValidIndex(index)) {
			res = true;
			Node<T> newNode = new Node<>(element);
			if (index == 0) {
				addHead(newNode);
			} else {
				addMiddle(newNode, index);
			}
			size++;
		}
		return res;
	}

	private void addMiddle(Node<T> newNode, int index) {
		Node<T> nodeAfter = getNode(index);
		newNode.next = nodeAfter;
		newNode.prev = nodeAfter.prev;
		// nodeAfter.prev => reference to the old previous element
		nodeAfter.prev.next = newNode;
		nodeAfter.prev = newNode;

	}

	private void addHead(Node<T> newNode) {
		newNode.next = head;
		head.prev = newNode;
		head = newNode;

	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public T get(int index) {
		T res = null;
		Node<T> resNode = getNode(index);
		if (resNode != null) {
			res = resNode.obj;
		}
		return res;
	}

	@Override
	public T remove(int index) {
		if (!isValidIndex(index)) {
			return null;
		}
		Node<T> resNode = getNode(index);
		T res = resNode.obj;
		if (head == tail) {
			head = tail = null;

		} else {
			if (index == 0) {
				head = resNode.next;
				head.prev = null;
			} else if (index == size - 1) {
				tail = resNode.prev;
				tail.next = null;

			} else {
				resNode.prev.next = resNode.next;
				resNode.next.prev = resNode.prev;
			}
		}
		size--;
		return res;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = -1;
		Node<T> resNode = head;
		for (int i = 0; i < size; i++) {
			if (predicate.test(resNode.obj)) {
				index = i;
				break;
			}
			resNode = resNode.next;
		}
		return index;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = -1;
		Node<T> resNode = tail;
		for (int i = size - 1; i >= 0; i--) {
			if (predicate.test(resNode.obj)) {
				index = i;
				break;
			}
			resNode = resNode.prev;
		}
		return index;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		Node<T> resNode = tail;
		for (int i = size - 1; i >= 0; i--) {
		if (predicate.test(resNode.obj)) {
			remove(i);
		}
			resNode = resNode.prev;
	}
		return oldSize > size;
	}

	@Override
	public void sort(Comparator<T> comp) {
		T[] array = listToArray();
		Arrays.sort(array, comp);
		fillListFromArray(array);

	}

	private T[] listToArray() {
		// TODO
		// creates array of T objects
		// passes over whole list and fills the array
		// sorting filled array
		return null;
	}

	private void fillListFromArray(T[] array) {
		// TODO
		// passes over whole list and fills elements from index=0 to index=size - 1
	}

}
