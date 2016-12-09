package Chapter2_ADT;

import org.omg.CORBA.Any;

import java.awt.Dialog.ModalExclusionType;

class MyLinkedList1<AnyType>implements Iterable<AnyType> {
	//定义链表的基本组成单位：Node——私有内部类:
	private class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> next;
		public Node<AnyType> prev;
		//构造方法
		public Node(AnyType data1,Node<AnyType> prev1,Node<AnyType> next1){
			data=data1;
			prev=prev1;
			next=next1;
		}
	}
	//定义数据成员
	private int theSize;
	private int modCount=0;
	private Node<AnyType> beginNode;
	private Node<AnyType> endNode;
	//定义构造方法,初始化咯！
	public MyLinkedList1(){
		doClear();
	}
	//public
	public void doClear(){
		//生成一个只有头尾节点的空链表：
		theSize=0;
		beginNode=new Node(null,null,null);
		endNode=new Node(null, null, null);
		beginNode.next=endNode;
		endNode.prev=beginNode;
		modCount+=1;//初始化一次改变一次
		
	}
	public void clear(){
		doClear();
	}
	public int size(){
		return theSize;
	}
	public boolean isEmpty(){
		return (size()==0);
	}
	//
	public AnyType get(int id){
		return(getNode(id).data);
	}
	//set
	public AnyType set(int id,AnyType x){
		//注意getNode传的是Node的引用；
		Node<AnyType> tempNode=getNode(id);
		AnyType oldValue=tempNode.data;
		//set
		tempNode.data=x;
		return oldValue;
	}
	//add两种形式
	//链表尾部加node或链表指定位置添加node
	//add1
	public boolean add(AnyType x){
		add(size()-1, x);
		return true;
	}
	//add2
	public void add(int id,AnyType x){
		addBefore(getNode(id), x);
	}
	//remove只需提供索引id即可：
	public AnyType remove(int id){
		return remove(getNode(id));
	}
	//iterator
	public java.util.Iterator<AnyType> iterator(){
		return new LinkedListIterator1() ;
	}
	//3.10:MyLinkedList的removeAll方法的实现：
	public void removeAll(Iterable<?extends AnyType>items){
		//items是MylinkedList的一个子集，将实现MyLinkedList的类去除掉子集中所包含的元素！
		for (AnyType i:items
			 ) {
			while (this.iterator().hasNext()){
				if ((this.iterator().next()==i)){
					this.iterator().remove();
				}
			}
		}

	}
	//private
	//本程序中，十分重要的一个method（）。定位找的Node
	//两个版本的getNode（）
	//外提供接口版本
	private Node<AnyType> getNode(int id){
		return(getNode(id, 0, size()-1));
	}
	//封装版本
	private Node<AnyType> getNode(int id,int low,int high){
		Node<AnyType>p;
		//异常检测！
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//循环遍历
		//两半区优化查找！
		if (id<(size()/2)) {
			//前半段：
			p=beginNode.next;
			for (int i = 0; i <id; i++) {
				p=p.next;
			}
		}
		else {
			p=endNode;
			for (int i = size(); i > id; i--) {
				p=p.prev;
			}
		}
		return p;//不改变链表的结构
	}
	//add before node
	//addBefore 结点p；
	private void addBefore(Node<AnyType> p,AnyType data){
		Node<AnyType> newNode=new Node(data,p.prev,p);
		//还需要修改两处链
		p.prev=newNode;
		newNode.prev.next=newNode;
		theSize+=1;
		modCount+=1;
	}
	//remove：去掉结点P；
	private AnyType remove(Node<AnyType>p){
		AnyType dataRemoved=p.data;
		//修改两处链：
		p.prev.next=p.next;
		p.next.prev=p.prev;
		theSize-=1;
		modCount+=1;
		return dataRemoved;
	}
	//iterator：。。。。私有内部类；
	private class LinkedListIterator1 implements java.util.Iterator<AnyType>{
		//当前结点：
		Node<AnyType> currentNode=beginNode.next;
		private int iteratorCount=modCount;
		private boolean okToRemove=false;
		//
		public boolean hasNext(){
			return(currentNode!=endNode);
		}
		//
		public AnyType next(){
			//异常检测：
			if (iteratorCount!=modCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测：
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			AnyType tempValue=currentNode.data;
			currentNode=currentNode.next;
			okToRemove=true;
			return tempValue;
		}
		public void remove(){
			//异常检测
			if (iteratorCount!=modCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			//remove
			MyLinkedList1.this.remove(currentNode.prev);
			iteratorCount+=1;
			okToRemove=false;
		}
	}
	
}
