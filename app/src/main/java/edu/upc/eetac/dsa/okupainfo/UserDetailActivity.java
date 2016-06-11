package edu.upc.eetac.dsa.okupainfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.User;

public class UserDetailActivity extends AppCompatActivity {
    GetUserTask mGetUserTask = null;
    String uri = null;
    String loginid = null;
    String email = null;
    String fullname = null;
    String description = null;
    private final static String TAG = UserDetailActivity.class.toString();
    TextView textViewLoginid = null;
    TextView textViewEmail = null;
    TextView textViewFullname = null;
    TextView textViewDescription = null;

    class GetUserTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetUserTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonUser = null;
            try {
                jsonUser = OkupaInfoClient.getInstance().getUser(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonUser;
        }

        @Override
        protected void onPostExecute(String jsonUser) {
            Log.d(TAG, jsonUser);
            User user = (new Gson()).fromJson(jsonUser, User.class);
            loginid = user.getLoginid();
            email = user.getEmail();
            fullname = user.getFullname();
            description = user.getDescription();

            textViewLoginid.setText(loginid);
            textViewEmail.setText(email);
            textViewFullname.setText(fullname);
            textViewDescription.setText(description);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        uri = (String) getIntent().getExtras().get("uri");

        textViewLoginid = (TextView) findViewById(R.id.textViewLoginidDetailUser);
        textViewEmail = (TextView) findViewById(R.id.textViewEmailDetailUser);
        textViewFullname = (TextView) findViewById(R.id.textViewFullnameDetailUser);
        textViewDescription = (TextView) findViewById(R.id.textViewDescriptionDetailUser);

        // Execute AsyncTask
        mGetUserTask = new GetUserTask(uri);
        mGetUserTask.execute((Void) null);


    }
}
