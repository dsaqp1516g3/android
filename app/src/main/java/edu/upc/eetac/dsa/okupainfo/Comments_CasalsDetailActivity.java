package edu.upc.eetac.dsa.okupainfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_Casals;

public class Comments_CasalsDetailActivity extends AppCompatActivity {
    GetCommentCasalTask mGetCommentCasalTask = null;
    String uri = null;
    String casalid = null;
    String creatorid = null;
    String content = null;
    Long creationtimestamp = null;
    Long lastmodified = null;
    private final static String TAG = Comments_CasalsDetailActivity.class.toString();
    TextView textViewCreatorid = null;
    TextView textViewCasalid = null;
    TextView textViewContent = null;
    TextView textViewCreationTimestamp = null;
    TextView textViewLastModified = null;

    class GetCommentCasalTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetCommentCasalTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonComments_Casals = null;
            try {
                jsonComments_Casals = OkupaInfoClient.getInstance().getComments_Casals(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonComments_Casals;
        }

        @Override
        protected void onPostExecute(String jsonComments_Casals) {
            Log.d(TAG, jsonComments_Casals);
            Comments_Casals comments_casals = (new Gson()).fromJson(jsonComments_Casals, Comments_Casals.class);
            creatorid = comments_casals.getCreatorid();
            casalid = comments_casals.getCasalid();
            content = comments_casals.getContent();
            creationtimestamp = comments_casals.getCreationTimestamp();
            lastmodified = comments_casals.getLastModified();

            String creationTimestamp = String.valueOf(creationtimestamp);
            String lastModified = String.valueOf(lastmodified);

            textViewCreatorid.setText(creatorid);
            textViewCasalid.setText(casalid);
            textViewContent.setText(content);
            textViewCreationTimestamp.setText(creationTimestamp);
            textViewLastModified.setText(lastModified);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_casals_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewCreatorid = (TextView) findViewById(R.id.textViewCreatoridDetailCommentCasal);
        textViewCasalid = (TextView) findViewById(R.id.textViewCasalidDetailCommentCasal);
        textViewContent = (TextView) findViewById(R.id.textViewContentDetailCommentCasal);
        textViewCreationTimestamp = (TextView) findViewById(R.id.textViewCreationTimestampDetailCommentCasal);
        textViewLastModified = (TextView) findViewById(R.id.textViewLastModifiedDetailCommentCasal);

        // Execute AsyncTask
        mGetCommentCasalTask = new GetCommentCasalTask(uri);
        mGetCommentCasalTask.execute((Void) null);


    }
}
