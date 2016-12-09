package Tree;

import org.omg.CORBA.Any;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * Created by John on 2016/12/7.
 */
public class AvlTree1<AnyType extends Comparable<?super AnyType>> {
    //����AVL���Ļ����ṹ-˽��Ƕ���ࣺ��㣡
    private static class AvlNode<AnyType>{
        //���ݳ�Ա��
        private AnyType data;
        private int height;
        private AvlNode<AnyType>left;
        private AvlNode<AnyType>right;
        //���췽����
        //���֣�
        //1
        public AvlNode(AnyType data1,int height1,AvlNode<AnyType>left1,AvlNode<AnyType>right1){
            this.data=data1;
            this.height=height1;
            this.left=left1;
            this.right=right1;
        }
        //2
        public AvlNode(AnyType data2){
            this(data2,0,null,null);
        }
    }
    //�������ݳ�Ա��
    private AvlNode<AnyType>root;
    //���췽����
    public AvlTree1(){
        root=null;
    }
    //����������
    public void makeEmpty(){
        root=null;
    }
    //isEmpty?
    public boolean isEmpty(){
        return root==null;
    }
    //search
    //public-search
    public AvlNode<AnyType>search(AnyType data){
        return search(data,root);
    }
    //public-findMin()
    public AnyType findMin(){
        if (root==null){
            throw new BufferUnderflowException();
        }
        return findMin(root).data;
    }
    //public-findMax()
    public AnyType findMAx(){
        if (root==null){
            throw new BufferOverflowException();
        }
        return findMax(root).data;
    }
    //public-height
    //���û��ɶ�ã�ֻ�Ƿ�������height��
    public int height(){
        return height(root);
    }
    //public-insert
    public void insert(AnyType data){
        root=insert(data,root);
    }
    //public-remove
    //private-remove
    private AvlNode<AnyType>remove(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            return t;//û�ҵ�������һ�ÿ�����
        }
        //�Ұɣ�
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=remove(X,t.right);
        }else if (compareValue<0){
            t.left=remove(X,t.left);
        }
        //�ҵ��ˣ���t���棡
        else {
            //t���������ӣ�
            if((t.left!=null)&&(t.right!=null)){
                t.data=findMin(t.right).data;
                //��t.right��ʼ���ݹ�ɾ�����data
                t.right=remove(t.data,t.right);
            }
            //t��һ�����ӻ���û������!
            t=(t.left!=null)?t.left:t.right;
        }
        return balance(t);
    }
    //private-insert
    private AvlNode<AnyType>insert(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            return new AvlNode<AnyType>(X);//�ҵ���һ���ڵ㣡//����������Ҷ�ڵ㣡
        }
        //Ѱ�Һ��ʵ�λ�ã�
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if (compareValue<0){
            t.left=insert(X,t.left);
        }
        //t�����Ѿ�����X��
        else {
        }
        return balance(t);
    }
    //private-height
    private int height(AvlNode<AnyType>t){
        if (t==null){
            return -1;//�ս���height=-1��
        }else {
            return t.height;
        }
    }
    //private_balance
    //�ȶ���һ��ƽ��Ľ��ޣ����������ĸ߶Ȳ�=1��
    private static final int ALLOWED_IMBLANCE=1;
    //balance
    private AvlNode<AnyType>balance(AvlNode<AnyType>t){
        //??
        if (t==null){
            return t;
        }
        //���������ë����
        if (height(t.left)-height(t.right)>ALLOWED_IMBLANCE){
            //����
            if (height((t.left.left))>=height((t.left.right))){
                t=rotateOnceLeft(t);
            }
            //���ң�
            else{
                //˫ת��
                t=rotateTwiceLeft(t);
            }
        }
        //��������ë����
        if (height(t.right)-height(t.left)>ALLOWED_IMBLANCE){
            //����
            if (height(t.right.right)>=height(t.right.left)){
                t=rotateOnceRight(t);
            }
            //����
            else{
                t=rotateTwiceRight(t);
            }
        }
        //����t��height;
        t.height=Math.max(height(t.left),height(t.right))+1;
        return t;
    }
    //private-��ת
    //����:k2�������k1����������ë����
    private AvlNode<AnyType> rotateOnceLeft(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.left;
        //�ȶ�k2
        k2.left=k1.right;
        k1.right=k2;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        return k1;
    }
    //����
    //k2���Ҷ���k1����������ë����
    private AvlNode<AnyType>rotateOnceRight(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.right;
        //�ȶ�k2
        k2.right=k1.left;
        k1.left=k2;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        return k1;
    }
    //private-˫ת��
    //����:k3�������k1��������k2��ë����
    //ִ�����ε�ת������
    private AvlNode<AnyType>rotateTwiceLeft(AvlNode<AnyType>k3){
        //���Һ���
        k3.left=rotateOnceRight(k3.left);
        return rotateOnceLeft(k3);
    }
    //����:k3���Ҷ���k1��������k2��ë����
    private AvlNode<AnyType>rotateTwiceRight(AvlNode<AnyType>k3){
        //ִ�����ε�ת������
        //������ң�
        k3.right=rotateOnceLeft(k3.left);
        return rotateOnceLeft(k3);
    }
    //private-findMax()
    private AvlNode<AnyType>findMax(AvlNode<AnyType>t){
        if (t==null){
            return t;
        }else if (t.right!=null){
            //�������ұ�Ѱ�ң�
            return findMax(t.right);
        }else {
            //�ҵ��ˣ�
            return t;
        }
    }
    //private-findMin()
    private AvlNode<AnyType>findMin(AvlNode<AnyType>t){
        if (t==null){
            return t;//û�ҵ������ǿ�����
        }else if (t.left!=null){
            //���������Ѱ�ң�
            return findMin(t.left);
        }else {
            //�ҵ��ˣ�
            return t;
        }
    }
    //private-search
    private AvlNode<AnyType>search(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //û�ҵ��������Ǳ����ǿ�����
            return t;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            return search(X,t.right);
        }else if (compareValue<0){
             return search(X,t.left);
        }//�ҵ��ˣ�
        else {
            return t;
        }
    }
}
