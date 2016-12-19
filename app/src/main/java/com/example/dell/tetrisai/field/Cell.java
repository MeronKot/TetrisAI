package com.example.dell.tetrisai.field;

import android.graphics.Point;
import android.widget.ImageButton;

/**
 * Created by DELL on 19/12/2016.
 */

public class Cell
{
    private Point location;
    private CellType state;

    public Cell() {
        this.location = null;
        this.state = CellType.EMPTY;
    }

    public Cell(int x, int y, CellType type) {
        this.location = new Point(x, y);
        this.state = type;
    }

    public boolean isOutOfBoundaries(Field f) {
        if(this.location.x >= f.getWidth() || this.location.x < 0 || this.location.y >= f.getHeight())
            return true;
        return false;
    }

    public boolean hasCollision(Field f) {
        Cell cell = f.getCell(this.location.x, this.location.y);
        if(cell == null)
            return false;
        return (this.state == CellType.SHAPE && (cell.isSolid() || cell.isBlock()));
    }

    public void setShape() {
        this.state = CellType.SHAPE;
    }

    public void setLocation(int x, int y) {
        if(this.location == null)
            this.location = new Point();

        this.setLocation(x,y);
    }

    public boolean isShape() {
        return this.state == CellType.SHAPE;
    }

    public boolean isSolid() {
        return this.state == CellType.SOLID;
    }

    public boolean isBlock() {
        return this.state == CellType.BLOCK;
    }

    public boolean isEmpty() {
        return this.state == CellType.EMPTY;
    }

    public CellType getState() {
        return this.state;
    }

    public Point getLocation() {
        return this.location;
    }
}
