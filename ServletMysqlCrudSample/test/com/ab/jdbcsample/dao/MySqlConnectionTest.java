package com.ab.jdbcsample.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class MySqlConnectionTest {

	@Test
	public void testGetConnection() throws SQLException {
		assertFalse(MySqlConnection.getConnection().isClosed());
	}

}
