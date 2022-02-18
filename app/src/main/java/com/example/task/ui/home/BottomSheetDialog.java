package com.example.task.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
  
import androidx.annotation.Nullable;

import com.example.task.R;
import com.example.task.room.AppDatabase;
import com.example.task.room.UserDao;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
  
public class BottomSheetDialog extends BottomSheetDialogFragment {

    AppDatabase database;
    int userid;
    public BottomSheetDialog(AppDatabase database, int userid) {
        this.database = database;
        this.userid = userid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
                                                      ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                                  container, false);
  
        EditText comments = v.findViewById(R.id.et_comments);
        Button save = v.findViewById(R.id.btn_save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                UserDao userDao = database.userDao();
                userDao.update(comments.getText().toString().trim(),userid);
                dismiss();

            }
        });
        return v;
    }


}