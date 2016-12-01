package Chapter2_ADT;
//泛型接口/类
class MyArrayList<AnyType> implements Iterable<AnyType> //话说必须得实现iterator（）方法
{
	//首先定义一下基本的东西
	private static final int DEFAULT_CAPACITY=10; //初始容纳10个
	private int theSize;
	private AnyType [] theItems;
	//最基本method（）,构造函数和初始化归0的method
	public void doclear(){
		theSize=0;
		ensureCapcity(DEFAULT_CAPACITY);
	}
	//访问权限+类名 ——就完成了构造方法的定义
	public MyArrayList(){
		doclear();
	}
	//clear method
	public void clear(){
		doclear();
	}
	//再定义其他基本的method
	public int size() {
		return theSize;
	}
	public boolean empty() {
		return (size()==0);
	}
	//get() and set()都需要抛出异常
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
	//定义数组容量扩展method（）
	public void ensureCapcity(int newCapcity) {
		if (size()>newCapcity) {
			return; //哪能往小了扩展呢？趁早结束！
		}
		//扩展吧！
		AnyType [] old=theItems;//先存到一个空数组中；
		theItems=(AnyType[])new Object[(newCapcity)];
		for (int i = 0; i <size(); i++) {
			theItems[i]=old[i];
			//注意只扩展容量但是元素个数没有改变还是=theSize
		}
	}
	//add and remove method
	//注意add和remove两种方法都改变了theSize：（注意手动改动！）
	//为了兼容两种add情形这里名字取成一样的了！
	public boolean add(AnyType x){
		//size()表示就是最后元素索引+1==添加完元素后新数组最后一个元素；
		add(size(),x);
		return true;
	}
	//在指定位置添加元素
	public void add(int id,AnyType x) {
		//需不需要扩展？如果元素个数==数组长度（可以为空），则需要扩展！
		if (theItems.length==size()) {
			ensureCapcity(2*size()+1);//+1:防止0的情况！
		}
		//add
		//size()表示就是最后元素索引+1==添加完元素后新数组最后一个元素；
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
	//因为MyArrayList实现了Iterable接口,所以得实现iterator（）方法：
	//Iterable中iterator（）的定义格式可查：
	//iterator（）在Iterator接口中所以还需要定义一个内部类来实现这个Iterator接口再实现iterator（）方法
	public java.util.Iterator<AnyType> iterator(){
		return new ArrayListIterator();
	}
	//定义内部类来实现Iterator接口：
	private class ArrayListIterator implements java.util.Iterator<AnyType>{
		//从头遍历，所以current=0；
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
