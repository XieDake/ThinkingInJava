package Tree;
import java.nio.BufferOverflowException;

/**
 * Created by John on 2016/12/6.
 */
public class AVLTTREE<AnyType extends Comparable<?super AnyType>> {
    //�����ṹ
    //˽��Ƕ���ࣺAvlNode
    private static class AvlNode<AnyType>{
        //���ݳ�Ա��
        private AnyType data;
        private AvlNode<AnyType> left;
        private AvlNode<AnyType>right;
        //������������ͬ�������ﻹ��Ҫ���ֽڵ�ĸ߶ȣ�
        private int height;
        //���췽���������汾��
        public AvlNode(AnyType data1,int height1,AvlNode<AnyType>left1,AvlNode<AnyType>right1){
            this.data=data1;
            this.left=left1;
            this.right=right1;
            this.height=height1;
        }
        public AvlNode(AnyType data2){
            this(data2,0,null,null);
        }
    }
    //���ݳ�Ա��ֻ�и��ڵ�root��
    private AvlNode<AnyType> root;
    //���췽����
    public AVLTTREE(){
        root=null;
    }
    //����������
    //clear AvlTree!
    public void clear(){
        root=null;
    }
    //isEmpty()?
    public boolean isEmpty(){
        return (root==null);
    }
    //�Ͳ���дcontains�����ˣ�������һ���ģ�
    //Ѱ��ֵΪ��data�Ľ�㲢���ػ�ڵ㣡
    //public-search()
    public AvlNode<AnyType>search(AnyType data){
        if (root==null){
            //root=null!���׳��쳣�ɣ�
            throw new BufferOverflowException();
        }
        return search(data,root);
    }
    //public-findMin()
    public AnyType finMin(AnyType data1){
        return findMin(root).data;
    }
    //public-findMax()
    public AnyType findMax(AnyType data2){
        return findMAx(root).data;
    }
    //���ؽڵ��height
    //public-height,ֻ��Ϊ�˻�ȡ���ĸ߶ȣ�
    public int height(){
        return height(root);
    }
    //public-insert
    public void insert(AnyType data){
        root=insert(data,root);
    }
    //public-remove
    public void remove(AnyType data){
        root=remove(data,root);
    }
    //AVL�������ֱ�����
    //ǰ������������ң�
    public void preOrder(){
        preOrder(root);
    }
    ////�������������ң�
    public void midOrder(){
        midOrder(root);
    }
    //������������Ҹ���
    private void backOrder(AvlNode<AnyType>t){
        backOrder(t.left);
        backOrder(t.right);
        System.out.println(t.data);
    }
    private void midOrder(AvlNode<AnyType>t){
        midOrder(t.left);
        System.out.println(t.data);
        midOrder(t.right);
    }
    private void preOrder(AvlNode<AnyType>t){
        System.out.println(t.data);
        preOrder(t.left);
        preOrder(t.right);
    }
    //private-remove
    private AvlNode<AnyType>remove(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            return t;//��Ϊ����������û�ҵ���
        }
        //finding��
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=remove(X,t.right);
        }else if(compareValue<0){
            t.left=remove(X,t.left);
        }else {
            //�ҵ��ˣ�
            //�ж϶��Ӹ�����
            //����
            if ((t.left!=null)&&(t.right!=null)){
                     t.data=findMin(t.right).data;
                     t.right=remove(t.data,t.right);//�ݹ�ɾ��t.data������
            }else {//һ�����ӣ�
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return balance(t);
    }
    //private-insert
    private AvlNode<AnyType>insert(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //����������Ҷ�ڵ㣿
            return new AvlNode<AnyType>(X);
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if(compareValue<0){
            t.left=insert(X,t.left);
        }else {
            //û���ҵ���
        }
        return balance(t);
    }
    //private-balance
    //balance���β��ǽ�㣬���������������ƽ���Ľ����
    //balance������ת�������������ע���β���������k2�������µĸ��ڵ�k1����ע��k2��k1ԭ���Ĺ�ϵ��
    //����
    private AvlNode<AnyType>rotateOnceLeftChild(AvlNode<AnyType>k2){
        //k2��ԭ��������ĸ��ڵ㣬k2���������k1
        AvlNode<AnyType>k1=k2.left;
        //��ת�Ľ����k1���k2�ĸ��ף�k2���k1���Ҷ��ӣ�
        //ע��ѭ��
        k2.left=k1.right;//�Ȱ�k1��right��k2��left��k2.leftһ����û�õ�!��
        k1.right=k2;//�ٰ�k2���k1���Ҷ��ӣ�
        //����height��
        k1.height=Math.max(k1.left.height,k1.right.height)+1;
        k2.height=Math.max(k2.left.height,k2.right.height)+1;
        //һ���Ƿ��ص����µĸ���k1��
        return k1;
    }
    //����---ԭ��ͬ����
    private AvlNode<AnyType>rotateOnceRight(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.right;
        k2.right=k1.left;
        k1.left=k2;
        k1.height=Math.max(k1.left.height,k1.right.height)+1;
        k2.height=Math.max(k2.left.height,k2.right.height)+1;
        return k1;
    }
    //balance������˫��ת�����������ע���漰������㣺k3��k2��k1����
    //ע��˫��ת=���ε���ת��ע�ⵥ��ת��k1��k2��
    //���ң�K3�������k1��������k2
    //��˳��ִ�����ε���ת�����ң�����
    private AvlNode<AnyType>rotateTwiceLeftRight(AvlNode<AnyType>k3){
        k3.left=rotateOnceRight(k3.left);//����
        return (rotateOnceLeftChild(k3));//���󣬣���󷵻���������
    }
    //����:k3���Ҷ���k1��������k2��
    // ִ�����ε�ת��������ң�
    private AvlNode<AnyType>rotateTwiceRightLeft(AvlNode<AnyType>k3){
        k3.right=rotateOnceLeftChild(k3.right);
        return (rotateTwiceLeftRight(k3));
    }
    //����һ����̬������ʾƽ��״̬�ļ��������
    private static final int ALLOWED_IMBALANCE=1;//�߶Ȳ�ֻ�ܲ�һ��
    //balance!
    private AvlNode<AnyType>balance(AvlNode<AnyType>t){
        if (t==null){
            return t;
        }//�ðɣ�û�㶮t==null�����ã�
        //�������г������⣡��߸߶ȴ����ұ�2��
        if (height(t.left)-height(t.right)>ALLOWED_IMBALANCE){
            //����
            if (height(t.left.left)>=height(t.left.right)){//�Լ�����Ϊɶ��>=����
                t=rotateOnceLeftChild(t);
            }
            //���ң�
            else{
                t=rotateTwiceLeftRight(t);
            }
        }
        //�������г������⣡�ұ߸߶ȴ������2��
        if(height(t.right)-height(t.left)>ALLOWED_IMBALANCE){
            //���ң�
            if (height(t.right.right)>=height(t.right.left)){
                t=rotateOnceRight(t);
            }
            //����
            else{
                t=rotateTwiceRightLeft(t);
            }
        }
        //ת�꿩�������߶ȣ�
        t.height=Math.max(height(t.left),height(t.right))+1;
        return t;
    }
    //private-height
    private int height(AvlNode<AnyType>t){
        if (t==null){
            return -1;
        }else {
            return t.height;
        }
    }
    //private-findMax()
    private AvlNode<AnyType>findMAx(AvlNode<AnyType>t){
        if (t==null){
            return t;
        }
        if (t.right!=null){
            return findMAx(t.right);
        }else {
            return t;
        }
    }
    //private-findMin()
    private AvlNode<AnyType>findMin(AvlNode<AnyType>t){
        if (t==null){
            //tΪ�գ�����û�ҵ���
            return t;
        }
        //finding!
        //�ж�t��㣡
        //t���ǡ�
        // �ж�t.left
        if (t.left!=null){
            return findMin(t.left);
        }else {
            //t�ǣ�
            return t;
        }
    }
    //private-search()
    private AvlNode<AnyType>search(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //û�ҵ������ǿ�����
            return t;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            //right,,,
            //�ж�t���ҽ�㣡
            return search(X,t.right);
        }else if (compareValue<0){
            //left,�ж�t����ڵ㣡
            return search(X,t.left);
        }else {
            //�ҵ��ˣ�t���ǣ�
            return t;
        }
    }
}
