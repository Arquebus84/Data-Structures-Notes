import java.util.*;

//Note: Originally made in the Chapter Finale, Additional Notes
public class HashMapTest {
	/**
	 * 	HashMap consists of key-value pairs in order to search and store items:
	 * 
	 * 		HashMap<K key, V value>
	 * 
	 */
	
	public static void main(String[] args) {
		HashMap<Integer, String> hash = new HashMap<>();
		hash.put(0, "Name");
		hash.put(1, "Date");
		hash.put(2, "ID");
		System.out.println(hash.get(0));
		
		HashMapCustom<String, Integer> customHash = new HashMapCustom<>();
		customHash.insert("Matt"); customHash.insert("Jacob"); customHash.insert("Lillith"); customHash.insert("Grace"); 
		//customHash.insert("Steven"); customHash.insert("Lillith"); customHash.insert("Hope"); customHash.insert("Milly");
		customHash.displayMap();
	}
	
//	V will be the value returned (represented as integer) and K will be the look-up key (object)
	// Convert the generic value into a hash and then find the hash value
	private static class HashMapCustom<K, V>{
		V value;
		K key;
		static int capacity = 10;
		int size;					//size keeps track of the amount of items inserted or removed
		K[] list;
		
		public HashMapCustom() {
			this(capacity);
		}
		public HashMapCustom(int size) {
			list = (K[])(new Object[size]);
		}
		
		public void insert(K key) {	//public void insert(K key, V value) {			
			if(size == Size()) {												//Check if map is full
				System.out.println("Reached Maximum Capacity");
				return;
			}
			if(contains(key)) {
				System.out.println("Key " + key + " already exists");
				return;
			}
						
			int hashValue = Math.abs(key.hashCode()%Size());
			System.out.print(hashValue + " ");
			
			if(list[hashValue] != null) {					//If the element at the hash index is taken, do the following:
				while(hashValue < list.length && list[hashValue] != null) {
					if(hashValue == list.length-1) {		// If the hash equals the index of the last element, Move to first element
						insertFromStart(key);
						return;								//Exit out of the method when the last element is checked
					}
					hashValue++;
				}
				list[hashValue] = key;
				
			}else {
				list[hashValue] = key;
			}
			size++;
		}
		
		public void insertFromStart(K key) {
			int start = 0;
			while(list[start] != null) {
				start++;
			}
			list[start] = key;
		}
		
		public boolean contains(K key) {
			for(K element : list) {
				if(element == key) {
					return true;
				}
			}
			return false;
		}
		
		public int Size() {
			return list.length;
		}
		
		public void displayMap() {
			System.out.println();
			for(int i = 0; i < list.length; i++) {
				System.out.print(list[i] + " ");
			}
		}
	}

}
