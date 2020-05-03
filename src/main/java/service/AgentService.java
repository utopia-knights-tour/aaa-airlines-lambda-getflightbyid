package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FlightDao;
import datasource.HikariCPDataSource;
import entity.Flight;

public class AgentService {

	public Flight getFlightById(Long flightId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			return new FlightDao(connection).get(flightId);
		} catch (SQLException e) {
			throw e;
		} finally {
			connection.close();
		}
	}
}
