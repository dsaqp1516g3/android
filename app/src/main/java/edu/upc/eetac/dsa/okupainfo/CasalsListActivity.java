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
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;
import edu.upc.eetac.dsa.okupainfo.client.entity.Casal;
import edu.upc.eetac.dsa.okupainfo.client.entity.CasalCollection;

public class CasalsListActivity extends AppCompatActivity {

    private final static String TAG = CasalsListActivity.class.toString();
    private GetCasalsTask mGetCasalsTask = null;
    private CasalCollection casals = new CasalCollection();
    private CasalCollectionAdapter adapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casals_list);

        Toolbar toolbarcasals = (Toolbar) findViewById(R.id.toolbarcasals);
        setSupportActionBar(toolbarcasals);
        // Execute AsyncTask
        mGetCasalsTask = new GetCasalsTask(null);
        mGetCasalsTask.execute((Void) null);

        // set list adapter
        ListView listacasals = (ListView)findViewById(R.id.listacasals);
        adapter = new CasalCollectionAdapter(this, casals);
        listacasals.setAdapter(adapter);

        // set list OnItemClick listener
        listacasals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CasalsListActivity.this, CasalDetailActivity.class);
                String uri = OkupaInfoClient.getLink(casals.getCasals().get(position).getLinks(), "self").getUri().toString();
                intent.putExtra("uri", uri);
                startActivity(intent);
            }
        });
        FloatingActionButton fabcasals = (FloatingActionButton) findViewById(R.id.fabcasals);
        fabcasals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Selecciona cualquiera de estos casals para verlos en detalle", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button CrearCasal = (Button) findViewById(R.id.CrearCasalList);
        CrearCasal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CasalsListActivity.this, CasalCreateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button VolverMenu = (Button) findViewById(R.id.VolverMenuList);
        VolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CasalsListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    class GetCasalsTask extends AsyncTask<Void, Void, String> {

        private String uri;

        public GetCasalsTask(String uri) {
            this.uri = uri;
        }

        @Override
        protected String doInBackground(Void... params) {
            String jsonCasalCollection = null;
            try {
                jsonCasalCollection = OkupaInfoClient.getInstance().getCasals(uri);
            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }
            return jsonCasalCollection;
        }

        @Override
        protected void onPostExecute(String jsonCasalCollection) {
            Log.d(TAG, jsonCasalCollection);
            CasalCollection casalCollection = (new Gson()).fromJson(jsonCasalCollection, CasalCollection.class);
            for (Casal casal : casalCollection.getCasals()) {
                casals.getCasals().add(casals.getCasals().size(), casal);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
