package javatermproject.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javatermproject.model.Account;


public class SelectQuery {

	/**
	 * ID 확인 쿼리.
	 * 만약 계좌정보가 있으면 값을 바로 저장해버린다.
	 * @param 계좌번호
	 * @return 계좌정보가 있는지 없는지에 대한 t/f 리턴
	 */
	public static boolean ConfirmID(
			String Name, String Balance) {

		DBConnect dbconn = DBConnect.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final String CONFIRMID =
				"SELECT * FROM ATM "
			+	"WHERE NAME = ? AND "
			+	"BALANCENUMBER = ?";

		try {
			conn = dbconn.restartConnection();

			Account CurrentAccountInfo = Account.getInstance();

			pstmt = conn.prepareStatement(CONFIRMID);
			pstmt.setString(1, Name);
			pstmt.setString(2, Balance);
			rs = pstmt.executeQuery();
			if(rs.isClosed())	// 읽어온게 없으면?
				return false;

			// 읽어온게 있으면 전역적으로 값을 넣는다.
			while(rs.next()) {
				CurrentAccountInfo.setName(rs.getString(1));
				CurrentAccountInfo.setBalance(rs.getString(2));
				CurrentAccountInfo.setPassword(rs.getString(3));
				CurrentAccountInfo.setMoney(rs.getInt(4));
			}

			pstmt.close();
			dbconn.endConnection();

			return true;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 금액조회 쿼리
	 * 상대방 금액 조회할 때 요긴하게 쓰일 것 같다!
	 */
	public static void InquiryQuery(String balanceNo) {

		DBConnect dbconn = DBConnect.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		final String INQUIRY =
				"SELECT MONEY FROM ATM "
			+	"WHERE BALANCENUMBER = ?";

		try {
			conn = dbconn.restartConnection();

			Account CurrentAccountInfo = Account.getInstance();

			pstmt = conn.prepareStatement(INQUIRY);
			pstmt.setString(1, CurrentAccountInfo.getBalance());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//값 넣기.

			}

			pstmt.close();
			dbconn.endConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
