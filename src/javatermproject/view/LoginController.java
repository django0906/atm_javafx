package javatermproject.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javatermproject.MainApp;
import javatermproject.model.Account;
import javatermproject.util.SelectQuery;

public class LoginController {

	private Stage dialogStage;

	@FXML TextField LoginNameField;
	@FXML TextField LoginBalanceField;

	@FXML Button loginBtn;
	@FXML Button cancelBtn;

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
     * 로그인 세션은 Account 정보를 써서 거기 저장해놓는다.
     */
    @FXML
    private void LoginButtonClicked() {

    	System.out.println("login btn test");
    	if(isValidAccountInfo()) {
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("로그인 성공");
			alert.setHeaderText("로그인 성공.");
			alert.setContentText("환영합니다.");

			alert.showAndWait();
			dialogStage.close();

			// 현재 로그인한 정보를 저장해둠.
			// 싱글턴 패턴 사용!
			CurrentLoginAccount = Account.getInstance();

			showAccountDetailDialog();

    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("로그인 실패");
			alert.setHeaderText("로그인 실패.");
			alert.setContentText("ID 혹은 계좌번호를 확인해 주세요.");

			alert.showAndWait();
			dialogStage.close();
    	}
    }


    @FXML
    private void CancelButtonClicked() {
    	dialogStage.close();
    }


    /**
     * SQLite에 접근, ID와 계좌번호가 제대로 된 것인지 확인한다.
     * 여기서 실제 로그인 쿼리를 수행!
     * @return select해서 결과가 안오면 false 리턴, 되면 true 리턴.
     */
    private boolean isValidAccountInfo() {

    	if(SelectQuery.ConfirmID(
    			LoginNameField.getText(),
    			LoginBalanceField.getText())) {
    		return true;// ID, 계좌번호 정보있음
    	}

    	return false;	// ID, 계좌번호 정보없음
    }


    /**
     * 로그인하면 뜨는 창
     */
    public void showAccountDetailDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/AccountDetailDialog.fxml"));
    		AnchorPane LoginDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("계좌 상세정보");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(LoginDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			AccountDetailController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }

    /**
     * 입금관련 다이얼로그
     */
    public void showDepositDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/DepositDialog.fxml"));
    		AnchorPane DepositDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("입금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);
			Scene scene = new Scene(DepositDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			DepositController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }


    /**
     * 출금관련 다이얼로그
     */
    public void showWithdrawalDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/WithdrawalDialog.fxml"));
    		AnchorPane WithdrawalDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("출금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(WithdrawalDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			WithdrawalController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }


    /**
     * 송금관련 다이얼로그
     */
    public void showTransferDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/TransferDialog.fxml"));
    		AnchorPane TransferDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("송금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(TransferDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			TransferController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }


    /**
     * 금액조회 관련 다이얼로그
     */
    public void showInquiryDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/InquiryDialog.fxml"));
    		AnchorPane InquiryDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("조회");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(InquiryDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			InquiryController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }
}
