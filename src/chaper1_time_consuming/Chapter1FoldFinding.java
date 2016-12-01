package chaper1_time_consuming;

class Chapter1FoldFinding {
	public static int foldFind(int[] a,int x) {
		int low=0;
		int high=a.length-1;
		int mid=(low+high)/2;
		while (low<=high) {
			if (a[mid]<x) {
				low=mid+1;
			}
			else if (a[mid]>x) {
				high=mid-1;
			}
			else {
				return mid;
			}
		}
		//���û��������������û�ҵ�������һ������
		return -1;
	}

}
