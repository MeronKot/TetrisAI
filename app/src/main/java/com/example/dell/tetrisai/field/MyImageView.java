package com.example.dell.tetrisai.field;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.dell.tetrisai.R;

/**
 * Created by DELL on 19/12/2016.
 */

public class MyImageView extends ImageView
{
    private int row;
    private int col;
    private Drawable backGround;

    public MyImageView(Context context,int row,int col) {
        super(context);
        this.row = row;
        this.col = col;
        this.setBackgroundColor(Color.BLUE);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
