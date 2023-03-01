package com.minesweeper.models;

import com.minesweeper.utils.FieldType;
import com.minesweeper.utils.VisibilityType;

import java.awt.*;

public class Field {

    private Point location;
    private int number;
    private FieldType field_type;
    private boolean visible = false;
    private boolean flagged = false;
    private boolean pressed = false;

    public Field(FieldType field_type, Point location) {
        this.location = location;
        this.field_type = field_type;
    }

    public void press(){
        pressed = true;
    }

    public boolean isPressable() {
        return !isVisible() && !flagged && !pressed;
    }

    public void setFieldType(FieldType field_type) {
        this.field_type = field_type;
    }


    public void setVisible(boolean visible) {
        this.visible = this.visible || visible;
    }

    public void toggleFlagged(){
        flagged = !flagged;
    }

    public boolean isFlagged(){
        return this.flagged;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public int getNumber(){
        return this.number;
    }

    public FieldType getFieldType(){
        return this.field_type;
    }

    public void setNumberOfBombs(int count) {
        this.number = count;
    }
}
