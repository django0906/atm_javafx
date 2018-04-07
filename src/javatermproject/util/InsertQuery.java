package javatermproject.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertQuery {


	/**
	 * 계좌등록 쿼리
	 */
	public static boolean CreateAccountQuery(
			String name, String balance, String password, String initialMoney) {

		DBConnect dbconn = DBConnect.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;

		int QueryResult;

		final String CREATEACCOUNT =
				"INSERT INTO "
			+	"ATM (NAME, BALANCENUMBER, PASSWORD, MONEY) "
			+	"VALUES (?, ?, ?, ?)";

		try {
			conn = dbconn.getConn();

			pstmt = conn.prepareStatement(CREATEACCOUNT);

			pstmt.setString(1, name);
			pstmt.setString(2, balance);
			pstmt.setString(3, password);
			pstmt.setInt(4, Integer.parseInt(initialMoney));

			QueryResult = pstmt.executeUpdate();
			if(QueryResult != 0) {
				pstmt.close();
				dbconn.endConnection();
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
