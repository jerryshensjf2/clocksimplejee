package test.javaforever.clocksimplejee4.test;

import test.javaforever.clocksimplejee4.servicetest.ClockRecordServiceTest;
import test.javaforever.clocksimplejee4.test.PrivilegeDaoImplTest;
import test.javaforever.clocksimplejee4.test.UserDaoImplTest;

import com.javaforever.clocksimplejee4.database.DBConf;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for default package");
		if (!DBConf.isTestsuiteOffline()){			
			//$JUnit-BEGIN$
			suite.addTestSuite(PrivilegeDaoImplTest.class);
			suite.addTestSuite(UserDaoImplTest.class);
			suite.addTestSuite(BonusDaoImplTest.class);
			suite.addTestSuite(ClockRecordDaoImplTest.class);
			suite.addTestSuite(FineDaoImplTest.class);
			suite.addTestSuite(LeaveDaoImplTest.class);
			suite.addTestSuite(LeaveLeftDaoImplTest.class);
			suite.addTestSuite(LeaveLimitDaoImplTest.class);
			suite.addTestSuite(LeaveTypeDaoImplTest.class);
			suite.addTestSuite(EmployeeTypeDaoImplTest.class);
			suite.addTestSuite(ClockRecordServiceTest.class);
		} 
		//$JUnit-END$
		return suite;
	}
	
	public static void main(String[] args){
		junit.textui.TestRunner.run(suite());
	}
}
