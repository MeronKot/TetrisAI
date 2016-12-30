package com.example.dell.tetrisai.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.tetrisai.field.Cell;
import com.example.dell.tetrisai.field.MyImageView;
import com.example.dell.tetrisai.gameLogic.GameLogic;
import com.example.dell.tetrisai.R;

public class MainActivity extends AppCompatActivity {

    private int rows = 22;
    private int cols = 10;
    private GridLayout grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grid = (GridLayout)findViewById(R.id.tetBoard);
        grid.setColumnCount(cols);
        grid.setRowCount(rows);
        grid.setId(0);
        GameLogic game = new GameLogic(rows,cols);
        for (int i = 0 ; i < rows ; i++)
        {
            for(int j = 0 ; j < cols ; j++)
            {
                MyImageView imageView = new MyImageView(this,i,j);
                int width = getWindowManager().getDefaultDisplay().getWidth();
                int height = getWindowManager().getDefaultDisplay().getHeight();
                android.widget.LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width/(rows+1),width/(rows+1));
                imageView.setLayoutParams(lp);
                grid.addView(imageView);
            }
        }
    }
}
