package com.minesweeper.controllers;

import com.minesweeper.ConstAndUtils;
import com.minesweeper.models.MineSweeperGameModel;
import com.minesweeper.views.MineFieldView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private MineSweeperGameModel game_model;

    private MineFieldView mine_field;
    @FXML
    private Pane background;


    int x_size = 12;
    int y_size = 10;

    int field_size = 70;

    public GameController() throws Exception {
        game_model = new MineSweeperGameModel(x_size, y_size);
        mine_field = new MineFieldView(x_size, y_size, field_size);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mine_field.init(this::handleFieldClick, this::handleFieldHoverEnter, this::handleFieldHoverExit);     
        background.getChildren().add(mine_field);

    }

    private void handleFieldClick(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        String ID = field_pressed.getId();

        int col = Character.getNumericValue(ID.charAt(0));
        int row = Character.getNumericValue(ID.charAt(1));

        mine_field.updateSpecificField(ConstAndUtils.FIELD_FLAGGED_IMAGE, row, col);
    }

    private void handleFieldHoverExit(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        String ID = field_pressed.getId();

        int col = Character.getNumericValue(ID.charAt(0));
        int row = Character.getNumericValue(ID.charAt(1));

        mine_field.updateSpecificField(ConstAndUtils.FIELD_CLICKABLE_IMAGE, row, col);
    }

    private void handleFieldHoverEnter(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        String ID = field_pressed.getId();

        int col = Character.getNumericValue(ID.charAt(0));
        int row = Character.getNumericValue(ID.charAt(1));

        mine_field.updateSpecificField(ConstAndUtils.FIELD_FLAGGED_IMAGE, row, col);
    }


}