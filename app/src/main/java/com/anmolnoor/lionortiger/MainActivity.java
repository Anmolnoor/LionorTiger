package com.anmolnoor.lionortiger;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    enum Player {
        ONE , TWO ,NoOne;
    }

    Player currentPlayer=Player.ONE;

    Player[] playerChoices = new Player[9];

    int [][] winnerRowColumn = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6}};

    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0] = Player.NoOne;
        playerChoices[1] = Player.NoOne;
        playerChoices[2] = Player.NoOne;
        playerChoices[3] = Player.NoOne;
        playerChoices[4] = Player.NoOne;
        playerChoices[5] = Player.NoOne;
        playerChoices[6] = Player.NoOne;
        playerChoices[7] = Player.NoOne;
        playerChoices[8] = Player.NoOne;
        textView2 = findViewById(R.id.textView2);


    }

    public void imageIsClicked(View imageView){

        ImageView tappedImageView =(ImageView) imageView;
        tappedImageView.setTranslationX(-2000);
        int num = Integer.parseInt(tappedImageView.getTag().toString());
        playerChoices[num] = currentPlayer;

        if (currentPlayer == Player.ONE) {
            tappedImageView.setImageResource(R.drawable.lion);
            currentPlayer = Player.TWO;
            textView2.setText("Current Player : Player 2");
        }
        else if (currentPlayer==Player.TWO){
            tappedImageView.setImageResource(R.drawable.tiger);
            currentPlayer = Player.ONE;
            textView2.setText("Current Player : Player 1");
        }

        tappedImageView.animate().translationXBy(2000).alpha(1f).rotation(3600).setDuration(2000);
        num++;
        Toast.makeText(MainActivity.this,num+"",Toast.LENGTH_SHORT).show();

        for (int[] winnercolumn : winnerRowColumn){
            if (playerChoices[winnercolumn[0]]== playerChoices[winnercolumn[1]]
                    && playerChoices[winnercolumn[1]]==playerChoices[winnercolumn[2]]
                && playerChoices[winnercolumn[0]]!=Player.NoOne){

                if (currentPlayer==Player.ONE){
                    Toast.makeText(MainActivity.this,"Player 2 is the Winner",Toast.LENGTH_LONG).show();
                }else if (currentPlayer==Player.TWO){
                    Toast.makeText(MainActivity.this,"Player 1 is the Winner",Toast.LENGTH_LONG).show();
                }

            }
        }

    }



}
