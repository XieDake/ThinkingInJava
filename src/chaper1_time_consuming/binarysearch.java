package chaper1_time_consuming;

//�㷨ʱ�临�Ӷ���O��N log N���ļ������������
//�����⼸���㷨Ӧ�õ� ǰ���ǣ����������������룬�����ÿ������ݵ�����Ļ��ѵ�ʱ��
//��һ�������ǣ��۰����
public class binarysearch {
	//�۰���ң�����һ������X��һ���ź������������A��i�������򣩣��ҵ�X����������е�λ��
	public static int halfinding(int [] a,int x) {
		//initialize
		int low=0;
		int high=a.length-1;//Ӧ���Ǽ�һ¥
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
//�ڶ��������ǣ�ŷ����÷����������ʽ
//�����������ŷ����÷����������������ʽ�ķ���
class Euclid_gcd {
	public static long euclid_gcd(long A,long B) {
		long gcd=0;
		while (B!=0) {
			gcd =A % B;
			A=B;
			B=gcd;
		}
		//���"gcd"һ��Ϊ0��ȡǰ���Ǹ����������A�Ľ����
		return A;
	}
}

//�����������ǣ������㣺����������������
//����ʹ�õ��ǵݹ�ķ���
//����ż���ݹ�
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















