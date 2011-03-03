package nephilim.study.spring.ch06.m04;

public class HelloTarget implements Hello{
	
	public String sayHello(String name) {
		return "Hello, " + name;
	}
}
