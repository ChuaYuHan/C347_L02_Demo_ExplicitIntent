package sg.edu.rp.c346.id19020844.demoexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // These request identify who started the second activity
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmatStats = 2;

    TextView tvSuperman, tvBatman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSuperman = (TextView) findViewById(R.id.textViewSuperman);
        tvBatman = (TextView) findViewById(R.id.textViewBatman);

        // Set listener to handle the clicking of Superman TextView
        tvSuperman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create Hero object of strength 100 & technical 60
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", superman);
//                startActivity(i);
                // Start activity with requestCodeForSupermanStats to
                // identify it was started by clicking on Superman
                startActivityForResult(i, requestCodeForSupermanStats);
            }
        });

        // Set listener to handle the clicking of Batman TextView
        tvBatman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Create Hero object of strength 60 & technical 90
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this, HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", batman);
//                startActivity(i);
                // Start activyt with requestCodeForBatmanStats to
                // identify started by clicking Batman
                startActivityForResult(i, requestCodeForBatmatStats);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        // and data contains something
        if (resultCode == RESULT_OK) {
            if (data != null) {
                // Gat data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                // If it is activity started by clikcing
                // Superman, create corresponding String
                if (requestCode == requestCodeForSupermanStats) {
                    statement = "You " + like + " Superman";
                }
                // If 2nd activity started by clicking
                // Batman, create a corresponding String
                if (requestCode == requestCodeForBatmatStats) {
                    statement = "You " + like + " Batman";
                }
                Toast.makeText(MainActivity.this, statement, Toast.LENGTH_LONG).show();
            }
        }
    }
}