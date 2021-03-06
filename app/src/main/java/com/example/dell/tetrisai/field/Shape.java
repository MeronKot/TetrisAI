package com.example.dell.tetrisai.field;

import android.graphics.Point;

/**
 * Created by DELL on 19/12/2016.
 */

public class Shape
{
    public ShapeType type;
    private Cell[][] shape; // 2-dimensional bounding box: a matrix that contains the block-cells of the shape
    private Cell[] blocks; // array that contains only the block-cells of the shape
    private int size;
    private Point location;
    private Field field;

    public Shape(ShapeType type, Field field, Point location) {
        this.type = type;
        this.field = field;
        this.blocks = new Cell[4];
        this.location = location;

        setShape();
        setBlockLocations();
    }

    // ACTIONS (no checks for errors are performed in the actions!)

    public Shape clone()
    {
        return new Shape(this.type,this.field,this.location);
    }

    /**
     * Rotates the shape counter-clockwise
     */
    public void turnLeft() {

        Cell[][] temp = this.transposeShape();
        for(int y=0; y < size; y++) {
            for(int x=0; x < size; x++) {
                this.shape[x][y] = temp[x][size - y - 1];
            }
        }

        this.setBlockLocations();
    }

    /**
     * Rotates the shape clockwise
     */
    public void turnRight() {

        Cell[][] temp = this.transposeShape();
        for(int x=0; x < size; x++) {
            this.shape[x] = temp[size - x - 1];
        }

        this.setBlockLocations();
    }

    public void oneDown() {

        this.location.y++;
        this.setBlockLocations();
    }

    public void oneRight() {

        this.location.x++;
        this.setBlockLocations();
    }

    public void oneLeft() {

        this.location.x--;
        this.setBlockLocations();
    }

    /**
     * Used for rotations
     * @return transposed matrix of current shape box
     */
    public Cell[][] transposeShape() {
        Cell[][] temp = new Cell[size][size];
        for(int y=0; y < size; y++) {
            for(int x=0; x < size; x++) {
                temp[y][x] = shape[x][y];
            }
        }
        return temp;
    }

    /**
     * Uses the shape's current orientation and position to
     * set the actual location of the block-type cells on the field
     */
    private void setBlockLocations() {
        for(int y=0; y < size; y++) {
            for(int x=0; x < size; x++) {
                if(shape[x][y].isShape()) {
                    shape[x][y].setLocation(location.x + x, location.y + y);
                }
            }
        }
    }

    /**
     * Set shape in square box.
     * Creates new Cells that can be checked against the actual
     * playing field.
     * */
    private void setShape() {
        switch(this.type) {
            case I:
                this.size = 4;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[0][1];
                this.blocks[1] = this.shape[1][1];
                this.blocks[2] = this.shape[2][1];
                this.blocks[3] = this.shape[3][1];
                break;
            case J:
                this.size = 3;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[0][0];
                this.blocks[1] = this.shape[0][1];
                this.blocks[2] = this.shape[1][1];
                this.blocks[3] = this.shape[2][1];
                break;
            case L:
                this.size = 3;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[2][0];
                this.blocks[1] = this.shape[0][1];
                this.blocks[2] = this.shape[1][1];
                this.blocks[3] = this.shape[2][1];
                break;
            case O:
                this.size = 2;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[0][0];
                this.blocks[1] = this.shape[1][0];
                this.blocks[2] = this.shape[0][1];
                this.blocks[3] = this.shape[1][1];
                break;
            case S:
                this.size = 3;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[1][0];
                this.blocks[1] = this.shape[2][0];
                this.blocks[2] = this.shape[0][1];
                this.blocks[3] = this.shape[1][1];
                break;
            case T:
                this.size = 3;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[1][0];
                this.blocks[1] = this.shape[0][1];
                this.blocks[2] = this.shape[1][1];
                this.blocks[3] = this.shape[2][1];
                break;
            case Z:
                this.size = 3;
                this.shape = initializeShape();
                this.blocks[0] = this.shape[0][0];
                this.blocks[1] = this.shape[1][0];
                this.blocks[2] = this.shape[1][1];
                this.blocks[3] = this.shape[2][1];
                break;
        }

        // set type to SHAPE
        for(int i=0; i < blocks.length; i++) {
            this.blocks[i].setShape();
        }
    }
    /**
     * Creates the matrix for the shape
     * @return
     */
    private Cell[][] initializeShape() {
        Cell[][] newShape = new Cell[size][size];
        for(int y=0; y < this.size; y++) {
            for(int x=0; x < this.size; x++) {
                newShape[x][y] = new Cell();
            }
        }
        return newShape;
    }

    public void rotateShape(int numOfRota)
    {
        Cell[][] newCell;
        for(int i=0; i< numOfRota; i++) {
            newCell = new Cell[size][size];
            switch (size) {
                case 2:
                    newCell[0][0] = this.shape[1][0];
                    newCell[0][1] = this.shape[0][0];
                    newCell[1][0] = this.shape[1][1];
                    newCell[1][1] = this.shape[0][1];
                    break;
                case 3:
                    newCell[0][0] = this.shape[2][0];
                    newCell[0][1] = this.shape[1][0];
                    newCell[0][2] = this.shape[0][0];
                    newCell[1][0] = this.shape[2][1];
                    newCell[1][1] = this.shape[1][1];
                    newCell[1][2] = this.shape[0][1];
                    newCell[2][0] = this.shape[2][2];
                    newCell[2][1] = this.shape[1][2];
                    newCell[2][2] = this.shape[0][2];
                    break;
                case 4:
                    newCell[0][0] = this.shape[3][0];
                    newCell[0][1] = this.shape[2][0];
                    newCell[0][2] = this.shape[1][0];
                    newCell[0][3] = this.shape[0][0];
                    newCell[1][3] = this.shape[0][1];
                    newCell[2][3] = this.shape[0][2];
                    newCell[3][3] = this.shape[0][3];
                    newCell[3][2] = this.shape[1][3];
                    newCell[3][1] = this.shape[2][3];
                    newCell[3][0] = this.shape[3][3];
                    newCell[2][0] = this.shape[3][2];
                    newCell[1][0] = this.shape[3][1];
                    newCell[1][1] = this.shape[2][1];
                    newCell[1][2] = this.shape[1][1];
                    newCell[2][2] = this.shape[1][2];
                    newCell[2][1] = this.shape[2][2];
                    break;
                }
            this.shape = newCell;
        }
    }

    public void setLocation(int x, int y) {
        this.location = new Point(x, y);
    }

    public Cell [][] getShape(){
        return shape;
    }

    public Cell[] getBlocks() {
        return this.blocks;
    }

    public Point getLocation() {
        return this.location;
    }

    public ShapeType getType() {
        return this.type;
    }

    public int getSize(){
        return size;
    }
}
