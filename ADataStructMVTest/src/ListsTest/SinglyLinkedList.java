package ListsTest;


public class SinglyLinkedList {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.addLast(2); list.addLast(4); list.addLast(6); list.addLast(8);
		//System.out.println(list.Size());
		list.displayList(list.head);
		//System.out.println(list.reverse(list.head));
		list.reverse(list.head);
		list.displayList(list.tail);

	}
	private static class Node<E>{
		E data;
		Node<E> next;
		public Node(E data) {
			this.data = data;
		}
		public Node(E data, Node<E> next) {
			this.data = data;
			this.next = next;
		}
	}
	
	private static class LinkedList<E>{
		Node<E> head;
		Node<E> tail;
		int size = 0;
		
		public LinkedList() {
			this.size = 0;
		}
		public boolean isEmpty() {return (Size() == 0);}
		public int Size() {return this.size;}
		
		public void addFirst(E data) {
			Node<E> newest = new Node<>(data, head);
			if(isEmpty()) {
				tail = newest;
			}else{
				head = newest;
			}
			size++;
		}
		public void addLast(E data) {
			Node<E> newest = new Node<>(data, null);
			if(isEmpty()) {
				head = newest;
			}else {
				tail.next = newest;
			}
			tail = newest;
			
			size++;
		}
		
		public Node<E> reverse(Node<E> head){
			Node<E> prev = null;
			Node<E> current = head;
			Node<E> next;
			int n = 0;
			while(current != null) {
				next = current.next;
				current.next = prev;
				prev = current;
				current = next;
			}
			return prev;
		}
		
		public void displayList(Node<E> node) {
			Node<E> current = node;
			int i = 0;
			while(current != null && i < 5) {
				System.out.print(current.data + " ");
				current = current.next;
				i++;
			}
			System.out.println();
		}
	}
}
