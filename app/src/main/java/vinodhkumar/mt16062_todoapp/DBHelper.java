package vinodhkumar.mt16062_todoapp;

//import SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


/**
 * Created by VinodhKumar on 04/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static Context context;
    public static final String DATABASE_NAME = "ToDoList.db";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "create table ToDoList " +
                        "(id integer primary key, name text, detail text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public Boolean insertInfo(int index, String name, String detail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",index);
        contentValues.put("name",name);
        contentValues.put("detail",detail);
        try {
            db.insertOrThrow("ToDoList", null, contentValues);
            //Toast.makeText(context, "Row Inserted!", Toast.LENGTH_SHORT).show();
            return true;
        }catch (Exception e){
            Toast.makeText(context.getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public Cursor getInfo(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from ToDoList where id="+id+"", null );
        return res;
    }

    public  Cursor getAllName(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from ToDoList ORDER BY id ASC",null);
        return res;
    }

}
