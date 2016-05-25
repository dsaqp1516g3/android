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
import edu.upc.eetac.dsa.okupainfo.client.entity.User;
import edu.upc.eetac.dsa.okupainfo.client.entity.UserCollection;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class UsersListActivity extends AppCompatActivity {

    private final static String TAG = CasalsListActivity.class.toString();
    private GetUsersTask mGetUsersTask = null;
    private UserCollection userCollection = new UserCollection();
    private UserCollectionAdapter  adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_users_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Execute AsyncTask
        mGetUsersTask = new GetUsersTask(null);
        mGetUsersTask.execute((Void) null);

        // set list adapter
        ListView list = (ListView)findViewById(R.id.listusers);
        UserCollectionAdapter  adapter = new UserCollectionAdapter(this, userCollection);
        list.setAdapter(adapter);

        // set list OnItemClick listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(UsersListActivity.this, UserDetailActivity.class);
                String uri = OkupaInfoClient.getLink(userCollection.getUsers().get(position).getLinks(), "self").getUri().toString();
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

    class GetUsersTask extends AsyncTask<Void, Void, String> {
        private String uri;

        public GetUsersTask(String uri) {
            this.uri = uri;

        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonUserCollection = null;
            try {
                jsonUserCollection = OkupaInfoClient.getInstance().getUsers(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonUserCollection;
        }

        @Override
        protected void onPostExecute(String jsonUserCollection) {
            Log.d(TAG, jsonUserCollection);
            UserCollection userCollection = (new Gson()).fromJson(jsonUserCollection, UserCollection.class);
            for(User user : userCollection.getUsers()){
                userCollection.getUsers().add(userCollection.getUsers().size(), user);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
