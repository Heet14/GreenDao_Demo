package com.example.greendaodemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.greendaodemo.db.DaoSession;
import com.example.greendaodemo.db.User;
import com.example.greendaodemo.db.UserDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    DaoSession mDaoSession;
    List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDaoSession = ((AppController) getApplication()).getDaoSession();
        bindViews();


    }

    private void bindViews() {
        mRecyclerView = (RecyclerView)findViewById(R.id.aMain_rv);
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        mAdapter = new MyAdapter(mUserList);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void fetchUserList(){
        mUserList.clear();
        //// Get the entity dao we need to work with.
        UserDao UserDao = mDaoSession.getUserDao();

        //// Load all items
        mUserList.addAll(UserDao.loadAll());

        /// Notify our adapter of changes
        mAdapter.notifyDataSetChanged();
    }


    private void deleteUserItem(long id){
        //// Get the entity dao we need to work with.
        UserDao UserDao = mDaoSession.getUserDao();
        /// perform delete operation
        UserDao.deleteByKey(id);

        fetchUserList();
    }

/*
    private void updateItem(long id,String aFirstName){
        UserDao UserDao = mDaoSession.getUserDao();
        User user = new User();
        user.setId(id);
        user.setFirst_name(UserDao);

        UserDao.saveInTx(user);
        Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        finish();
    }*/

    private void insetItem(long aId,String aFname,String aLastName){
        UserDao UserDao = mDaoSession.getUserDao();
        User user = new User();
        user.setId(aId);
        user.setFirst_name(aFname);
        user.setLast_name(aLastName);
        user.setEmail("gmail.com");
        UserDao.insert(user);
        Toast.makeText(this, "Item inserted", Toast.LENGTH_SHORT).show();
      }
}
