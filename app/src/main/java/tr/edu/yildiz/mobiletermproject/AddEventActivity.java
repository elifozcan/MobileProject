package tr.edu.yildiz.mobiletermproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddEventActivity extends AppCompatActivity {


    private static final String FILE_NAME = "events.txt";
    private Button add_event_button;
    private EditText add_event_name, add_event_type, add_event_location, add_event_date;


    public void init(){
        this.add_event_button = findViewById(R.id.add_event_button);
        this.add_event_name = findViewById(R.id.add_event_name);
        this.add_event_type = findViewById(R.id.add_event_type);
        this.add_event_location = findViewById(R.id.add_event_location);
        this.add_event_date = findViewById(R.id.add_event_date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        init();

        add_event_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddEventActivity.this, EventsActivity.class);
                String event_name = add_event_name.getText().toString();
                String event_type = add_event_type.getText().toString();
                String event_location = add_event_location.getText().toString();
                String event_date = add_event_date.getText().toString();

                String event_info = event_name+","+event_type+","+event_location+","+event_date;

            int flag = load(event_name);
                if(flag ==0 && !event_name.equals("") && !event_type.equals("") &&!event_location.equals("") &&!event_date.equals("")){
                save(event_info);
                startActivity(intent);
            }
                else if(flag == 1){
                Toast toast_register_error = Toast.makeText(getApplicationContext(), "Bu etkinlik adıyla başka bir etkinlik var !", Toast.LENGTH_LONG);
                toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast_register_error.show();
            }
                else {
                Toast toast_register_error = Toast.makeText(getApplicationContext(), "Tüm Alanları Doldurun !", Toast.LENGTH_LONG);
                toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast_register_error.show();
            }
            }
        });
    }


    public void save(String text){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            text = text + "\n";
            fos.write(text.getBytes());
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Etkinlik Kaydedildi !", Toast.LENGTH_LONG);
            toast_dir.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
            toast_dir.show();
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    public int load(String event){

        try {
            FileInputStream fileInputStream = openFileInput("events.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                String[] part = lines.split(",");
                if(event.equals(part[0])) return 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }


}