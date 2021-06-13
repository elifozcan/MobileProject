package tr.edu.yildiz.mobiletermproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
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
import java.util.ArrayList;
import java.util.List;

import static android.provider.Telephony.Mms.Part._DATA;

public class AddClothesActivity extends AppCompatActivity {

    private EditText add_clothe_type, add_clothe_color, add_clothe_pattern, add_clothe_price,add_clothe_date;
    private ImageView add_clothe_image;
    private Button image_button, add_clothe_button;
    static final int SELECT_IMAGE = 0;
    Uri imageUri;
    String str;

    public void init(){
        image_button = findViewById(R.id.image_button);
        add_clothe_button = findViewById(R.id.add_clothe_button);
        add_clothe_type = findViewById(R.id.add_clothe_type);
        add_clothe_color = findViewById(R.id.add_clothe_color);
        add_clothe_pattern = findViewById(R.id.add_clothe_pattern);
        add_clothe_price = findViewById(R.id.add_clothe_price);
        add_clothe_date = findViewById(R.id.add_clothe_date);
        add_clothe_image = findViewById(R.id.add_clothe_image);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clothes);
        init();
        Intent intent = getIntent();
        String drawer = intent.getStringExtra("DRAWER");

        image_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentImage = new Intent(Intent.ACTION_GET_CONTENT);
                intentImage.setType("image/*");
                startActivityForResult(intentImage, SELECT_IMAGE);
            }
        });

        add_clothe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add_clothe = new Intent(AddClothesActivity.this, InsideOfDrawerActivity.class );
                //intent_add_clothe.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent_add_clothe.putExtra("DRAWER", drawer);

                String type = add_clothe_type.getText().toString();
                String color = add_clothe_color.getText().toString();
                String pattern = add_clothe_pattern.getText().toString();
                String date = add_clothe_date.getText().toString();
                String price = add_clothe_price.getText().toString();
                String path = imageUri.toString();


                String text = type+","+color+","+pattern+","+date+","+price+","+path;
                int flag = load(drawer, path);
                if (flag == 0 && !type.equals("") && !color.equals("") && !pattern.equals("")&& !date.equals("")&& !price.equals("")&& !path.equals("")){
                   save(drawer, text);
                   startActivity(intent_add_clothe);
                }
                else if(flag == 1){
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Bu Kıyafet Zaten Kayıtlı!", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }
                else{
                    Toast toast_register_error = Toast.makeText(getApplicationContext(), "Tüm Alanları Doldurun !", Toast.LENGTH_LONG);
                    toast_register_error.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast_register_error.show();
                }



            }
        });



    }

    public int load(String drawer, String image_path){

        try {
            FileInputStream fileInputStream = openFileInput(drawer+".txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                String[] part = lines.split(",");
                if(image_path.equals(part[5])) return 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return 0;
    }

    public void save(String drawer,String text){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(drawer+".txt", MODE_APPEND);
            text = text + "\n";
            fos.write(text.getBytes());
            Toast toast_dir = Toast.makeText(getApplicationContext(), "Kıyafet Kaydedildi !", Toast.LENGTH_LONG);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK){
            imageUri = data.getData();
            str = imageUri.toString();
            add_clothe_image.setImageURI(Uri.parse(str));

        }
    }

}