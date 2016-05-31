package edu.upc.eetac.dsa.okupainfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Event;

public class EventDetailActivity extends AppCompatActivity {

    GetEventTask mGetEventTask = null;
    String uri = null;
    String casalid = null;
    String title = null;
    String description = null;
    private final static String TAG = EventDetailActivity.class.toString();
    TextView textViewCasalid = null;
    TextView textViewTitle = null;
    TextView textViewDescription = null;

    class GetEventTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetEventTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonEvent = null;
            try {
                jsonEvent = OkupaInfoClient.getInstance().getEvent(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonEvent;
        }

        @Override
        protected void onPostExecute(String jsonEvent) {
            Log.d(TAG, jsonEvent);
            Event event = (new Gson()).fromJson(jsonEvent, Event.class);
            casalid = event.getCasalid();
            title = event.getTitle();
            description = event.getDescription();

            textViewCasalid.setText(casalid);
            textViewTitle.setText(title);
            textViewDescription.setText(description);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewCasalid = (TextView) findViewById(R.id.textViewCasalid);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        // Execute AsyncTask
        mGetEventTask = new GetEventTask(uri);
        mGetEventTask.execute((Void) null);


    }
}
