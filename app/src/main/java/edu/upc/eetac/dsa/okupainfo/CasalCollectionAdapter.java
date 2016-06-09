package edu.upc.eetac.dsa.okupainfo;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import edu.upc.eetac.dsa.okupainfo.client.entity.CasalCollection;

/**
 * Created by Guillermo on 09/06/2016.
 */
public class CasalCollectionAdapter extends AppCompatActivity implements ListAdapter {

    private CasalCollection casalCollection;
    private LayoutInflater layoutInflater;

    public CasalCollectionAdapter(Context context, CasalCollection casalCollection){
        layoutInflater = LayoutInflater.from(context);
        this.casalCollection = casalCollection;
    }
    class ViewHolder{
        TextView textViewName;
        TextView textViewEmail;

        ViewHolder(View row){
            this.textViewName = (TextView) row
                    .findViewById(R.id.textViewNameCasal);
            this.textViewEmail = (TextView) row
                    .findViewById(R.id.textViewEmailCasal);
        }
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    public void notifyDataSetChanged() {
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return casalCollection.getCasals().size();
    }

    @Override
    public Object getItem(int position) {
        return casalCollection.getCasals().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listcasal_row_data, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String name = casalCollection.getCasals().get(position).getName();
        String email = casalCollection.getCasals().get(position).getEmail();

        viewHolder.textViewName.setText(name);
        viewHolder.textViewEmail.setText(email);
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}

