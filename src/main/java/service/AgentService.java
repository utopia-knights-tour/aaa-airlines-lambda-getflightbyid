package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.FlightDao;
import entity.Flight;
import util.ConnectUtil;

public class AgentService {

	public Flight getFlightById(Long flightId) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = ConnectUtil.getInstance().getConnection();
			return new FlightDao(connection).get(flightId);
		} catch (SQLException e) {
			throw e;
		}
	}
}
