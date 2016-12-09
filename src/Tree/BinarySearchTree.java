package Tree;
import org.omg.CORBA.Any;

import java.nio.BufferUnderflowException;
/**
 * Created by John on 2016/12/4.
 */
public class BinarySearchTree <AnyType extends Comparable<?super AnyType>>{
    //������Ԫ�����ڵ㣻
    private static class BinaryNode<AnyType> {
        AnyType data;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        //���췽��
        //����Ҷ�ڵ����ͨ���ڵ�
        //Ҷ�ڵ�:ֱ�Ӹ�data���ɣ�
        BinaryNode(AnyType data1) {
            this(data1, null, null);
        }

        //һ��
        BinaryNode(AnyType data1, BinaryNode<AnyType> left1, BinaryNode<AnyType> right1) {
            this.data = data1;
            this.left = left1;
            this.right = right1;
        }
    }
    //�������ݳ�Ա��ֻ����ڵ㼴�ɣ�
    private BinaryNode<AnyType>root;
    //���๹�췽�������ڵ�ʲôҲû�У�
    public BinarySearchTree(){
        root=null;
    }
    //�������������
    //clear����root������
    public void clear(){
        root=null;
    }
    //isEmpty?
    public boolean isEmpty(){
        return (root==null);
    }
    //contains
    public boolean contains(AnyType X){
        return contains(X,root);//����˵�𡣡���һ���ǴӸ��ڵ㿪ʼѰ�ң�
    }
    //findmin�ҵ�������С�ģ���data��,,,��󷵻����ݣ�
    public AnyType findMin(){
        //����ǿ����򣬣��׳��쳣��
        if(isEmpty()){throw new BufferUnderflowException();}
        return findMin(root).data;
    }
    //findMax������󷵻����ݣ�
    public AnyType findMax(){
        //���������׳��쳣��
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        //
        return findMax(root).data;
    }
    //insert!�����㣡����ʹ�õݹ�ʵ�֣�
    //insert�꿩������Ϊ��һ������������������һ�����������������������ã�
    public void insert(AnyType X){
        root=insert(X,root);
    }
    //��������������ֱ�����
    //ǰ������������ң�
    public void preOeder(){
        preOrder(root);
    }
    ////�������������ң�
    public void midOrder(){
        midOrder(root);
    }
    //������������Ҹ���
    private void backOrder(BinaryNode<AnyType>t){
        backOrder(t.left);
        backOrder(t.right);
        System.out.println(t.data);
    }
    private void midOrder(BinaryNode<AnyType>t){
        midOrder(t.left);
        System.out.println(t.data);
        midOrder(t.right);
    }
    private void preOrder(BinaryNode<AnyType>t){
        System.out.println(t.data);
        preOrder(t.left);
        preOrder(t.right);
    }
    //remove!ʹ�õݹ�ʵ�֣�
    //removeҲ�Ǹı���ԭ����һ�����Ľṹ����������Ϊ�Ƿ��ص���һ���������������������ĸ��ڵ�����ã�
    //remove-private����������һ������������root��
    private BinaryNode<AnyType>remove(AnyType X, BinaryNode<AnyType>t){
        //û���ҵ���
        if (t==null){
            return null;
        }
        //finding!....
        int compareValue=X.compareTo(t.data);
        //X>t.data.,,,right
        if (compareValue>0){
            t.right=remove(X,t.right);
        }
        //X<t.data,,,,left
        if (compareValue<0){
            t.left=remove(X,t.left);
        }
        //X=t.data...
        //�ҵ�����
        //�����������
        if ((compareValue==0)){
            //���������ӣ����Ҳ�ǵ�һ�����ӻ���Ҷ�ڵ�������
            if ((t.right!=null)||(t.left!=null)){
                //�ҵ���ǰ�������������С�Ľ�㣡��������ڵ��ֵ������ڵ㣡
                t.data=findMin(t.right).data;
                //ɾ�����data���ڵĽ�㣡
                t.right=remove(t.data,t.right);
            }//1:��һ��Ҷ�ڵ㣡//��1�����ӣ�
            else{
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return t;//����һ���µ�����
    }
    //insert-private
    private BinaryNode<AnyType> insert(AnyType X,BinaryNode<AnyType> t){
        //�ҵ���һ��λ�ÿ���
        //���ڶ��������������Ĺ��򣬣���ʹ���²������ֻ�ܲ���Ҷ�ڵ㴦���������Ҳ���λ�ã�
        if (t==null){
            return new BinaryNode<AnyType>(X,null,null);
        }
        //�Ƚϲ��ң�
        int compareValue=X.compareTo(t.data);
        //X>t.data,,,right....
        if (compareValue>0){
            t.right=insert(X,t.right);
        }
        //X<t.data,,,,left
        if (compareValue<0){
            t.left=insert(X,t.left);
        }
        //Bingo!�ظ�����
        // return null!
        if (compareValue==0){
            return null;
        }
        //��󷵻ص��Ǹ��������ã�
        return t;
    }
    //findMin-private,����С��
    //������С�����ڵĽ�����ã�����ʹ�õݹ�!
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType>t){
       //û���ҵ���
        if (t==null){
            return null;
        }
        //����С���������,,������һ��Ҷ�ڵ㣡
        //�ҵ��Ǹ�Ҷ�ڵ㿩��
        if (t.left==null) {
            return t;
        }//�������Ѱ�ң�
        else{
            return findMin(t.left);
        }
    }
    //findMax-Private�����ҵ����ġ���������
    private BinaryNode<AnyType>findMax(BinaryNode<AnyType>t){
        if (t==null){
            return null;
        }
        //�ҵ��Ǹ�Ҷ�ڵ㿩��
        if (t.right==null){
            return t;
        }else{
            return findMax(t.right);
        }
    }
    //contains-private:�ݹ�ʵ�֣��жϽ�����޴�Ԫ�أ�
    private boolean contains(AnyType X,BinaryNode<AnyType> t){
        //����жϣ�����,,,û�ҵ�����
        if (t==null){
            return false;
        }
        //���ң�
        int compareValue=X.compareTo(t.data);
        //X>t.data��Ӧ����t���ұ�Ѱ�ң�
        if (compareValue>0){
            return contains(X,t.right);
        }
        //X<t.data��Ӧ����t������ң�
        if (compareValue<0){
            return contains(X,t.left);
        }
        //X=t.data,,�ҵ��ˣ�����
        return true;
    }
}
