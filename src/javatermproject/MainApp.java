package javatermproject;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javatermproject.view.CreateAccountController;
import javatermproject.view.LoginController;
import javatermproject.view.TermProjectMainController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The ATM");
        this.primaryStage.setResizable(false);

        initRootLayout();

        showMainOverview();
    }

    /**
     * 초기화
     */
    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 메인화면 초기화
     */
    public void showMainOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TermProjectMain.fxml"));
            AnchorPane mainOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(mainOverview);

            // controller
            TermProjectMainController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * TODO: Account를 매개변수로 안받아도 되게 짜기.
     * 필요할 때마다 리스너로 설정해놓고 값 읽어오게 만들기.
     */
    public void showCreateAccountDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/CreateAccountDialog.fxml"));
    		AnchorPane createAccountDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("계좌 생성");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(createAccountDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			CreateAccountController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			//controller.setAccount(account);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    /**
     * TODO:
     * @return
     */
    public void showLoginDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/LoginDialog.fxml"));
    		AnchorPane LoginDialog = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("로그인");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(LoginDialog);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			// Set the person into the controller.
			LoginController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

    	} catch (IOException e) {
			// TODO: handle exception
		}
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }
}