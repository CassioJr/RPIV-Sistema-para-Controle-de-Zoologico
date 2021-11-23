package application;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	private static Stage stage;
	private static Scene sc;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		AnchorPane root = FXMLLoader.load(getClass().getResource("/view/telaApp.fxml"));
	    sc = new Scene(root);
		stage.getIcons().add(new Image("file:img/zooIcone.png"));
		stage.setScene(sc);
		stage.setTitle("Sistema Zoologico");
		stage.setResizable(false);

		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}
