package de.hhn.se.labsw.gdt.client.gui;



import de.hhn.se.labsw.gdt.client.controller.Controller;
import de.hhn.se.labsw.gdt.client.util.Connector;
import de.hhn.se.labsw.gdt.client.util.Storage;
import de.hhn.se.labsw.gdt.client.util.Utility;
import de.hhn.se.labsw.gdt.library.Player;
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
import javafx.scene.paint.Color;
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
	private Button buttonLeave;
	
	private Button buttonEdit;
	private Button buttonAccept;
	
	
	private ListView<String> listBrowser;
	private ListView<String> listInspect;
	
	
	ObservableList<String> listData;
	

	private InspectPlayerBox playerBox;
	
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
		playerBox = new InspectPlayerBox(); 
		
		
		
		

		vBoxBrowser.getChildren().add(listBrowser);
		vBoxInspect.getChildren().add(listInspect);
		
		vBoxBrowser.setVgrow(listBrowser, Priority.ALWAYS);
		vBoxInspect.setVgrow(listBrowser, Priority.ALWAYS);
		
		listBrowser.setItems(Utility.getBrowserData());
		listInspect.setItems(Utility.getGameData());
		
		
		listBrowser.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        Utility.setupGame(newValue, true);
		        listInspect.setItems(Utility.getGameData());
		        ObservableList<String> items = listInspect.getItems();
		        playerBox.displayPlayerData(items.get(0));
		    }
		});
		
		listInspect.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        playerBox.displayPlayerData(newValue);
		    }
		});
		
		
		
		scene = new Scene(gridPane, 600, 600);
		titleBrowser = new Text("games");
		titleGame = new Text("");

	
		
		buttonCreate = new Button("create");
		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				Utility.setupGame(Storage.user.getName(), false);
				listInspect.setItems(Utility.getGameData());
//				new GameMenu();
//				MainMenu.this.hide();
//				forceListRefreshOn(listBrowser);
//				forceListRefreshOn(listInspect);
				
			}
		});
		
//		buttonRefresh = new Button("refresh");
//		buttonRefresh.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent evt){
//				listBrowser.setItems(Utility.getBrowserData());
//				listInspect.setItems(Utility.getGameData());
////				forceListRefreshOn(listBrowser);
////				forceListRefreshOn(listInspect);
//				
//			}
//		});
		
		buttonLeave = new Button("leave");
		buttonLeave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				
				// remove user/player from Game
				Storage.activeGame.removePlayer(Storage.playerOfUser);
				// update the Server storage
				Connector.postGame(Storage.activeGame.getHost().getName(), Storage.activeGame);
				// update list data
				 Utility.setupGame(Storage.activeGame.getHost().getName(), true);
			     listInspect.setItems(Utility.getGameData());
			
				// delete active game
				Storage.activeGame = null;
				// delete user's player
				Storage.playerOfUser = null;
				// change button state
				buttonJoin.setDisable(false);
				buttonCreate.setDisable(false);
				buttonAccept.setDisable(true);
				buttonLeave.setDisable(true);
				buttonEdit.setDisable(true);
				
				// update list
				String currentlySelectedGame = listBrowser.getSelectionModel().getSelectedItem();
				Utility.setupGame(currentlySelectedGame, true);
				listInspect.setItems(Utility.getGameData());
				ObservableList<String> items = listInspect.getItems();
		        playerBox.displayPlayerData(items.get(0));
				
				playerBox.changeEditable(false);
				
				// enable game Browser interaction
				listBrowser.setMouseTransparent( false );
				listBrowser.setFocusTraversable( true );
				
				// enable inspectList interaction
				listInspect.setMouseTransparent( false );
				listInspect.setFocusTraversable( true );
			}
		});
		
		buttonJoin = new Button("join");
		buttonJoin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				
				// disable game Browser interaction
				listBrowser.setMouseTransparent( true );
				listBrowser.setFocusTraversable( false );
				
				// disable inspectList interaction
				listInspect.setMouseTransparent( true );
				listInspect.setFocusTraversable( false );
				
				
