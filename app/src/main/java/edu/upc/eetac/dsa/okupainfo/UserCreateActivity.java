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

public class UserCreateActivity extends AppCompatActivity {
    EditText etLoginid = null;
    EditText etEmail = null;
    EditText etFullname = null;
    EditText etDescription = null;
    Button btCreate = null;
    private CreateUserTask mCreateUserTask = null;
    private final static String TAG = LoginActivity.class.toString();
    class CreateUserTask extends AsyncTask<Void, Void, Boolean> {
        private Form form;

        public CreateUserTask(Form form) {
            this.form = form;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;
            try
            {
                result = OkupaInfoClient.getInstance().CreateUser(form);

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
        setContentView(R.layout.activity_user_create);
        etLoginid = (EditText)findViewById(R.id.LoginidCreateUser);
        etEmail = (EditText)findViewById(R.id.EmailCreateUser);
        etFullname = (EditText)findViewById(R.id.FullnameCreateUser);
        etDescription = (EditText)findViewById(R.id.DescriptionCreateUser);
        btCreate = (Button)findViewById(R.id.CreateUser);

        btCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(etLoginid.getText().length()!=0 && etEmail.getText().length()!=0 && etFullname.getText().length()!=0 && etDescription.getText().length()!=0)
                {
                    String loginid = etLoginid.getText().toString();
                    String email = etEmail.getText().toString();
                    String fullname = etFullname.getText().toString();
                    String description = etDescription.getText().toString();
                    Form form = new Form();
                    form.param("loginid", loginid);
                    form.param("email", email);
                    form.param("fullname", fullname);
                    form.param("description", description);

                    // Execute AsyncTask
                    mCreateUserTask = new CreateUserTask(form);
                    mCreateUserTask.execute((Void) null);
                }


                else
                {
                    Log.d(TAG, "Debes escribir todos los campos para crear el Usuario");
                }
            }

        });
    }

}
