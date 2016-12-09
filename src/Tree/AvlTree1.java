package Tree;

import org.omg.CORBA.Any;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/**
 * Created by John on 2016/12/7.
 */
public class AvlTree1<AnyType extends Comparable<?super AnyType>> {
    //定义AVL树的基本结构-私有嵌套类：结点！
    private static class AvlNode<AnyType>{
        //数据成员！
        private AnyType data;
        private int height;
        private AvlNode<AnyType>left;
        private AvlNode<AnyType>right;
        //构造方法！
        //两种！
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
    //定义数据成员！
    private AvlNode<AnyType>root;
    //构造方法！
    public AvlTree1(){
        root=null;
    }
    //基本方法！
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
    //这个没有啥用！只是返回树的height的
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
            return t;//没找到或者是一棵空树！
        }
        //找吧！
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=remove(X,t.right);
        }else if (compareValue<0){
            t.left=remove(X,t.left);
        }
        //找到了！在t里面！
        else {
            //t有两个儿子！
            if((t.left!=null)&&(t.right!=null)){
                t.data=findMin(t.right).data;
                //从t.right开始，递归删掉这个data
                t.right=remove(t.data,t.right);
            }
            //t有一个儿子或者没儿子了!
            t=(t.left!=null)?t.left:t.right;
        }
        return balance(t);
    }
    //private-insert
    private AvlNode<AnyType>insert(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            return new AvlNode<AnyType>(X);//找到那一个节点！//空树或者是叶节点！
        }
        //寻找合适的位置！
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if (compareValue<0){
            t.left=insert(X,t.left);
        }
        //t中早已经存在X；
        else {
        }
        return balance(t);
    }
    //private-height
    private int height(AvlNode<AnyType>t){
        if (t==null){
            return -1;//空结点的height=-1；
        }else {
            return t.height;
        }
    }
    //private_balance
    //先定义一个平衡的界限！左右子树的高度差=1；
    private static final int ALLOWED_IMBLANCE=1;
    //balance
    private AvlNode<AnyType>balance(AvlNode<AnyType>t){
        //??
        if (t==null){
            return t;
        }
        //左边子树有毛病！
        if (height(t.left)-height(t.right)>ALLOWED_IMBLANCE){
            //左左？
            if (height((t.left.left))>=height((t.left.right))){
                t=rotateOnceLeft(t);
            }
            //左右？
            else{
                //双转！
                t=rotateTwiceLeft(t);
            }
        }
        //右子树有毛病！
        if (height(t.right)-height(t.left)>ALLOWED_IMBLANCE){
            //右右
            if (height(t.right.right)>=height(t.right.left)){
                t=rotateOnceRight(t);
            }
            //右左
            else{
                t=rotateTwiceRight(t);
            }
        }
        //调整t的height;
        t.height=Math.max(height(t.left),height(t.right))+1;
        return t;
    }
    //private-单转
    //左左:k2的左儿子k1的左子树有毛病！
    private AvlNode<AnyType> rotateOnceLeft(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.left;
        //先动k2
        k2.left=k1.right;
        k1.right=k2;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        return k1;
    }
    //右右
    //k2的右儿子k1的右子树有毛病！
    private AvlNode<AnyType>rotateOnceRight(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.right;
        //先动k2
        k2.right=k1.left;
        k1.left=k2;
        k1.height=Math.max(height(k1.left),height(k1.right))+1;
        k2.height=Math.max(height(k2.left),height(k2.right))+1;
        return k1;
    }
    //private-双转！
    //左右:k3的左儿子k1的右子树k2有毛病！
    //执行两次单转操作！
    private AvlNode<AnyType>rotateTwiceLeft(AvlNode<AnyType>k3){
        //先右后左：
        k3.left=rotateOnceRight(k3.left);
        return rotateOnceLeft(k3);
    }
    //右左:k3的右儿子k1的左子树k2有毛病！
    private AvlNode<AnyType>rotateTwiceRight(AvlNode<AnyType>k3){
        //执行两次单转操作：
        //先左后右！
        k3.right=rotateOnceLeft(k3.left);
        return rotateOnceLeft(k3);
    }
    //private-findMax()
    private AvlNode<AnyType>findMax(AvlNode<AnyType>t){
        if (t==null){
            return t;
        }else if (t.right!=null){
            //继续向右边寻找！
            return findMax(t.right);
        }else {
            //找到了！
            return t;
        }
    }
    //private-findMin()
    private AvlNode<AnyType>findMin(AvlNode<AnyType>t){
        if (t==null){
            return t;//没找到或者是空树！
        }else if (t.left!=null){
            //继续往左边寻找！
            return findMin(t.left);
        }else {
            //找到了！
            return t;
        }
    }
    //private-search
    private AvlNode<AnyType>search(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //没找到，或者是本身是空树！
            return t;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            return search(X,t.right);
        }else if (compareValue<0){
             return search(X,t.left);
        }//找到了！
        else {
            return t;
        }
    }
}
