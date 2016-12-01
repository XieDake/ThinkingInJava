package ThinkingInJavaChapter4Tests;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

//1����ӡ��1��100
class PrintValue{
	public void printout(){
		for (int i = 0; i <100; i++) {
			//��ӡ1~100
			System.out.println(i+1);
		}
	}
}
//2��
class RandNum{
	public void radNum(){
		Random rand=new Random(47);
		int ranum=rand.nextInt(100)+1;
		int temp=ranum;
		//�����1-100
		for (int i = 0; i <24; i++) {
			ranum=rand.nextInt(100)+1;
			//�����ࣺ
			if (temp>ranum) {
				System.out.println("class 1:��һ����������ڽ�����������������ֵ�ǣ�"+temp);
				temp=ranum;
			}
			else if (temp==ranum) {
				System.out.println("class 2:��һ����������ڽ�������������� ������ǣ�"+temp);
				temp=ranum;
			}
			else {
				System.out.println("class 3:��һ�������С�ڽ�������������� ������ǣ�"+temp);
				temp=ranum;
			}
		}
	}
}
//3:�������飺
class SuNumber{
	public void suNumberTest(){
		//���0~100��������
		int count=0;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j <=i; j++) {
				if ((i%j)==0) {
					count+=1;
					}
				}
			//����ѭ��ÿ����һ�ξ��ж�һ�Σ�
			if (count<3){
				System.out.println("This is a SuNumber! And the value is : "+i);
				}
			//���ü�����
			count=0;
			}
		}	
	}
//4:returnʹ��:
class ReturnUse{
	//Ĭ��
	public int begin=25;
	public int end=29;
	//���췽��
	//
	public void useReturn(int testValue){
		if ((begin<testValue)&&(testValue<end)) {
			System.out.println("the testValue is in the range of begin~end");
		}
		else {
			System.out.println("the testValue is not in the range of begin~end");
		}
	}
}
//5:��һ����Ƕ��forѭ��break������һ����
class ForForBreak{
	public void breakTest(){
		for (int i = 0; i <100; i++) {
			for (int j = 0; j < i; j++) {
				if (j==5) {
					System.out.println("break? "+"i=:"+i+": j=: "+j);
					break;
				}
				else {
					System.out.println("not break!");
				}
			}
		}
	}
	
}
//6��switch���ص�ʵ�飬û��break��������
class SwichTest{
	public void swichTest(){
		Random rand=new Random(47);
		int c='a'-1;
		for (int i = 0; i <30; i++) {
			//int c=rand.nextInt(26)+'a';
			//System.out.println((char)c+", "+": ");
			//ֻ���a�Ϳ��ԣ�
			c++;
			switch (c) {
			case 'a':
				System.out.println("has not break!see next is b or not??");
			case 'b':
				System.out.println("break test! is case b print???");
			case 'f':
				System.out.println("break test! print??");
			case 'g':
				System.out.println("break test! print??");
			default:
				System.out.println("end!");
				break;
			}
		}
	}
}
//Fibonacci���в�����
class Fibonacci{
	private int times;
	public Fibonacci(int times){
		this.times=times;
	}
	public void fibonacciCreating(){
		int sum=0;
		int sumLast2Time=1;
		System.out.println("out put the "+1+" th Fibonacci number is: "+sumLast2Time);
		int sumLast1Time=1;
		System.out.println("out put the "+2+" th Fibonacci number is: "+sumLast1Time);
		for (int i = 3; i < times+1; i++) {
			sum=sumLast1Time+sumLast2Time;
			System.out.println("out put the "+i+" th Fibonacci number is: "+sum);
			sumLast2Time=sumLast1Time;
			sumLast1Time=sum;
		}
	}
}
//Vimpare number:��4λ��Vimpare����
class VimpareNumber{
	public void vimpareNumber(){
		int count=0;
		ArrayList<Integer> a=new ArrayList<Integer>();
		//�������if�������Сѭ����������������Ͳ������ˣ�
		for (int i = 10; i <100; i++) {
			for (int j = 10; j <100; j++) {
				if ((1000<=i*j)&&(i*j<10000)) {
					if (!a.contains(i*j)) {
						a.add(i*j);
					}
				}
			}
		}
		//Ԫ��չʾ��foreach
		for (Integer item : a) {
			System.out.println("Vimpare number showing: "+item);
		}
		
	}
} 



public class ThinkingInJavaChapter4Tests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1
		PrintValue obj1=new PrintValue();
		obj1.printout();
		//2
		RandNum obj2=new RandNum();
		obj2.radNum();
		//3
	 	SuNumber obj3=new SuNumber();
	 	obj3.suNumberTest();
	 	//4
	 	ReturnUse obj4=new ReturnUse();
	 	obj4.useReturn(22);
	 	//5
	 	ForForBreak obj5=new ForForBreak();
	 	obj5.breakTest();
	 	//6
	 	SwichTest obj6=new SwichTest();
	 	obj6.swichTest();
	 	//7
	 	Fibonacci obj7=new Fibonacci(9);
	 	obj7.fibonacciCreating();
	 	//8
	 	VimpareNumber obj8=new VimpareNumber();
	 	obj8.vimpareNumber();
	 	//9
	 	VimpareNumber obj9=new VimpareNumber();
	 	obj9.vimpareNumber();
	 	//10
	 	
	 	
	 	
	}

}
