package de.hhn.se.labsw.gdt.client.gui;


import java.util.ArrayList;

import de.hhn.se.labsw.gdt.client.util.Storage;
import de.hhn.se.labsw.gdt.library.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Callback;

public class InspectPlayerBox extends VBox {

	private Label playerColor;
	private Label playerName;
	private Label playerAge;
	private Label playerScore;
	private Label playerAp;
	
	private ComboBox playerColorVal;
	private TextField playerNameVal;
	private TextField playerAgeVal;
	private Label playerScoreVal;
	private Label playerApVal;
	
	private GridPane gridPane;
	
	public InspectPlayerBox() {
		
		playerColor = new Label("color:");
		playerName = new Label("name:");
		playerAge = new Label("age:");
		playerScore = new Label("score:");
		playerAp = new Label("AP:");
		
		
		playerNameVal = new TextField();
		playerAgeVal = new TextField();
		playerScoreVal = new Label();
		playerApVal = new Label();
		
		
		ObservableList<String> data = FXCollections.observableArrayList(
	            "chocolate", "salmon", "gold", "coral", "darkorchid",
	            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
	            "blueviolet", "brown");
		
		playerColorVal = new ComboBox<String>();
		playerColorVal.setItems(data);
		
		Callback<ListView<String>, ListCell<String>> factory = new Callback<ListView<String>, ListCell<String>>() {
	        @Override
	        public ListCell<String> call(ListView<String> list) {
	            return new ColorRectCell();
	        }
	    };
	    
	    playerColorVal.setCellFactory(factory);
	    playerColorVal.setButtonCell(factory.call(null));
	    
	    playerColorVal.setEditable(false);
	   
		
		gridPane = new GridPane();
		gridPane.setHgap(30);
		gridPane.setVgap(10);
		
		ColumnConstraints col1 = new ColumnConstraints();
	     col1.setPercentWidth(25);
	     ColumnConstraints col2 = new ColumnConstraints();
	     col2.setPercentWidth(75);
	     gridPane.getColumnConstraints().addAll(col1,col2);
		
		gridPane.add(playerName, 0, 0);
		gridPane.add(playerAge, 0, 1);
		gridPane.add(playerColor, 0, 2);
		gridPane.add(playerScore, 0, 3);
		gridPane.add(playerAp, 0, 4);
		
		gridPane.add(playerNameVal, 1, 0);
		gridPane.add(playerAgeVal, 1, 1);
		gridPane.add(playerColorVal, 1, 2);
		gridPane.add(playerScoreVal, 1, 3);
		gridPane.add(playerApVal, 1, 4);
		
		
		
		
		playerColorVal.setEditable(false);
		playerNameVal.setEditable(false);
		playerAgeVal.setEditable(false);
		
		playerColorVal.setMouseTransparent( true );
		playerColorVal.setFocusTraversable( false );
		playerNameVal.setMouseTransparent( true );
		playerNameVal.setFocusTraversable( false );
		playerAgeVal.setMouseTransparent( true );
		playerAgeVal.setFocusTraversable( false );
		
		this.getChildren().add(gridPane);
	}
	
	public void changeEditable(boolean val) {
		//playerColorVal.setEditable(val);
		playerNameVal.setEditable(val);
		playerAgeVal.setEditable(val);
		
		playerColorVal.setMouseTransparent(!val);
		playerColorVal.setFocusTraversable(val);
		playerNameVal.setMouseTransparent(!val);
		playerNameVal.setFocusTraversable(val);
		playerAgeVal.setMouseTransparent(!val);
		playerAgeVal.setFocusTraversable(val);
	}
	
	public void displayPlayerData(String name) {
		
		if (Storage.inspectedGame != null && Storage.inspectedGame.getPlayers().size() > 0) {
			for (Player p : Storage.inspectedGame.getPlayers()) {
				if (p.getName().equals(name)) {
					playerNameVal.setText(p.getName());
					playerColorVal.getSelectionModel().select(p.getColor());
					playerAgeVal.setText(p.getAge() + "");;
					playerScoreVal.setText(p.getScore() + "");
					playerApVal.setText(p.getAp() + "");
				}
			}
		}
			
	}
	
	public void clear() {
		playerNameVal.setText("");
		playerColorVal.getSelectionModel().select("");
		playerAgeVal.setText("");;
		playerScoreVal.setText("");
		playerApVal.setText("");
	}

	public Label getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Label playerColor) {
		this.playerColor = playerColor;
	}

	public Label getPlayerName() {
		return playerName;
	}

	public void setPlayerName(Label playerName) {
		this.playerName = playerName;
	}

	public Label getPlayerAge() {
		return playerAge;
	}

	public void setPlayerAge(Label playerAge) {
		this.playerAge = playerAge;
	}

	public Label getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(Label playerScore) {
		this.playerScore = playerScore;
	}

	public Label getPlayerAp() {
		return playerAp;
	}

	public void setPlayerAp(Label playerAp) {
		this.playerAp = playerAp;
	}

	public ComboBox<String> getPlayerColorVal() {
		return playerColorVal;
	}

	public void setPlayerColorVal(ComboBox<String> playerColorVal) {
		this.playerColorVal = playerColorVal;
	}

	public TextField getPlayerNameVal() {
		return playerNameVal;
	}

	public void setPlayerNameVal(TextField playerNameVal) {
		this.playerNameVal = playerNameVal;
	}

	public TextField getPlayerAgeVal() {
		return playerAgeVal;
	}

	public void setPlayerAgeVal(TextField playerAgeVal) {
		this.playerAgeVal = playerAgeVal;
	}

	public Label getPlayerScoreVal() {
		return playerScoreVal;
	}

	public void setPlayerScoreVal(Label playerScoreVal) {
		this.playerScoreVal = playerScoreVal;
	}

	public Label getPlayerApVal() {
		return playerApVal;
	}

	public void setPlayerApVal(Label playerApVal) {
		this.playerApVal = playerApVal;
	}

	public GridPane getGridPane() {
		return gridPane;
	}

	public void setGridPane(GridPane gridPane) {
		this.gridPane = gridPane;
	}
	
	 class ColorRectCell extends ListCell<String>{
	        @Override
	        public void updateItem(String item, boolean empty){
	            super.updateItem(item, empty);
	            Rectangle rect = new Rectangle(120,18);
	            if(item != null){
	                rect.setFill(Color.web(item));
	                setGraphic(rect);
	        }
	        }
	    }   
	
}