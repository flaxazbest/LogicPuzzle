package ua.azbest;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Controller {

    private Field f;
    private final int W = 4;
    private final int H = 4;

    @FXML
    private AnchorPane workfield;

    private ToggleButton[][] buttons;
    private Label[] horizRight;
    private Label[] vertRight;
    private Label[] horizUser;
    private Label[] vertUser;

    EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            String id = ((ToggleButton)event.getSource()).getId();
            int n = Integer.valueOf(id);
            int row = n / 100;
            int col = n % 100;
            f.turn(row, col);
            updateUserLabels();
        }
    };

    private void updateUserLabels() {
        for (int i=0; i<W; i++) {
            horizUser[i].setText(String.valueOf(f.getUserRow(i)));
            if (f.getUserRow(i) == f.getRightRow(i))
                horizUser[i].setTextFill(Color.GREEN);
            else if (f.getUserRow(i) > f.getRightRow(i))
                horizUser[i].setTextFill(Color.RED);
            else
                horizUser[i].setTextFill(Color.BURLYWOOD);
        }
        for (int i=0; i<H; i++) {
            vertUser[i].setText(String.valueOf(f.getUserCol(i)));
            if (f.getUserCol(i) == f.getRightCol(i))
                vertUser[i].setTextFill(Color.GREEN);
            else if (f.getUserCol(i) > f.getRightCol(i))
                vertUser[i].setTextFill(Color.RED);
            else
                vertUser[i].setTextFill(Color.BURLYWOOD);
        }
        if (f.isWin()) {
            System.out.println("WIN");
            Label win = new Label("You win");
            win.setLayoutX(300.0);
            win.setLayoutY(50.0);
            win.setVisible(true);
            workfield.getChildren().add(win);
            for (int i=0; i<W; i++) {
                for (int j=0; j<H; j++) {
                    buttons[i][j].setOnMouseClicked(null);
                    buttons[i][j].setVisible(false);
                }
            }
        }
    }

    public void initialize() {

        f = new Field(W, H);
        f.show();

        buttons = new ToggleButton[W][H];
        for (int i=0; i<W; i++) {
            for (int j=0; j<H; j++) {
                buttons[i][j] = new ToggleButton();
                buttons[i][j].setLayoutX(250.0+30.0*i);
                buttons[i][j].setLayoutY(130.0+30.*j);
                buttons[i][j].setPrefHeight(25.0);
                buttons[i][j].setPrefWidth(25.0);
                buttons[i][j].setText(String.valueOf(f.getElement(i, j)));
                buttons[i][j].setId(String.valueOf(i*100+j));
                buttons[i][j].setOnMouseClicked(handler);
                workfield.getChildren().add(buttons[i][j]);
            }
        }

        horizRight = new Label[W];
        for (int i=0; i<W; i++) {
            horizRight[i] = new Label(String.valueOf(f.getRightRow(i)));
            horizRight[i].setLayoutX(250.0+30.0*i);
            horizRight[i].setLayoutY(105.0);
            horizRight[i].setPrefHeight(25.0);
            horizRight[i].setPrefWidth(25.0);
            horizRight[i].setAlignment(Pos.CENTER);
            horizRight[i].setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            workfield.getChildren().add(horizRight[i]);
        }

        vertRight = new Label[H];
        for (int i=0; i<H; i++) {
            vertRight[i] = new Label(String.valueOf(f.getRightCol(i)));
            vertRight[i].setLayoutX(225.0);
            vertRight[i].setLayoutY(130.0+30.0*i);
            vertRight[i].setPrefHeight(25.0);
            vertRight[i].setPrefWidth(25.0);
            vertRight[i].setAlignment(Pos.CENTER);
            vertRight[i].setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            workfield.getChildren().add(vertRight[i]);
        }

        horizUser = new Label[W];
        for (int i=0; i<W; i++) {
            horizUser[i] = new Label(String.valueOf(f.getUserRow(i)));
            horizUser[i].setLayoutX(250.0+30.0*i);
            horizUser[i].setLayoutY(80.0);
            horizUser[i].setPrefHeight(25.0);
            horizUser[i].setPrefWidth(25.0);
            horizUser[i].setAlignment(Pos.CENTER);
            horizUser[i].setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            workfield.getChildren().add(horizUser[i]);
        }

        vertUser = new Label[H];
        for (int i=0; i<H; i++) {
            vertUser[i] = new Label(String.valueOf(f.getUserCol(i)));
            vertUser[i].setLayoutX(200.0);
            vertUser[i].setLayoutY(130.0+30.0*i);
            vertUser[i].setPrefHeight(25.0);
            vertUser[i].setPrefWidth(25.0);
            vertUser[i].setAlignment(Pos.CENTER);
            vertUser[i].setFont(Font.font("Georgia", FontWeight.BOLD, 14));
            workfield.getChildren().add(vertUser[i]);
        }

        updateUserLabels();

    }

}
