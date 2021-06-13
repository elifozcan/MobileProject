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

public class ShowDrawerActivity extends AppCompatActivity {

    private Button drawer_add;
    int i;

    public void init(){
        drawer_add = findViewById(R.id.drawer_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_drawer);
        init();


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] drawers = load();

        if (drawers != null){

            int size = drawers.length;
            DrawerData[] myDrawerData = new DrawerData[size] ;

            for (i=0;i<size;i++){
                myDrawerData[i] = new DrawerData(drawers[i],R.drawable.drawer);
            }
            DrawerAdapter myDrawerAdapter = new DrawerAdapter(myDrawerData, ShowDrawerActivity.this);
            recyclerView.setAdapter(myDrawerAdapter);

        }


        drawer_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowDrawerActivity.this, AddDrawerActivity.class);
                startActivity(intent);
            }
        });

    }

    public String[] load(){

        try {
            FileInputStream fileInputStream = openFileInput("drawer2.txt");
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