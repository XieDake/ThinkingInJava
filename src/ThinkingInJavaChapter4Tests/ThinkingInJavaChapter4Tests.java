package ThinkingInJavaChapter4Tests;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;

//1：打印从1到100
class PrintValue{
	public void printout(){
		for (int i = 0; i <100; i++) {
			//打印1~100
			System.out.println(i+1);
		}
	}
}
//2：
class RandNum{
	public void radNum(){
		Random rand=new Random(47);
		int ranum=rand.nextInt(100)+1;
		int temp=ranum;
		//随机数1-100
		for (int i = 0; i <24; i++) {
			ranum=rand.nextInt(100)+1;
			//分三类：
			if (temp>ranum) {
				System.out.println("class 1:第一个随机数大于紧跟其后的随机数，这个值是："+temp);
				temp=ranum;
			}
			else if (temp==ranum) {
				System.out.println("class 2:第一个随机数等于紧随其后的随机数： 这个数是："+temp);
				temp=ranum;
			}
			else {
				System.out.println("class 3:第一个随机数小于紧随其后的随机数： 这个数是："+temp);
				temp=ranum;
			}
		}
	}
}
//3:素数检验：
class SuNumber{
	public void suNumberTest(){
		//输出0~100的素数：
		int count=0;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j <=i; j++) {
				if ((i%j)==0) {
					count+=1;
					}
				}
			//最内循环每结束一次就判断一次：
			if (count<3){
				System.out.println("This is a SuNumber! And the value is : "+i);
				}
			//重置计数：
			count=0;
			}
		}	
	}
//4:return使用:
class ReturnUse{
	//默认
	public int begin=25;
	public int end=29;
	//构造方法
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
//5:看一哈，嵌套for循环break跳出哪一个？
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
//6：switch开关的实验，没有break会怎样？
class SwichTest{
	public void swichTest(){
		Random rand=new Random(47);
		int c='a'-1;
		for (int i = 0; i <30; i++) {
			//int c=rand.nextInt(26)+'a';
			//System.out.println((char)c+", "+": ");
			//只检测a就可以！
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
//Fibonacci序列产生！
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
//Vimpare number:找4位的Vimpare数：
class VimpareNumber{
	public void vimpareNumber(){
		int count=0;
		ArrayList<Integer> a=new ArrayList<Integer>();
		//可以添加if语句来减小循环遍历次数，这里就不考虑了！
		for (int i = 10; i <100; i++) {
			for (int j = 10; j <100; j++) {
				if ((1000<=i*j)&&(i*j<10000)) {
					if (!a.contains(i*j)) {
						a.add(i*j);
					}
				}
			}
		}
		//元素展示：foreach
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
