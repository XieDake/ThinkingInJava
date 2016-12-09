package Tree;
import java.nio.BufferOverflowException;
/**
 * Created by John on 2016/12/5.
 */
public class BinarySearchTree1<AnyType extends Comparable<?super AnyType>> {
    //˽��Ƕ���࣬����Ϊ����������Ļ�����ɵ�λ��
    private static class BinaryNode<AnyType>{
        private AnyType data;
        private BinaryNode<AnyType>left;
        private BinaryNode<AnyType>right;
        //���췽����
        public BinaryNode(AnyType data1,BinaryNode<AnyType>left1,BinaryNode<AnyType>righ1){
            this.data=data1;
            this.left=left1;
            this.right=righ1;
        }
    }
    //�������ݳ�Ա��
    private BinaryNode<AnyType>root;
    //���๹�췽����
    public BinarySearchTree1(){
        root=null;
    }
    //����������
    //clear!
    public void clear(){
        root=null;
    }
    //isEmpty()
    public boolean isEmpty(){
        return (root==null);
    }
    //�����������ܷ�����
    //public-contains()
    public boolean contains(AnyType X){
        return contains(X,root);
    }
    //public-findMin()
    public AnyType findMin(){
        if (root==null){
            throw new BufferOverflowException();
        }
        return findMin(root).data;
    }
    //public-findMax()
    public AnyType findMax(){
        if (root==null){
            throw new BufferOverflowException();
        }
        return findMax(root).data;
    }
    //public-insert()
    public void insert(AnyType X){
        root=insert(X,root);
    }
    //public-remove;
    public void remove(AnyType X){
        root=remove(X,root);
    }
    //private-remove
    private BinaryNode<AnyType>remove(AnyType X,BinaryNode<AnyType>t){
        if (t==null){
            return null;//û�ҵ���
        }
        //finding��
        int compareValue=X.compareTo(t.data);
        //X>t,.data...right..
        if (compareValue>0){
            t.right=remove(X,t.right);//�ж���һ����t.right������ֻ�Ե�ǰ����������
        }
        //X<t.data...left..
        if (compareValue<0){
            t.left=remove(X,t.left);
        }
        //�ҵ��ˣ�
        //���������
        if (compareValue==0){
            //2�����ӣ�
            if ((t.left!=null)&&(t.right!=null)){
                t.data=findMin(t).data;
                remove(t.data,t);
            }
            //һ�����ӻ����ǹ��Ҷ�ڵ㣡
            else{
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return t;
    }
    //private-insert()
    private BinaryNode<AnyType>insert(AnyType X,BinaryNode<AnyType>t){
        //���ڶ��������������Ľṹ�ص㣡����ֻ�������Ҷ�ڵ㴦��
        //��Ӧ����������½�һ��Ҷ�ڵ�������ݣ�
        if (t==null){
            t=new BinaryNode<AnyType>(X,null,null);
        }
        //��û���ҵ�λ�ã��������ң�
        int compareValue=X.compareTo(t.data);
        //X>t.data....right..
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if (compareValue<0){
            t.left=insert(X,t.left);
        }else {
            System.out.println("already have!");
        }
        return t;//�����µĸ��ڵ㣻
    }
    //private-findMax()
    private BinaryNode<AnyType>findMax(BinaryNode<AnyType>t){
        if (t==null){
            return null;
        }
        else if (t.right!=null){
            return findMax(t.right);
        }else{
            return t;
        }
    }
    //private-findMin()
    private BinaryNode<AnyType>findMin(BinaryNode<AnyType>t){
        if (t==null){
            return null;
        }//��t.left����
        else if (t.left!=null){
            return findMin(t.left);
        }//�ҵ��ˣ�
        else {
            return t;
        }
    }
    //private-contains����
    private boolean contains(AnyType X,BinaryNode<AnyType>t) {
        boolean sign=false;
        //�ҵ����û���ҵ���
        if (t == null) {
            return false;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        //X>t.data....right..
        if (compareValue>0){
            return contains(X,t.right);//ʹ�õݹ�ķ���������������ж������ǲ��᷵��boolean�����!
        }//X<t.data...left!
        else if(compareValue<0){
            return contains(X,t.left);
        }else{
            return true;
        }
    }




}
