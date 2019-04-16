package com.example.greendaodemo;

import android.app.Application;
import com.example.greendaodemo.db.DaoMaster;
import com.example.greendaodemo.db.DaoSession;
import org.greenrobot.greendao.database.Database;


public class AppController extends Application {

    public static final boolean ENCRYPTED = true;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"users-db");
        //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
