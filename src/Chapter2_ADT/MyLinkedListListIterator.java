package Chapter2_ADT;

/**
 * Created by John on 2016/12/3.
 */
//泛型类
class MyLinkedListListIterator<AnyType> implements Iterable<AnyType> {
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
    public MyLinkedListListIterator(){
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
    public java.util.Iterator<AnyType> listiterator(){
        return new LinkedListListIterator();
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
    public class LinkedListListIterator implements java.util.ListIterator<AnyType>{
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
            MyLinkedListListIterator.this.remove(current.prev);
            okToRemove=false;
            iteratorModCount+=1;
        }
        }
        public int nextIndex(){
            return 1;
        }
    }
