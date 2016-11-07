package vinodhkumar.mt16062_todoapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private Button mAddButton, mCancelButton;
    private EditText mNewTitle, mNewDetail;

    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        mydb = new DBHelper(this.getApplicationContext());

        mAddButton = (Button)findViewById(R.id.addButton);
        mCancelButton = (Button)findViewById(R.id.cancelButton);
        mNewTitle = (EditText)findViewById(R.id.newTitle);
        mNewDetail = (EditText)findViewById(R.id.newDetail);


        mAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    int defaultValue = 0;
                    int index = getIntent().getIntExtra(MainActivity.POSITION,defaultValue);
                    String title = mNewTitle.getText().toString().trim();
                    String detail = mNewDetail.getText().toString().trim();
                    if(title.equals("") || detail.equals("")){
                        Toast.makeText(getApplicationContext(), "Invalid data!", Toast.LENGTH_SHORT).show();
                    }else {
                        mydb.insertInfo(index + 1, mNewTitle.getText().toString(), mNewDetail.getText().toString());
                        setResult(Activity.RESULT_OK);
                        //Toast.makeText(getApplicationContext(), "New task added!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                setResult(Activity.RESULT_OK);
                finish();
            }
        });


    }
}
