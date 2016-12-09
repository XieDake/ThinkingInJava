package Tree;
import org.omg.CORBA.Any;

import java.nio.BufferUnderflowException;
/**
 * Created by John on 2016/12/4.
 */
public class BinarySearchTree <AnyType extends Comparable<?super AnyType>>{
    //基本单元：树节点；
    private static class BinaryNode<AnyType> {
        AnyType data;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;

        //构造方法
        //区分叶节点和普通树节点
        //叶节点:直接给data即可！
        BinaryNode(AnyType data1) {
            this(data1, null, null);
        }

        //一般
        BinaryNode(AnyType data1, BinaryNode<AnyType> left1, BinaryNode<AnyType> right1) {
            this.data = data1;
            this.left = left1;
            this.right = right1;
        }
    }
    //定义数据成员！只需根节点即可！
    private BinaryNode<AnyType>root;
    //外类构造方法！根节点什么也没有！
    public BinarySearchTree(){
        root=null;
    }
    //定义基本方法！
    //clear！即root存在吗？
    public void clear(){
        root=null;
    }
    //isEmpty?
    public boolean isEmpty(){
        return (root==null);
    }
    //contains
    public boolean contains(AnyType X){
        return contains(X,root);//还用说吗。。。一定是从根节点开始寻找！
    }
    //findmin找到树中最小的，，data！,,,最后返回数据！
    public AnyType findMin(){
        //如果是空树则，，抛出异常！
        if(isEmpty()){throw new BufferUnderflowException();}
        return findMin(root).data;
    }
    //findMax，，最后返回数据！
    public AnyType findMax(){
        //空树，，抛出异常！
        if (isEmpty()){
            throw new BufferUnderflowException();
        }
        //
        return findMax(root).data;
    }
    //insert!插入结点！还是使用递归实现！
    //insert完咯！我认为是一颗新树，，，建立了一棵新树，，返回新树的引用！
    public void insert(AnyType X){
        root=insert(X,root);
    }
    //二叉查找树的三种遍历！
    //前序遍历：根左右；
    public void preOeder(){
        preOrder(root);
    }
    ////中序遍历：左根右；
    public void midOrder(){
        midOrder(root);
    }
    //后序遍历：左右根；
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
    //remove!使用递归实现！
    //remove也是改变了原来那一颗树的结构，，，我认为是返回的是一课新树！，，返回新树的根节点的引用！
    //remove-private，，，返回一颗新树，，，root！
    private BinaryNode<AnyType>remove(AnyType X, BinaryNode<AnyType>t){
        //没有找到！
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
        //找到咯！
        //分三种情况！
        if ((compareValue==0)){
            //有两个儿子！最后也是到一个儿子或者叶节点的情况！
            if ((t.right!=null)||(t.left!=null)){
                //找到当前结点右子树中最小的结点！并把这个节点的值给这个节点！
                t.data=findMin(t.right).data;
                //删除这个data所在的结点！
                t.right=remove(t.data,t.right);
            }//1:是一个叶节点！//有1个儿子！
            else{
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return t;//返回一棵新的树！
    }
    //insert-private
    private BinaryNode<AnyType> insert(AnyType X,BinaryNode<AnyType> t){
        //找到那一个位置咯！
        //由于二叉查找树的特殊的规则，，，使得新插入的树只能插在叶节点处，，否则找不到位置，
        if (t==null){
            return new BinaryNode<AnyType>(X,null,null);
        }
        //比较差找！
        int compareValue=X.compareTo(t.data);
        //X>t.data,,,right....
        if (compareValue>0){
            t.right=insert(X,t.right);
        }
        //X<t.data,,,,left
        if (compareValue<0){
            t.left=insert(X,t.left);
        }
        //Bingo!重复咯！
        // return null!
        if (compareValue==0){
            return null;
        }
        //最后返回的是根结点的引用！
        return t;
    }
    //findMin-private,找最小！
    //返回最小的所在的结点引用！还是使用递归!
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType>t){
       //没有找到！
        if (t==null){
            return null;
        }
        //找最小，，往左边,,左边最后一个叶节点！
        //找到那个叶节点咯！
        if (t.left==null) {
            return t;
        }//否则继续寻找！
        else{
            return findMin(t.left);
        }
    }
    //findMax-Private，，找到最大的。。。。！
    private BinaryNode<AnyType>findMax(BinaryNode<AnyType>t){
        if (t==null){
            return null;
        }
        //找到那个叶节点咯！
        if (t.right==null){
            return t;
        }else{
            return findMax(t.right);
        }
    }
    //contains-private:递归实现，判断结点有无此元素！
    private boolean contains(AnyType X,BinaryNode<AnyType> t){
        //最后判断，，，,,,没找到！！
        if (t==null){
            return false;
        }
        //查找！
        int compareValue=X.compareTo(t.data);
        //X>t.data，应该往t的右边寻找！
        if (compareValue>0){
            return contains(X,t.right);
        }
        //X<t.data，应该往t的左边找！
        if (compareValue<0){
            return contains(X,t.left);
        }
        //X=t.data,,找到了！！！
        return true;
    }
}
