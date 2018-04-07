package javatermproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javatermproject.model.Account;
import javatermproject.util.MessageManager;
import javatermproject.util.UpdateQuery;

public class WithdrawalController {

	private Stage dialogStage;
	private Account CurrentLoginAccount;

	@FXML TextField WithdrawalAmount;
	@FXML PasswordField UserPassword;

	@FXML Button ConfirmWithdrawalBtn;
	@FXML Button CancelBtn;

	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
	}


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    /**
     * 값 입력
     * TODO: 예외처리 해라!
     */
    @FXML
    private void handleWithdrawalDeposit() {
    	String ErrorMsg	= "";
    	String OKMsg	= "";

    	CurrentLoginAccount = Account.getInstance();

    	// 입력값이 올바르지 않으면 그냥 안되는걸로...
    	if(isInputValid() && isPasswordValid()) {
    		UpdateQuery.WithdrawalQuery(WithdrawalAmount.getText()); /* 출금쿼리 수행 */

    		OKMsg += "출금";
    		MessageManager.QueryOKMsgCtrl(OKMsg);
        	dialogStage.close();
    	} else {
    		// 실패 이유 설명하고 다이얼로그 종료
    		ErrorMsg += "출금";
    		MessageManager.QueryErrorMsgCtrl(ErrorMsg);
    		dialogStage.close();
    	}
    }


    /**
     * 화면종료
     */
    @FXML
    private void handleClose() {
    	dialogStage.close();
    }


    private boolean isInputValid() {
    	String ErrorMsg = "";

    	try {
    		if(WithdrawalAmount.getText() == null
	    		|| WithdrawalAmount.getText().length() == 0) {

	    		ErrorMsg += "올바른 값을 입력하세요.";
	    		MessageManager.ErrorMsgCtrl(ErrorMsg);
    		}
    	} catch (NumberFormatException e) {
			e.printStackTrace();
			ErrorMsg += "정수를 입력하세요.";
			MessageManager.ErrorMsgCtrl(ErrorMsg);
			return false;
		}

    	return true;
    }

    private boolean isPasswordValid() {
    	String s1 = CurrentLoginAccount.getPassword();
    	String s2 = UserPassword.getText();

    	if(s1.equals(s2)) {
    		return true;
    	} else {
    		String ErrorMsg = "비밀번호를 정확히 입력하세요.";
    		MessageManager.ErrorMsgCtrl(ErrorMsg);
    		return false;
    	}
    }
}
