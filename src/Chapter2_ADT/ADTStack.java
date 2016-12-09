package Chapter2_ADT;
/**
 * Created by John on 2016/12/3.
 */
//一个数组实现两个ADT栈！
class AdtStack<AnyType>{
    //定义基本的结构：数组：
    private static final int DEFAULT_CAPACITY=10;
    private AnyType [] theArray;
    private int topOfStack1;
    private int topOfStack2;
    private int theSize;
    private int theSize1;
    private int theSize2;
    //size()
    public int size(){
        return theSize;
    }
    //构造方法：
    public AdtStack(){
        doClear();
    }
    //clear
    public void clear(){
        doClear();
    }
    public void doClear(){
        theSize=0;//加入元素才能称作theSize;
        //扩容！
        eneureCapacity(DEFAULT_CAPACITY);
        //初始化成员变量！
        topOfStack1=-1;//空栈初始化！
        topOfStack2=theArray.length;//空栈初始化！
        //位置定好了，但是数据没有加进去！
        theSize1=0;
        theSize2=0;
    }
    //push:由于栈的结构的特殊性，，，所以只是在栈顶添加元素！
    public void push(int identification,AnyType data){
        //扩容检测！
        if (topOfStack2-topOfStack1==1){
            eneureCapacity(size()*2+1);
        }
        //Identification=1,表示左边的栈；Identification=2：表示右边的栈；
        if (identification==1){
            topOfStack1+=1;
            theArray[topOfStack1]=data;
            theSize1+=1;
        }
        if (identification==2){
            topOfStack2-=1;
            theArray[topOfStack2]=data;
            theSize2+=1;
        }
        theSize+=1;
    }
    //pop：同样只是在栈顶删除元素！,话说只需要标识就可以！
    public AnyType pop(int identification){
        AnyType returning=null;
        if (identification==1){
            returning= theArray[topOfStack1];
            topOfStack1-=1;
        }
        if (identification==2){
            returning=theArray[topOfStack2];
            topOfStack2+=1;
        }
        return returning;
    }
    //ensureCapacity
    public void eneureCapacity(int newCapacity){
        //不能往小了扩容！
        if (newCapacity<theSize){
            return;
        }
        //扩容3步骤！
        AnyType[]old=theArray;//1
        theArray=(AnyType[])new Object[newCapacity];
        int head=0;
        int bottom=theArray.length;
        //两个stack分别导入！
        if (old.length==1){
            //空栈！不用操作了！gg!
            return;
        }else {
            for (int i = 0; i < topOfStack1; i++) {
                theArray[head] = old[i];
                head++;
            }
            for (int j = old.length-1; j > topOfStack2; j--) {
                theArray[bottom] = old[j];
                bottom--;
            }
        }
    }


}