//				listBrowser.setItems(Utility.getBrowserData());
//				// refresh player list
//				listInspect.setItems(Utility.getGameData());
				// create new player in inspected game and associate it with the user on the local client

				Storage.playerOfUser = new Player(Storage.user.getName());
				
				
				// Storage.inspectedGame.addPlayer(Storage.playerOfUser);
				// create active game
				Storage.activeGame = Utility.cloneGameState(Storage.inspectedGame);
				
				Storage.activeGame.addPlayer(Storage.playerOfUser);
				// post the new game and overwrite
				Connector.postGame(Storage.activeGame.getHost().getName(), Storage.activeGame);
				// update list
				Storage.inspectedGame = Utility.cloneGameState(Storage.activeGame);
				
				listInspect.setItems(Utility.getGameData());
				// select the newly created player
				listInspect.getSelectionModel().select(Storage.user.getName());
				playerBox.displayPlayerData(Storage.playerOfUser.getName());
				
				// set attributes editable
				playerBox.changeEditable(true);
				
				// enable accept button
				buttonAccept.setDisable(false);
				
				// disable creation of new games
				buttonCreate.setDisable(true);
				
				// enable leave
				buttonLeave.setDisable(false);
				
				// disable join button
				buttonJoin.setDisable(true);
				
				
				
				
				
			}
		});
		
		buttonEdit = new Button("edit");
		buttonEdit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				
				// select active Game and user's player from browsers
				listBrowser.getSelectionModel().select(Storage.activeGame.getHost().getName());
				listInspect.getSelectionModel().select(Storage.playerOfUser.getName());
				
				// disable game Browser interaction
				listBrowser.setMouseTransparent( true );
				listBrowser.setFocusTraversable( false );
				
				// disable inspectList interaction
				listInspect.setMouseTransparent( true );
				listInspect.setFocusTraversable( false );
				
				// set attributes editable
				playerBox.changeEditable(true);
				
				// enable accept button
				buttonAccept.setDisable(false);
				
				// disable creation of new games
				buttonCreate.setDisable(true);
				
				// disable join button
				buttonJoin.setDisable(true);
				
				// disable edit button
				buttonEdit.setDisable(true);
				
				// highlight game in browser that shall be edited
				listBrowser.getSelectionModel().select(Storage.activeGame.getHost().getName());
			    // refer to selection
				String currentlySelectedGame = listBrowser.getSelectionModel().getSelectedItem();
				// setup as inspected game
				Utility.setupGame(currentlySelectedGame, true);
				// retrieve appropriate player data
				listInspect.setItems(Utility.getGameData());
				// select user's player
		        playerBox.displayPlayerData(Storage.playerOfUser.getName());
				
				
			}
		});
		
		
		
		buttonAccept = new Button("accept");
		buttonAccept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evt){
				
//				// if edit button has been pressed and a playerOfUser already exists, edit this player
//				if (Storage.playerOfUser != null) {
//					
//					// remove old player from active game
//					Storage.activeGame.removePlayer(Storage.playerOfUser);
//					
//					// update data
//					Storage.playerOfUser.setName(playerBox.getPlayerNameVal().getText());
//					Storage.playerOfUser.setAge(Integer.parseInt(playerBox.getPlayerAgeVal().getText()));
//					Storage.playerOfUser.setColor((Color)playerBox.getPlayerColorVal().getSelectionModel().getSelectedItem());
//					
//					// add edited player to activeGame
//					Storage.activeGame.addPlayer(Storage.playerOfUser);
//					
//					// update inspected game with active game
//					Storage.inspectedGame = Utility.cloneGameState(Storage.activeGame);
//					
//				} else {
				
				// update newly created player
//					for (Player p : Storage.inspectedGame.getPlayers()) {
//						if (p.getName().equals(Storage.user.getName())) {
//							p.setName(playerBox.getPlayerNameVal().getText());
//							p.setAge(Integer.parseInt(playerBox.getPlayerAgeVal().getText()));
//							p.setColor((Color)playerBox.getPlayerColorVal().getSelectionModel().getSelectedItem());
//							// set playerOfUser to updated player
//							Storage.playerOfUser = p;
//						}
//					}
					
				// remove old player from active game
				Storage.activeGame.removePlayer(Storage.playerOfUser);
				
				Storage.playerOfUser.setName(playerBox.getPlayerNameVal().getText());
				Storage.playerOfUser.setAge(Integer.parseInt(playerBox.getPlayerAgeVal().getText()));
				Storage.playerOfUser.setColor(playerBox.getPlayerColorVal().getSelectionModel().getSelectedItem());
				

				// add new player
				Storage.activeGame.addPlayer(Storage.playerOfUser);
				
//				// update activeGame with inspectedGame data
//				Storage.activeGame = Utility.cloneGameState(Storage.inspectedGame);
					
				
				// post new game data to server
				Connector.postGame(Storage.activeGame.getHost().getName(),Storage.activeGame);
					
				Storage.inspectedGame = Storage.activeGame;
				// update list
				listInspect.setItems(Utility.getGameData());
				// select the newly created player
//				listInspect.getSelectionModel().select(Storage.user.getName());
				listInspect.getSelectionModel().select(Storage.playerOfUser.getName());
				// set attributes editable
				playerBox.changeEditable(false);
				
				// disable new and create Button so that a player cannot join multiple games at the same time
				buttonJoin.setDisable(true);
				buttonCreate.setDisable(true);
				
				// but player data should be editable
				buttonEdit.setDisable(false);
				
				// disable accept
				buttonAccept.setDisable(true);
				
				// enable game Browser interaction
				listBrowser.setMouseTransparent( false );
				listBrowser.setFocusTraversable( true );
				
				// enable inspectList interaction
				listInspect.setMouseTransparent( false );
				listInspect.setFocusTraversable( true );
				
				// if browser is empty then no join button
				if (Utility.getBrowserData().isEmpty()) {
					buttonJoin.setDisable(true);
				}
				
			}
		});
		
		
		
		
		gridPane.add(titleBrowser, 0, 0, 3, 1);
		gridPane.add(titleGame, 3, 0, 3, 1);
		gridPane.add(vBoxBrowser, 0, 1, 3, 5);
		gridPane.add(vBoxInspect, 3, 1, 4, 2);
		gridPane.add(playerBox, 3, 4, 4, 3);
		gridPane.add(buttonJoin, 0, 7);
		gridPane.add(buttonCreate, 1, 7);
