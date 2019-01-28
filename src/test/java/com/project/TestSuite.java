package com.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.project.constants.VehicleConstants;
import com.project.controller.LineControllerTest;
import com.project.controller.PricelistControllerTest;
import com.project.controller.UserControllerTest;
import com.project.controller.VehicleControllerTest;
import com.project.service.LineServiceTest;
import com.project.service.LineServiceTestInt;
import com.project.service.PricelistServiceIntTest;
import com.project.service.PricelistServiceTest;
import com.project.service.TicketServiceIntTest;
import com.project.service.TicketServiceTest;
import com.project.service.UserServiceTest;
import com.project.service.UserServiceTestInt;
import com.project.service.VehicleServiceTest;
import com.project.service.VehicleServiceTestInt;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LineControllerTest.class,
	UserControllerTest.class,
	LineServiceTest.class,
	LineServiceTestInt.class,
	UserServiceTest.class,
	UserServiceTestInt.class,
	VehicleServiceTest.class,
	VehicleServiceTestInt.class,
	VehicleControllerTest.class,
	PricelistControllerTest.class,
	PricelistServiceTest.class,
	PricelistServiceIntTest.class,
	TicketServiceTest.class,
	TicketServiceIntTest.class,
})
public class TestSuite {

}
