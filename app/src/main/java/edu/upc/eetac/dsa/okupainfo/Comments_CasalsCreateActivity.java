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

public class Comments_CasalsCreateActivity extends AppCompatActivity {
    EditText etContent = null;
    Button btCreate = null;
    private CreateCommentCasalTask mCreateCommentCasalTask = null;
    private final static String TAG = Comments_CasalsCreateActivity.class.toString();
    class CreateCommentCasalTask extends AsyncTask<Void, Void, Boolean> {
        private Form form;

        public CreateCommentCasalTask(Form form) {
            this.form = form;

        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean result = false;
            try
            {
                result = OkupaInfoClient.getInstance().CreateCommentCasal(form);

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
        setContentView(R.layout.activity_comments_casals_create);
        etContent = (EditText)findViewById(R.id.ContentCommentsCasalsCreate);
        btCreate = (Button)findViewById(R.id.CreateCommentsCasals);

        btCreate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(etContent.getText().length()!=0)
                {
                    String content = etContent.getText().toString();
                    Form form = new Form();
                    form.param("content", content);

                    // Execute AsyncTask
                    mCreateCommentCasalTask = new CreateCommentCasalTask(form);
                    mCreateCommentCasalTask.execute((Void) null);
                }


                else
                {
                    Log.d(TAG, "Debes escribir en ambos campos para crear el Casal");
                }
            }

        });
    }

}

