package tr.edu.yildiz.mobiletermproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
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

public class InsideOfEventActivity extends AppCompatActivity {
    private Button event_clothe_add;
    int i;
    private static final int REQUEST_PERMISSION = 0;

    public void init(){
        event_clothe_add = findViewById(R.id.event_clothe_add);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_of_event);
        init();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int hasWritePermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int hasReadPermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            List<String> permissions = new ArrayList<String>();
            if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (hasReadPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);

            } else {
//              preferencesUtility.setString("storage", "true");
            }

            if (!permissions.isEmpty()) {
//              requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);

                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE},
                        REQUEST_PERMISSION);
            }
        }

        Intent intent = getIntent();
        String event = intent.getStringExtra("EVENT");

        RecyclerView recyclerView = findViewById(R.id.event_inside_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        String[] clothes = load(event);
        if (clothes != null){

            int size = clothes.length;
            ClothesData[] myEventClothesData = new ClothesData[size] ;
            for (i=0;i<size;i++){
                String[] parts = clothes[i].split(",");
                myEventClothesData[i] = new ClothesData(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5]);
            }
            InsideOfEventAdapter myInsideEventAdapter = new InsideOfEventAdapter(myEventClothesData, InsideOfEventActivity.this);
            recyclerView.setAdapter(myInsideEventAdapter);

        }

        event_clothe_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent(InsideOfEventActivity.this, AddClothesToEventActivity.class);
                intent_add.putExtra("EVENT", event);
                startActivity(intent_add);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {


                        System.out.println("Permissions --> " + "Permission Granted: " + permissions[i]);
                    } else if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        System.out.println("Permissions --> " + "Permission Denied: " + permissions[i]);
                    }
                }
            }
            break;
            default: {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }

    public String[] load(String event){

        try {
            FileInputStream fileInputStream = openFileInput(event+".txt");
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