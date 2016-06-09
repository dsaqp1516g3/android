package edu.upc.eetac.dsa.okupainfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_EventsCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_EventsCollectionAdapter extends BaseAdapter {

    private Comments_EventsCollection comments_eventsCollection;
    private LayoutInflater layoutInflater;

    public Comments_EventsCollectionAdapter(Context context, Comments_EventsCollection comments_eventsCollection){
        layoutInflater = LayoutInflater.from(context);
        this.comments_eventsCollection = comments_eventsCollection;
    }

    class ViewHolder{
        TextView textViewCreatorid;
        TextView textViewEventoid;
        TextView textViewContent;

        ViewHolder(View row){
            this.textViewCreatorid = (TextView) row
                    .findViewById(R.id.textViewCreatoridCommentEvent);
            this.textViewEventoid = (TextView) row
                    .findViewById(R.id.textViewEventoidCommentEvent);
            this.textViewContent = (TextView) row
                    .findViewById(R.id.textViewContentCommentEvent);
        }
    }

    @Override
    public int getCount() {
        return comments_eventsCollection.getComments_eventsCollection().size();
    }

    @Override
    public Object getItem(int position) {
        return comments_eventsCollection.getComments_eventsCollection().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listcommentevents_row_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String creatorid = comments_eventsCollection.getComments_eventsCollection().get(position).getCreatorid();
        String eventoid = comments_eventsCollection.getComments_eventsCollection().get(position).getEventoid();
        String content = comments_eventsCollection.getComments_eventsCollection().get(position).getContent();


        viewHolder.textViewCreatorid.setText(creatorid);
        viewHolder.textViewEventoid.setText(eventoid);
        viewHolder.textViewContent.setText(content);
        return convertView;
    }
}
