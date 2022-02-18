package com.example.task.ui.home;

import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.task.network.ApiLinks;
import com.example.task.network.getUserData.ApiResponse;
import com.example.task.network.getUserData.UserData;
import com.example.task.network.getUserData.getUserData;
import com.example.task.room.AppDatabase;
import com.example.task.room.User;
import com.example.task.room.UserDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<List<UserData>> userDataList = new MutableLiveData();



    public MutableLiveData<List<UserData>> getUserDataList(int page) {


        ApiLinks.getClient().create(getUserData.class).get(page).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                if(response.isSuccessful()&&response.body()!=null){
                    if (!response.body().getUserData().isEmpty()) {
                        userDataList.setValue(response.body().getUserData());
                        Log.d("homeFragment","Page1="+response.body().getMeta().getPagination().getPage());
                    }


                }else {

                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });



        return userDataList;
    }
}
