package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.config.DataBaseConfig;
import com.parkit.parkingsystem.constants.DBConstants;

class DataBaseConfigTest {
	private DataBaseConfig dataBaseConfig;

	@BeforeEach
	public void setUp() {
		dataBaseConfig = new DataBaseConfig();
	}

	@DisplayName("Establishing connection")
	@Test

	public void getConnection() throws ClassNotFoundException, SQLException {

		Connection connection;

		connection = dataBaseConfig.getConnection();

		assertNotNull(connection);
	}

	@DisplayName("Closing connection")
	@Test
	public void closeConnection() throws ClassNotFoundException, SQLException {
		Connection connection = dataBaseConfig.getConnection();

		dataBaseConfig.closeConnection(connection);

		assertTrue(connection.isClosed());
	}

	@DisplayName("Closing prepared statement")
	@Test
	public void closePreparedStatement() throws ClassNotFoundException, SQLException {
		Connection connection = dataBaseConfig.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(DBConstants.GET_TICKET);

		dataBaseConfig.closePreparedStatement(preparedStatement);
		dataBaseConfig.closeConnection(connection);

		assertTrue(preparedStatement.isClosed());
	}

	@DisplayName("Closing resultatset")
	@Test
	public void closeResultSet() throws ClassNotFoundException, SQLException {
		String vehicleRegNumber = "ABCDEF";
		Connection connection = dataBaseConfig.getConnection();
		ResultSet resultSet;
		PreparedStatement preparedStatement = connection.prepareStatement(DBConstants.GET_TICKET);
		preparedStatement.setString(1, vehicleRegNumber);
		resultSet = preparedStatement.executeQuery();

		dataBaseConfig.closeResultSet(resultSet);

		assertTrue(resultSet.isClosed());
	}
}
