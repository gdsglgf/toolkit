package com.howtodoinjava.demo.aspect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test.xml" })
public class AspectTest {
	
	@Test
	public void testSchemaBeforeAdvice() {
		System.out.println("========testSchemaBeforeAdvice=======");
		helloworldService.sayBefore("before");
		System.out.println("======================================\n");
	}

	@Test
	public void testSchemaAfterReturningAdvice() {
		System.out.println("======testSchemaAfterReturningAdvice=======");
		helloworldService.sayAfterReturning();
		System.out.println("======================================\n");
	}

	@Test(expected = RuntimeException.class)
	public void testSchemaAfterThrowingAdvice() {
		System.out.println("=======testSchemaAfterThrowingAdvice========");
		helloworldService.sayAfterThrowing();
		System.out.println("======================================\n");
	}

	@Test(expected = RuntimeException.class)
	public void testSchemaAfterFinallyAdvice() {
		System.out.println("======testSchemaAfterFinallyAdvice=======");
		helloworldService.sayAfterFinally();
		System.out.println("======================================\n");
	}

	@Test
	public void testSchemaAroundAdvice() {
		System.out.println("=======testSchemaAroundAdvice=========");
		helloworldService.sayAround("haha");
		System.out.println("======================================\n");
	}

	@Test
	public void testSchemaIntroduction() {
		System.out.println("=======testSchemaIntroduction=========");
		introductionService.induct();
		System.out.println("======================================\n");
	}

	@Test
	public void testSchemaAdvisor() {
		System.out.println("========testSchemaAdvisor=========");
		helloworldService.sayAdvisorBefore("haha");
		System.out.println("======================================\n");
	}

	@Autowired
	IHelloWorldService helloworldService;

	@Autowired
	IIntroductionService introductionService;

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-test.xml");
		System.out.println("======================================");
		IHelloWorldService helloworldService = ctx.getBean("helloWorldService", IHelloWorldService.class);
		helloworldService.sayBefore("before");
		System.out.println("======================================");
		((ClassPathXmlApplicationContext)ctx).close();
	}
}
