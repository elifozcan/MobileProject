package tr.edu.yildiz.mobiletermproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ChooseFromDrawersActivity extends AppCompatActivity {
    int i;
    public String flag;

    public String getFlag() {
        return flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_from_drawers);

        Intent intent = getIntent();
        flag = intent.getStringExtra("FLAG");

        RecyclerView recyclerView = findViewById(R.id.choose_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] choose = load();


        if (choose != null){
            int size = choose.length;
            ClothesData[] myChooseData = new ClothesData[size] ;

            for (i=0;i<size;i++){
                String[] lines = choose[i].split("\n");
                String[] chooses = load_clothe(lines[i]);
                int size2  = chooses.length;
                int j;
                for (j=0;j<size2;j++){
                    String[] parts = chooses[i].split(",");
                    myChooseData[i] = new ClothesData(parts[0],parts[1],parts[2],parts[3],parts[4], parts[5]);}
            }
            ChooseAdapter myChooseAdapter = new ChooseAdapter(myChooseData, ChooseFromDrawersActivity.this);
            recyclerView.setAdapter(myChooseAdapter);

        }

    }

    public String[] load(){

        try {
            FileInputStream fileInputStream = openFileInput("drawer2.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            List<String> line = new ArrayList<String>();
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
                line.add(lines+"\n");
            }
            String[] array = new String[line.size()  ];
            line.toArray( array );
            return array;

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }

    public String[] load_clothe(String drawer){

        try {
            FileInputStream fileInputStream = openFileInput(drawer+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            List<String> clothes = new ArrayList<String>();
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
                clothes.add(lines+"\n");
            }
            String[] clothes_array = new String[ clothes.size() ];
            clothes.toArray( clothes_array );
            return clothes_array;


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


}