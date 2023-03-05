package com.minesweeper.controllers;

import com.minesweeper.ConstAndUtils;
import com.minesweeper.models.Field;
import com.minesweeper.models.MineSweeperGameModel;
import com.minesweeper.views.MineFieldView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    private MineSweeperGameModel game_model;

    private MineFieldView mine_field;
    @FXML
    private Pane background;

    Map<Integer, Image> int_to_image = new HashMap<>();

    int x_size = 12;
    int y_size = 10;

    int field_size = 70;

    public GameController() throws Exception {
        game_model = new MineSweeperGameModel(x_size, y_size);
        mine_field = new MineFieldView(x_size, y_size, field_size);
        int_to_image.put(0, ConstAndUtils.FIELD_NUMBER_ONE);
        int_to_image.put(1, ConstAndUtils.FIELD_NUMBER_ONE);
        int_to_image.put(2, ConstAndUtils.FIELD_NUMBER_TWO);
        int_to_image.put(3, ConstAndUtils.FIELD_NUMBER_THREE);
        int_to_image.put(4, ConstAndUtils.FIELD_NUMBER_FOUR);
        int_to_image.put(5, ConstAndUtils.FIELD_NUMBER_FIVE);
        int_to_image.put(6, ConstAndUtils.FIELD_NUMBER_SIX);
        int_to_image.put(7, ConstAndUtils.FIELD_NUMBER_SEVEN);
        int_to_image.put(8, ConstAndUtils.FIELD_NUMBER_EIGHT);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mine_field.init(this::handleFieldClick, this::handleFieldHoverEnter, this::handleFieldHoverExit);     
        background.getChildren().add(mine_field);
    }

    private void handleFieldClick(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        Point coord = extractPoint(field_pressed.getId());

        game_model.clickField((int) coord.getX(), (int) coord.getY());

        handleFieldImage((int) coord.getX(), (int) coord.getY());
    }

    private void handleFieldHoverExit(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        Point coord = extractPoint(field_pressed.getId());
        mine_field.updateSpecificField(ConstAndUtils.FIELD_CLICKABLE_IMAGE, coord);
    }

    private void handleFieldHoverEnter(MouseEvent m) {
        ImageView field_pressed = (ImageView) m.getSource();
        Point coord = extractPoint(field_pressed.getId());
        mine_field.updateSpecificField(ConstAndUtils.FIELD_FLAGGED_IMAGE, coord);
    }

    private void handleFieldImage(int row, int col) {
        Field field = game_model.getField(row, col);

        if(field.isFlagged()){
            mine_field.updateSpecificField(ConstAndUtils.FIELD_FLAGGED_IMAGE, row, col);
            return;
        }


        switch (field.getFieldType()) {
            case BOMB -> {
            }

            case EMPTY -> {
                if(field.isPressable()) {
                    mine_field.updateSpecificField(ConstAndUtils.FIELD_CLICKABLE_IMAGE, row, col);
                } else {
                    mine_field.updateSpecificField(translateNumberToImage(field.getNumber()), row, col);
                }
            }
        }
    }

    // ASSUMING NUMBER IS BETWEEN 0 - 8
    public Image translateNumberToImage(int n) {
        return int_to_image.get(n);
    }

    private Point extractPoint(String ID) {
        String[] values = ID.split(" ");

        int col = Integer.parseInt(values[0]);
        int row = Integer.parseInt(values[1]);

        return new Point(row, col);
    }


}