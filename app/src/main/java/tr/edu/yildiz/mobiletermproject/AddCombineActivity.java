package tr.edu.yildiz.mobiletermproject;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddCombineActivity extends AppCompatActivity {

    private static final String FILE_NAME = "cabins.txt";
    private Button image_button_shoe, image_button_pants, image_button_top, image_button_face, image_button_hat, combine_save;
    private EditText add_combine_name;
    private ImageView add_hat, add_face, add_top, add_pants, add_shoe;

    String path_hat ;
    String path_face ;
    String path_top ;
    String path_pant;
    String path_shoe;
    String flag ="0";

    public void init() {
        image_button_face = findViewById(R.id.image_button_face);
        image_button_hat = findViewById(R.id.image_button_hat);
        image_button_shoe = findViewById(R.id.image_button_shoe);
        image_button_pants = findViewById(R.id.image_button_pants);
        image_button_top = findViewById(R.id.image_button_top);
        combine_save = findViewById(R.id.combine_save);

        add_face = findViewById(R.id.add_face);
        add_hat = findViewById(R.id.add_hat);
        add_pants = findViewById(R.id.add_pants);
        add_top = findViewById(R.id.add_top);
        add_shoe = findViewById(R.id.add_shoe);

        add_combine_name = findViewById(R.id.add_combine_name);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_combine);
        init();

        Intent intent = getIntent();
        String img = intent.getStringExtra("IMG");
        flag = intent.getStringExtra("FLAG");

        if (flag==null){
            flag="0";
        }

        if (flag.equals("1")){
            path_hat = img;
            add_hat.setImageURI(Uri.parse(path_hat));
        }else if (flag.equals("2")){
            path_face = img;
            add_face.setImageURI(Uri.parse(path_face));
        }else if (flag.equals("3")){
            path_top = img;
            add_top.setImageURI(Uri.parse(path_top));
        }else if (flag.equals("4")){
            path_pant = img;
            add_pants.setImageURI(Uri.parse(path_pant));
        }else if (flag.equals("5")){
            path_shoe = img;
            add_shoe.setImageURI(Uri.parse(path_shoe));
        }

        image_button_hat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(AddCombineActivity.this, ChooseFromDrawersActivity.class);
                intentImage.putExtra("FLAG","1");
                startActivity(intentImage);
            }
        });
        image_button_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(AddCombineActivity.this, ChooseFromDrawersActivity.class);
                intentImage.putExtra("FLAG","2");
                startActivity(intentImage);
            }
        });
        image_button_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(AddCombineActivity.this, ChooseFromDrawersActivity.class);
                intentImage.putExtra("FLAG","3");
                startActivity(intentImage);
            }
        });
        image_button_pants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(AddCombineActivity.this, ChooseFromDrawersActivity.class);
                intentImage.putExtra("FLAG","4");
                startActivity(intentImage);
            }
        });
        image_button_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(AddCombineActivity.this, ChooseFromDrawersActivity.class);
                intentImage.putExtra("FLAG","5");
                startActivity(intentImage);


            }
        });


        combine_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_combine = new Intent(AddCombineActivity.this, CabinActivity.class);

                String name = add_combine_name.getText().toString();
                String text = name + "," + path_hat + "," + path_face + "," + path_top + "," + path_pant + "," + path_shoe;
                int flag = load(name);
                if (flag == 0 && !path_face.equals("") && !path_hat.equals("") && !path_top.equals("") && !path_pant.equals("") && !path_shoe.equals("")) {
                    save_combine(name);
                    save(name,text);
                    startActivity(intent_combine);
                }else if(flag == 1){
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Bu kombin adıyla başka bir çekmece var !", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }else{
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Tüm Alanları Doldurun !", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }


            }
        });


    }

    public int load(String name){

        try {
            FileInputStream fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                if(name.equals(lines)) return 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }
    public void save(String name, String text){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(name+".txt", MODE_APPEND);
            text = text + "\n";
            fos.write(text.getBytes());
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Kombin Kaydedildi !", Toast.LENGTH_LONG);
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

    public void save_combine(String text){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_APPEND);
            text = text + "\n";
            fos.write(text.getBytes());
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


}



