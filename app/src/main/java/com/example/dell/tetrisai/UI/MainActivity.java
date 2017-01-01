package com.example.dell.tetrisai.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import com.example.dell.tetrisai.field.MyImageView;
import com.example.dell.tetrisai.gameLogic.GameLogic;
import com.example.dell.tetrisai.R;

public class MainActivity extends AppCompatActivity {

    private int rows = 22;
    private int cols = 10;
    private GridLayout grid1;
    private GridLayout grid2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int id = 0;

        grid1 = (GridLayout)findViewById(R.id.tetBoard1);
        grid2 = (GridLayout)findViewById(R.id.tetBoard2);
        grid1.setColumnCount(cols);
        grid1.setRowCount(rows);
        grid2.setColumnCount(cols);
        grid2.setRowCount(rows);
        grid1.setId(0);
        grid2.setId(0);
        GameLogic game1 = new GameLogic(rows,cols);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();
        android.widget.LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width/(rows+1),width/(rows+1));

        for (int i = 0 ; i < rows ; i++)
        {
            for(int j = 0 ; j < cols ; j++)
            {

                MyImageView imageView1 = new MyImageView(this,i,j);
                MyImageView imageView2 = new MyImageView(this,i,j);
                imageView1.setBackgroundResource(R.drawable.blue);
                imageView2.setBackgroundResource(R.drawable.red);
                imageView1.setLayoutParams(lp);
                imageView2.setLayoutParams(lp);
                //grid1.addView(imageView1);
                //grid2.addView(imageView2);
                grid1.setBackgroundResource(R.drawable.blue);
                grid2.setBackgroundResource(R.drawable.red);

            }
        }

        MyImageView iVPlayer1 = new MyImageView(this,0,cols/2);
        MyImageView iVPlayer2 = new MyImageView(this,0,cols/2);
        iVPlayer1.setBackgroundResource(R.drawable.white);
        iVPlayer2.setBackgroundResource(R.drawable.white);
        iVPlayer1.setLayoutParams(lp);
        iVPlayer2.setLayoutParams(lp);
        grid1.addView(iVPlayer1,4,lp);
        grid2.addView(iVPlayer2,4,lp);
       // grid2.addView(imageViewCell);


    }
}
