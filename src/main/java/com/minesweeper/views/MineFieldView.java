package com.minesweeper.views;

import com.minesweeper.ConstAndUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class MineFieldView extends GridPane {

    @FXML
    private ImageView[][] fields;
    private final int x_size;
    private final int y_size;
    private final int field_size;

    public MineFieldView(int x_size, int y_size, int field_size){
        this.setGridLinesVisible(false);

        this.x_size = x_size;
        this.y_size = y_size;
        this.field_size = field_size;

        this.fields = new ImageView[y_size][x_size];

        this.setPrefSize(x_size * field_size, y_size * field_size);
    }

    public void init(
            EventHandler<MouseEvent> field_click,
            EventHandler<MouseEvent> field_hover_entered,
            EventHandler<MouseEvent> field_hover_exit
    ){
        for (int y = 0; y < y_size; y++) {
            for (int x = 0; x < x_size; x++) {
                fields[y][x] = new ImageView();
                ImageView temp = fields[y][x];
                temp.setImage(ConstAndUtils.FIELD_NUMBER_EIGHT);
                temp.setFitHeight(field_size);
                temp.setFitWidth(field_size);
                temp.setCursor(Cursor.HAND);
                temp.setOnMouseClicked(field_click);
                temp.setOnMouseEntered(field_hover_entered);
                temp.setOnMouseExited(field_hover_exit);
                temp.setId(x+""+y);
                this.add(fields[y][x], x, y);
            }
        }
    }

    public void updateSpecificField(Image img, int row, int col){
        fields[row][col].setImage(img);
    }
}
