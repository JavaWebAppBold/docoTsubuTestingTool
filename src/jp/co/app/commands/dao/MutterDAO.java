package jp.co.app.commands.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.app.results.dao.Mutter;

public class MutterDAO {
	private final String JDBC_URL = "jdbc:h2:~/docoTsubu";
	private final String DB_USER = "SA";
	private final String DB_PASS = "";

	public List<Mutter> findAll() {
		List<Mutter> mutterList = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TEXT FROM MUTTER ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				Mutter mutter = new Mutter(id, userName, text);
				mutterList.add(mutter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mutterList;
	}

	public Mutter find(String user, String comment) {

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TEXT FROM MUTTER WHERE NAME = ? AND TEXT = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, user);
			pStmt.setString(2, comment);

			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("ID");
			String userName = rs.getString("NAME");
			String text = rs.getString("TEXT");
			return new Mutter(id, userName, text);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean remove(Mutter mutter) {
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM MUTTER WHERE ID = ? AND NAME = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, mutter.getId());
			pStmt.setString(2, mutter.getUserName());
			int result = pStmt.executeUpdate();

			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
