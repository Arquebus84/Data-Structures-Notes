package AdvancedListExamples;

public class PriorityQueueTest {

	public static void main(String[] args) {
		ArrayQueue<Integer> queue = new ArrayQueue<>(5);
		queue.enQ(2); queue.enQ(4); queue.enQ(1); queue.enQ(3);
		
		queue.printQueue();
		
		queue.deQ(); queue.deQ();
		
		queue.printQueue();
	}
	
	private static interface Queue<E>{
		int size();
		boolean isEmpty();
		void enQ(E element);
		E deQ();
		E first();
	}
	
	private static class ArrayQueue<E> implements Queue<E>{	//Queue is FIFO 
		private E[] data;
		private int frontIndex = 0;
		
		private int size;

		public ArrayQueue(){
			this.size = 0;
			data = (E[]) new Object[size];
		}
		
		public ArrayQueue(int size){
			this.size = size;
			data = (E[]) new Object[size];
		}
		
		@Override
		public int size() {
			return data.length;
		}

		@Override
		public boolean isEmpty() {
			if(size() < 1)
				return true;
			else
				return false;
		}
		
		@Override
		public E first() {
			if(isEmpty()) 
				return null;
			else
				return data[0];
		}

		@Override
		public void enQ(E element) {		// Pop an element into the array
			if(size() > size) {
				throw new IllegalArgumentException();
			}else {
				int rear = (frontIndex + size) % size();
				data[rear] = element;
				size++;
			}
		}

		@Override
		public E deQ() { 		// Push and element out of the array
			if(isEmpty()) {
				return null;
			}else {
				E element = data[frontIndex];
				data[frontIndex] = null;
				frontIndex = (frontIndex + 1) % size();
				size--;
				return element;
			}
		}

		public void printQueue() {
			System.out.print("[ ");
			for(int i = 0; i < size(); i++) {
				System.out.print(data[i] + " ");
			}
			System.out.println("]");
		}
	}

}
