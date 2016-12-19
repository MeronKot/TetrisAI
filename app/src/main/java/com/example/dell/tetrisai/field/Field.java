package com.example.dell.tetrisai.field;

/**
 * Created by DELL on 19/12/2016.
 */

public class Field
{
    private int width;
    private int height;
    private Cell grid[][];

    public Field(int width, int height, String fieldString) {
        this.width = width;
        this.height = height;

        parse(fieldString);
    }

    /**
     * Parses the input string to get a grid with Cell objects
     * @param fieldString : input string
     */
    private void parse(String fieldString) {

        this.grid = new Cell[this.width][this.height];

        // get the separate rows
        String[] rows = fieldString.split(";");
        for(int y=0; y < this.height; y++) {
            String[] rowCells = rows[y].split(",");

            // parse each cell of the row
            for(int x=0; x < this.width; x++) {
                int cellCode = Integer.parseInt(rowCells[x]);
                this.grid[x][y] = new Cell(x, y, CellType.values()[cellCode]);
            }
        }
    }

    public Cell getCell(int x, int y) {
        if(x < 0 || x >= this.width || y < 0 || y >= this.height)
            return null;
        return this.grid[x][y];
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }
}
