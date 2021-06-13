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

public class CabinActivity extends AppCompatActivity {

    private Button cabin_add;
    int i;

    public void init(){
        cabin_add = findViewById(R.id.cabin_add);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabin);
        init();


        RecyclerView recyclerView = findViewById(R.id.cabin_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] combines = load();

        if (combines != null){

            int size = combines.length;
            CabinData[] myCabinData = new CabinData[size] ;

            for (i=0;i<size;i++){
                myCabinData[i] = new CabinData(combines[i],R.drawable.cabin);
            }
            CabinAdapter myCabinAdapter = new CabinAdapter(myCabinData, CabinActivity.this);
            recyclerView.setAdapter(myCabinAdapter);

        }

        cabin_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CabinActivity.this, AddCombineActivity.class);
                intent.putExtra("FLAG","0");
                startActivity(intent);
            }
        });

    }

    public String[] load(){

        try {
            FileInputStream fileInputStream = openFileInput("cabins.txt");
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