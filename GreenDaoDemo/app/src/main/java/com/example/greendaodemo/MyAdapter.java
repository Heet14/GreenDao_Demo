package com.example.greendaodemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.greendaodemo.db.User;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<User> mUserList = new ArrayList<>();

    public MyAdapter() {

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindView(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void addList(List<User> aUserList){
        mUserList.clear();
        mUserList.addAll(aUserList);
        notifyDataSetChanged();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.item_txtId)
        TextView mTxtId;
        @BindView(R.id.item_txtFirstName)
        TextView mTxtFirstName;
        @BindView(R.id.item_txtLastName)
        TextView mTxtLastName;

        public MyViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        public void bindView(int position) {

            User mUserModel = mUserList.get(position);

            mTxtId.setText(String.valueOf(mUserModel.getUser_id()));
            mTxtFirstName.setText(mUserModel.getFirst_name());
            mTxtLastName.setText(mUserModel.getLast_name());
        }
    }
}