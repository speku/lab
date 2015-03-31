package de.hhn.se.labsw.gdt.client.gui;

import de.hhn.se.labsw.gdt.client.util.Storage;
import de.hhn.se.labsw.gdt.client.util.Utility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainMenu extends Stage{


	private Scene scene;
//	private Scene listScene;
	private GridPane gridPane;
	private VBox vBoxBrowser;
	private VBox vBoxInspect;
	private Text titleBrowser;
	private Text titleGame;
//	private Label userName;
//	private TextField userNameTextField;
	private Button buttonJoin;
	private Button buttonCreate;
	private Button buttonRefresh;
	
	private ListView<String> listBrowser;
	private ListView<String> listInspect;
	
	
	ObservableList<String> listData;
	
	public MainMenu() {
		
		
		
		
		gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(25, 25, 25, 25));
		
		listBrowser = new ListView<>();
		listInspect = new ListView<>();
		
		vBoxBrowser = new VBox();
		vBoxInspect = new VBox();
//		hBox.setAlignment(Pos.BOTTOM_RIGHT);
		vBoxBrowser.getChildren().add(listBrowser);
		vBoxInspect.getChildren().add(listInspect);
		
		vBoxBrowser.setVgrow(listBrowser, Priority.ALWAYS);
		vBoxInspect.setVgrow(listBrowser, Priority.ALWAYS);
		
		listBrowser.setItems(Utility.getBrowserData());
		listInspect.setItems(Utility.getGameData());
		
		listBrowser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		    	String selectedGame = listBrowser.getSelectionModel().getSelectedItem();
		        Utility.setupGame(selectedGame, true);
		        listInspect.setItems(Utility.getGameData());
		        forceListRefreshOn(listBrowser);
				forceListRefreshOn(listInspect);
				System.out.println("listener responded");
		    }
		});
		
		listInspect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        //tableViewPlayer = Utility.getPlayerData(newValue);;
		    }
		});
		
		
		
		scene = new Scene(gridPane, 600, 600);
//		listScene = new Scene(vBox, 300, 100);
		titleBrowser = new Text("games");
		titleGame = new Text("");
//		userName = new Label("User Name");
//		userNameTextField = new TextField();
		
		buttonJoin = new Button("join");
		buttonJoin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				String gameId = listBrowser.getSelectionModel().getSelectedItem();
				Utility.setupGame(gameId, true);
				listInspect.setItems(Utility.getGameData());
//				new GameMenu();
//				MainMenu.this.hide();
				forceListRefreshOn(listBrowser);
				forceListRefreshOn(listInspect);
			}
		});
		
		buttonCreate = new Button("create");
		buttonJoin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				Utility.setupGame(Storage.user.getName(), false);
				listInspect.setItems(Utility.getGameData());
//				new GameMenu();
//				MainMenu.this.hide();
				forceListRefreshOn(listBrowser);
				forceListRefreshOn(listInspect);
				
			}
		});
		
		buttonRefresh = new Button("refresh");
		buttonJoin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				listBrowser.setItems(Utility.getBrowserData());
				listInspect.setItems(Utility.getGameData());
				forceListRefreshOn(listBrowser);
				forceListRefreshOn(listInspect);
				
			}
		});
		
		
		gridPane.add(titleBrowser, 0, 0, 3, 1);
		gridPane.add(titleGame, 3, 0, 3, 1);
		gridPane.add(vBoxBrowser, 0, 1, 3, 5);
		gridPane.add(vBoxInspect, 3, 1, 3, 5);
		gridPane.add(buttonJoin, 0, 7);
		gridPane.add(buttonCreate, 1, 7);
		gridPane.add(buttonRefresh, 2, 7);
		
		this.setTitle("Welcome " + Storage.user.getName());
		this.setScene(scene);
		this.show();
		
	}
	
	private <T> void forceListRefreshOn(ListView<T> lsv) {
	    ObservableList<T> items = lsv.<T>getItems();
	    lsv.<T>setItems(null);
	    lsv.<T>setItems(items);
	}
}
