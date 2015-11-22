package com.ab.jdbcsample.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookDAOTest.class, MySqlConnectionTest.class })
public class AllTests {

}
