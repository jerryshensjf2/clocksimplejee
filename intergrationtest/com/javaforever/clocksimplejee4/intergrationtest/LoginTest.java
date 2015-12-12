package com.javaforever.clocksimplejee4.intergrationtest;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.SubmitButton;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class LoginTest extends TestCase {
	public void testLogin() {
		try {
			long now = System.currentTimeMillis();
			for (int i = 0; i < 100; i++){
				WebConversation wc2 = new WebConversation();

	
				WebResponse   resp = wc2.getResponse( "http://localhost:8080/ClockSimpleJEE3/login/index.jsp" );

				WebForm webForm = resp.getForms()[0];
				SubmitButton submitButton1 = webForm.getSubmitButton("login");
				WebRequest req1 = webForm.getRequest();
				webForm.setParameter("empid","160208");
				webForm.setParameter("password","jerry");
				resp = webForm.submit(submitButton1);
				
				Assert.assertTrue(resp.getText().contains("Welcome to the Clock Online System."));
			}
			long now2 = System.currentTimeMillis();
			System.out.println("In miliseconds:"+ (now2-now));
//			throw new Exception();
		} catch (Exception e) {
			System.err.println("Exception: " + e);
			Assert.assertTrue("Test failed.", false);
		}
	}
}
