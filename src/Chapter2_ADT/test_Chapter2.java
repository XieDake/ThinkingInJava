package Chapter2_ADT;
import Chapter2_ADT.MyLinkedList;
import Chapter2_ADT.MyLinkedList.LinkedListIterator;
import org.omg.CORBA.Any;
import sun.plugin2.applet.context.NoopExecutionContext;

import java.util.ArrayList;
import java.util.prefs.NodeChangeEvent;

import static sun.swing.MenuItemLayoutHelper.max;
import static sun.swing.MenuItemLayoutHelper.useCheckAndArrow;

//定义一个单链表：
class SingleLinkedList<AnyType> implements Iterable<AnyType> {
	//内部类定义基本单元：结点：
	private class Node<AnyType>{
		public AnyType data;
		public Node<AnyType> next;
		//构造方法：
		public Node(AnyType data1,Node<AnyType>next1){
			data=data1;
			next=next1;
		}
	}
	//定义数据成员：
	//定义头尾节点：
	private Node<AnyType> first;
	private Node<AnyType> end;
	private int theSize;
	private int modCount=0;
	//构造方法初始化；
	public void doClear(){
		theSize=0;
		first=new Node(null, end);
		end=new Node(null, null);
		modCount+=1;
	}
	//构造方法：
	public SingleLinkedList(){
		doClear();
	}
	//theSize
	public int size(){
		return theSize;
	}
	//这里一些method就不实现了。
	//clear method
	//add
	//remove
	//getNode（）:还是两个同名嵌套：
	//给你的
	private Node<AnyType> getNode(int id){
		return(getNode(id, 0, size()-1));
	}
	//隐藏的
	private Node<AnyType> getNode(int id,int low,int high){
		//返回node的引用；
		Node<AnyType> p;
		//index超界异常检测；
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//遍历查找；因为不是双向联表所以只能从头开始；
		p=first.next;
		for (int i = 0; i <id; i++) {
			p=p.next;
		}
		return p;
	}
	//实现这个题目要实现的要求：
	//交换相邻俩个元素,这里只需给出最前面的index即可；
	public void neighborChange(int idHead){
		//异常检测：头尾不能交换；
		if ((getNode(idHead)==first)||(getNode(idHead-1)==end)) {
			throw new IllegalStateException();
		}
		//继续；
		//需要四个Node引用；
		Node<AnyType> n1;
		Node<AnyType> n2;
		Node<AnyType> n3;
		Node<AnyType> n4;
		//n1_n4确定关系！
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
	//不解释咯！用内部类来完成iterato ——private class iterator....
	public java.util.Iterator<AnyType> iterator(){
		return new SingleLinkedListIterator();
	}
	public class SingleLinkedListIterator implements java.util.Iterator<AnyType>{
		//设置一个空Node表示当前结点。
		//iterator是从第一个有效节点开始遍历，所以初始的结点是从头开始的！
		Node<AnyType> current=first.next;
		//迭代器中也要观察链表的修改情况，因为迭代器有remove（）方法；
		int iteratorModCount=modCount;
		//remove状态信号！
		private boolean okToRemove=false;
		//hasNext()
		public boolean hasNext(){
			return(current!=end);
		}
		//next()，返回node的data！
		public AnyType next(){
			//异常检测！
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			//next操作！
			AnyType tempData=current.data;
			current=current.next;
			//可以remove咯！
			okToRemove=true;
			return tempData;
		}
		//remove
		public void remove(){
			//异常检测
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测：——当okToRemove=false时，抛出异常！
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			//remove操作
			
		}
		
		
	}
	
}
//3.11：只有头节点的单链表！
class DanLinkedList<AnyType>{
    //定义基本单位：Node：
    private class Node<AnyType>{
        public AnyType data;
        public Node<AnyType>next;
        //构造方法：
        public Node(AnyType data1,Node<AnyType> next1){
            this.data=data1;
            this.next=next1;
        }
    }
    //定义数据成员：
    private int theSize=1;//最起码，，至少有一个头节点！
    private Node<AnyType> beginNode;//只保留头节点的引用！
    //定义基本方法！
    public int size(){
        return theSize;
    }
    //构造方法：
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
        //从头节点开始print！
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
        //没办法！从头遍历!
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
    //如果x不在链表中，则将其加入到链表中！
    public void addNoExist(AnyType x){
        //从头开始
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
    //x在链表中，，并且删除他！
    public void removeExist(AnyType x){
        Node<AnyType>lastOne=null;
        Node<AnyType> temp=beginNode;
        //在头节点位置！
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
                        //尾结点处理：
                        lastOne.next=null;
                    }else {
                        //不是尾节点！
                        lastOne.next=temp.next;
                    }
                }
            }
        }
        //在尾结点位置！
    }
}
//双链表交换操作；
class TwoMyLinkedList<AnyType> implements Iterable<AnyType> {
	//基本单位：节点定义
	//使用private static 内部类
	private static class Node<AnyType> {
		//还是一个泛型类
		//成员变量中前驱和后继还是Node类型
		public AnyType data;
		public Node<AnyType> next;//注意前驱应该也是Node类型
		public Node<AnyType> prev;//注意后继也应该是Node类型
		public Node(AnyType data1,Node<AnyType>prev1,Node<AnyType>next1){
			data=data1;
			next=next1;
			prev=prev1;
		}
	}
	//链表的基本组成单位定好以后，接下来定义MyLinkedList类的内容：数据成员和成员方法！
	private int theSize;
	private int modCount=0;
	private Node<AnyType>beginNode;
	private Node<AnyType>endNode;
	//
	//
	//
	//
	//构造方法:注意构造方法的格式咯！
    public TwoMyLinkedList(){
		doClear();
	}
    //clear()
    public void clear(){
    	doClear();
    }
    //doClear()
	public void doClear(){
		//生成头尾结点
		//形成空链表
		//size=0
		//这也相当于一次对链表的改变 modcount++
		beginNode=new Node<AnyType>(null,null,null);//有参的构造方法；
		endNode=new Node<AnyType>(null,null,null);//有参的构造方法；
		//连接形成空链表;
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
		//话说不用size（）也阔以滴！return(beginNode.next==endNode);
		return size()==0;
	}
	//add()
	//还是两种add情况，还是相同的名字功能兼容！
	public boolean add(AnyType newData ){
		add(size(), newData);//size（）是尾结点没有错，因为我们是addBefore！当在链表的最后位置添加时最后结点没有错！addBefore尾结点！
		return true;
	}
	public void add(int id,AnyType newData){
		addBefore(getNode(id), newData);
	}
	//get的是id结点中的data！
	public AnyType get(int id){
		AnyType value;
		value=getNode(id).data;
		return value;
	}
	//set:是在第id个node中改变data
	public AnyType set(int id,AnyType data){
		AnyType oldValue;
		Node<AnyType> tempNode=getNode(id);
		oldValue=tempNode.data;
		tempNode.data=data;//getNode（）得到的是Node的引用，所以可以直接操作！
		return oldValue;
	}
	//remove(),只需提供索引id即可；
	public AnyType remove(int id){
		return remove(getNode(id));
	}
	//iterator,,,,,,,逻辑就不解释咯！
	public java.util.Iterator<AnyType> iterator(){
		return new LinkedListIterator();
	}
	//双向链表实现交换操作；
	//还是规定只需给出前面的索引即可；
	public void exchange(int headId){
		//异常检测：头元素不可以做头节点，且第二个不允许为尾结点
		if ((getNode(headId)==beginNode)||(getNode(headId-1)==endNode)) {
			throw new IllegalStateException();
		}
		//继续
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
	//test3.3:实现MyLinkedList的contains例程；
	public boolean contains(AnyType data){
		//只能循环遍历咯！
		boolean signal=false;
		Node<AnyType> p;
		p=beginNode.next;
		for (int i = 0; i<size(); i++) {
			p=p.next;
			if (p.data==data) {
				signal=true;
			}
			//找到就结束可以咯！
			break;
		}
		return signal;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////上面是public的method（）/////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////下面是相应private封装的method（）///////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////注意这里只是对成员方法进行划分，对数据成员没有划分，，，比较清楚，，，规范//////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//非常重要的概念，在链表中基本单位是Node，所以要想对链表进行操作，
	//我们需要定位Node的位置（这不是数组表示的线性表），这样我们才能add和remove操作：
	//话说getNode（），我们只需要提供id即可，所以我们这里写两个同名、功能兼容的方法！
	private Node<AnyType> getNode(int id){
		return getNode(id, 0, size()-1);
	}
	//
	private Node<AnyType> getNode(int id,int low,int high){
		//建立空Node！
		Node<AnyType> p;
		//index超界检测
		if ((id>high)||(id<low)) {
			throw new IndexOutOfBoundsException();
		}
		//因为是链表（不是数组表示的线性表！），所以只能循环遍历 咯！
		//话说也只有通过判断前后半区来初级的提高效率！
		if (id<(size()/2)) {
			p=beginNode.next;//指向第一个有效结点！
			for (int i = 0; i <id; i++) {
				p=p.next;
			}
		}
		//后半区！
		else {
			p=endNode;
			for (int i = size(); i >id; i--) {
				p=p.prev;
			}
		}
		return p;//只是得到Node而不没有改变链表的结构！所以modcount不用改变！
	}
	//addBefore(),这是add方法的private的封装，add操作：确定Node后，然后在此节点后加进去数据x；
	//Node p，data x；
	private void addBefore(Node<AnyType> p,AnyType x){
		//在p结点前加入新节点，数据为x；
		//有用的信息都在p上
		//话说我们需要创建新的结点啊！
		Node<AnyType> newNode=new Node<AnyType>(x,p.prev,p);
		//上式完成操作如下：
		//newNode.data=x;
		//newNode.next=p;
		//newNode.prev=p.prev;
		//newNode已经指向你们咯！，，你们要指向newNode！
		//方式有很多，怎么理解的舒服怎么来吧！
		newNode.prev.next=newNode;
		newNode.next.prev=newNode;
		//由于是改变的是链表的结构，所以modcount++
		theSize+=1;
		modCount+=1;
	}
	//private remove（node p）；private中则是提供的是Node,返回里面的数据
	private AnyType remove(Node<AnyType> p){
		//还是信息在node p 中；只需改两个链即可！
		AnyType tempdata=p.data;
		p.prev.next=p.next;
		p.next.prev=p.prev;
		//由于改变了链表的结构，所以得更新modcount；
		theSize-=1;
		modCount=+1;
		return tempdata;
	}
	//不解释咯！用内部类来完成iterato ——private class iterator....
	public class LinkedListIterator implements java.util.Iterator<AnyType>{
		//设置一个空Node表示当前结点。
		//iterator是从第一个有效节点开始遍历，所以初始的结点是从头开始的！
		Node<AnyType> current=beginNode.next;
		//迭代器中也要观察链表的修改情况，因为迭代器有remove（）方法；
		int iteratorModCount=modCount;
		//remove状态信号！
		private boolean okToRemove=false;
		//hasNext()
		public boolean hasNext(){
			return(current!=endNode);
		}
		//next()，返回node的data！
		public AnyType next(){
			//异常检测！
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			//next操作！
			AnyType tempData=current.data;
			current=current.next;
			//可以remove咯！
			okToRemove=true;
			return tempData;
		}
		//remove
		public void remove(){
			//异常检测
			if (modCount!=iteratorModCount) {
				throw new java.util.ConcurrentModificationException();
			}
			//异常检测：——当okToRemove=false时，抛出异常！
			if (!okToRemove) {
				throw new IllegalStateException();
			}
			//remove操作
			TwoMyLinkedList.this.remove(current.prev);
			okToRemove=false;
			iteratorModCount+=1;
		}
		
		
	}
}
//3.4和3.5
class JiaoBing<AnyType>{
    ArrayList<AnyType>L1=new ArrayList<AnyType>();
    ArrayList<AnyType>L2=new ArrayList<AnyType>();
    //3.4 L1交L2
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
    //3.5:L1并L2
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
    //基本组成单位；
    private class Node {
        private int data;
        private Node next;

        //Node构造方法，初始化
        public Node(int data1, Node next1) {
            this.data = data1;
            this.next = next1;
        }
    }

    //数据成员：只要头节点就ok！
    private Node beginNode;
    private int theSize = 0;

    //构造方法初始化：
    public CycleLinkedList() {
        doClear();
    }

    public void doClear() {
        theSize = 1;//头节点算一个结点！
        beginNode = new Node(1, null);
        beginNode.next = beginNode;//头节点指向自己，即做头节点也做尾结点!
    }

    //几个基本方法：
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

    //add，插入链表；
    //add第一种情形：在链表尾部添加结点；
    public void add(int data) {
        add(size() - 1, data);
    }

    //add第二种情形：在id位置后面加新节点！
    public void add(int id, int data) {
        addAfter(getNode(id), data);
    }

    public void remove(int id) {
        //删除的是第id个Node，但是需要知道前一个Node的信息：
        //id不可以是0，头节点不可删除！
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
        //第一个节点可以删除！
        if (p==beginNode){
            lastOne=getNode(size()-1);//最后一个结点！
        }else {
            for (int i = 0; i < size(); i++) {
                if (temp != p) {
                    lastOne = temp;
                    temp = temp.next;
                }//lastOne存的是p前一个节点！
            }
        }
        //remove!操作：
        lastOne.next=p.next;
    }

    //add第二种情形：在链表的中间指定位置插入结点；
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
        //不分前后半区！
        p = beginNode;
        for (int i = 0; i < id; i++) {
            p = p.next;
        }
        return p;
    }

    //Josephus problems!
    class JosephusProblem {
        private int m = 0;//默认游戏进行m=0次！
        private int n = 1;//至少有一个人！
        private CycleLinkedList queue = new CycleLinkedList();
        public JosephusProblem(int m, int n) {
            this.m = m;
            this.n = n;
            //使用循环链表来组成队列！
            //第一个人里面的data默认为1；
            for (int i = 2; i < n + 1; i++) {
                queue.add(i);
            }
        }

        public void kill() {
            //因为是最后一人获胜，并且每一次都只kill一个人，，，所以可以知道进行了n-1次kill游戏！
            //首次游戏从第一个人开始！
            Node people;
            people=queue.beginNode;
            for (int i = 1; i < this.n; i++) {
                //每一次kill，都要传m次炸弹！
                for (int j = 0; j < this.m; j++) {
                    people=people.next;
                }
                //kill this people!
                //死之前，有台词！
                System.out.println("my numberID is: "+people.data);
                queue.remove(people);
                //下一次从kill的人的后一个人开始！
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
