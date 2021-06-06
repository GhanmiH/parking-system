package com.parkit.parkingsystem.dao;

import com.parkit.parkingsystem.config.DataBaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.parkit.parkingsystem.constants.DBConstants;
import com.parkit.parkingsystem.model.Userrec;

public class UserDAO {

	private static final Logger logger = LogManager.getLogger("UserDAO");

	public static final DataBaseConfig dataBaseConfig = new DataBaseConfig();

	public static boolean saveUser(Userrec userrec) {
		Connection con = null;

		try {
			con = dataBaseConfig.getConnection();
			PreparedStatement ps = con.prepareStatement(DBConstants.SAVE_USERREC);

			ps.setString(1, userrec.getlicenPlaNumber());
			return ps.execute();

		} catch (Exception ex) {
			logger.error("Error fetching next available slot", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			return false;

		}
	}

	public static Userrec getUser(String licenPlaNumber) {
		Connection con = null;
		Userrec userrec = null;
		try {
			con = dataBaseConfig.getConnection();
			PreparedStatement ps = con.prepareStatement(DBConstants.GET_USERREC);
			ps.setString(1, licenPlaNumber);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				userrec = new Userrec();
				userrec.setlicenPlaNumber(licenPlaNumber);

			}

			dataBaseConfig.closeResultSet(rs);
			dataBaseConfig.closePreparedStatement(ps);

		} catch (Exception ex) {
			logger.error("Error fetching next available slot", ex);
		} finally {
			dataBaseConfig.closeConnection(con);
			return userrec;
		}
	}
}