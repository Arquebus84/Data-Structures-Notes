package AVLTree;

public class AVLTreeFinal {

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree<>();
		tree.insert(4);
		System.out.println(tree.root.data);
		tree.insert(3); tree.insert(7); tree.insert(6);// tree.insert(new Node<Integer>(2));
		tree.display();
		tree.delete(7);
		tree.display();
	}
	
	private static class Node<E extends Comparable<E>>{
		E data;
		Node<E> leftChild;
		Node<E> rightChild;
		int height;
		
		public Node(E data) {
			this.data = data;
			this.height = 1;
		}
//		public Node(E data, Node<E> leftChild, Node<E> rightChild) {
//			this.data = data;
//			this.leftChild = leftChild;
//			this.rightChild = rightChild;
//			this.height = 1;
//		}
	}
	
	private static class Tree<E extends Comparable<E>>{
		Node<E> root;
		int height;
		
		public Tree() {
			
		}
		public Tree(E data) {
			root = new Node(data);
		}
		public boolean isEmpty() {return (height < 1);}
		
		public void insert(E data) {
			insertHelper(data, root);
		}
		private Node<E> insertHelper(E data, Node<E> node) {
			if(root == null) {this.root = new Node(data);}
			if(node == null) {
				node = new Node<>(data);
			}
			if(data.compareTo(node.data) < 0) {
				node.leftChild = insertHelper(data, node.leftChild);
			}else if(data.compareTo(node.data) > 0) {
				node.rightChild = insertHelper(data, node.rightChild);
			}
			return node;
			
		}
		
		public void delete(E data) {
			deleteHelper(data, root);
		}
		public Node<E> deleteHelper(E data, Node<E> node) {
			if(node == null) {
				System.out.println("Node not found");
				return null;
			}
			if(data.compareTo(node.data) < 0) {
				node.leftChild = deleteHelper(data, node.leftChild);
			}else if(data.compareTo(node.data) > 0) {
				node.rightChild = deleteHelper(data, node.rightChild);
			}else {
				if(node.leftChild == null && node.rightChild == null) {
					node = null;
				}else if(node.leftChild != null) {
					
				}else if(node.rightChild != null) {
					
				}
				
			}
			return node;
		}
		
		public void traverse() {
			
		}
		
		public void display() {
			System.out.println();
			displayHelper(root);
		}
		private void displayHelper(Node<E> current) {
			if(current != null) {
				displayHelper(current.leftChild);
				System.out.print(current.data + " ");
				displayHelper(current.rightChild);
			}
		}
	}
}
