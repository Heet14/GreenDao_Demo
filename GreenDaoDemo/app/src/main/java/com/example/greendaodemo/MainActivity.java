package com.example.greendaodemo;

import android.os.Bundle;import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.greendaodemo.db.User;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.aMain_edtFirstName)
    EditText mEdtFirstName;
    @BindView(R.id.aMain_edtLastName)
    EditText mEdtLastName;
    @BindView(R.id.aMain_btnInsert)
    Button mBtnInsert;
    @BindView(R.id.aMain_btnShow)
    Button mBtnShow;
    @BindView(R.id.aMain_btnDelete)
    Button mBtnDelete;
    @BindView(R.id.aMain_rv)
    RecyclerView mRecyclerView;

    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataBaseManager mDatabaseInstance;

    List<User> mUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mDatabaseInstance = new DataBaseManager();
        bindRecyclerViews();
    }

    private void bindRecyclerViews() {
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }


    @OnClick({R.id.aMain_btnInsert, R.id.aMain_btnShow, R.id.aMain_btnDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.aMain_btnInsert:
                mDatabaseInstance.insetIntoDB(mEdtFirstName.getText().toString(), mEdtLastName.getText().toString());
                Toast.makeText(this,"Inserted",Toast.LENGTH_LONG);
                break;
            case R.id.aMain_btnShow:
                showUsersList();
                break;
            case R.id.aMain_btnDelete:
                mDatabaseInstance.deleteUserFromDB();
                showUsersList();
                break;
        }
    }

    private void showUsersList() {
        mUserList = mDatabaseInstance.fetchUserListFromDB();
        mAdapter.addList(mUserList);
    }
}
