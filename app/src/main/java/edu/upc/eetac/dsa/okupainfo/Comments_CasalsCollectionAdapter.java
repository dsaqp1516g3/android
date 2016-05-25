package edu.upc.eetac.dsa.okupainfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_CasalsCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_CasalsCollectionAdapter extends BaseAdapter {

    private Comments_CasalsCollection comments_casalsCollection;
    private LayoutInflater layoutInflater;

    public Comments_CasalsCollectionAdapter(Context context, Comments_CasalsCollection comments_casalsCollection){
        layoutInflater = LayoutInflater.from(context);
        this.comments_casalsCollection = comments_casalsCollection;
    }

    class ViewHolder{
        TextView textViewCreatorid;
        TextView textViewCasalid;
        TextView textViewContent;

        ViewHolder(View row){
            this.textViewCreatorid = (TextView) row
                    .findViewById(R.id.textViewCreatorid);
            this.textViewCasalid = (TextView) row
                    .findViewById(R.id.textViewCasalid);
            this.textViewContent = (TextView) row
                    .findViewById(R.id.textViewContent);
        }
    }

    @Override
    public int getCount() {
        return comments_casalsCollection.getComments_casals().size();
    }

    @Override
    public Object getItem(int position) {
        return comments_casalsCollection.getComments_casals().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listcommentcasals_row_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String creatorid = comments_casalsCollection.getComments_casals().get(position).getCreatorid();
        String casalid = comments_casalsCollection.getComments_casals().get(position).getCasalid();
        String content = comments_casalsCollection.getComments_casals().get(position).getContent();


        viewHolder.textViewCreatorid.setText(creatorid);
        viewHolder.textViewCasalid.setText(casalid);
        viewHolder.textViewContent.setText(content);
        return convertView;
    }
}
