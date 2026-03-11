public class AVLTreeTest {
	
	public static void main(String[] args) {

		// ------------ test AVLTree
		System.out.println("============================= Test AVLTree");
		// 
		// ----------------------------------
		//             7
		//           /   \
		//         4       8
		//       /   \    
		//      3     5       
		//       
		
		BTNode<Integer> n3 = new BTNode<Integer>(3, null, null);
		BTNode<Integer> n5 = new BTNode<Integer>(5, null, null);
		BTNode<Integer> n8 = new BTNode<Integer>(8, null, null);
		
		BTNode<Integer> n4 = new BTNode<Integer>(4, n3, n5);
		BTNode<Integer> n7 = new BTNode<Integer>(7, n4, n8);
		
		n3.setParent(n4);
		n5.setParent(n4);
		n4.setParent(n7);
		n8.setParent(n7);

		AVLTree<Integer> tree = new AVLTree<Integer>(n7);

		System.out.println("Size of the tree: " + tree.size(tree.getRoot()));
		System.out.println("Height of the tree: " + tree.height(tree.getRoot()));
		System.out.print("\ninOrder: ");
		tree.inOrder(tree.getRoot());
		System.out.println("\n");

		// // ----------------------- test search
//		 System.out.println("------------------ Test search");
//		 System.out.println(tree.search(5).toString());
//		 System.out.println("\n");
//		// 
//		// ----------------------- test insert
//		// ----------------------------------
//		  //           7                    4
//		  //         /   \                /   \
//		  //       4       8             3     7
//		  //     /   \                  /     / \ 
//		  //    3     5                2     5   8
//		  //  /
//		  // 2   
//		 System.out.println("------------------ Test insert");
//		 BTNode<Integer> n2 = new BTNode<Integer>(2, null, null);
//		 tree.insert(n2);
//		 tree.inOrder(tree.getRoot());
//		 System.out.println("\n");
//		 // 
//		 System.out.println(n4.toString());
//		 System.out.println(n3.toString());
//		 System.out.println(n2.toString());
//		 System.out.println(n5.toString());
//		
//		// ----------------------- test delete
//		// ----------------------------------
//		//             7                    5
//		//           /                    /   \
//		//         4                     4     7
//		//       /   \                  /       
//		//      3     5                3        
//		//    
//		 System.out.println("------------------ Test delete 1");
//		 tree.delete(n8);
//		 tree.inOrder(tree.getRoot());
//		 System.out.println("\n");
//
//		 System.out.println(n4.toString());
//		 System.out.println(n3.toString());
//		 System.out.println(n5.toString());
	}

	private static class BTNode<E>{
		// ---------------------------------- data field
		private E element;
		private BTNode<E> parent;
		private BTNode<E> leftChild;
		private BTNode<E> rightChild;
		// ---------------------------------- method field

		// --------------------- constructors
		public BTNode() {
			this.element = null;
			this.parent = null;
			this.leftChild = null;
			this.rightChild = null;
		}
		@SuppressWarnings("unchecked")
		public BTNode(Object element, BTNode<E> leftChild, BTNode<E> rightChild) {
			this.element = (E) element;
			this.parent = null;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}

		// --------------------- 
		public E getElement() {
			return this.element;
		}
		public void setElement(E element) {
			this.element = element;
		}

		public BTNode<E> getLeftChild() {
			return this.leftChild;
		}
		public void setLeftChild(BTNode<E> leftChild) {
			this.leftChild = leftChild;
			if(leftChild != null) {
				this.leftChild.parent = this; //
			}
		}

		public BTNode<E> getRightChild() {
			return this.rightChild;
		}
		public void setRightChild(BTNode<E> rightChild) {
			this.rightChild = rightChild;
			if(rightChild != null) {
				this.rightChild.parent = this;
			}
		}

		public BTNode<E> getParent() {
			return this.parent;
		}	
		public void setParent(BTNode<E> parent) {
			this.parent = parent;
			// ....
		}
		public int numChildren() {
			if (this.leftChild == null && this.rightChild == null) {
				return 0;
			} else if (this.leftChild != null && this.rightChild != null) {
				return 2;
			} else {
				return 1;
			}
		}


		public boolean isLeftChild() {
			if(this.parent == null) {
				System.out.println("It is the root");
				return false;
			} 
			if (this.getParent().getLeftChild() == null) {
				return false;
			}
			else if (this.getParent().getLeftChild() != this) {
				return false;
			}
			else {
				return true;
			}
		}

		public BTNode getSibling() {
			if (this.parent == null) {
				System.out.println("It is the root and it has no sibling!");
				return null;
			}
			if (this.isLeftChild()) {
				return this.parent.getRightChild();
			} else {
				return this.parent.getLeftChild();
			}		
			
		}

		@Override
		public String toString() {
			String leftChildString;
			String rightChildString;
			String parentString;
			if (this.getParent() == null) {
				parentString = "null";
			} else {
				parentString = this.getParent().getElement() + "";
			}

			if (this.getLeftChild() == null) {
				leftChildString = "null";
			} else {
				leftChildString = this.getLeftChild().getElement() + "";
			}
			if (this.getRightChild() == null) {
				rightChildString = "null";
			} else {
				rightChildString = this.getRightChild().getElement() + "";
			}

	        return this.getElement() + 
	        	" with parent " + parentString +
	        	" and leftChild: " + leftChildString + 
	        	" and rightChild: " + rightChildString;
	    }
	}

	private static class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
	    
	    public AVLTree(BTNode<E> root){
	        super(root);
	    }

	    @Override
	    public void insert(BTNode<E> node){
	        super.insert(node);
	 
	        // try to fix the heights!
	        BTNode<E> x = null;
	        BTNode<E> y = node;
	        BTNode<E> z = null;

	        trinodeReconstructuring(x, y, z, "insert");
	    }

	    @Override
	    public void delete(BTNode<E> node){
	        BTNode<E> siblingOfRealDeletedNode = super.deleteWithReturn(node);
	        // System.out.println(siblingOfRealDeletedNode.toString());
	        // 
	        // try to fix the heights!
	        BTNode<E> x = null;
	        BTNode<E> y = siblingOfRealDeletedNode;
	        BTNode<E> z = null;

	        trinodeReconstructuring(x, y, z, "delete");
	    }
	    
	    public void trinodeReconstructuring(BTNode<E> x, BTNode<E> y, BTNode<E> z, String methodName) {
	        while (y.getParent() != null) {
	            BTNode<E> ySibling = y.getSibling();
	            if (Math.abs(height(ySibling) - height(y)) < 2) {
	                y = y.getParent();
	                // System.out.println(y.toString());
	                continue;
	            }
	            // identify three nodes x, y, z
	            if (height(ySibling) - height(y) > 0) {
	                y = ySibling;
	            } 
	            BTNode<E> yLeftChild = y.getLeftChild();
	            BTNode<E> yRightChild = y.getRightChild();
	            x = (height(yLeftChild) > height(yRightChild))? yLeftChild :  yRightChild;
	            z = y.getParent();

	            // System.out.println("=================");
	            // System.out.println(x.toString());
	            // System.out.println(y.toString());
	            // System.out.println(z.toString());

	            // Trinode Reconstructuring 
	            if(x.getElement().compareTo(y.getElement()) <= 0 && 
	                y.getElement().compareTo(z.getElement()) <= 0) {
	                // Case 1: x <= y <= z
	                System.out.println("=================== Case 1");
	                BTNode<E> zParent = z.getParent();
	                BTNode<E> yRightSubTree = y.getRightChild();
	                y.setLeftChild(x);
	                y.setRightChild(z);
	                y.setParent(zParent);
	                z.setLeftChild(yRightSubTree);
	                if (z == getRoot()) {
	                    setRoot(y);
	                }
	                if (methodName.equals("insert")) {
	                    break;
	                }               
	            } else if (z.getElement().compareTo(y.getElement()) <= 0 && 
	                y.getElement().compareTo(x.getElement()) <= 0) {
	                // Case 2: z <= y <= x
	                System.out.println("=================== Case 2");
	                BTNode<E> zParent = z.getParent();
	                BTNode<E> yLeftSubTree = y.getLeftChild();
	                y.setLeftChild(z);
	                y.setRightChild(x);
	                y.setParent(zParent);
	                z.setRightChild(yLeftSubTree);
	                if (z == getRoot()) {
	                    setRoot(y);
	                }
	                if (methodName.equals("insert")) {
	                    break;
	                }  
	            } else if (y.getElement().compareTo(x.getElement()) <= 0 && 
	                x.getElement().compareTo(z.getElement()) <= 0) {
	                // Case 3: y <= x <= z
	                System.out.println("=================== Case 3");
	                BTNode<E> zParent = z.getParent();
	                BTNode<E> xLeftSubTree = x.getLeftChild();
	                BTNode<E> xRightSubTree = x.getRightChild();
	                x.setLeftChild(y);
	                x.setRightChild(z);
	                x.setParent(zParent);
	                y.setRightChild(xLeftSubTree);
	                z.setLeftChild(xRightSubTree);
	                if (z == getRoot()) {
	                    setRoot(x);
	                }
	                if (methodName.equals("insert")) {
	                    break;
	                }  
	            } else {
	                // Case 4: z <= x <= y
	                System.out.println("=================== Case 4");
	                BTNode<E> zParent = z.getParent();
	                BTNode<E> xLeftSubTree = x.getLeftChild();
	                BTNode<E> xRightSubTree = x.getRightChild();
	                x.setLeftChild(z);
	                x.setRightChild(y);
	                x.setParent(zParent);
	                z.setRightChild(xLeftSubTree);
	                y.setLeftChild(xRightSubTree);
	                if (z == getRoot()) {
	                    setRoot(x);
	                }
	                if (methodName.equals("insert")) {
	                    break;
	                }  
	            }
	        }
	    } 
	    
	 }

	private static class BinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {

		public BinarySearchTree(BTNode<E> root){
			super(root);
		}
		
		public BTNode<E> search(E key){
			BTNode<E> current = this.getRoot();
			while (current != null) {
				// System.out.println(current.getElement() + "    ");
				if(key.compareTo(current.getElement()) == 0){
					return current;
				}else if(key.compareTo(current.getElement()) < 0){
					current = current.getLeftChild();
				}else{
					current = current.getRightChild();
				}
			}
			System.out.println("Did not find the key!");
			return null;
		}

		public void insert(BTNode<E> node){
			if(getRoot() == null){
				setRoot(node);
				return;
			}
			BTNode<E> current = getRoot();
			BTNode<E> parent;
			while(true){
				parent = current;
				if (node.getElement().compareTo(current.getElement()) == 0){	
					// do nothing
					System.out.println("Node" + node.getElement().toString() + "exists!");
					break;
				} else if (node.getElement().compareTo(current.getElement()) < 0){				
					current = current.getLeftChild();
					if(current == null){
						parent.setLeftChild(node);
						break;
					}
				} else {
					current = current.getRightChild();
					if(current == null){
						parent.setRightChild(node);
						break;
					}
				}
			}
		}

		public void delete(BTNode<E> node){
			BTNode<E> target = this.search(node.getElement());
			if (target == null) {
				System.out.println("Did not find the target!");
				return;
			} 
			
			// realDeletedNode is the node we actually delete.
			// finding replacement node -> inorder successor or predecessor
			BTNode<E> realDeletedNode = this.getRealDeletedNode(target);


			if (realDeletedNode == getRoot()) {
				setRoot(null);
				return;
			}
			// delete the realDeletedNode
			if(realDeletedNode.isLeftChild()){
				realDeletedNode.getParent().setLeftChild(null);
			}else{
				realDeletedNode.getParent().setRightChild(null);
			}

			// replace the target with the realDeletedNode
			if (realDeletedNode != target) { 
				if(target.isLeftChild()){
					target.getParent().setLeftChild(realDeletedNode);
				}else{
					target.getParent().setRightChild(realDeletedNode);
				}
				realDeletedNode.setLeftChild(target.getLeftChild());
				realDeletedNode.setRightChild(target.getRightChild());
			}
		}
		

		public BTNode<E> getRealDeletedNode(BTNode<E> deleteNode){ //finding replacement node
			BTNode<E> realDeletedNode = deleteNode;

			// find the smallest key in the right subtree
			if (deleteNode.getRightChild() != null) { 
				BTNode<E> child = deleteNode.getRightChild();
				while (child != null) {
					realDeletedNode = child;
					child = child.getLeftChild();
				}
			} else if (deleteNode.getLeftChild() != null) {
				// find the largest key in the left subtree
				BTNode<E> child = deleteNode.getLeftChild();
				while (child != null) {
					realDeletedNode = child;
					child = child.getRightChild(); 
				}
			} else {
				// do nothing
			}
			return realDeletedNode;
		}

		public BTNode<E> deleteWithReturn(BTNode<E> node){  //same thing with return 
	        BTNode<E> target = this.search(node.getElement());
	        if (target == null) {
	            System.out.println("Did not find the target!");
	            return null;
	        } 
	        
	        // realDeletedNode is the node we actually delete.
	        BTNode<E> realDeletedNode = this.getRealDeletedNode(target);
			
	        if (realDeletedNode == getRoot()) {
	            setRoot(null);
	            return null;
	        }

	        BTNode<E> siblingOfRealDeletedNode = realDeletedNode.getSibling();

	        // delete the realDeletedNode
	        if(realDeletedNode.isLeftChild()){
	            realDeletedNode.getParent().setLeftChild(null);
	        }else{
	            realDeletedNode.getParent().setRightChild(null);
	        }
	        // replace the target with the realDeletedNode
	        if (realDeletedNode != target) { 
	            if(target.isLeftChild()){
	                target.getParent().setLeftChild(realDeletedNode);
	            }else{
	                target.getParent().setRightChild(realDeletedNode);
	            }
	            realDeletedNode.setLeftChild(target.getLeftChild());
	            realDeletedNode.setRightChild(target.getRightChild());
	        }
	        return siblingOfRealDeletedNode;
		}
	}

	private static class LinkedBinaryTree<E> {
		// ---------------------------------- data field
		private BTNode<E> root;
		// int height;
		// ---------------------------------- method field

		// --------------------- constructors
		public LinkedBinaryTree(BTNode<E> root) {
			this.root = root;
			// this.height = 0;
		}
		
		// --------------------- 
		public BTNode<E> getRoot(){
			return this.root;
		}
		public void setRoot(BTNode<E> root){
			this.root = root;
		}
		public int size(BTNode<E> node) {
			if(node == null) {
				return 0;
			} else {
				return 1  + size(node.getLeftChild()) + size(node.getRightChild());
			}
		}

		public boolean isEmpty() {
			return (this.size(this.root)==0);
		}

		public boolean isInternal(BTNode<E> node) {
			return (node.getLeftChild() != null || node.getRightChild() != null);
		}

		public boolean isExternal(BTNode<E> node) {
			return (node.getLeftChild() == null && node.getRightChild() == null);
		}

		public int height(BTNode node) {
			if (node == null){
				return 0;
			}
			if(this.isExternal(node)) {
				return 1; 
			} else if (node.getLeftChild() != null && node.getRightChild() != null){
				return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
			} else if (node.getLeftChild() == null && node.getRightChild() != null){
				return 1 + height(node.getRightChild());
			} else {
				return 1 + height(node.getLeftChild());
			}
		}

		public boolean isRoot(BTNode node) {
			return (node == this.root);
		}
		
		// Traversals
		public void inOrder(BTNode<E> node) {
			if(node == null) {
				return ;
			}
			if(node.getLeftChild() != null) {
				inOrder(node.getLeftChild());
			}

			System.out.print(node.getElement() + "	");
			
			if(node.getRightChild() != null) {
				inOrder(node.getRightChild());
			}
		}
		
		public void preOrder(BTNode<E> node) {
			if(node == null) {
				return ;
			}
			System.out.print(node.getElement() + "	");
			if(node.getLeftChild() != null) {
				preOrder(node.getLeftChild());
			}
			if(node.getRightChild() != null) {
				preOrder(node.getRightChild());
			}
		}
		
		public void postOrder(BTNode<E> node) {
			if(node == null) {
				return ;
			}
			if(node.getLeftChild() != null) {
				postOrder(node.getLeftChild());
			}
			if(node.getRightChild() != null) {
				postOrder(node.getRightChild());
			}
			System.out.print(node.getElement() + "	");
		}

	}

}
