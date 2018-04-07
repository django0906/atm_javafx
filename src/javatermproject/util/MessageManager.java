package javatermproject.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javatermproject.model.Account;

public class MessageManager {

	private static Account CurrentLoginAccount = Account.getInstance();

    public static void ErrorMsgCtrl(String ErrorMsg) {
		// 오류 메시지를 보여준다.
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("오류");
        alert.setHeaderText("올바르지 않은 값이 있습니다.");
        alert.setContentText(ErrorMsg);

        alert.showAndWait();
    }

    public static void QueryErrorMsgCtrl(String ErrorMsg) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(ErrorMsg + " 실패");
		alert.setHeaderText(ErrorMsg + "에 실패하였습니다.");
		alert.setContentText("올바른 값을 다시 입력하시거나 관리자에게 문의해 주세요.");

		alert.showAndWait();
    }

    public static void QueryOKMsgCtrl(String OKMsg) {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(OKMsg + " 금액");
    	alert.setHeaderText(OKMsg + "하신 금액은 다음과 같습니다.");
    	alert.setContentText("현재 금액: "+CurrentLoginAccount.getMoney());

    	alert.showAndWait();
    }
}