package tr.edu.yildiz.mobiletermproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private Button event_add;
    int i;

    public void init(){
        event_add = findViewById(R.id.event_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        init();


        RecyclerView recyclerView = findViewById(R.id.event_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] events = load();

        if (events != null){

            int size = events.length;
            EventsData[] myEventsData = new EventsData[size] ;

            for (i=0;i<size;i++){
                String[] parts = events[i].split(",");
                myEventsData[i] = new EventsData(parts[0],parts[1],parts[2],parts[3],R.drawable.event);
            }
            EventsAdapter myEventAdapter = new EventsAdapter(myEventsData, EventsActivity.this);
            recyclerView.setAdapter(myEventAdapter);

        }

        event_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventsActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });


    }



    public String[] load(){

        try {
            FileInputStream fileInputStream = openFileInput("events.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            List<String> drawers = new ArrayList<String>();
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
                drawers.add(lines+"\n");
            }
            String[] drawer_array = new String[ drawers.size() ];
            drawers.toArray( drawer_array );
            return drawer_array;


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


}