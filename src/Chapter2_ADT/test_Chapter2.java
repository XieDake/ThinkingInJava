package Chapter2_ADT;
import Chapter2_ADT.MyLinkedList;
import Chapter2_ADT.MyLinkedList.LinkedListIterator;
import org.omg.CORBA.Any;
import sun.plugin2.applet.context.NoopExecutionContext;

import java.util.ArrayList;
import java.util.prefs.NodeChangeEvent;

import static sun.swing.MenuItemLayoutHelper.max;
import static sun.swing.MenuItemLayoutHelper.useCheckAndArrow;

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
//3.11��ֻ��ͷ�ڵ�ĵ�����
class DanLinkedList<AnyType>{
    //���������λ��Node��
    private class Node<AnyType>{
        public AnyType data;
        public Node<AnyType>next;
        //���췽����
        public Node(AnyType data1,Node<AnyType> next1){
            this.data=data1;
            this.next=next1;
        }
    }
    //�������ݳ�Ա��
    private int theSize=1;//�����룬��������һ��ͷ�ڵ㣡
    private Node<AnyType> beginNode;//ֻ����ͷ�ڵ�����ã�
    //�������������
    public int size(){
        return theSize;
    }
    //���췽����
    public DanLinkedList(){
        doClear();
    }
    public void doClear(){
        theSize=1;
        beginNode=new Node(null,null);
    }
    //clear
    public void clear(){
        doClear();
    }
    //print the items!
    public void printList(){
        //��ͷ�ڵ㿪ʼprint��
        Node<AnyType> temp=beginNode;
        if (temp.next!=null){
            System.out.println("the item of this list is : "+temp.data);
            for (int i=0;i<size();i++){
                temp=temp.next;
                System.out.println("the item of this list is : "+temp.data);
            }
        }
    }
    //contains
    public boolean contains(AnyType x){
        //û�취����ͷ����!
        boolean status=false;
        Node<AnyType> temp=beginNode;
        if (temp.data==x){
            return true;
        }else {
            for (int i = 0; i < size(); i++) {
                temp = temp.next;
                if (temp.data==x){
                    status=true;
                    break;
                }
            }
        }
        return status;
    }
    //���x���������У�������뵽�����У�
    public void addNoExist(AnyType x){
        //��ͷ��ʼ
        Node<AnyType> temp=beginNode;
        Node<AnyType> newNode=new Node<>(x,null);
        if (!contains(x)){
            if (temp.next==null){
                temp.next=newNode;
            }else {
                for (int i = 0; i < size(); i++) {
                    temp = temp.next;
                    if (temp.next==null){
                        temp.next=newNode;
                    }
                }
            }
        }else{
            return;
        }
    }
    //x�������У�������ɾ������
    public void removeExist(AnyType x){
        Node<AnyType>lastOne=null;
        Node<AnyType> temp=beginNode;
        //��ͷ�ڵ�λ�ã�
        if (temp.data==x){
            if (temp.next==null){
                temp.data=null;
            }else {
                beginNode=temp.next;
            }
        }else {
            for (int i=0;i<size();i++){
                lastOne=temp;
                temp=temp.next;
                if (temp.data==x){
                    if (temp.next==null){
                        //β��㴦��
                        lastOne.next=null;
                    }else {
                        //����β�ڵ㣡
                        lastOne.next=temp.next;
                    }
                }
            }
        }
        //��β���λ�ã�
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
//3.4��3.5
class JiaoBing<AnyType>{
    ArrayList<AnyType>L1=new ArrayList<AnyType>();
    ArrayList<AnyType>L2=new ArrayList<AnyType>();
    //3.4 L1��L2
    public ArrayList<AnyType>JiaoJi(){
        ArrayList<AnyType>temp=new ArrayList<AnyType>();
        for (int i=0;i<L1.size();i++){
            if (L2.contains(L1.get(i))){
                temp.add(L1.get(i));
            }
        }
        for (AnyType item:temp
                ) {
            System.out.println("L1,l2 both have: "+item);
        }
        return temp;
    }
    //3.5:L1��L2
    public ArrayList<AnyType>BingJi(){
        ArrayList<AnyType>temp=new ArrayList<AnyType>();
        for (int i=0;i<Math.max(L1.size(),L2.size());i++){
            //L1:
            if ((L1.get(i))!=null||(!temp.contains(L1.get(i)))){
                temp.add(L1.get(i));
            }
            if ((L2.get(i))!=null||(!temp.contains(L2.get(i)))){
                temp.add(L2.get(i));
            }
        }
        for (AnyType item:temp
             ) {
            System.out.println("temp contains items of L1&L2: "+temp);
        }
        return temp;
    }
}
//3.6:Josephus problem!
class CycleLinkedList {
    //������ɵ�λ��
    private class Node {
        private int data;
        private Node next;

        //Node���췽������ʼ��
        public Node(int data1, Node next1) {
            this.data = data1;
            this.next = next1;
        }
    }

    //���ݳ�Ա��ֻҪͷ�ڵ��ok��
    private Node beginNode;
    private int theSize = 0;

    //���췽����ʼ����
    public CycleLinkedList() {
        doClear();
    }

    public void doClear() {
        theSize = 1;//ͷ�ڵ���һ����㣡
        beginNode = new Node(1, null);
        beginNode.next = beginNode;//ͷ�ڵ�ָ���Լ�������ͷ�ڵ�Ҳ��β���!
    }

    //��������������
    //clear
    public void clear() {
        doClear();
    }

    //size()
    public int size() {
        return theSize;
    }

    //getNode
    public Node getNode(int id) {
        return getNode(id, 0, size() - 1);
    }

    //add����������
    //add��һ�����Σ�������β����ӽ�㣻
    public void add(int data) {
        add(size() - 1, data);
    }

    //add�ڶ������Σ���idλ�ú�����½ڵ㣡
    public void add(int id, int data) {
        addAfter(getNode(id), data);
    }

    public void remove(int id) {
        //ɾ�����ǵ�id��Node��������Ҫ֪��ǰһ��Node����Ϣ��
        //id��������0��ͷ�ڵ㲻��ɾ����
        if (id == 0) {
            throw new IllegalStateException();
        }
        Node nodeBeforeRemoved = getNode(id - 1);
        Node nodeRemoved = getNode(id);
        nodeBeforeRemoved.next = nodeRemoved.next;
        theSize -= 1;
    }
    //remove node!
    public void remove(Node p){
        Node temp=beginNode;
        Node lastOne=temp;
        //��һ���ڵ����ɾ����
        if (p==beginNode){
            lastOne=getNode(size()-1);//���һ����㣡
        }else {
            for (int i = 0; i < size(); i++) {
                if (temp != p) {
                    lastOne = temp;
                    temp = temp.next;
                }//lastOne�����pǰһ���ڵ㣡
            }
        }
        //remove!������
        lastOne.next=p.next;
    }

    //add�ڶ������Σ���������м�ָ��λ�ò����㣻
    private void addAfter(Node p, int data) {
        Node newNode = new Node(data, null);
        newNode.next = p.next;
        p.next = newNode;
        theSize += 1;
    }

    private Node getNode(int id, int low, int high) {
        Node p;
        if ((id < low) || (id > high)) {
            throw new IndexOutOfBoundsException();
        }
        //go on!
        //����ǰ�������
        p = beginNode;
        for (int i = 0; i < id; i++) {
            p = p.next;
        }
        return p;
    }

    //Josephus problems!
    class JosephusProblem {
        private int m = 0;//Ĭ����Ϸ����m=0�Σ�
        private int n = 1;//������һ���ˣ�
        private CycleLinkedList queue = new CycleLinkedList();
        public JosephusProblem(int m, int n) {
            this.m = m;
            this.n = n;
            //ʹ��ѭ����������ɶ��У�
            //��һ���������dataĬ��Ϊ1��
            for (int i = 2; i < n + 1; i++) {
                queue.add(i);
            }
        }

        public void kill() {
            //��Ϊ�����һ�˻�ʤ������ÿһ�ζ�ֻkillһ���ˣ��������Կ���֪��������n-1��kill��Ϸ��
            //�״���Ϸ�ӵ�һ���˿�ʼ��
            Node people;
            people=queue.beginNode;
            for (int i = 1; i < this.n; i++) {
                //ÿһ��kill����Ҫ��m��ը����
                for (int j = 0; j < this.m; j++) {
                    people=people.next;
                }
                //kill this people!
                //��֮ǰ����̨�ʣ�
                System.out.println("my numberID is: "+people.data);
                queue.remove(people);
                //��һ�δ�kill���˵ĺ�һ���˿�ʼ��
                people=people.next;
            }
        }
    }
}

public class test_Chapter2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stud

	}

}
