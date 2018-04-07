package javatermproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import javatermproject.model.Account;
import javatermproject.util.InsertQuery;

public class CreateAccountController {

	private Stage dialogStage;

	@FXML Button ConfirmBtn;
	@FXML Button CloseBtn;

	@FXML TextField nameField;			/* 이름 */
	@FXML TextField balanceField;		/* 계좌번호 */
	@FXML PasswordField pwField;		/* 비밀번호 */
	@FXML TextField initialMoneyField;	/* 초기금액 */

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


    public void setAccount(Account account) {
    }


    /**
     * 계좌생성 ok?
     */
    @FXML
    private void handleConfirm() {

    	// 입력값 검증 없음!!
    	if(InsertQuery.CreateAccountQuery(
    			nameField.getText().toString(),
    			balanceField.getText().toString(),
    			pwField.getText().toString(),
    			initialMoneyField.getText().toString())) {
    		// 성공
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("회원가입 성공");
			alert.setHeaderText("회원가입 성공.");
			alert.setContentText("환영합니다.");

			alert.showAndWait();
			dialogStage.close();
    	} else {
    		// 실패
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("회원가입 실패");
			alert.setHeaderText("회원가입 실패.");
			alert.setContentText("관리자에게 문의해 주세요.");

			alert.showAndWait();
			dialogStage.close();
    	}
    }


    /**
     * 화면 종료
     */
    @FXML
    private void handleClose() {
    	dialogStage.close();
    }
}