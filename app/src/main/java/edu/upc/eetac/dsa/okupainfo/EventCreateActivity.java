package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.ws.rs.core.Form;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;

/**
 * Created by Guillermo on 24/05/2016.
 */
public class EventCreateActivity extends AppCompatActivity {
    EditText etTitle = null;
    EditText etDescription = null;
    Button btCreate = null;
    private CreateEventTask mCreateEventTask = null;
    private final static String TAG = CasalsListActivity.class.toString();
    class CreateEventTask extends AsyncTask<Void, Void, Boolean> {
        private Form form;

        public CreateEventTask(Form form) {
            this.form = form;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;
            try
            {
                result = OkupaInfoClient.getInstance().CreateEvent(form);

            } catch (OkupaInfoClientException e) {
                // TODO: Handle gracefully
                Log.d(TAG, e.getMessage());
            }

            return result;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if (result == true) {
                Intent i = getIntent();
                setResult(RESULT_OK, i);
                finish();
            }

            if (result == false) {
                Intent i = getIntent();
                setResult(RESULT_CANCELED, i);
                finish();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);
        etTitle = (EditText)findViewById(R.id.Title);
        etDescription = (EditText)findViewById(R.id.Description);
        btCreate = (Button)findViewById(R.id.Create);

        btCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(etTitle.getText().length()!=0 && etDescription.getText().length()!=0)
                {
                    String title = etTitle.getText().toString();
                    String description = etDescription.getText().toString();
                    Form form = new Form();
                    form.param("title", title);
                    form.param("description", description);

                    // Execute AsyncTask
                    mCreateEventTask = new CreateEventTask(form);
                    mCreateEventTask.execute((Void) null);
                }


                else
                {
                    Log.d(TAG, "Debes escribir en ambos campos para crear el Evento");
                }
            }

        });
    }

}
