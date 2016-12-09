package Tree;
import java.nio.BufferOverflowException;
/**
 * Created by John on 2016/12/5.
 */
public class BinarySearchTree1<AnyType extends Comparable<?super AnyType>> {
    //私有嵌套类，，作为二叉查找树的基本组成单位！
    private static class BinaryNode<AnyType>{
        private AnyType data;
        private BinaryNode<AnyType>left;
        private BinaryNode<AnyType>right;
        //构造方法！
        public BinaryNode(AnyType data1,BinaryNode<AnyType>left1,BinaryNode<AnyType>righ1){
            this.data=data1;
            this.left=left1;
            this.right=righ1;
        }
    }
    //定义数据成员！
    private BinaryNode<AnyType>root;
    //外类构造方法！
    public BinarySearchTree1(){
        root=null;
    }
    //基本方法！
    //clear!
    public void clear(){
        root=null;
    }
    //isEmpty()
    public boolean isEmpty(){
        return (root==null);
    }
    //定义其他功能方法！
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
            return null;//没找到！
        }
        //finding！
        int compareValue=X.compareTo(t.data);
        //X>t,.data...right..
        if (compareValue>0){
            t.right=remove(X,t.right);//判断下一个：t.right，，，只对当前根结点操作！
        }
        //X<t.data...left..
        if (compareValue<0){
            t.left=remove(X,t.left);
        }
        //找到了：
        //三种情况！
        if (compareValue==0){
            //2个儿子！
            if ((t.left!=null)&&(t.right!=null)){
                t.data=findMin(t).data;
                remove(t.data,t);
            }
            //一个儿子或者是光棍叶节点！
            else{
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return t;
    }
    //private-insert()
    private BinaryNode<AnyType>insert(AnyType X,BinaryNode<AnyType>t){
        //由于二叉查找树的特殊的结构特点！插入只能在最后叶节点处！
        //对应空树和最后新建一个叶节点插入数据！
        if (t==null){
            t=new BinaryNode<AnyType>(X,null,null);
        }
        //还没有找到位置！继续查找！
        int compareValue=X.compareTo(t.data);
        //X>t.data....right..
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if (compareValue<0){
            t.left=insert(X,t.left);
        }else {
            System.out.println("already have!");
        }
        return t;//返回新的根节点；
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
        }//向t.left遍历
        else if (t.left!=null){
            return findMin(t.left);
        }//找到了！
        else {
            return t;
        }
    }
    //private-contains（）
    private boolean contains(AnyType X,BinaryNode<AnyType>t) {
        boolean sign=false;
        //找到最后没有找到！
        if (t == null) {
            return false;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        //X>t.data....right..
        if (compareValue>0){
            return contains(X,t.right);//使用递归的方法，，不到最后判断条件是不会返回boolean结果的!
        }//X<t.data...left!
        else if(compareValue<0){
            return contains(X,t.left);
        }else{
            return true;
        }
    }




}
