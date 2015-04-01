package de.hhn.se.labsw.gdt.client.gui;

import de.hhn.se.labsw.gdt.client.controller.Controller;
import de.hhn.se.labsw.gdt.client.util.Storage;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.*;

public class Login extends Application{

	private Scene scene;
	private GridPane gridPane;
	private HBox hBox;
	private Text title;
	private Label userName;
	private TextField userNameTextField;
	private Button button;
	
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		
		hBox = new HBox(10);
		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		scene = new Scene(gridPane, 300, 200);
		title = new Text("Login");
		userName = new Label("User Name");
		userNameTextField = new TextField();
		
		button = new Button("Sign in");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				Storage.user.setName(userNameTextField.getText());
				primaryStage.close();
				new MainMenu();
				
			}
		});
		
		hBox.getChildren().add(button);
		
		gridPane.add(title, 0, 0, 2, 1);
		gridPane.add(userName, 0, 1);
		gridPane.add(userNameTextField, 1, 1);
		gridPane.add(hBox, 1, 3);
		
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	
	
	
	
	
}
