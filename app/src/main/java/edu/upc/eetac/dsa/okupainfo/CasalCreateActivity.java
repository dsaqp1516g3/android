package edu.upc.eetac.dsa.okupainfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.ws.rs.core.Form;

import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClient;
import edu.upc.eetac.dsa.okupainfo.client.OkupaInfoClientException;

public class CasalCreateActivity extends AppCompatActivity {

    EditText etName = null;
    EditText etEmail = null;
    EditText etDescription = null;
    EditText etLocalization = null;
    Button btCreate = null;
    private CreateCasalTask mCreateCasalTask = null;
    private final static String TAG = CasalCreateActivity.class.toString();
    class CreateCasalTask extends AsyncTask<Void, Void, Boolean> {
        private Form form;

        public CreateCasalTask(Form form) {
            this.form = form;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;
            try
            {
                result = OkupaInfoClient.getInstance().CreateCasal(form);

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
        setContentView(R.layout.activity_casal_create);
        etName = (EditText)findViewById(R.id.CreateCasalName);
        etEmail = (EditText)findViewById(R.id.CreateCasalEmail);
        etDescription = (EditText)findViewById(R.id.CreateCasalDescription);
        etLocalization = (EditText)findViewById(R.id.CreateCasalLocalization);
        btCreate = (Button)findViewById(R.id.CreateCasal);

        btCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(etName.getText().length()!=0 && etEmail.getText().length()!=0 && etDescription.getText().length()!=0 && etLocalization.getText().length()!=0)
                {
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String description = etDescription.getText().toString();
                    String localization = etLocalization.getText().toString();
                    Form form = new Form();
                    form.param("name", name);
                    form.param("email", email);
                    form.param("description", description);
                    form.param("localization", localization);

                    // Execute AsyncTask
                    mCreateCasalTask = new CreateCasalTask(form);
                    mCreateCasalTask.execute((Void) null);
                }


                else
                {
                    Log.d(TAG, "Debes escribir en ambos campos para crear el Casal");
                }
            }

        });
    }

}
