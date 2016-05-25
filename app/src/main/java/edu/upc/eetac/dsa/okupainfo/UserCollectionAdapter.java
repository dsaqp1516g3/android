package edu.upc.eetac.dsa.okupainfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.okupainfo.client.entity.UserCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class UserCollectionAdapter extends BaseAdapter {

    private UserCollection userCollection;
    private LayoutInflater layoutInflater;

    public UserCollectionAdapter(Context context, UserCollection userCollection){
        layoutInflater = LayoutInflater.from(context);
        this.userCollection = userCollection;
    }

    class ViewHolder{
        TextView textViewLoginid;
        TextView textViewEmail;
        TextView textViewFullname;

        ViewHolder(View row){
            this.textViewLoginid = (TextView) row
                    .findViewById(R.id.textViewLoginid);
            this.textViewEmail = (TextView) row
                    .findViewById(R.id.textViewEmail);
            this.textViewFullname = (TextView) row
                    .findViewById(R.id.textViewFullname);
        }
    }

    @Override
    public int getCount() {
        return userCollection.getUsers().size();
    }

    @Override
    public Object getItem(int position) {
        return userCollection.getUsers().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listusers_row_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String loginid = userCollection.getUsers().get(position).getLoginid();
        String email = userCollection.getUsers().get(position).getEmail();
        String fullname = userCollection.getUsers().get(position).getFullname();


        viewHolder.textViewLoginid.setText(loginid);
        viewHolder.textViewEmail.setText(email);
        viewHolder.textViewFullname.setText(fullname);
        return convertView;
    }
}
