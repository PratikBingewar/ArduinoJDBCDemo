package com.java.dao.impl;

import java.sql.Connection;

import com.java.dao.DAO;
import com.java.db.conn.ConnectionManager;

public class DAOImpl implements DAO {

	@Override
	public boolean setconnection() {
		Connection con = ConnectionManager.getConnection();
		if(con != null) {
			return true;
		}
		return false;
	}

	@Override
	public void AddData(String ds, String as) {

		boolean stts = ConnectionManager.getADDStatement(ds, as);
		if(!stts) {
			System.out.println("last entry is rejected from databse...");
			System.exit(0);
		}
		
	}

}
