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

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.ViewHolder>{

    DrawerData[] myDrawerData;
    Context context;

    public DrawerAdapter(DrawerData[] myDrawerData, ShowDrawerActivity activity) {
        this.context = activity;
        this.myDrawerData = myDrawerData;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.drawer_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        final DrawerData drawerDataList = myDrawerData[position];
        holder.drawerName.setText(drawerDataList.getDrawerName());
        holder.drawerImage.setImageResource(drawerDataList.getDrawerImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = drawerDataList.getDrawerName();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), InsideOfDrawerActivity.class);
                str = str.split("\n")[0];
                intent.putExtra("DRAWER",str);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return myDrawerData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView drawerName;
        ImageView drawerImage;


        public ViewHolder( View itemView) {
            super(itemView);
            drawerImage = itemView.findViewById(R.id.imageview);
            drawerName = itemView.findViewById(R.id.drawerName);


        }
    }

}
