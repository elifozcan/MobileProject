package tr.edu.yildiz.mobiletermproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button drawers, events, cabin;
     public void init(){
         drawers = findViewById(R.id.drawers);
         events = findViewById(R.id.events);
         cabin = findViewById(R.id.cabin);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


        drawers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent drawer = new Intent(MainActivity.this, ShowDrawerActivity.class);
                startActivity(drawer);
            }
        });

        events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent event = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(event);
            }
        });

        cabin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cabins = new Intent(MainActivity.this, CabinActivity.class);
                startActivity(cabins);
            }
        });
    }
}