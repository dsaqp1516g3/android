package edu.upc.eetac.dsa.okupainfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_Events;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_EventsDetailActivity extends AppCompatActivity {
    GetCommentEventTask mGetCommentEventTask = null;
    String uri = null;
    String creatorid = null;
    String eventoid = null;
    String content = null;
    private final static String TAG = Comments_EventsDetailActivity.class.toString();
    TextView textViewCreatorid = null;
    TextView textViewEventoid = null;
    TextView textViewContent = null;

    class GetCommentEventTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetCommentEventTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonCommentEvent = null;
            try {
                jsonCommentEvent = OkupaInfoClient.getInstance().getComments_Events(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonCommentEvent;
        }

        @Override
        protected void onPostExecute(String jsonCommentEvent) {
            Log.d(TAG, jsonCommentEvent);
            Comments_Events comments_events = (new Gson()).fromJson(jsonCommentEvent, Comments_Events.class);
            creatorid = comments_events.getCreatorid();
            eventoid = comments_events.getEventoid();
            content = comments_events.getContent();

            textViewCreatorid.setText(creatorid);
            textViewEventoid.setText(eventoid);
            textViewContent.setText(content);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentevent_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewCreatorid = (TextView) findViewById(R.id.textViewCreatorid);
        textViewEventoid = (TextView) findViewById(R.id.textViewEventoid);
        textViewContent = (TextView) findViewById(R.id.textViewContent);

        // Execute AsyncTask
        mGetCommentEventTask = new GetCommentEventTask(uri);
        mGetCommentEventTask.execute((Void) null);


    }
}
