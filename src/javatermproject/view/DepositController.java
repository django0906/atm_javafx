	package javatermproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javatermproject.model.Account;
import javatermproject.util.MessageManager;
import javatermproject.util.UpdateQuery;

public class DepositController {

	private Stage dialogStage;
	private Account CurrentLoginAccount;

	@FXML TextField DepositValue;

	@FXML Button ConfirmDepositBtn;
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
     */
    @FXML
    private void handleConfirmDeposit() {
    	String ErrorMsg	= "";
    	String OKMsg	= "";

    	CurrentLoginAccount = Account.getInstance();

    	if(isInputValid()) {
    		UpdateQuery.DepositQuery(DepositValue.getText());	/* 입금쿼리 수행 */
    		OKMsg += "입금";
    		MessageManager.QueryOKMsgCtrl(OKMsg);
        	dialogStage.close();
    	} else {
    		ErrorMsg += "입금";
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
     * 에러값 처리
     * @return 범위내의 값이면 true, 아니면 false.
     */
    private boolean isInputValid() {
    	String ErrorMsg = "";

    	try {
	    	if(DepositValue.getText() == null
	    		|| DepositValue.getText().length() == 0) {

	    		ErrorMsg += "올바른 값을 입력하세요.";
	    		MessageManager.ErrorMsgCtrl(ErrorMsg);
	    		return false;
	    	}

	    	// 에러 일으켜서 잡음.
	    	int test = Integer.parseInt(DepositValue.getText());

    	} catch (NumberFormatException e) {
    		//e.printStackTrace();
			ErrorMsg += "범위 내의 숫자를 입력하세요.";
			MessageManager.ErrorMsgCtrl(ErrorMsg);
			return false;
    	}

    	return true;
    }
}
