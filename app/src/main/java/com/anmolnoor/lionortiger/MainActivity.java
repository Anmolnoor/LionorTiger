package com.anmolnoor.lionortiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button button;
    private GridLayout gridLayout;
    private Boolean gameOver = false;
    private TextView textView3,textView4;
    private int s1,s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setNoOne();
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        gridLayout = findViewById(R.id.gridLayout);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });


    }

    public void imageIsClicked(View imageView){

        ImageView tappedImageView =(ImageView) imageView;
        int num = Integer.parseInt(tappedImageView.getTag().toString());

        if (playerChoices[num]==Player.NoOne && !gameOver){
        tappedImageView.setTranslationX(-2000);

        playerChoices[num] = currentPlayer;

        if (currentPlayer == Player.ONE) {
            tappedImageView.setImageResource(R.drawable.lion);
            currentPlayer = Player.TWO;
            s1++;
            textView3.setText(s1+"");
            textView2.setText("Player 2");
        }
        else if (currentPlayer==Player.TWO){
            tappedImageView.setImageResource(R.drawable.tiger);
            currentPlayer = Player.ONE;
            s2++;
            textView4.setText(s2+"");
            textView2.setText("Player 1");
        }

        tappedImageView.animate().translationXBy(2000).alpha(1f).rotation(3600).setDuration(2000);
        num++;
        Toast.makeText(MainActivity.this,num+"",Toast.LENGTH_SHORT).show();

        for (int[] winnercolumn : winnerRowColumn){
            if (playerChoices[winnercolumn[0]]== playerChoices[winnercolumn[1]]
                    && playerChoices[winnercolumn[1]]==playerChoices[winnercolumn[2]]
                && playerChoices[winnercolumn[0]]!=Player.NoOne){

                button.setVisibility(View.VISIBLE);

                gameOver=true;

                if (currentPlayer==Player.ONE){
                    Toast.makeText(MainActivity.this,"Player 2 is the Winner",Toast.LENGTH_LONG).show();
                }else if (currentPlayer==Player.TWO){
                    Toast.makeText(MainActivity.this,"Player 1 is the Winner",Toast.LENGTH_LONG).show();
                }

            }
        }

        }
    }

    //reset game function

    private void resetTheGame(){
        for (int i = 0; i<gridLayout.getChildCount();i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(.3f);
        }
        currentPlayer=Player.ONE;

        setNoOne();

        gameOver = false ;

        button.setVisibility(View.INVISIBLE);
        s1=0;s2=0;
        textView4.setText(s1+"");
        textView3.setText(s2+"");

    }

        private void setNoOne (){
            for (int j=0;j<playerChoices.length;j++){
                playerChoices[j] = Player.NoOne;
            }
        }
}
