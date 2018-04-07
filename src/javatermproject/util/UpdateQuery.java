package javatermproject.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javatermproject.model.Account;

public class UpdateQuery {


	/**
	 * 입금 쿼리
	 * @param DepositValue
	 * 더 간단! 처음 로그인할 때의 값을 가지고 바로 처리!
	 *
	 * TODO: 정상수행이면 commit, 아니면 rollback하는 로직넣기
	 */
	public static void DepositQuery(String DepositValue) {

		DBConnect dbconn = DBConnect.getInstance();
		Account CurrentAccountInfo = Account.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;

		final String DEPOSIT =
				"UPDATE ATM "
			+	"SET MONEY = ? "
			+	"WHERE BALANCENUMBER = ?";

		// 입출금 수행
		try {
			int ToBeUpdatedMoney = Integer.parseInt(DepositValue) + CurrentAccountInfo.getMoney();

			conn = dbconn.restartConnection();

			pstmt = conn.prepareStatement(DEPOSIT);
			pstmt.setInt(1, ToBeUpdatedMoney);
			pstmt.setString(2, CurrentAccountInfo.getBalance());

			if(pstmt.executeUpdate() == 0) {
				System.err.println("0을 리턴하면 쿼리수행이 안됐다는 것!");
				throw new SQLException();
			}

			CurrentAccountInfo.setMoney(ToBeUpdatedMoney);

			pstmt.close();
			dbconn.endConnection();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * TODO: 쿼리문제만 해결하면 됨.
	 * 출금 쿼리
	 * @param WithdrawalValue
	 * 이것도 마찬가지. 처음 로그인할 때의 값을 가지고 바로 처리!
	 */
	public static void WithdrawalQuery(String WithdrawalValue) {

		DBConnect dbconn = DBConnect.getInstance();
		Account CurrentAccountInfo = Account.getInstance();

		Connection conn = null;
		PreparedStatement pstmt = null;

		final String WITHDRAWAL =
				"UPDATE ATM "
			+	"SET MONEY = ? "
			+	"WHERE BALANCENUMBER = ?";

		// 입출금 수행
		try {
			int ToBeUpdatedMoney = CurrentAccountInfo.getMoney() - Integer.parseInt(WithdrawalValue);

			if(ToBeUpdatedMoney < 0) {
				// TODO: 출금금액이 0 이하면 에러발생시키기!
				System.out.println("0이하!!!!");
			}

			conn = dbconn.restartConnection();

			pstmt = conn.prepareStatement(WITHDRAWAL);
			pstmt.setInt(1, ToBeUpdatedMoney);
			pstmt.setString(2, CurrentAccountInfo.getBalance());
			if(pstmt.executeUpdate() == 0) {
				System.err.println("0을 리턴하면 쿼리수행이 안됐다는 것!");
				throw new SQLException();
			}

			CurrentAccountInfo.setMoney(ToBeUpdatedMoney);

			pstmt.close();
			dbconn.endConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	/**
	 * 송금 쿼리
	 * 1) 보내는사람 돈빼고
	 * 2) 받는사람 돈넣어줌
	 * 출금금액이 0이하면 에러발생시키기!
	 * DepositQuery, WithdrawalQuery를 오버라이딩해서 각각 호출하기!
	 */
	public static boolean TransferMoneyQuery(String TransferAmount, String TransferToBalance) {

		DBConnect dbconn = DBConnect.getInstance();
		Account CurrentAccountInfo = Account.getInstance();

		int TransferToMoney = 0;


		final String TRANSFERFROM =
				"UPDATE ATM "
			+	"SET MONEY = ? "
			+	"WHERE BALANCENUMBER = ?";
		final String TRANSFERTO =
				"UPDATE ATM "
			+	"SET MONEY = ? "
			+	"WHERE BALANCENUMBER = ?";
		final String INQUIRY =
				"SELECT MONEY FROM ATM "
			+	"WHERE BALANCENUMBER = ?";

		// 받는사람 잔고 가져옴
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			conn = dbconn.restartConnection();
			pstmt = conn.prepareStatement(INQUIRY);
			pstmt.setString(1, TransferToBalance);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//값 넣기.
				TransferToMoney = rs.getInt(1);
			}

			pstmt.close();
			dbconn.endConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}


		// 보내는 사람 출금 수행
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;

			conn = dbconn.restartConnection();

			int ToBeUpdatedMoney = CurrentAccountInfo.getMoney() - Integer.parseInt(TransferAmount);

			if(ToBeUpdatedMoney < 0) {
				return false;
			}

			pstmt = conn.prepareStatement(TRANSFERFROM);
			pstmt.setInt(1, ToBeUpdatedMoney);
			pstmt.setString(2, CurrentAccountInfo.getBalance());
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException();
			}

			pstmt.close();
			dbconn.endConnection();

			CurrentAccountInfo.setMoney(ToBeUpdatedMoney);

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		// 받는사람 잔고에 돈 더해줌
		try {
			Connection conn = null;
			PreparedStatement pstmt = null;

			conn = dbconn.restartConnection();

			int ToBeUpdatedMoney = TransferToMoney + Integer.parseInt(TransferAmount);

			pstmt = conn.prepareStatement(TRANSFERFROM);
			pstmt.setInt(1, ToBeUpdatedMoney);
			pstmt.setString(2, TransferToBalance);
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException();
			}

			pstmt.close();
			dbconn.endConnection();

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
