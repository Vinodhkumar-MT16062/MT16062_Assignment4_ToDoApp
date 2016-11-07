package vinodhkumar.mt16062_todoapp;

import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayTaskActivity extends AppCompatActivity {
//public class DisplayTaskActivity extends FragmentActivity {
    TextView mDisplayDetail;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);

//        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//        viewPager.setAdapter(new CustomPagerAdapter(this));














        mydb = new DBHelper(this.getApplicationContext());

        //mDisplayDetail = (TextView)findViewById(R.id.displayDetail);
        int defaultValue = 0;
        String title;
        String detail;
        int id = getIntent().getIntExtra(MainActivity.POSITION,defaultValue);
        Cursor res;
        try {
            res = mydb.getInfo(id);
            //int count = res.getCount();
            res.moveToFirst();
            title = res.getString(res.getColumnIndex("name"));
            detail = res.getString(res.getColumnIndex("detail"));
            this.setTitle(title);
            mDisplayDetail.setText(detail);
            setResult(Activity.RESULT_OK);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
