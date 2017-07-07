package adt.bst;

import java.util.LinkedList;

import adt.bt.BTNode;
import util.Util;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		int resp = -1;
		
		if(!isEmpty()) {
			resp = height(this.getRoot());
		}

		return resp;
	}
	
	private int height(BSTNode<T> node) {
		
		if(node == null) 
			return -1;
		
		
		int heightLeft = height(nodeGetLeft(node));
		int heightRight = height(nodeGetRight(node));
		
		if(heightLeft > heightRight)
			return heightLeft + 1;
		else
			return heightRight + 1;
	}
	


	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> resp = new BSTNode<T>();
		
		if(element != null && !isEmpty()) {
			resp = search(this.getRoot(), element);
		}
		
		return resp;
		
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		
		if(node == null) {
			return new BSTNode<T>();
		}
		
		if(node.getData().equals(element)) {
			return node;
		}
		
		if(node.getData().compareTo(element) < 0) {
			return search( nodeGetRight(node), element);
		} else {
			return search( nodeGetLeft(node), element);
		}
	}

	@Override
	public void insert(T element) {
		
		if(element == null) {
			return;
		}
		
		if(isEmpty()) {
			
			this.root =  createNode(element, null, null, null);
			
		} else {
			insert(this.getRoot(), element);
		}
	}
	

	private void insert(BSTNode<T> node, T element) {
		if(element.compareTo(node.getData()) > 0) {

			if(node.getRight() == null) {
				node.setRight(this.createNode(element, null, null, node));
			} else {
				insert( nodeGetRight(node), element);
			}
			
		} else if(element.compareTo(node.getData()) < 0) {

			if(node.getLeft() == null) {
				node.setLeft(this.createNode(element, null, null, node));
			} else {
				insert( nodeGetLeft(node), element);
			}
		}
		
	}



	@Override
	public BSTNode<T> maximum() {
		BSTNode<T> maximum = null;
		if(!isEmpty()) {
			maximum = maximum(this.getRoot());
		}
		
		return maximum;
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		
		if(node.getRight() == null) {
			return node;
		} else {
			return maximum( nodeGetRight(node) );
		}
	}

	@Override
	public BSTNode<T> minimum() {
		BSTNode<T> minimum = null;
		if(!isEmpty()) {
			
			minimum = minimum(this.getRoot());
		}
		
		return minimum;
	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		
		if(node.getLeft() == null) {
			return node;
		} else {
			return minimum( nodeGetLeft(node) );
		}
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> resp = null;
		
		if(element != null && !isEmpty()) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
			
				if(nodeGetRight(node) != null) 
					// change the resp to the minimum node in the right of found node.
					resp = minimum(nodeGetRight(node)); // Can be null
				
				else {
					// go up the tree from x until we encounter
					// a node that is the left child of its parent
					BSTNode<T> nodeSuccessor = nodeGetParent(node);
					while(nodeSuccessor != null && node.equals(nodeGetRight(nodeSuccessor))) {
						node = nodeSuccessor;
						nodeSuccessor = nodeGetParent(node);
						
					}
					
					resp = nodeSuccessor; // Can be null
				}	
			}
			
		}
		
		return resp;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> resp = null;
		
		if(element != null && !isEmpty()) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
			
				if(nodeGetLeft(node) != null) 
					// change the resp to the maximum node in the left of found node.
					resp = maximum(nodeGetLeft(node)); // Can be null
				
				else {
					// go up the tree from x until we encounter
					// a node that is the right child of its parent
					BSTNode<T> nodePredecessor = nodeGetParent(node);
					while(nodePredecessor != null && node.equals(nodeGetLeft(nodePredecessor))) {
						node = nodePredecessor;
						nodePredecessor = nodeGetParent(node);
						
					}
					
					resp = nodePredecessor; // Can be null
				}	
			}
			
		}
		
		return resp;
	}

	@Override
	public void remove(T element) {
		
		if(element != null && !isEmpty()) {
			
			BSTNode<T> nodeToDelete = this.search(element); // Found element to be deleted.
			
			if(!nodeToDelete.isEmpty()) {
				
				BSTNode<T> aNodeToDelete 		= null;
				BSTNode<T> childNodeToDelete 	= null;
				
				// if a node has one child or no one child i can't need the sucessor.
				// if the node has two child call the sucessor.
				if(nodeGetLeft(nodeToDelete) == null || nodeGetRight(nodeToDelete) == null) {
					aNodeToDelete = nodeToDelete;
				}else { 
					aNodeToDelete = this.sucessor(element);
				}
				
				
				if(nodeGetLeft(aNodeToDelete) != null){ 
					childNodeToDelete = nodeGetLeft(aNodeToDelete);
				}else if(nodeGetRight(aNodeToDelete) != null){ 
					childNodeToDelete = nodeGetRight(aNodeToDelete);
				}
				
				BSTNode<T> parentNodeToDelete = this.nodeGetParent(aNodeToDelete);
				
				// if the node to be deleted has child, the child have new parent.
				if(childNodeToDelete != null) {
					childNodeToDelete.setParent(parentNodeToDelete);
				}
				
				if(parentNodeToDelete == null) { // If the node is the root.
					
					if(childNodeToDelete != null) { // If the root has child
						this.root = childNodeToDelete;
					} else { // The  root don't has child, so, put nil on the root.
						this.root = new BSTNode<T>();
					}
				} else { // The node to be deleted not is the root.
					
					// Find if the node to be deleted is the left or right of his father
					// After you find the corrent position, put the sons of the element to be deleted in corrent position.
					if(aNodeToDelete.equals(nodeGetLeft(parentNodeToDelete))) {

						parentNodeToDelete.setLeft(childNodeToDelete);
					} else {
						parentNodeToDelete.setRight(childNodeToDelete);
					}
					
				}
				// The node to be deleted has two child
				if(!aNodeToDelete.equals(nodeToDelete)) {
					// nodeToDelete is replaced with the successor.
					nodeToDelete.setData(aNodeToDelete.getData());
				}
				
				
				
				
			}
			
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		
		T[] resp = (T[]) Util.makeArrayOfComparable(this.size());
		
		if(!isEmpty()) {
			LinkedList<T> preOrd = new LinkedList<T>();
			preOrder(this.getRoot(), preOrd);
			
			copyArray(preOrd, resp);
		}
		
			
		return resp;
	}

	// Node, left, right
	private void preOrder(BSTNode<T> node, LinkedList<T> preOrder) {
		
		preOrder.add(node.getData());
		
		if(node.getLeft() != null) {
			preOrder( nodeGetLeft(node), preOrder );
		}
		
		if(node.getRight() != null) {
			preOrder( nodeGetRight(node), preOrder );
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		
		T[] resp = (T[]) Util.makeArrayOfComparable(this.size());
		
		if(!isEmpty()) {
			LinkedList<T> order = new LinkedList<T>();
			
			order(this.getRoot(), order);
			
			copyArray(order, resp);
		}
		
			
		return resp;
	}
	

	// left, node, right
	private void order(BSTNode<T> node, LinkedList<T> order) {
		
		if(node.getLeft() != null) {
			order( nodeGetLeft(node), order);
		}
		
		order.add(node.getData());
		
		if(node.getRight() != null) {
			order( nodeGetRight(node), order);
		}
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		T[] resp = (T[]) Util.makeArrayOfComparable(this.size());
		
		if(!isEmpty()) {
			LinkedList<T> postOrder = new LinkedList<T>();
			postOrder(this.getRoot(), postOrder);
			
			copyArray(postOrder, resp);
		}
		
			
		return resp;
	}
	
	// left, right, node
	private void postOrder(BSTNode<T> node, LinkedList<T> postOrder) {
		
		if(node.getLeft() != null) {
			postOrder( nodeGetLeft(node), postOrder);
		}
		
		if(node.getRight() != null) {
			postOrder( nodeGetRight(node), postOrder);
		}
		
		postOrder.add(node.getData());
		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		int size = 0;
		
		if(!isEmpty()) {
			size =  size(this.getRoot());
		}
		
		return size;
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (node != null) { // indusctive case
			result = 1 + size( nodeGetLeft(node))
					+ size( nodeGetRight(node));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	private BSTNode<T> createNode(T element, BTNode<T> left, BTNode<T> right, BTNode<T> parent) {
		
		BSTNode.Builder<T> builder = new BSTNode.Builder<>();
		builder.data(element);
		builder.left(left);
		builder.right(right);
		builder.parent(parent);
		
		return builder.build();
	}
	
	private BSTNode<T> nodeGetLeft(BTNode<T> node) {
		return (BSTNode<T>) node.getLeft();
	}
	
	private BSTNode<T> nodeGetRight(BTNode<T> node) {
		return (BSTNode<T>) node.getRight();
	}
	
	private BSTNode<T> nodeGetParent(BTNode<T> node) {
		return (BSTNode<T>) node.getParent();
	}

	private void copyArray(LinkedList<T> order, T[] resp) {
		for(int i = 0; i < order.size(); i ++) {
			resp[i] = order.get(i);
		}
	}
	
}