//		gridPane.add(buttonRefresh, 2, 7);
		gridPane.add(buttonLeave, 2, 7);
		gridPane.add(buttonEdit, 4, 7);
		gridPane.add(buttonAccept, 5, 7);
		
		buttonLeave.setDisable(true);
		buttonAccept.setDisable(true);
		buttonEdit.setDisable(true);
		
		this.setTitle("Welcome " + Storage.user.getName());
		this.setScene(scene);
		this.show();
		
		// hand reference of this object to Controller
		Controller.setMainMenu(this);
	}
	
	
	public void refreshBrowser() {
		// safe current game and player selection
		String selectedGame = listBrowser.getSelectionModel().getSelectedItem();
		String selectedPlayer = listInspect.getSelectionModel().getSelectedItem();
		
		// refresh browser
		listBrowser.setItems(Utility.getBrowserData());

		// restore last selected game 
		listBrowser.getSelectionModel().select(selectedGame);
		// rebuild inspected game
		Utility.setupGame(selectedGame, true);
		// setup game details according to selected game 
		listInspect.setItems(Utility.getGameData());
		// select the last selected player
		listInspect.getSelectionModel().select(selectedPlayer);
		// update the player data 
		playerBox.displayPlayerData(selectedPlayer);
		
	        
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private <T> void forceListRefreshOn(ListView<T> lsv) {
	    ObservableList<T> items = lsv.<T>getItems();
	    lsv.<T>setItems(null);
	    lsv.<T>setItems(items);
	}
}
