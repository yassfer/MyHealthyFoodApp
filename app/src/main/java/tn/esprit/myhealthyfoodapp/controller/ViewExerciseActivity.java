package tn.esprit.myhealthyfoodapp.controller;

import android.os.CountDownTimer;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import tn.esprit.myhealthyfoodapp.R;

import tn.esprit.myhealthyfoodapp.db.MyDatabaseHelper;
import tn.esprit.myhealthyfoodapp.Utils.Common;

public class ViewExerciseActivity extends AppCompatActivity {

    private int imageId;

    private String name;

    private TextView title, timer;

    private ImageView imageDetail;

    private Button startButton;

    private boolean timerIsRunning = false;

    private MyDatabaseHelper yogaAndroidDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        yogaAndroidDB = new MyDatabaseHelper(this);

        //timer = (TextView) findViewById(R.id.timerr);
        timer = (EditText) findViewById(R.id.timerr);

        timer.setText("");
        title = (TextView) findViewById(R.id.titlex);
        imageDetail = (ImageView) findViewById(R.id.image_detail);

        startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timerIsRunning) {
                    startButton.setText("STOP");

                    int timeLimit = Common.TIME_LIMIT_EASY;



                    new CountDownTimer(timeLimit, 1000) {

                        @Override
                        public void onTick(long l) {
                            timer.setText("");
                            System.out.println("timer before set text= "+ timer.getText());
                            timer.setText("" + (l / 1000));
                            System.out.println("timer after set text= "+ timer.getText());


                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExerciseActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }.start();

                } else {
                    Toast.makeText(ViewExerciseActivity.this, "Done!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                timerIsRunning = !timerIsRunning;
            }
        });

        if (getIntent() != null) {
            imageId = getIntent().getIntExtra("imageId", -1);
            name = getIntent().getStringExtra("name");

            imageDetail.setImageResource(imageId);
            title.setText(name);
        }
    }
}
