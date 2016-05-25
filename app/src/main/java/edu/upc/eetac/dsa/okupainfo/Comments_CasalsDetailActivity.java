package edu.upc.eetac.dsa.okupainfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Comments_Casals;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class Comments_CasalsDetailActivity extends AppCompatActivity {
    GetCommentCasalTask mGetCommentCasalTask = null;
    String uri = null;
    String casalid = null;
    String creatorid = null;
    String content = null;
    private final static String TAG = Comments_CasalsDetailActivity.class.toString();
    TextView textViewCreatorid = null;
    TextView textViewCasalid = null;
    TextView textViewContent = null;

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

            textViewCreatorid.setText(creatorid);
            textViewCasalid.setText(casalid);
            textViewContent.setText(content);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentcasal_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewCreatorid = (TextView) findViewById(R.id.textViewCreatorid);
        textViewCasalid = (TextView) findViewById(R.id.textViewCasalid);
        textViewContent = (TextView) findViewById(R.id.textViewContent);

        // Execute AsyncTask
        mGetCommentCasalTask = new GetCommentCasalTask(uri);
        mGetCommentCasalTask.execute((Void) null);


    }
}
