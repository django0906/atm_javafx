package javatermproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javatermproject.model.Account;
import javatermproject.util.MessageManager;
import javatermproject.util.UpdateQuery;

public class TransferController {

	private Stage dialogStage;

	@FXML TextField 		TransferAmount;
	@FXML TextField 		TransferToBalance;
	@FXML PasswordField 	TransferFromBalancePW;

	@FXML Button	ConfirmTransferBtn;
	@FXML Button	CancelBtn;

	private Account CurrentLoginAccount;

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
     */
    @FXML
    private void handleTransfer() {
    	String ErrorMsg = "";
    	String OKMsg	= "";

    	CurrentLoginAccount = Account.getInstance();

    	if(isInputValid() && isPasswordValid()) {
    		/* 송금쿼리 수행*/
    		boolean successTransfer = UpdateQuery.TransferMoneyQuery(
    				TransferAmount.getText(), TransferToBalance.getText());

    		if(successTransfer) {
	    		OKMsg += "송금";
	    		MessageManager.QueryOKMsgCtrl(OKMsg);
	    		dialogStage.close();
    		} else {
        		ErrorMsg += "송금";
        		MessageManager.QueryErrorMsgCtrl(ErrorMsg);
    			dialogStage.close();
    		}

    	} else {
    		// 실패 이유 설명하고 다이얼로그 종료
    		ErrorMsg += "송금";
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


    /**
     * 제대로된 숫자입력?
     * @return
     */
    private boolean isInputValid() {
    	String ErrorMsg = "";

    	try {
        	if(TransferAmount.getText() == null
        		|| TransferAmount.getText().length() == 0) {

        		ErrorMsg += "올바른 값을 입력하세요.";
        		MessageManager.ErrorMsgCtrl(ErrorMsg);
        		return false;
        	}

	    	int test = Integer.parseInt(TransferAmount.getText());

		} catch (NumberFormatException e) {
			//e.printStackTrace();
			ErrorMsg += "정수를 입력하세요.";
			MessageManager.ErrorMsgCtrl(ErrorMsg);
			return false;
		}

    	return true; // 오류가 없으면 여기올것.
    }


    /**
     * 비밀번호 점
     * @return
     */
    private boolean isPasswordValid() {
    	String s1 = CurrentLoginAccount.getPassword();
    	String s2 = TransferFromBalancePW.getText();

    	if(s1.equals(s2)) {
    		return true;
    	} else {
    		String ErrorMsg = "비밀번호를 정확히 입력하세요.";
    		MessageManager.ErrorMsgCtrl(ErrorMsg);
    		return false;
    	}
    }
}
