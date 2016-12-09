package Chapter2_ADT;

import org.omg.CORBA.Any;

import java.awt.Dialog.ModalExclusionType;

class MyLinkedList1<AnyType>implements Iterable<AnyType> {
	//��������Ļ�����ɵ�λ��Node����˽���ڲ���:
	private class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> next;
		public Node<AnyType> prev;
		//���췽��
		public Node(AnyType data1,Node<AnyType> prev1,Node<AnyType> next1){
			data=data1;
			prev=prev1;
			next=next1;
		}
	}
	//�������ݳ�Ա
	private int theSize;
	private int modCount=0;
	private Node<AnyType> beginNode;
	private Node<AnyType> endNode;
	//���幹�췽��,��ʼ������
	public MyLinkedList1(){
		doClear();
	}
	//public
	public void doClear(){
		//����һ��ֻ��ͷβ�ڵ�Ŀ�����
		theSize=0;
		beginNode=new Node(null,null,null);
		endNode=new Node(null, null, null);
		beginNode.next=endNode;
		endNode.prev=beginNode;
		modCount+=1;//��ʼ��һ�θı�һ��
		
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
		//ע��getNode������Node�����ã�
		Node<AnyType> tempNode=getNode(id);
		AnyType oldValue=tempNode.data;
		//set
		tempNode.data=x;
		return oldValue;
	}
	//add������ʽ
	//����β����node������ָ��λ�����node
	//add1
	public boolean add(AnyType x){
		add(size()-1, x);
		return true;
	}
	//add2
	public void add(int id,AnyType x){
		addBefore(getNode(id), x);
	}
	//removeֻ���ṩ����id���ɣ�
	public AnyType remove(int id){
		return remove(getNode(id));
	}
	//iterator
	public java.util.Iterator<AnyType> iterator(){
		return new LinkedListIterator1() ;
	}
	//3.10:MyLinkedList��removeAll������ʵ�֣�
	public void removeAll(Iterable<?extends AnyType>items){
		//items��MylinkedList��һ���Ӽ�����ʵ��MyLinkedList����ȥ�����Ӽ�����������Ԫ�أ�
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
	//�������У�ʮ����Ҫ��һ��method��������λ�ҵ�Node
	//�����汾��getNode����
	//���ṩ�ӿڰ汾
	private Node<AnyType> getNode(int id){
		return(getNode(id, 0, size()-1));
	}
	//��װ�汾
	private Node<AnyType> getNode(int id,int low,int high){
		Node<AnyType>p;
		//�쳣��⣡
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//ѭ������
		//�������Ż����ң�
		if (id<(size()/2)) {
			//ǰ��Σ�
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
		return p;//���ı�����Ľṹ
	}
	//add before node
	//addBefore ���p��
	private void addBefore(Node<AnyType> p,AnyType data){
		Node<AnyType> newNode=new Node(data,p.prev,p);
		//����Ҫ�޸�������
		p.prev=newNode;
		newNode.prev.next=newNode;
		theSize+=1;
		modCount+=1;
	}
	//remove��ȥ�����P��
	private AnyType remove(Node<AnyType>p){
		AnyType dataRemoved=p.data;
		//�޸���������
		p.prev.next=p.next;
		p.next.prev=p.prev;
		theSize-=1;
		modCount+=1;
		return dataRemoved;
	}
	//iterator����������˽���ڲ��ࣻ
	private class LinkedListIterator1 implements java.util.Iterator<AnyType>{
		//��ǰ��㣺
		Node<AnyType> currentNode=beginNode.next;
		private int iteratorCount=modCount;
		private boolean okToRemove=false;
		//
		public boolean hasNext(){
			return(currentNode!=endNode);
		}
		//
		public AnyType next(){
			//�쳣��⣺
			if (iteratorCount!=modCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣��⣺
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			AnyType tempValue=currentNode.data;
			currentNode=currentNode.next;
			okToRemove=true;
			return tempValue;
		}
		public void remove(){
			//�쳣���
			if (iteratorCount!=modCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣���
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
