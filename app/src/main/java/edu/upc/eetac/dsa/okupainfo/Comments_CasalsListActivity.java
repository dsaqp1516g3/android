package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_Casals;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_CasalsCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_CasalsListActivity extends AppCompatActivity {

    private final static String TAG = Comments_CasalsListActivity.class.toString();
    private GetCommentCasalTask mGetCommentCasalTask = null;
    private Comments_CasalsCollection comments_casals = new Comments_CasalsCollection();
    private Comments_CasalsCollectionAdapter  adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_commentcasals_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Execute AsyncTask
        mGetCommentCasalTask = new GetCommentCasalTask(null);
        mGetCommentCasalTask.execute((Void) null);

        // set list adapter
        ListView list = (ListView)findViewById(R.id.listcommentcasals);
        Comments_CasalsCollectionAdapter  adapter = new Comments_CasalsCollectionAdapter(this, comments_casals);
        list.setAdapter(adapter);

        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Comments_CasalsListActivity.this, Comments_CasalsDetailActivity.class);
                String uri = OkupaInfoClient.getLink(comments_casals.getComments_casals().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    class GetCommentCasalTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetCommentCasalTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonComments_CasalsCollection = null;
            try {
                jsonComments_CasalsCollection = OkupaInfoClient.getInstance().getComments_CasalsCollection(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonComments_CasalsCollection;
        }

        @Override
        protected void onPostExecute(String jsonComments_CasalsCollection) {
            Log.d(TAG, jsonComments_CasalsCollection);
            Comments_CasalsCollection comments_casalsCollection = (new Gson()).fromJson(jsonComments_CasalsCollection, Comments_CasalsCollection.class);
            for(Comments_Casals comments_casals : comments_casalsCollection.getComments_casals()){
                comments_casalsCollection.getComments_casals().add(comments_casalsCollection.getComments_casals().size(), comments_casals);
            }
            adapter.notifyDataSetChanged();
        }
    }
}