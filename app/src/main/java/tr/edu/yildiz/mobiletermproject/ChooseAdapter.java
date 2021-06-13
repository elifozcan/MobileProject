package tr.edu.yildiz.mobiletermproject;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.RecyclerView;

import static android.support.v4.content.ContextCompat.startActivity;

public class ChooseAdapter extends RecyclerView.Adapter<ChooseAdapter.ViewHolder>{

    ClothesData[] myChooseData;
    Context context;
    ChooseFromDrawersActivity object;
    public ChooseAdapter(ClothesData[] myChooseData, ChooseFromDrawersActivity activity) {
        this.context = activity;
        this.myChooseData = myChooseData;
    }

    String flag = object.getFlag();

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.choose_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final ClothesData chooseDataList = myChooseData[position];
        holder.type.setText(chooseDataList.getClothes_type());
        holder.color.setText(chooseDataList.getColor());
        holder.date.setText(chooseDataList.getPurchase_date());
        holder.price.setText(chooseDataList.getPrice());
        holder.pattern.setText(chooseDataList.getPattern());

        holder.chooseImage.setImageURI(Uri.parse(chooseDataList.getPath()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddCombineActivity.class);
                intent.putExtra("FLAG",flag);
                intent.putExtra("IMG",chooseDataList.getPath());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myChooseData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView type, color, date, price, pattern;
        ImageView chooseImage;


        public ViewHolder( View itemView) {
            super(itemView);
            chooseImage = itemView.findViewById(R.id.choose_clothe_imageview);
            type = itemView.findViewById(R.id.choose_clothe_type);
            price = itemView.findViewById(R.id.choose_clothe_price);
            color = itemView.findViewById(R.id.choose_clothe_color);
            date = itemView.findViewById(R.id.choose_clothe_date);
            pattern = itemView.findViewById(R.id.choose_clothe_pattern);
        }
    }

}
