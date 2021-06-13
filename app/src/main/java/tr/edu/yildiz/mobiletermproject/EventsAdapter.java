package tr.edu.yildiz.mobiletermproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class EventsAdapter  extends RecyclerView.Adapter<EventsAdapter.ViewHolder>{

    EventsData[] myEventData;
    Context context;

    public EventsAdapter(EventsData[] myEventData, EventsActivity activity) {
        this.myEventData = myEventData;
        this.context = activity;
    }

    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.event_item_list,parent,false);
        EventsAdapter.ViewHolder viewHolder = new EventsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsAdapter.ViewHolder holder, int position) {
        final EventsData eventsDataList = myEventData[position];
        holder.eventName.setText(eventsDataList.getEventName());
        holder.eventType.setText(eventsDataList.getEventType());
        holder.eventLocation.setText(eventsDataList.getEventLocation());
        holder.eventDate.setText(eventsDataList.getEventDate());
        holder.eventImage.setImageResource(eventsDataList.getEventImage());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = eventsDataList.getEventName();
                Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), InsideOfEventActivity.class);
                str = str.split("\n")[0];
                intent.putExtra("EVENT",str);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return myEventData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView eventName, eventType, eventLocation, eventDate;
        ImageView eventImage;


        public ViewHolder( View itemView) {
            super(itemView);
            eventImage = itemView.findViewById(R.id.event_imageview);
            eventName = itemView.findViewById(R.id.eventName);
            eventType = itemView.findViewById(R.id.eventType);
            eventLocation = itemView.findViewById(R.id.eventLocation);
            eventDate = itemView.findViewById(R.id.eventDate);



        }
    }




}
