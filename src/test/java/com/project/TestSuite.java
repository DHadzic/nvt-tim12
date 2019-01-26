package com.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.project.controller.LineControllerTest;
import com.project.controller.UserControllerTest;
import com.project.service.LineServiceTest;
import com.project.service.LineServiceTestInt;
import com.project.service.UserServiceTest;
import com.project.service.UserServiceTestInt;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LineControllerTest.class,
	UserControllerTest.class,
	LineServiceTest.class,
	LineServiceTestInt.class,
	UserServiceTest.class,
	UserServiceTestInt.class
})
public class TestSuite {

}
