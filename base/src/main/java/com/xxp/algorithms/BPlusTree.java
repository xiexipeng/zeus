package com.xxp.algorithms;

/**
 * @Author: xiexipeng
 * @Date: 2019/6/27
 * @Time: 下午11:41
 * @Description: B+ Tree
 */
public class BPlusTree<T extends Comparable<T>, V> {

	private int factor;

	private static final int DEFAULT_FACTOR = 5;

	private int MIN_CHILDREN_FOR_INTERNAL;
	private int MAX_CHILDREN_FOR_INTERNAL;
	private int MIN_FOR_LEAF;
	private int MAX_FOR_LEAF;

	private Node root;

	private Integer size;

	private Integer high;

	public BPlusTree() {
		this(DEFAULT_FACTOR);
	}

	public BPlusTree(int factor) {
		this.factor = factor;

		this.MIN_CHILDREN_FOR_INTERNAL = Double.valueOf(Math.ceil(1.0 * this.factor / 2)).intValue();
		this.MAX_CHILDREN_FOR_INTERNAL = this.factor;
		this.MIN_FOR_LEAF = Double.valueOf(Math.floor(1.0 * this.factor / 2)).intValue();
		this.MAX_FOR_LEAF = this.factor;
	}

	/**
	 * 子节点长度和当前节点项的长度相等
	 */
	abstract class Node<T extends Comparable<T>, V> {

		// 父节点
		protected Node<T, V> parent;

		// 节点的项，按键非降序存放
		protected Object[] keys;

		protected int size;

		//所在高度
//		private Integer level;

		// 是否为叶子节点
//		private boolean leaf;

		public Node() {
		}

		abstract Node<T, V> insert(T key, V value);

		abstract V get(T key);
	}

	/**
	 * 内部节点，非叶子节点只包括子节点的引用，不包括数据
	 *
	 * @param <T>
	 * @param <V>
	 */
	class InternalNode<T extends Comparable<T>, V> extends Node<T, V> {

		// 子节点
		private Node<T, V>[] child;

		public InternalNode() {
			super.size = 0;
			super.keys = new Object[MAX_CHILDREN_FOR_INTERNAL];
			this.child = new Node[MAX_CHILDREN_FOR_INTERNAL + 1];
		}

		@Override
		Node<T, V> insert(T key, V value) {
			int i = 0;
			for (; i < this.size; i++) {
				if (key.compareTo((T) this.keys[i]) < 0) {
					break;
				}
			}
			return this.child[i].insert(key, value);
		}

		@Override
		V get(T key) {
			int i = 0;
			for (; i < this.size; i++) {
				if (key.compareTo((T) this.keys[i]) < 0) {
					break;
				}
			}
			return this.child[i].get(key);
		}

		public Node<T, V> insert(T key, Node<T, V> leftChild, Node<T, V> rightChild) {
			if (this.size == 0) {
				this.size++;
				keys[0] = key;
				child[0] = leftChild;
				child[1] = rightChild;
				return this;
			}

			Object[] newKeys = new Object[MAX_CHILDREN_FOR_INTERNAL + 1];
			Node[] newChild = new Node[MAX_CHILDREN_FOR_INTERNAL + 2];

			int i = 0;
			for (; i < this.size; i++) {
				if (((T) this.keys[i]).compareTo(key) > 0) {
					break;
				}
			}

			// 将key按大小排序保存到指定的位置
			System.arraycopy(this.keys, 0, newKeys, 0, i);
			newKeys[i] = key;
			System.arraycopy(this.keys, i, newKeys, i + 1, this.size - i);
			// 将孩子节点保存到指定位置
			System.arraycopy(this.child, 0, newChild, 0, i + 1);
			newChild[i + 1] = rightChild;
			System.arraycopy(this.child, i + 1, newChild, i + 2, this.size - i);

			this.size++;
			if (this.size <= MAX_CHILDREN_FOR_INTERNAL) {
				System.arraycopy(newKeys, 0, this.keys, 0, this.size);
				System.arraycopy(newChild, 0, this.child, 0, this.size + 1);
				return null;
			}

			// 内节点分裂
			int m = (this.size / 2);

			// split the internal node
			InternalNode<T, V> newNode = new InternalNode<T, V>();

			newNode.size = this.size - m - 1;
			System.arraycopy(newKeys, m + 1, newNode.keys, 0, this.size - m - 1);
			System.arraycopy(newChild, m + 1, newNode.child, 0, this.size - m);

			// 更新孩子节点的父节点为新的内节点
			for (int j = 0; j <= newNode.size; j++) {
				newNode.child[j].parent = newNode;
			}

			// 更新当前内节点
			this.size = m;
			this.keys = new Object[MAX_CHILDREN_FOR_INTERNAL];
			this.child = new Node[MAX_CHILDREN_FOR_INTERNAL];
			System.arraycopy(newKeys, 0, this.keys, 0, m);
			System.arraycopy(newChild, 0, this.child, 0, m + 1);
			if (this.parent == null) {
				this.parent = new InternalNode<T, V>();
			}
			newNode.parent = this.parent;

			return ((InternalNode<T, V>) this.parent).insert((T) newKeys[m], this, newNode);
		}
	}

	class LeafNode<T extends Comparable<T>, V> extends Node<T, V> {

		private Object[] values;

		public LeafNode() {
			super.size = 0;
			super.keys = new Object[MAX_FOR_LEAF];
			this.values = new Object[MAX_FOR_LEAF];
		}

		@Override
		public Node<T, V> insert(T key, V value) {
			Object[] newKeys = new Object[MAX_FOR_LEAF + 1];
			Object[] newValues = new Object[MAX_FOR_LEAF + 1];

			int i = 0;
			for (; i < this.size; i++) {
				T curKey = (T) this.keys[i];

				if (curKey.compareTo(key) == 0) {
					this.values[i] = value;
					return null;
				}

				if (curKey.compareTo(key) > 0) break;
			}

			// 将按大小key保存到指定位置
			System.arraycopy(this.keys, 0, newKeys, 0, i);
			newKeys[i] = key;
			System.arraycopy(this.keys, i, newKeys, i + 1, this.size - i);
			// 将value保存到指定位置
			System.arraycopy(this.values, 0, newValues, 0, i);
			newValues[i] = value;
			System.arraycopy(this.values, i, newValues, i + 1, this.size - i);

			this.size++;

			if (this.size <= MAX_FOR_LEAF){
				System.arraycopy(newKeys, 0, this.keys, 0, this.size);
				System.arraycopy(newValues, 0, this.values, 0, this.size);
				return null;
			}

			// need split this node
			int m = this.size / 2;

			this.keys = new Object[MAX_FOR_LEAF];
			this.values = new Object[MAX_FOR_LEAF];
			System.arraycopy(newKeys, 0, this.keys, 0, m);
			System.arraycopy(newValues, 0, this.values, 0, m);

			LeafNode<T, V> newNode = new LeafNode<T, V>();
			newNode.size = this.size - m;
			System.arraycopy(newKeys, m, newNode.keys, 0, newNode.size);
			System.arraycopy(newValues, m, newNode.values, 0, newNode.size);

			this.size = m;

			if (this.parent == null) {
				this.parent = new InternalNode<T, V>();
			}
			newNode.parent = this.parent;

			return ((InternalNode<T, V>)this.parent).insert((T)newNode.keys[0], this, newNode);
		}

		@Override
		V get(T key) {
			return null;
		}
	}

//	private static class Entry {
//
//		private String key;
//
//		private Integer value;
//
//		public Entry(String key, Integer value) {
//			this.key = key;
//			this.value = value;
//		}
//	}

}