package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_Events;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_EventsCollection;

public class Comments_EventsListActivity extends AppCompatActivity {

    private final static String TAG = Comments_EventsListActivity.class.toString();
    private GetCommentEventTask mGetCommentEventTask = null;
    private Comments_EventsCollection comments_eventsCollection = new Comments_EventsCollection();
    private Comments_EventsCollectionAdapter  adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_comments_events_list);

        // Execute AsyncTask
        mGetCommentEventTask = new GetCommentEventTask(null);
        mGetCommentEventTask.execute((Void) null);

        // set list adapter
        ListView list = (ListView)findViewById(R.id.listcommentevents);
        adapter = new Comments_EventsCollectionAdapter(this, comments_eventsCollection);
        list.setAdapter(adapter);

        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Comments_EventsListActivity.this, Comments_EventsDetailActivity.class);
                String uri = OkupaInfoClient.getLink(comments_eventsCollection.getComments_eventsCollection().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });
        FloatingActionButton fabcommentsevents = (FloatingActionButton) findViewById(R.id.fabcommentsevents);
        fabcommentsevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Selecciona cualquiera de estos eventos para verlos en detalle", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    class GetCommentEventTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetCommentEventTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonCommentEventCollection = null;
            try {
                jsonCommentEventCollection = OkupaInfoClient.getInstance().getComments_EventsCollection(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonCommentEventCollection;
        }

        @Override
        protected void onPostExecute(String jsonCommentEventCollection) {
            Log.d(TAG, jsonCommentEventCollection);
            Comments_EventsCollection comments_eventsCol = (new Gson()).fromJson(jsonCommentEventCollection, Comments_EventsCollection.class);
            for(Comments_Events comments_events : comments_eventsCol.getComments_eventsCollection()){
                comments_eventsCollection.getComments_eventsCollection().add(comments_eventsCollection.getComments_eventsCollection().size(), comments_events);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
