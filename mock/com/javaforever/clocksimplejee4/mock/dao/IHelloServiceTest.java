package com.javaforever.clocksimplejee4.mock.dao;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class IHelloServiceTest extends TestCase {
	     private Mockery context = new JUnit4Mockery();
	     private IHelloService helloService;
	 
	     /**
	      * @throws java.lang.Exception
	      */
	     @Before
	     public void setUp() throws Exception {
	         // set up
	         helloService = context.mock(IHelloService.class);
	     }
	 
	     /**
	      * Test method for
	      * {@link org.hook.jmock.firstcase.HelloServiceImpl#sayHelloToSomebody(java.lang.String)}.
	      */
	     @Test
	     public void testSayHelloToSomebody() {
	         final String message = "HELLO,alex!";
	         final String name = "alex";
	         // expectations
	         context.checking(new Expectations() {
	             {
	                 one(helloService).sayHelloToSomebody(name);
	                 will(returnValue(message));
	             }
	         });
	         // execute
	         String result = helloService.sayHelloToSomebody(name);
	         // verify
	         context.assertIsSatisfied();
	         assertSame(result, message);
	     }
	 } 