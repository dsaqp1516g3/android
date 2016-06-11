package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class UsersListActivity extends AppCompatActivity {

                    private final static String TAG = UsersListActivity.class.toString();
                    private GetUsersTask mGetUsersTask = null;
                    private UserCollection users = new UserCollection();
                    private UserCollectionAdapter  adapter = null;

                    @Override
                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_users_list);

                        Toolbar toolbarevents = (Toolbar) findViewById(R.id.toolbarusers);
                        setSupportActionBar(toolbarevents);
                        // Execute AsyncTask
                        mGetUsersTask = new GetUsersTask(null);
                        mGetUsersTask.execute((Void) null);

                        // set list adapter
                        ListView list = (ListView)findViewById(R.id.listausers);
                        adapter = new UserCollectionAdapter(this, users);
                        list.setAdapter(adapter);

                        // set list OnItemClick listener
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(UsersListActivity.this, UserDetailActivity.class);
                                String uri = OkupaInfoClient.getLink(users.getUsers().get(position).getLinks(), "self").getUri().toString();
                                intent.putExtra("uri", uri);
                                startActivity(intent);
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
                                users.getUsers().add(users.getUsers().size(), user);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                }

