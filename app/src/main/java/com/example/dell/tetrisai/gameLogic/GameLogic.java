package com.example.dell.tetrisai.gameLogic;

import com.example.dell.tetrisai.field.Cell;
import com.example.dell.tetrisai.field.CellType;
import com.example.dell.tetrisai.field.Shape;

import java.util.ArrayList;
import java.util.jar.Pack200;

/**
 * Created by DELL on 19/12/2016.
 */
public class GameLogic {

    private int rows;
    private int cols;
    private Cell cells[][];


    public GameLogic(int rows,int cols)
    {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];
        for(int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                cells[i][j] = new Cell(i,j,CellType.EMPTY);
    }

    public GameLogic cloneGrid()
    {
        GameLogic newGrid = new GameLogic(this.rows,this.cols);
        for(int r=0; r<this.rows; r++)
            for(int c=0; c<this.cols; c++)
                newGrid.cells[r][c] = this.cells[r][c];
        return newGrid;
    }

    public int clearLines()
    {
        int distance = 0;
        int arr[][] = new int[rows][cols];
        for(int r = this.rows - 1 ; r >= 0 ; r--){
            if(this.isLine(r)){
                distance++;
                for(int c = 0 ; c < this.cols ; c++){
                    this.cells[r][c].setEmpty();
                }
            }else if (distance > 0){
                for(int c = 0 ; c < this.cols ; c++){
                    this.cells[r + distance][c] = this.cells[r][c];
                    this.cells[r][c].setEmpty();
                }
            }
        }
        return distance;
    }

    public boolean isLine(int row)
    {
        for(int c = 0 ; c < cols ; c++){
            if(this.cells[row][c].isEmpty())
                return false;
        }
        return true;
    }

    public boolean isEmptyRow(int row)
    {
        for(int c = 0 ; c < cols ; c++){
            if(this.cells[row][c].isShape())
                return false;
        }
        return true;
    }

    public boolean exceeded()
    {
        return !this.isEmptyRow(0) || this.isEmptyRow(1);
    }

    public int getHeight()
    {
        int r = 0;
        for(; r < this.rows && this.isEmptyRow(r); r++);
        return this.rows - r;
    }

    public int countLines()
    {
        int count = 0;
        for(int r = 0 ; r < this.rows ; r++){
            if(this.isLine(r)){
                count++;
            }
        }
        return count;
    }

    public int getHole()
    {
        int count = 0;
        for(int c = 0 ; c < this.cols; c++){
            boolean block = false;
            for(int r = 0 ; r < this.rows; r++){
                if(this.cells[r][c].isShape()){
                    block = true;
                }else if(this.cells[r][c].isEmpty() && block){
                    count++;
                }
            }
        }
        return count;
    }

    public int blockades(){
        int count = 0;
        for(int c = 0 ; c < this.cols ; c++){
            boolean hole = false;
            for(int r = this.rows - 1 ; r >= 0 ; r--){
                if(this.cells[r][c].isEmpty()){
                    hole = true;
                }else if(this.cells[r][c].isShape() && hole){
                    count++;
                }
            }
        }
        return count;
    }

    public int aggregateHeight(){
        int total = 0;
        for(int c = 0 ; c < this.cols; c++){
            total += this.getColHeight(c);
        }
        return total;
    }

    public int bumpiness(){
        int total = 0;
        for(int c = 0 ; c < this.cols - 1; c++){
            total += Math.abs(this.getColHeight(c) - this.getColHeight(c + 1));
        }
        return total;
    }

    public void minHeight()
    {}

    public void getColDiff()
    {
    }

    public int getColHeight(int col)
    {
        int r = 0;
        for(; r < this.rows && this.cells[r][col].isEmpty(); r++);
        return this.rows - r;
    }

    public void addShape(Shape shape)
    {
        for(int r = 0 ; r < shape.getShape().length ; r++){
            for(int c = 0 ; c < shape.getShape()[r].length ; c++){
                int tempRow = shape.getLocation().x + r;
                int tempCol = shape.getLocation().y + c;
                if(shape.getShape()[r][c].isShape() && tempRow >= 0){
                    this.cells[tempRow][tempCol].setShape();
                }
            }
        }
    }

    //check two options
    public boolean isValid(Shape shape)
    {
        /*
        for (int r = 0 ; r < shape.getSize() ; r++){
            for(int c = 0 ; c < shape.getSize() ; c++){
                if(!shape.getShape()[r][c].isShape())
                    return false;
            }
        }
        return true;
        */
        for(int r = 0 ; r < shape.getShape().length ; r++){
            for(int c = 0 ; c < shape.getShape()[r].length ; c++){
                int tempRow = shape.getLocation().x + r;
                int tempCol = shape.getLocation().y + c;
                if(shape.getShape()[r][c].isShape()){
                    if(!(tempCol < this.cols && tempRow < this.rows && this.cells[tempRow][tempCol].isEmpty())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isMoveDown(Shape shape) {
        for (int r = 0; r < shape.getShape().length; r++) {
            for (int c = 0; c < shape.getShape()[r].length; c++) {
                int tempRow = shape.getLocation().x + r;
                int tempCol = shape.getLocation().y + c;
                if(shape.getShape()[r][c].isShape() && tempRow >= 0){
                    if(!(tempRow < this.rows && this.cells[rows][cols].isEmpty())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isMoveLeft(Shape shape)
    {
        for (int r = 0; r < shape.getShape().length; r++) {
            for (int c = 0; c < shape.getShape()[r].length; c++) {
                int tempRow = shape.getLocation().x + r;
                int tempCol = shape.getLocation().y + c - 1;
                if(shape.getShape()[r][c].isShape()){
                    if(!(tempCol >= 0 && this.cells[tempRow][tempCol].isEmpty())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isMoveRight(Shape shape)
    {
        for (int r = 0; r < shape.getShape().length; r++) {
            for (int c = 0; c < shape.getShape()[r].length; c++) {
                int tempRow = shape.getLocation().x + r;
                int tempCol = shape.getLocation().y + c + 1;
                if(shape.getShape()[r][c].isShape()){
                    if(!(tempCol >= 0 && this.cells[tempRow][tempCol].isEmpty())){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //check if correct
    public void rotateShape(Shape shape)
    {
        //shape.transposeShape();

    }



}
