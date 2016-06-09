package edu.upc.eetac.dsa.okupainfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.okupainfo.client.entity.EventCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class EventCollectionAdapter extends BaseAdapter {

    private EventCollection eventCollection;
    private LayoutInflater layoutInflater;

    public EventCollectionAdapter(Context context, EventCollection eventCollection){
        layoutInflater = LayoutInflater.from(context);
        this.eventCollection = eventCollection;
    }

    class ViewHolder{
        TextView textViewEventdate;
        TextView textViewTitle;
        TextView textViewDescription;

        ViewHolder(View row){
            this.textViewEventdate = (TextView) row
                    .findViewById(R.id.textViewEventdateEvent);
            this.textViewTitle = (TextView) row
                    .findViewById(R.id.textViewTitleEvent);
            this.textViewDescription = (TextView) row
                    .findViewById(R.id.textViewDescriptionEvent);
        }
    }

    @Override
    public int getCount() {
        return eventCollection.getEvents().size();
    }

    @Override
    public Object getItem(int position) {
        return eventCollection.getEvents().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listevent_row_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Long date = eventCollection.getEvents().get(position).getEventdate();
        String title = eventCollection.getEvents().get(position).getTitle();
        String description = eventCollection.getEvents().get(position).getDescription();
        String eventdate = String.valueOf(date);

        viewHolder.textViewEventdate.setText(eventdate);
        viewHolder.textViewTitle.setText(title);
        viewHolder.textViewDescription.setText(description);
        return convertView;
    }
}
