package Chapter2_ADT;
import Chapter2_ADT.MyLinkedList;
import Chapter2_ADT.MyLinkedList.LinkedListIterator;
//����һ��������
class SingleLinkedList<AnyType> implements Iterable<AnyType> {
	//�ڲ��ඨ�������Ԫ����㣺
	private class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> next;
		//���췽����
		public Node(AnyType data1,Node<AnyType>next1){
			data=data1;
			next=next1;
		}
	}
	//�������ݳ�Ա��
	//����ͷβ�ڵ㣺
	private Node<AnyType> first;
	private Node<AnyType> end;
	private int theSize;
	private int modCount=0;
	//���췽����ʼ����
	public void doClear(){
		theSize=0;
		first=new Node(null, end);
		end=new Node(null, null);
		modCount+=1;
	}
	//���췽����
	public SingleLinkedList(){
		doClear();
	}
	//theSize
	public int size(){
		return theSize;
	}
	//����һЩmethod�Ͳ�ʵ���ˡ�
	//clear method
	//add
	//remove
	//getNode����:��������ͬ��Ƕ�ף�
	//�����
	private Node<AnyType> getNode(int id){
		return(getNode(id, 0, size()-1));
	}
	//���ص�
	private Node<AnyType> getNode(int id,int low,int high){
		//����node�����ã�
		Node<AnyType> p;
		//index�����쳣��⣻
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//�������ң���Ϊ����˫����������ֻ�ܴ�ͷ��ʼ��
		p=first.next;
		for (int i = 0; i <id; i++) {
			p=p.next;
		}
		return p;
	}
	//ʵ�������ĿҪʵ�ֵ�Ҫ��
	//������������Ԫ��,����ֻ�������ǰ���index���ɣ�
	public void neighborChange(int idHead){
		//�쳣��⣺ͷβ���ܽ�����
		if ((getNode(idHead)==first)||(getNode(idHead-1)==end)) {
			throw new IllegalStateException();
		}
		//������
		//��Ҫ�ĸ�Node���ã�
		Node<AnyType> n1;
		Node<AnyType> n2;
		Node<AnyType> n3;
		Node<AnyType> n4;
		//n1_n4ȷ����ϵ��
		//n1
		if (idHead==0) {
			n1=first;
		}
		else {
			n1=getNode(idHead-1);
		}
		//n2
		n2=n1.next;
		//n3
		n3=n2.next;
		//n4
		n4=n3.next;
		//exchange!n2-n3
		n1.next=n3;
		n3.next=n2;
		n2.next=n4;
	}
	//�����Ϳ������ڲ��������iterato ����private class iterator....
	public java.util.Iterator<AnyType> iterator(){
		return new SingleLinkedListIterator();
	}
	public class SingleLinkedListIterator implements java.util.Iterator<AnyType>{
		//����һ����Node��ʾ��ǰ��㡣
		//iterator�Ǵӵ�һ����Ч�ڵ㿪ʼ���������Գ�ʼ�Ľ���Ǵ�ͷ��ʼ�ģ�
		Node<AnyType> current=first.next;
		//��������ҲҪ�۲�������޸��������Ϊ��������remove����������
		int iteratorModCount=modCount;
		//remove״̬�źţ�
		private boolean okToRemove=false;
		//hasNext()
		public boolean hasNext(){
			return(current!=end);
		}
		//next()������node��data��
		public AnyType next(){
			//�쳣��⣡
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣���
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			//next������
			AnyType tempData=current.data;
			current=current.next;
			//����remove����
			okToRemove=true;
			return tempData;
		}
		//remove
		public void remove(){
			//�쳣���
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣��⣺������okToRemove=falseʱ���׳��쳣��
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			//remove����
			
		}
		
		
	}
	
}
//˫������������
class TwoMyLinkedList<AnyType> implements Iterable<AnyType> {
	//������λ���ڵ㶨��
	//ʹ��private static �ڲ���
	private static class Node<AnyType> {
		//����һ��������
		//��Ա������ǰ���ͺ�̻���Node����
		public AnyType data;
		public Node<AnyType> next;//ע��ǰ��Ӧ��Ҳ��Node����
		public Node<AnyType> prev;//ע����ҲӦ����Node����
		public Node(AnyType data1,Node<AnyType>prev1,Node<AnyType>next1){
			data=data1;
			next=next1;
			prev=prev1;
		}
	}
	//����Ļ�����ɵ�λ�����Ժ󣬽���������MyLinkedList������ݣ����ݳ�Ա�ͳ�Ա������
	private int theSize;
	private int modCount=0;
	private Node<AnyType>beginNode;
	private Node<AnyType>endNode;
	//
	//
	//
	//
	//���췽��:ע�⹹�췽���ĸ�ʽ����
    public TwoMyLinkedList(){
		doClear();
	}
    //clear()
    public void clear(){
    	doClear();
    }
    //doClear()
	public void doClear(){
		//����ͷβ���
		//�γɿ�����
		//size=0
		//��Ҳ�൱��һ�ζ�����ĸı� modcount++
		beginNode=new Node<AnyType>(null,null,null);//�вεĹ��췽����
		endNode=new Node<AnyType>(null,null,null);//�вεĹ��췽����
		//�����γɿ�����;
		beginNode.next=endNode;
		endNode.prev=beginNode;
		//size////modCount
		theSize=0;
		modCount+=1;
		}
	//size()
	public int size(){
		return theSize;
		}
	//isEmpty()
	public boolean isEmpty(){
		//��˵����size����Ҳ���ԵΣ�return(beginNode.next==endNode);
		return size()==0;
	}
	//add()
	//��������add�����������ͬ�����ֹ��ܼ��ݣ�
	public boolean add(AnyType newData ){
		add(size(), newData);//size������β���û�д���Ϊ������addBefore��������������λ�����ʱ�����û�д�addBeforeβ��㣡
		return true;
	}
	public void add(int id,AnyType newData){
		addBefore(getNode(id), newData);
	}
	//get����id����е�data��
	public AnyType get(int id){
		AnyType value;
		value=getNode(id).data;
		return value;
	}
	//set:���ڵ�id��node�иı�data
	public AnyType set(int id,AnyType data){
		AnyType oldValue;
		Node<AnyType> tempNode=getNode(id);
		oldValue=tempNode.data;
		tempNode.data=data;//getNode�����õ�����Node�����ã����Կ���ֱ�Ӳ�����
		return oldValue;
	}
	//remove(),ֻ���ṩ����id���ɣ�
	public AnyType remove(int id){
		return remove(getNode(id));
	}
	//iterator,,,,,,,�߼��Ͳ����Ϳ���
	public java.util.Iterator<AnyType> iterator(){
		return new LinkedListIterator();
	}
	//˫������ʵ�ֽ���������
	//���ǹ涨ֻ�����ǰ����������ɣ�
	public void exchange(int headId){
		//�쳣��⣺ͷԪ�ز�������ͷ�ڵ㣬�ҵڶ���������Ϊβ���
		if ((getNode(headId)==beginNode)||(getNode(headId-1)==endNode)) {
			throw new IllegalStateException();
		}
		//����
		Node<AnyType> m1;
		Node<AnyType> m2;
		Node<AnyType> m3;
		Node<AnyType> m4;
		//m1_m4
		//m1
		if (getNode(headId-1)==beginNode) {
			m1=beginNode;
		}
		else {
			m1=getNode(headId-1);
		}
		//m2
		m2=m1.next;
		//m3
		m3=m2.next;
		//m4
		m4=m3.next;
		//exchange!
		m1.next=m3;
		m3.next=m2;
		m2.next=m4;
		m3.prev=m1;
		m2.prev=m3;
		m4.prev=m2;
	}
	//test3.3:ʵ��MyLinkedList��contains���̣�
	public boolean contains(AnyType data){
		//ֻ��ѭ����������
		boolean signal=false;
		Node<AnyType> p;
		p=beginNode.next;
		for (int i = 0; i<size(); i++) {
			p=p.next;
			if (p.data==data) {
				signal=true;
			}
			//�ҵ��ͽ������Կ���
			break;
		}
		return signal;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////������public��method����/////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////��������Ӧprivate��װ��method����///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////ע������ֻ�ǶԳ�Ա�������л��֣������ݳ�Աû�л��֣������Ƚ�����������淶//////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//�ǳ���Ҫ�ĸ���������л�����λ��Node������Ҫ���������в�����
	//������Ҫ��λNode��λ�ã��ⲻ�������ʾ�����Ա����������ǲ���add��remove������
	//��˵getNode����������ֻ��Ҫ�ṩid���ɣ�������������д����ͬ�������ܼ��ݵķ�����
	private Node<AnyType> getNode(int id){
		return getNode(id, 0, size()-1);
	}
	//
	private Node<AnyType> getNode(int id,int low,int high){
		//������Node��
		Node<AnyType> p;
		//index������
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//��Ϊ���������������ʾ�����Ա���������ֻ��ѭ������ ����
		//��˵Ҳֻ��ͨ���ж�ǰ����������������Ч�ʣ�
		if (id<(size()/2)) {
			p=beginNode.next;//ָ���һ����Ч��㣡
			for (int i = 0; i <id; i++) {
				p=p.next;
			}
		}
		//�������
		else {
			p=endNode;
			for (int i = size(); i >id; i--) {
				p=p.prev;
			}
		}
		return p;//ֻ�ǵõ�Node����û�иı�����Ľṹ������modcount���øı䣡
	}
	//addBefore(),����add������private�ķ�װ��add������ȷ��Node��Ȼ���ڴ˽ڵ��ӽ�ȥ����x��
	//Node p��data x��
	private void addBefore(Node<AnyType> p,AnyType x){
		//��p���ǰ�����½ڵ㣬����Ϊx��
		//���õ���Ϣ����p��
		//��˵������Ҫ�����µĽ�㰡��
		Node<AnyType> newNode=new Node<AnyType>(x,p.prev,p);
		//��ʽ��ɲ������£�
		//newNode.data=x;
		//newNode.next=p;
		//newNode.prev=p.prev;
		//newNode�Ѿ�ָ�����ǿ�����������Ҫָ��newNode��
		//��ʽ�кܶ࣬��ô���������ô���ɣ�
		newNode.prev.next=newNode;
		newNode.next.prev=newNode;
		//�����Ǹı��������Ľṹ������modcount++
		theSize+=1;
		modCount+=1;
	}
	//private remove��node p����private�������ṩ����Node,�������������
	private AnyType remove(Node<AnyType> p){
		//������Ϣ��node p �У�ֻ������������ɣ�
		AnyType tempdata=p.data;
		p.prev.next=p.next;
		p.next.prev=p.prev;
		//���ڸı�������Ľṹ�����Եø���modcount��
		theSize-=1;
		modCount=+1;
		return tempdata;
	}
	//�����Ϳ������ڲ��������iterato ����private class iterator....
	public class LinkedListIterator implements java.util.Iterator<AnyType>{
		//����һ����Node��ʾ��ǰ��㡣
		//iterator�Ǵӵ�һ����Ч�ڵ㿪ʼ���������Գ�ʼ�Ľ���Ǵ�ͷ��ʼ�ģ�
		Node<AnyType> current=beginNode.next;
		//��������ҲҪ�۲�������޸��������Ϊ��������remove����������
		int iteratorModCount=modCount;
		//remove״̬�źţ�
		private boolean okToRemove=false;
		//hasNext()
		public boolean hasNext(){
			return(current!=endNode);
		}
		//next()������node��data��
		public AnyType next(){
			//�쳣��⣡
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣���
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			//next������
			AnyType tempData=current.data;
			current=current.next;
			//����remove����
			okToRemove=true;
			return tempData;
		}
		//remove
		public void remove(){
			//�쳣���
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//�쳣��⣺������okToRemove=falseʱ���׳��쳣��
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			//remove����
			TwoMyLinkedList.this.remove(current.prev);
			okToRemove=false;
			iteratorModCount+=1;
		}
		
		
	}
	
	
	
}




public class test_Chapter2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
