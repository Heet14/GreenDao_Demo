package com.example.greendaodemo;

import android.database.sqlite.SQLiteException;

import com.example.greendaodemo.db.DaoMaster;
import com.example.greendaodemo.db.DaoSession;
import com.example.greendaodemo.db.User;
import com.example.greendaodemo.db.UserDao;
import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    private DaoMaster.DevOpenHelper mHelper;
    private Database mDatabase;


    public DataBaseManager() {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(AppController.getInstance(), "users-db");
        //The users-db here is the name of our database.
        Database db = helper.getWritableDb();

        mDaoSession = new DaoMaster(db).newSession();
    }


    public List<User> fetchUserListFromDB(){
        List<User> mUserList = new ArrayList<>();
        mUserList.clear();
        //// Get the entity dao we need to work with.
        UserDao UserDao = mDaoSession.getUserDao();
        //// Load all items
        mUserList.addAll(UserDao.loadAll());
        return mUserList;
    }


    public void deleteUserFromDB(){
        //// Get the entity dao we need to work with.
        UserDao UserDao = mDaoSession.getUserDao();
        /// perform delete operation
        UserDao.deleteAll();
    }


    public void insetIntoDB(String aFname,String aLastName){
        UserDao UserDao = mDaoSession.getUserDao();
        User user = new User();
        user.setUser_id(0);
        user.setFirst_name(aFname);
        user.setLast_name(aLastName);
        UserDao.insert(user);
    }
}
