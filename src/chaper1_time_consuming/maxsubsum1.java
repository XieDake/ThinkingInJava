package chaper1_time_consuming;

public class maxsubsum1{
	//this class only has 4 methods
	//first algorithm
	// 1
	public static int max1(int [] a) {
		//初始化
		int maxsum=0;
		int tempsum=0;
		for (int i = 0; i < a.length; i++) {//i is the start point
			for (int j = i; j < a.length; j++) {//j is the end point
				for (int k = i;  k<=j; k++) {
					tempsum+=a[k];
					
					if (maxsum<=tempsum) {
						maxsum=tempsum;
					}
				}
			    tempsum=0;
			}
		}
		return maxsum;
	}
	
	//the second method
	// 2:
	//changing according to method 1:
	public static int max2(int [] a) {
		//initialize
		int maxsum=0;
		int tempsum=0;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length; j++) {
				tempsum+=a[j];
				
				if (maxsum<=tempsum) {
					maxsum=tempsum;
				}
			}
			tempsum=0;
			}
		return maxsum;
	}
	//the third method
	//3:
	//devide_and_conqure: using the iteration.
	//首先定义一个比较函数
	public static int getmax(int aa, int bb,int cc) {
		int maxvalue=0;
		//a,b,c comparing and returning the maximum
		if ((aa>bb)&&(aa>cc)) {
			maxvalue=aa;
		}
		if((aa>bb)&&(aa<cc)){
			maxvalue=cc;
		}
		if ((aa<bb)&&(bb>cc)) {
			maxvalue=bb;
		}
		if ((aa<bb)&&(bb<cc)) {
			maxvalue=cc;
		}
		return maxvalue;
	}
	//third method
	public static int max3(int [] a,int left,int right) {
		//initialize
		int mediumsumvalue=0;//bingo!
		int leftmaxsum=0;
		int leftmax=0;//bingo!
		int templeftsum=0;
		int rightmaxsum=0;
		int rightmax=0;//bingo!
		int temptrightsum=0;
		int center=(left+right)/2;
		//case 0
		if (left==right) {
			if (right>0) {
				return left;
			}
			else {
				return 0;
			}
		}
		//case 1:left_maxsum
		leftmax=max3(a,left,center);
		//case 2: right_maxsum
		rightmax=max3(a,center+1,right);
		//case 3: medium
		//left
		for (int i1 = center; i1>=left; i1--) {
			templeftsum+=a[i1];
			if (leftmaxsum<=templeftsum) {
				leftmaxsum=templeftsum;
			}
		}
		//right
		for (int i2 = center+1; i2 <=right; i2++) {
			temptrightsum+=a[i2];
			if (rightmaxsum<=temptrightsum) {
				rightmaxsum=temptrightsum;
			}
		}
		mediumsumvalue=leftmaxsum+rightmaxsum;
		//then we get three max_sum
		return getmax(leftmax, rightmax, mediumsumvalue);
	}
	//the 4th method,,smart but not easy understand
	public static int max4(int [] a) {
		//initialize
		int temptmaxsum=0;
		int maxsum=0;
		for (int i = 0; i < a.length; i++) {
			temptmaxsum+=a[i];
			if (maxsum<=temptmaxsum) {
				maxsum=temptmaxsum;
			}
			if (temptmaxsum<0) {
				maxsum=0;
			}
		}
		return maxsum;
	}
	
	
}
