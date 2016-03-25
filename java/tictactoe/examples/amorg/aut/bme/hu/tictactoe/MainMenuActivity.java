package tictactoe.examples.amorg.aut.bme.hu.tictactoe;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import tictactoe.examples.amorg.aut.bme.hu.tictactoe.model.TicTacToeModel;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button btnHighscore = (Button) findViewById(R.id.btnHighscore);
        btnHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenuActivity.this, getString(R.string.toast_highscore), Toast.LENGTH_LONG).show();
            }
        });

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TicTacToeModel.getInstance().resetModel(); // modell törlése új játék indításakor
                Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

        Button btnAbout = (Button) findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenuActivity.this,AboutActivity.class);
                startActivity(i);
            }
        });
    }
}