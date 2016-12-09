package Tree;
import java.nio.BufferOverflowException;

/**
 * Created by John on 2016/12/6.
 */
public class AVLTTREE<AnyType extends Comparable<?super AnyType>> {
    //基本结构
    //私有嵌套类：AvlNode
    private static class AvlNode<AnyType>{
        //数据成员：
        private AnyType data;
        private AvlNode<AnyType> left;
        private AvlNode<AnyType>right;
        //与二叉查找树不同的是这里还需要体现节点的高度！
        private int height;
        //构造方法！两个版本！
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
    //数据成员：只有根节点root！
    private AvlNode<AnyType> root;
    //构造方法：
    public AVLTTREE(){
        root=null;
    }
    //基本方法！
    //clear AvlTree!
    public void clear(){
        root=null;
    }
    //isEmpty()?
    public boolean isEmpty(){
        return (root==null);
    }
    //就不是写contains（）了！下面是一样的！
    //寻找值为：data的结点并返回会节点！
    //public-search()
    public AvlNode<AnyType>search(AnyType data){
        if (root==null){
            //root=null!就抛出异常吧！
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
    //返回节点的height
    //public-height,只是为了获取树的高度！
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
    //AVL树的三种遍历：
    //前序遍历：根左右；
    public void preOrder(){
        preOrder(root);
    }
    ////中序遍历：左根右；
    public void midOrder(){
        midOrder(root);
    }
    //后序遍历：左右根；
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
            return t;//树为空树或者是没找到！
        }
        //finding！
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=remove(X,t.right);
        }else if(compareValue<0){
            t.left=remove(X,t.left);
        }else {
            //找到了！
            //判断儿子个数！
            //两个
            if ((t.left!=null)&&(t.right!=null)){
                     t.data=findMin(t.right).data;
                     t.right=remove(t.data,t.right);//递归删除t.data。。。
            }else {//一个儿子！
                t=(t.left!=null)?t.left:t.right;
            }
        }
        return balance(t);
    }
    //private-insert
    private AvlNode<AnyType>insert(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //空树或者是叶节点？
            return new AvlNode<AnyType>(X);
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            t.right=insert(X,t.right);
        }else if(compareValue<0){
            t.left=insert(X,t.left);
        }else {
            //没有找到！
        }
        return balance(t);
    }
    //private-balance
    //balance的形参是结点，，返回是这个结点的平衡后的结果！
    //balance依赖单转——两种情况：注意形参是问题结点k2，返回新的根节点k1，，注意k2与k1原来的关系：
    //左左
    private AvlNode<AnyType>rotateOnceLeftChild(AvlNode<AnyType>k2){
        //k2是原来出问题的父节点，k2的左儿子是k1
        AvlNode<AnyType>k1=k2.left;
        //单转的结果是k1变成k2的父亲，k2变成k1的右儿子！
        //注意循序：
        k2.left=k1.right;//先把k1的right给k2的left（k2.left一定是没用的!）
        k1.right=k2;//再把k2变成k1的右二子！
        //修正height！
        k1.height=Math.max(k1.left.height,k1.right.height)+1;
        k2.height=Math.max(k2.left.height,k2.right.height)+1;
        //一定是返回的是新的根！k1；
        return k1;
    }
    //右右---原理同左左：
    private AvlNode<AnyType>rotateOnceRight(AvlNode<AnyType>k2){
        AvlNode<AnyType>k1=k2.right;
        k2.right=k1.left;
        k1.left=k2;
        k1.height=Math.max(k1.left.height,k1.right.height)+1;
        k2.height=Math.max(k2.left.height,k2.right.height)+1;
        return k1;
    }
    //balance还依赖双旋转，两种情况；注意涉及三个结点：k3，k2，k1，，
    //注意双旋转=两次单旋转！注意单旋转的k1与k2；
    //左右：K3的左儿子k1的右子树k2
    //按顺序执行两次单旋转：先右；后左！
    private AvlNode<AnyType>rotateTwiceLeftRight(AvlNode<AnyType>k3){
        k3.left=rotateOnceRight(k3.left);//先右
        return (rotateOnceLeftChild(k3));//后左，，最后返回新树根！
    }
    //右左:k3的右儿子k1的左子树k2；
    // 执行两次单转：先左后右：
    private AvlNode<AnyType>rotateTwiceRightLeft(AvlNode<AnyType>k3){
        k3.right=rotateOnceLeftChild(k3.right);
        return (rotateTwiceLeftRight(k3));
    }
    //定义一个静态常量表示平衡状态的极限情况！
    private static final int ALLOWED_IMBALANCE=1;//高度差只能差一！
    //balance!
    private AvlNode<AnyType>balance(AvlNode<AnyType>t){
        if (t==null){
            return t;
        }//好吧！没搞懂t==null的作用！
        //左子树中出现问题！左边高度大于右边2；
        if (height(t.left)-height(t.right)>ALLOWED_IMBALANCE){
            //左左？
            if (height(t.left.left)>=height(t.left.right)){//自己领悟为啥是>=咯！
                t=rotateOnceLeftChild(t);
            }
            //左右？
            else{
                t=rotateTwiceLeftRight(t);
            }
        }
        //右子树中出现问题！右边高度大于左边2；
        if(height(t.right)-height(t.left)>ALLOWED_IMBALANCE){
            //右右？
            if (height(t.right.right)>=height(t.right.left)){
                t=rotateOnceRight(t);
            }
            //右左？
            else{
                t=rotateTwiceRightLeft(t);
            }
        }
        //转完咯！调整高度！
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
            //t为空，或者没找到！
            return t;
        }
        //finding!
        //判断t结点！
        //t不是。
        // 判断t.left
        if (t.left!=null){
            return findMin(t.left);
        }else {
            //t是！
            return t;
        }
    }
    //private-search()
    private AvlNode<AnyType>search(AnyType X,AvlNode<AnyType>t){
        if (t==null){
            //没找到或者是空树！
            return t;
        }
        //finding!
        int compareValue=X.compareTo(t.data);
        if (compareValue>0){
            //right,,,
            //判断t的右结点！
            return search(X,t.right);
        }else if (compareValue<0){
            //left,判断t的左节点！
            return search(X,t.left);
        }else {
            //找到了！t就是！
            return t;
        }
    }
}
