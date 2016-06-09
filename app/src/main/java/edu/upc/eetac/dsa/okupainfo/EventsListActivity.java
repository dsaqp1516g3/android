package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Event;
import edu.upc.eetac.dsa.okupainfo.client.entity.EventCollection;

public class EventsListActivity extends AppCompatActivity {

    private final static String TAG = EventsListActivity.class.toString();
    private GetEventsTask mGetEventsTask = null;
    private EventCollection events = new EventCollection();
    private EventCollectionAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        Toolbar toolbarevents = (Toolbar) findViewById(R.id.toolbarevents);
        setSupportActionBar(toolbarevents);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Execute AsyncTask
        mGetEventsTask = new GetEventsTask(null);
        mGetEventsTask.execute((Void) null);

        // set list adapter
        ListView listaevents = (ListView)findViewById(R.id.listaevents);
        adapter = new EventCollectionAdapter(this, events);
        listaevents.setAdapter(adapter);

        // set list OnItemClick listener
        listaevents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventsListActivity.this, EventDetailActivity.class);
                String uri = OkupaInfoClient.getLink(events.getEvents().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });
        FloatingActionButton fabevents = (FloatingActionButton) findViewById(R.id.fabevents);
        fabevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Selecciona cualquiera de estos eventos para verlos en detalle", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    class GetEventsTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetEventsTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonEventCollection = null;
            try {
                jsonEventCollection = OkupaInfoClient.getInstance().getEvents(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonEventCollection;
        }

        @Override
        protected void onPostExecute(String jsonEventCollection) {
            Log.d(TAG, jsonEventCollection);
            EventCollection eventCollection = (new Gson()).fromJson(jsonEventCollection, EventCollection.class);
            for(Event event : eventCollection.getEvents()){
                events.getEvents().add(events.getEvents().size(), event);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
