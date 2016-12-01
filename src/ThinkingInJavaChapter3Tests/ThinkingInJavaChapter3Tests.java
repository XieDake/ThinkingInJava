package ThinkingInJavaChapter3Tests;

import java.util.*;
//test1:展示别名机制
class AnotherName{
	float item;
}
//tests2:对象传递到方法中，出现别名问题
class MethodAnotherName{
	float x=3;
}
//test3:java算数运算符：练习
class SpeedCalculate{
	private float distance;
	private float time;
	//initial
	public SpeedCalculate(Float distance,Float time) {
		// TODO Auto-generated constructor stub
		this.distance=distance;
		this.time=time;
	}
	//calculate the speed!
	public float Speed() {
		float speed;
		if (time==0) {
			speed=-1.0f;
		}
		else {
			speed=distance/time;
		}
		return speed;
	}
}
//test4&5:==&equals()比较
class Dog{
	String name;
	String says;
	//initialize
	public Dog(String name,String says) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.says=says;
	}
}
//test6:coin throwing
class ThrowingCoin{
	Random rand= new Random(34);
	private float x=rand.nextFloat()*100+1;
	public double sigmoid() {
		return 1/(1+Math.exp(x));
	}
	public void throwCoins() {
		double result;
		result=sigmoid();
		if (result>0.5) {
			System.out.println("throws one coin,the result is: positive!");
		}
		if (result<=0.5) {
			System.out.println("throws one coin,the result is: negative!");
		}
	}
	
}
//test8:float和double指数表示法所能表示的最大的和最小的数
class MaxMinFloatAndDouble{
	private float fmin=Float.MIN_VALUE;
	private float fmax=Float.MAX_VALUE;
	private double dmin=Double.MIN_VALUE;
	private double dmax=Double.MAX_VALUE;
	public void minAndMaxOutput() {
		System.out.println("float min_value=:"+fmin);
		System.out.println("float min_value=:"+fmax);
		System.out.println("double min_value=:"+dmin);
		System.out.println("double min_value=:"+dmax);
	}
}

public class ThinkingInJavaChapter3Tests {
	public static void f(MethodAnotherName y){
		y.x=6; //Boom
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnotherName a=new AnotherName();
		AnotherName b=new AnotherName();
		a.item=1;
		b.item=2;
		System.out.println("a.item=:"+a.item+"b.item=:"+b.item);
		a.item=b.item;
		System.out.println("a.item=:"+a.item+"b.item=:"+b.item);
		a=b;
		System.out.println("a.item=:"+a.item+"b.item=:"+b.item);
		a.item=100;
		System.out.println("a.item=:"+a.item+"b.item=:"+b.item);
		//
		MethodAnotherName c=new MethodAnotherName();
		System.out.println("1):x="+c.x);
		f(c);
		System.out.println("2):x="+c.x);
		//
		SpeedCalculate v=new SpeedCalculate(100.4f, 10.2f);
		float speed=v.Speed();
		System.out.println("the speed is: "+speed);
		//
		Dog dog1=new Dog("spot", "Ruff!");
		Dog dog2=new Dog("scruffy", "Wurf");
		System.out.println(dog1==dog2);
		Dog dog3=new Dog("scruffy", "Wurf");
		System.out.println("test:"+dog2.equals(dog3));
		//
		ThrowingCoin coin=new ThrowingCoin();
		coin.throwCoins();
		//test7:16/8进制计数：
		long n1=0x18f;
		long n2=023;
		//二进制形式输出展示：
		System.out.println("n1=:"+Long.toBinaryString(n1));
		System.out.println("n1=:"+Long.toBinaryString(n2));
		//
		MaxMinFloatAndDouble minMax=new MaxMinFloatAndDouble();
		minMax.minAndMaxOutput();
		//
		Random rand=new Random(47);
		int n11=rand.nextInt();
		int n12=n11;
		System.out.println(Integer.toBinaryString(n11));
		System.out.println(Integer.toBinaryString(n12));
		System.out.println(Integer.toBinaryString(n11>>5));
		System.out.println(Integer.toBinaryString(n11>>=5));
		//string 比较
		String q1="i am working for a better life!";
		String q2="and by the way beating you forever!!BAT!taopangzi!";
		System.out.println(q1);
		System.out.println(q2);
		System.out.println(q1==q2);
		System.out.println(q1.equals(q2));
		q2=q1;
		System.out.println(q1==q2);
		System.out.println(q1.equals(q2));
		System.out.println(q1);
		System.out.println(q2);
	}

}
