package Chapter2_ADT;

/**
 * Created by John on 2016/12/3.
 */
//������
class MyLinkedListListIterator<AnyType> implements Iterable<AnyType> {
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
    public MyLinkedListListIterator(){
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
    public java.util.Iterator<AnyType> listiterator(){
        return new LinkedListListIterator();
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
    public class LinkedListListIterator implements java.util.ListIterator<AnyType>{
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
            MyLinkedListListIterator.this.remove(current.prev);
            okToRemove=false;
            iteratorModCount+=1;
        }
        }
        public int nextIndex(){
            return 1;
        }
    }
