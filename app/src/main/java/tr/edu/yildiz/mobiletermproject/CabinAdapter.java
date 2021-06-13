package tr.edu.yildiz.mobiletermproject;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.widget.RecyclerView;

import static android.support.v4.content.ContextCompat.startActivity;

public class CabinAdapter extends RecyclerView.Adapter<CabinAdapter.ViewHolder>{

    CabinData[] myCabinData;
    Context context;

    public CabinAdapter(CabinData[] myDrawerData, CabinActivity activity) {
        this.context = activity;
        this.myCabinData = myDrawerData;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cabin_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final CabinData cabinDataList = myCabinData[position];
        holder.cabinName.setText(cabinDataList.getCombineName());
        holder.cabinImage.setImageResource(cabinDataList.getCabinImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = cabinDataList.getCombineName();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ShowCombineActivity.class);
                str = str.split("\n")[0];
                intent.putExtra("CABIN",str);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myCabinData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cabinName;
        ImageView cabinImage;


        public ViewHolder( View itemView) {
            super(itemView);
            cabinImage = itemView.findViewById(R.id.cabin_imageview);
            cabinName = itemView.findViewById(R.id.combineName);


        }
    }

}
