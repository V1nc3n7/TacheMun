package univ.etu.tachemun.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AndroidTask extends SQLiteOpenHelper {
    public AndroidTask(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase bdd) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase bdd, int oldVersion, int newVersion) {

    }
}
