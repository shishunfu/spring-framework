package tech.ssf.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tech.ssf.spring.service.HelloService;

public class HelloTest {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("tech.ssf.spring.service");
		HelloService helloService = context.getBean(HelloService.class);
		helloService.say();
	}
}
