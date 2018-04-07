package javatermproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javatermproject.model.Account;
import javatermproject.util.MessageManager;
import javatermproject.util.SelectQuery;

public class InquiryController {

	private Stage dialogStage;
	private Account CurrentLoginAccount;

	@FXML PasswordField InquiryPWValue;

	@FXML Button ConfirmInquiryBtn;
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
     * 값 입력..
     * 비밀번호를 치면 해당 로그인한 계좌의 금액이 나오도록 한다.
     */
    @FXML
    private void handleInquiry() {
    	String OKMsg	= "";

    	CurrentLoginAccount = Account.getInstance();

    	if(isPasswordValid()) {
        	SelectQuery.InquiryQuery(CurrentLoginAccount.getBalance());	/* 조쿼리 수행 */
        	OKMsg += "조회";
        	MessageManager.QueryOKMsgCtrl(OKMsg);
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
     * 에러처리.
     * @return 범위내의 값이면 true, 아니면 false.
     */
//    private boolean isInputValid() {
//    	String ErrorMsg = "";
//
//    	if(InquiryPWValue.getText() == null
//    		|| InquiryPWValue.getText().length() == 0) {
//
//    		ErrorMsg += "올바른 값을 입력하세요.";
//    		MessageManager.ErrorMsgCtrl(ErrorMsg);
//    		return false;
//    	}
//
//    	if(InquiryPWValue.getText().length() > 24) {
//    		ErrorMsg += "적당히 좀 길게치세요";
//    		MessageManager.ErrorMsgCtrl(ErrorMsg);
//    		return false;
//    	}
//
//    	return true;
//    }


    private boolean isPasswordValid() {
    	String s1 = CurrentLoginAccount.getPassword();
    	String s2 = InquiryPWValue.getText();

    	String ErrorMsg = "";

    	if(s1.equals(s2)) {
    		return true;
    	} else {
    		ErrorMsg += "비밀번호를 정확히 입력하세요.";
    		MessageManager.ErrorMsgCtrl(ErrorMsg);
    		return false;
    	}
    }
}
