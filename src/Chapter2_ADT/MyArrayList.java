package Chapter2_ADT;
//���ͽӿ�/��
class MyArrayList<AnyType> implements Iterable<AnyType> //��˵�����ʵ��iterator��������
{
	//���ȶ���һ�»����Ķ���
	private static final int DEFAULT_CAPACITY=10; //��ʼ����10��
	private int theSize;
	private AnyType [] theItems;
	//�����method����,���캯���ͳ�ʼ����0��method
	public void doclear(){
		theSize=0;
		ensureCapcity(DEFAULT_CAPACITY);
	}
	//����Ȩ��+���� ����������˹��췽���Ķ���
	public MyArrayList(){
		doclear();
	}
	//clear method
	public void clear(){
		doclear();
	}
	//�ٶ�������������method
	public int size() {
		return theSize;
	}
	public boolean empty() {
		return (size()==0);
	}
	//get() and set()����Ҫ�׳��쳣
	public AnyType get(int id){
		if ((id<0)||(id>size())) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return theItems[id];
	}
	public AnyType set(int id,AnyType newItem){
		if ((id<0)||(id>size())) {
			throw new ArrayIndexOutOfBoundsException();
		}
		AnyType old=theItems[id];
		theItems[id]=newItem;
		return old;
	}
	//��������������չmethod����
	public void ensureCapcity(int newCapcity) {
		if (size()>newCapcity) {
			return; //������С����չ�أ����������
		}
		//��չ�ɣ�
		AnyType [] old=theItems;//�ȴ浽һ���������У�
		theItems=(AnyType[])new Object[(newCapcity)];
		for (int i = 0; i <size(); i++) {
			theItems[i]=old[i];
			//ע��ֻ��չ��������Ԫ�ظ���û�иı仹��=theSize
		}
	}
	//add and remove method
	//ע��add��remove���ַ������ı���theSize����ע���ֶ��Ķ�����
	//Ϊ�˼�������add������������ȡ��һ�����ˣ�
	public boolean add(AnyType x){
		//size()��ʾ�������Ԫ������+1==�����Ԫ�غ����������һ��Ԫ�أ�
		add(size(),x);
		return true;
	}
	//��ָ��λ�����Ԫ��
	public void add(int id,AnyType x) {
		//�費��Ҫ��չ�����Ԫ�ظ���==���鳤�ȣ�����Ϊ�գ�������Ҫ��չ��
		if (theItems.length==size()) {
			ensureCapcity(2*size()+1);//+1:��ֹ0�������
		}
		//add
		//size()��ʾ�������Ԫ������+1==�����Ԫ�غ����������һ��Ԫ�أ�
		for (int i = size(); i <id; i--) {
			theItems[i]=theItems[i-1];
		}
		theItems[id]=x;
		theSize-=1;
	}
	//remove
	public AnyType remove(int id) {
		AnyType itemRemoved;
		itemRemoved=theItems[id];
		for (int i = id; i <size(); i++) {
			theItems[i]=theItems[i+1];
		}
		theSize-=1;
		return itemRemoved;
	}
	//iterator
	//��ΪMyArrayListʵ����Iterable�ӿ�,���Ե�ʵ��iterator����������
	//Iterable��iterator�����Ķ����ʽ�ɲ飺
	//iterator������Iterator�ӿ������Ի���Ҫ����һ���ڲ�����ʵ�����Iterator�ӿ���ʵ��iterator��������
	public java.util.Iterator<AnyType> iterator(){
		return new ArrayListIterator();
	}
	//�����ڲ�����ʵ��Iterator�ӿڣ�
	private class ArrayListIterator implements java.util.Iterator<AnyType>{
		//��ͷ����������current=0��
		private int current=0;
		public boolean hasNext(){
			return(current<size());
		}
		public AnyType next(){
			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}
			return theItems[current++];
		}
		public void remove(){
			MyArrayList.this.remove(--current);
		}
	}
	
	

}
