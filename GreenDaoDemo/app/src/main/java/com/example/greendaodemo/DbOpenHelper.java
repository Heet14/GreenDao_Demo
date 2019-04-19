package com.example.greendaodemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greendaodemo.db.DaoMaster;

import org.greenrobot.greendao.database.Database;

public class DbOpenHelper extends DaoMaster.OpenHelper {


    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onCreate(Database db) {
        super.onCreate(db);

    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        //Log.d("DEBUG", "DB_OLD_VERSION : " + oldVersion + ", DB_NEW_VERSION : " + newVersion);
        switch (oldVersion) {
            case 1:
            case 2:
                //db.execSQL("ALTER TABLE " + UserDao.TABLENAME + " ADD COLUMN " + UserDao.Properties.Name.columnName + " TEXT DEFAULT 'DEFAULT_VAL'");
        }
    }
}
