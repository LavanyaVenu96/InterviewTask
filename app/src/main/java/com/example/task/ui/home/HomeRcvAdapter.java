package com.example.task.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;
import com.example.task.databinding.CardUserDataBinding;
import com.example.task.room.User;

import java.util.ArrayList;
import java.util.List;

public class HomeRcvAdapter extends RecyclerView.Adapter<HomeRcvAdapter.ViewHolder> {

    List<User> users = new ArrayList<>();
    Context context;
    private ItemClickAdapterListener onClickListener;

    public HomeRcvAdapter(FragmentActivity activity, List<User> user, ItemClickAdapterListener itemClickAdapterListener) {
        this.context = activity;
        this.users = user;
        this.onClickListener = itemClickAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        CardUserDataBinding cardUserDataBinding = CardUserDataBinding.inflate(LayoutInflater.from(viewGroup.getContext()));
        return new ViewHolder(cardUserDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardUserDataBinding.setUser(users.get(position));

        holder.cardUserDataBinding.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = users.get(position).userId;

                onClickListener.itemCommentButtonClick(view,position,userid);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = users.get(position).userId;

                onClickListener.itemClick(view,position,userid);
            }
        });


    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public void addItems(List<User> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardUserDataBinding cardUserDataBinding;
        public ViewHolder(@NonNull CardUserDataBinding cardUserDataBinding) {
            super(cardUserDataBinding.getRoot());
            this.cardUserDataBinding = cardUserDataBinding;
        }

    }
    public interface ItemClickAdapterListener {
        void itemCommentButtonClick(View v, int position,int userid);

        void itemClick(View v,int position,int userid);


    }
}
