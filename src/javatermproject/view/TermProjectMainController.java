package javatermproject.view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javatermproject.MainApp;

public class TermProjectMainController {

    @FXML Button createAccountButton;
    @FXML Button LoginButton;
    @FXML Button CloseButton;

    private MainApp mainApp;

    public TermProjectMainController() {
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }


    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    }


    /**
     * 계좌 등록
     */
    @FXML
    private void handleCreateAccount() {
    	//TODO: 다이얼로그 뜨게 만들기
    	mainApp.showCreateAccountDialog();
    }


    /**
     * 로그인
     */
    @FXML
    private void handleLogin() {
    	//TODO: 로그인버튼 누르면 DB에서 값 가져오게 짜기
    	mainApp.showLoginDialog();
    }


    /**
     * 프로그램 종료
     */
    @FXML
    private void handleClose() {
    	Platform.exit();
    	System.exit(0);
    }
}