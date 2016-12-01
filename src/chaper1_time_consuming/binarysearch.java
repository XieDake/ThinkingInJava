package chaper1_time_consuming;

//算法时间复杂度是O（N log N）的几个特殊的例子
//这里这几个算法应用的 前提是，假设数据早已输入，即不用考虑数据的输入的话费的时间
//第一个例子是：折半查找
public class binarysearch {
	//折半查找：给定一个整数X和一个排好序的整数序列A【i】（升序），找到X在这个序列中的位置
	public static int halfinding(int [] a,int x) {
		//initialize
		int low=0;
		int high=a.length-1;//应该是减一楼
		while (low<=high) {
			int mid=(low+high)/2;
			if (x<a[mid]) {
				high=mid-1;
			}
			if (x>a[mid]) {
				low=mid+1;
			}
			if (x==a[mid]) {
				return mid;
			}
		}
		//loop ending!
		//not finding!
		System.out.println("sorry! we do not find x in it! and we only give you '-1'!");
		return -1;
	}
	
}
//第二个例子是：欧几里得法计算最大公因式
//下面的例子是欧几里得法计算两个数最大公因式的方法
class Euclid_gcd {
	public static long euclid_gcd(long A,long B) {
		long gcd=0;
		while (B!=0) {
			gcd =A % B;
			A=B;
			B=gcd;
		}
		//最后"gcd"一定为0，取前面那个数！即最后A的结果！
		return A;
	}
}

//第三个例子是：幂运算：计算整数的幂运算
//这里使用的是递归的方法
//分奇偶数递归
class pow_calculate{
	public static boolean isevent(int n) {
		if (n%2==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int int_pow(int x,int N) {
		if (N==0) {
			return 1;
		}
		if (N==1) {
			return x;
		}
		if (isevent(N)) {
			return int_pow(x*x,N/2);
		}
		else {
			return int_pow(x*x, N/2)*x;
		}
	}	
}















