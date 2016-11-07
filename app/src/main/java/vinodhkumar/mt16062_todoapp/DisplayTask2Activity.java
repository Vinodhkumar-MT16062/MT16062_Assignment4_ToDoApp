package vinodhkumar.mt16062_todoapp;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by VinodhKumar on 07/11/16.
 */

public class DisplayTask2Activity extends AppCompatActivity {
    ViewPager view = null;
    int id;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task);

        mydb = new DBHelper(this.getApplicationContext());
        id = getIntent().getIntExtra(MainActivity.POSITION,0);

        view = (ViewPager)findViewById(R.id.viewPager);
        view.setAdapter(new MyAdapter());
        view.setCurrentItem(id-1);
    }

    class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return getDataSet().size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ArrayList<DataObject> results = new ArrayList<DataObject>();
            results = getDataSet();
            TextView tView = new TextView(DisplayTask2Activity.this);
            tView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            tView.setText(results.get(position).getmDetail());
            //setTitle(results.get(position).getmTitle());
            ((ViewPager) container).addView(tView, 0);
            return tView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object obj) {
            ((ViewPager) container).removeView((View) obj);
        }

        @Override
        public boolean isViewFromObject(View container, Object obj) {
            return container == obj;
             //return container == (View) obj;
        }

    }

    public ArrayList<DataObject> getDataSet() {
        ArrayList<DataObject> results = new ArrayList<DataObject>();
        Cursor res;
        String title;
        String detail;
        try {
            res = mydb.getAllName();
            int count = res.getCount();
            res.moveToFirst();
            for (int i = 0 ; i < count; i++){
                title = res.getString(res.getColumnIndex("name"));
                detail = res.getString(res.getColumnIndex("detail"));
                DataObject obj = new DataObject(title,detail);
                results.add(i, obj);
                res.moveToNext();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return results;
    }
}
