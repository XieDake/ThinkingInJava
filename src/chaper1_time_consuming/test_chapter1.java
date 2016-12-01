package chaper1_time_consuming;

public class test_chapter1 {
	public static void chapter_15(int [] a) {
		int count=0;
		for (int i = 0; i < a.length; i++) {
			if (a[i]==i) {
				System.out.println("finding: "+ i);
				count++;
			}
		}
	}
	
	

}
