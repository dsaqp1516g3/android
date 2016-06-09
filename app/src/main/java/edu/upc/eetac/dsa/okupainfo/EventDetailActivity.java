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
    //String uri = "http://10.0.2.2:8080/okupainfo/events";
    String uri = null;
    String casalid = null;
    String title = null;
    String description = null;
    String localization = null;
    Double latitude = null;
    Double longitude = null;
    Long eventdate = null;
    Long creationtimestamp = null;
    Long lastmodified = null;
    private final static String TAG = EventDetailActivity.class.toString();
    TextView textViewCasalid = null;
    TextView textViewTitle = null;
    TextView textViewDescription = null;
    TextView textViewLocalization = null;
    TextView textViewLatitude = null;
    TextView textViewLongitude = null;
    TextView textViewCreationTimestamp = null;
    TextView textViewLastModified = null;
    TextView textViewEventdate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewCasalid = (TextView) findViewById(R.id.textViewCasalidDetailCasal);
        textViewTitle = (TextView) findViewById(R.id.textViewTitleDetailCasal);
        textViewDescription = (TextView) findViewById(R.id.textViewDescriptionDetailCasal);
        textViewLocalization = (TextView) findViewById(R.id.textViewLocalizationDetailCasal);
        textViewLatitude = (TextView) findViewById(R.id.textViewLatitudeDetailCasal);
        textViewLongitude = (TextView) findViewById(R.id.textViewLongitudeDetailCasal);
        textViewEventdate = (TextView) findViewById(R.id.textViewEventdateDetailCasal);
        textViewCreationTimestamp = (TextView) findViewById(R.id.textViewCreationTimestampDetailCasal);
        textViewLastModified = (TextView) findViewById(R.id.textViewLastModifiedDetailCasal);

        // Execute AsyncTask
        mGetEventTask = new GetEventTask(uri);
        mGetEventTask.execute((Void) null);
    }

    class GetEventTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetEventTask(String uri) {
            this.uri = uri;;

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
            localization = event.getLocalization();
            latitude = event.getLatitude();
            longitude = event.getLongitude();
            eventdate = event.getEventdate();
            creationtimestamp = event.getCreationTimestamp();
            lastmodified = event.getLastModified();

            String latitudee = String.valueOf(latitude);
            String longitudee = String.valueOf(longitude);
            String eventDate = String.valueOf(eventdate);
            String creationTimestamp = String.valueOf(creationtimestamp);
            String lastModified = String.valueOf(lastmodified);

            textViewCasalid.setText(casalid);
            textViewTitle.setText(title);
            textViewDescription.setText(description);
            textViewLocalization.setText(localization);
            textViewLatitude.setText(latitudee);
            textViewLongitude.setText(longitudee);
            textViewEventdate.setText(eventDate);
            textViewCreationTimestamp.setText(creationTimestamp);
            textViewLastModified.setText(lastModified);
        }
    }


}
