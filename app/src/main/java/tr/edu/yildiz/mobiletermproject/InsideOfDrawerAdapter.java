package tr.edu.yildiz.mobiletermproject;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class InsideOfDrawerAdapter extends RecyclerView.Adapter<InsideOfDrawerAdapter.ViewHolder>{

    ClothesData[] myClothesData;
    Context context;

    public InsideOfDrawerAdapter(ClothesData[] myClothesData, InsideOfDrawerActivity activity) {
        this.myClothesData = myClothesData;
        this.context = activity;
    }

    @Override
    public InsideOfDrawerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.inside_drawer_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(InsideOfDrawerAdapter.ViewHolder holder, int position) {
        final ClothesData clothesDataList = myClothesData[position];
        holder.clothe_type.setText(clothesDataList.getClothes_type());
        holder.clothe_pattern.setText(clothesDataList.getPattern());
        holder.clothe_price.setText(clothesDataList.getPrice());
        holder.clothe_date.setText(clothesDataList.getPurchase_date());
        holder.clothe_color.setText(clothesDataList.getColor());
        Uri imageUri;
        if (clothesDataList.getPath().split("\n")[0].substring(0,21).equals("content://com.android")) {
            String [] photo_split= clothesDataList.getPath().split("%3A");
            String imageUriBasePath = "content://media/external/images/media/"+photo_split[1].split("\n")[0];
            imageUri= Uri.parse(imageUriBasePath);
            holder.clothe_image.setImageURI(imageUri);
        }
        //holder.clothe_image.setImageURI(clothesDataList.getClotheUri());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return myClothesData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView clothe_type, clothe_pattern, clothe_price, clothe_date, clothe_color;
        ImageView clothe_image;


        public ViewHolder( View itemView) {
            super(itemView);
            clothe_image = itemView.findViewById(R.id.clothe_imageview);
            clothe_type = itemView.findViewById(R.id.clothe_type);
            clothe_color = itemView.findViewById(R.id.clothe_color);
            clothe_date = itemView.findViewById(R.id.clothe_date);
            clothe_price = itemView.findViewById(R.id.clothe_price);
            clothe_pattern = itemView.findViewById(R.id.clothe_pattern);


        }
    }

}
