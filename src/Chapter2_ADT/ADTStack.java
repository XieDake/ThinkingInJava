package Chapter2_ADT;
/**
 * Created by John on 2016/12/3.
 */
//һ������ʵ������ADTջ��
class AdtStack<AnyType>{
    //��������Ľṹ�����飺
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
    //���췽����
    public AdtStack(){
        doClear();
    }
    //clear
    public void clear(){
        doClear();
    }
    public void doClear(){
        theSize=0;//����Ԫ�ز��ܳ���theSize;
        //���ݣ�
        eneureCapacity(DEFAULT_CAPACITY);
        //��ʼ����Ա������
        topOfStack1=-1;//��ջ��ʼ����
        topOfStack2=theArray.length;//��ջ��ʼ����
        //λ�ö����ˣ���������û�мӽ�ȥ��
        theSize1=0;
        theSize2=0;
    }
    //push:����ջ�Ľṹ�������ԣ���������ֻ����ջ�����Ԫ�أ�
    public void push(int identification,AnyType data){
        //���ݼ�⣡
        if (topOfStack2-topOfStack1==1){
            eneureCapacity(size()*2+1);
        }
        //Identification=1,��ʾ��ߵ�ջ��Identification=2����ʾ�ұߵ�ջ��
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
    //pop��ͬ��ֻ����ջ��ɾ��Ԫ�أ�,��˵ֻ��Ҫ��ʶ�Ϳ��ԣ�
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
        //������С�����ݣ�
        if (newCapacity<theSize){
            return;
        }
        //����3���裡
        AnyType[]old=theArray;//1
        theArray=(AnyType[])new Object[newCapacity];
        int head=0;
        int bottom=theArray.length;
        //����stack�ֱ��룡
        if (old.length==1){
            //��ջ�����ò����ˣ�gg!
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
