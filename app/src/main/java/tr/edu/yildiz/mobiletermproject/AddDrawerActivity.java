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

public class AddDrawerActivity extends AppCompatActivity {

    private static final String FILE_NAME = "drawer2.txt";
    private Button create_button;
    private EditText create_drawer_name;

    public void init(){
        this.create_button = findViewById(R.id.create_button);
        this.create_drawer_name = findViewById(R.id.create_drawer_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drawer);
        init();


        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddDrawerActivity.this, ShowDrawerActivity.class);
                String drawer = create_drawer_name.getText().toString();
                int flag = load(drawer);
                if(!drawer.equals("") && flag ==0){
                    save(drawer);
                    startActivity(intent);
                }
                else if(flag == 1){
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Bu çekmece adıyla başka bir çekmece var !", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }
                else {
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Çekmece Adını Girin !", Toast.LENGTH_LONG);
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
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Çekmece Kaydedildi !", Toast.LENGTH_LONG);
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

    public int load(String drawer_name){

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                if(drawer_name.equals(lines)) return 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }


}