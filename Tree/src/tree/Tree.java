package tree;

import java.util.ArrayList;

import tree.Tree.Node;

public class Tree {

	class Node {
		private int value;
		private Node leftChild;
		private Node rightChild;

		public Node(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}

	}

	// create root node
	Node root;

	// insert value in Tree
	private void insert(int value) {
		if (root == null) {
			root = new Node(value);
			return;
		}
		Node current = root;
		while (true) {
			if (value < current.value) {
				if (current.leftChild == null) {
					current.leftChild = new Node(value);
					break;
				}
				current = current.leftChild;
			} else {
				if (current.rightChild == null) {
					current.rightChild = new Node(value);
					break;
				}
				current = current.rightChild;
			}
		}

	}

	// ------- Traversal of Tree --------

	// preOrder Traversal
	private void preOrderTraverse(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.value + " ");
		preOrderTraverse(root.leftChild);
		preOrderTraverse(root.rightChild);
	}

	public void preOrderTraverse() {
		preOrderTraverse(root);
	}

	// inOrder Traversal
	private void inOrderTraverse(Node root) {
		if (root == null) {
			return;
		}

		inOrderTraverse(root.leftChild);
		System.out.print(root.value + " ");
		inOrderTraverse(root.rightChild);

	}

	public void inOrderTraverse() {
		inOrderTraverse(root);
	}

	// postOrder Traversal
	private void postOrderTraverse(Node root) {
		if (root == null) {
			return;
		}
		postOrderTraverse(root.leftChild);
		postOrderTraverse(root.rightChild);
		System.out.print(root.value + " ");
	}

	public void postOrderTraverse() {
		postOrderTraverse(root);

	}

	// find
	public boolean find(int value) {
		if (root == null) {
			return false;
		}
		Node current = root;
		while (current != null) {
			if (value < current.value) {
				current = current.leftChild;
			}
			if (value > current.value) {
				current = current.rightChild;
			} else {
				return true;
			}
		}
		return false;

	}

	private boolean isLeaf(Node node) {
		return node.leftChild == null && node.rightChild == null;
	}

	// minimum value from tree
	private int min(Node root) {
		if (isLeaf(root)) {
			return root.value;
		}
		int left = min(root.leftChild);
		int right = min(root.rightChild);
		return Math.min(Math.min(left, right), root.value);
	}

	public int min() {
		if (root == null) {
			throw new IllegalStateException();
		}
		Node current = root;
		Node last = current;

		while (current != null) {
			last = current;
			current = current.leftChild;
		}
		return last.value;
	}

	// maximum value from tree
	private int max(Node root) {
		if (isLeaf(root)) {
			return root.value;
		}
		int left = max(root.leftChild);
		int right = max(root.rightChild);
		return Math.max(Math.max(left, right), root.value);
	}

	public int max() {
		if (root == null) {
			throw new IllegalStateException();
		}
		Node current = root;
		Node last = current;

		while (current != null) {
			last = current;
			current = current.rightChild;
		}
		return last.value;
	}

	// Height of Tree
	private int height(Node root) {
		if (root == null) {
			return -1;
		}
		if (root.leftChild == null && root.rightChild == null) {
			return 0;
		}
		return 1 + Math.max(height(root.leftChild), height(root.rightChild));
	}

	public int height() {
		return height(root);
	}

	// Binary Tree Validation
	private boolean isBinaryTree(Node root, int min, int max) {
		if (root == null) {
			return true;
		}
		Node current = root;
		if (current.value < min || current.value > max) {
			return false;
		}
		return isBinaryTree(root.leftChild, min, root.value - 1) && isBinaryTree(root.rightChild, root.value + 1, max);
	}

	public boolean isBinaryTree() {
		return isBinaryTree(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	// Equality of Tree
	private boolean equals(Node first, Node second) {
		if (first == null && second == null) {
			return true;
		} else if (first != null && second != null) {
			return (first.value == second.value) && equals(first.leftChild, second.leftChild)
					&& equals(first.rightChild, second.rightChild);
		}
		return false;
	}

	public boolean equals(Tree other) {
		if (other == null) {
			return false;
		}
		return equals(root, other.root);
	}

	// swapping
	public void swap() {
		Node temp = root.leftChild;
		root.leftChild = root.rightChild;
		root.rightChild = temp;
	}

	// find K th node from Tree

	private void getNodeAtDistance(Node root, int distance, ArrayList<Integer> list) {
		if (root == null) {
			return;
		}
		if (distance == 0) {
			list.add(root.value);
		}
		getNodeAtDistance(root.leftChild, distance - 1, list);
		getNodeAtDistance(root.rightChild, distance - 1, list);
	}

	public ArrayList<Integer> getNodeAtDistance(int distance) {
		ArrayList<Integer> list = new ArrayList<>();
		getNodeAtDistance(root, distance, list);
		return list;
	}

	// LevelOrder Traversal
	public void levelOrderTraversal() {
		for (int i = 0; i <= height(); i++) {
			for (int value : getNodeAtDistance(i)) {
				System.out.print(value + " ");
			}
		}
	}

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.insert(7);
		tree.insert(4);
		tree.insert(9);
		tree.insert(1);
		tree.insert(8);
		tree.insert(10);
		tree.insert(6);

		Tree tree2 = new Tree();
		tree2.insert(7);
		tree2.insert(4);
		tree2.insert(9);
		tree2.insert(1);
		tree2.insert(8);
		tree2.insert(10);
		tree2.insert(6);

		System.out.println("Root: " + tree.root);
		System.out.println("\nPreOrder Traversal: ");
		tree.preOrderTraverse();
		System.out.println("\nInOrder Traversal: ");
		tree.inOrderTraverse();
		System.out.println("\nPostOrder Traversal: ");
		tree.postOrderTraverse();
		System.out.println("\nIs element present: " + tree.find(26));
		System.out.println("Min: " + tree.min());
		System.out.println("Max: " + tree.max());
		System.out.println("Height: " + tree.height());

		System.out.println("Is Binary Tree: " + tree.isBinaryTree());
		tree.swap();
		System.out.println("After Swapping");
		System.out.println("Is Binary Tree: " + tree.isBinaryTree());
		System.out.println("Is both Trees are Equal ? " + tree.equals(tree2));
		System.out.println("elements present at distance: " + tree.getNodeAtDistance(2));
		System.out.println("Level Order Traversal: ");
		tree.levelOrderTraversal();
		System.out.println("\nisLeaf: " + tree.isLeaf(tree.root));
	}

}