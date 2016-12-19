package com.example.dell.tetrisai.gameLogic;

import com.example.dell.tetrisai.field.Cell;
import com.example.dell.tetrisai.field.CellType;

import java.util.ArrayList;

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

    public void clearLines()
    {}

    public void isLine()
    {}

    public void isEmptyRow()
    {}

    public void isWin()
    {}

    public void getHeight()
    {}

    public void countLines()
    {}

    public void getHole()
    {}

    public void minHeight()
    {}

    public void getColDiff()
    {}

    public void getColHeight()
    {}

    public void addShape()
    {}

    public void isValid()
    {}

    public void isMoveDown()
    {}

    public void isMoveLeft()
    {}

    public void isMoveRight()
    {}

    public void rotateShape()
    {}



}
