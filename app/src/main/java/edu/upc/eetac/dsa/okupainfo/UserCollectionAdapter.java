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
        TextView textViewDescription;
        TextView textViewEmail;
        TextView textViewFullname;

        ViewHolder(View row){
            this.textViewDescription = (TextView) row
                    .findViewById(R.id.textViewDescriptionUser);
            this.textViewEmail = (TextView) row
                    .findViewById(R.id.textViewEmailUser);
            this.textViewFullname = (TextView) row
                    .findViewById(R.id.textViewFullnameUser);
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

        String description = userCollection.getUsers().get(position).getDescription();
        String email = userCollection.getUsers().get(position).getEmail();
        String fullname = userCollection.getUsers().get(position).getFullname();


        viewHolder.textViewDescription.setText(description);
        viewHolder.textViewEmail.setText(email);
        viewHolder.textViewFullname.setText(fullname);
        return convertView;
    }
}
