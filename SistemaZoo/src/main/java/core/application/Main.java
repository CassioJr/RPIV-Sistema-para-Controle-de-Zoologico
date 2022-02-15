package core.application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
	private static Stage stage;
	private static Scene sc;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("/view/View_Login.fxml"));
	    sc = new Scene(root);
		stage.setScene(sc);
		stage.getIcons().add(new Image("file:img/zooIcone.png"));
		stage.setTitle("Sistema Zoologico");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
		
	}
}
