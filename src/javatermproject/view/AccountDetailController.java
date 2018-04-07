package javatermproject.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javatermproject.MainApp;
import javatermproject.model.Account;

public class AccountDetailController {

	private Stage dialogStage;
	private Account CurrentAccountInfo = Account.getInstance();

	@FXML Label	LoggedInUserName;

	@FXML Button DepositBtn;
	@FXML Button WithdrawalBtn;
	@FXML Button TransferBtn;
	@FXML Button InquiryBtn;
	@FXML Button LogoutBtn;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		LoggedInUserName.setText(CurrentAccountInfo.getName());
	}


	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	/**
	 * 입금 버튼
	 * 버튼 누르는 컨트롤러하고 연결해줘야됨.
	 */
	@FXML
	private void handleDepositBtn() {
		showDepositDialog();
	}


	/**
	 * 출금 버튼
	 */
	@FXML
	private void handleWithdrawalBtn() {
		showWithdrawalDialog();
	}


	/**
	 * 송금 버튼
	 */
	@FXML
	private void handleTransferBtn() {
		showTransferDialog();
	}


	/**
	 * 조회 버튼
	 */
	@FXML
	private void handleInquiryBtn() {
		showInquiryDialog();
	}


	/**
	 * 로그아웃 버튼
	 */
	@FXML
	private void handleLogoutBtn() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Logout");
		alert.setHeaderText("로그아웃 성공.");
		alert.setContentText("성공적으로 로그아웃 하였습니다.");

		alert.showAndWait();

		dialogStage.close();
	}


    /**
     * 입금 다이얼로그
     */
    public void showDepositDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/DepositDialog.fxml"));
    		AnchorPane DepositDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("입금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);	// 출금, 송금에 이게 없더니 안돌아갔음.
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
     * 출금 다이얼로그
     */
    public void showWithdrawalDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/WithdrawalDialog.fxml"));
    		AnchorPane WithdrawalDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("출금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);
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
    		e.printStackTrace();
		}
    }


    /**
     * 송금 다이얼로그
     */
    public void showTransferDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/TransferDialog.fxml"));
    		AnchorPane TransferDialog = (AnchorPane) loader.load(); // 여기서 터짐

			Stage dialogStage = new Stage();
			dialogStage.setTitle("송금");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(this.dialogStage);
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
    		e.printStackTrace();
		}
    }


    /**
     * 조회 다이얼로그
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
