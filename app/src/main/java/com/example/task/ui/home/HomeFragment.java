package com.example.task.ui.home;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.task.R;
import com.example.task.databinding.FragmentHomeBinding;
import com.example.task.network.getUserData.UserData;
import com.example.task.room.AppDatabase;
import com.example.task.room.User;
import com.example.task.room.UserDao;
import com.example.task.utilities.PaginationListener;
import com.example.task.utilities.Utils;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {



    private FragmentHomeBinding binding;
    HomeViewModel homeViewModel;
    HomeRcvAdapter homeRcvAdapter;
    AppDatabase database;
    List<User> users = new ArrayList<>();
    UserDao userDao;
    int page = PaginationListener.PAGE_START;
    private boolean isLastPage = false;
    private int totalPage = 10;
    private boolean isLoading = false;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
         homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        database = Room.databaseBuilder(getActivity().getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();
        userDao = database.userDao();
       setRecyclerView();
        return root;
    }



    public void getUserDetails() {

        homeViewModel.getUserDataList(page).observe(getActivity(), new Observer<List<UserData>>() {
            @Override
            public void onChanged(List<UserData> userData) {
                if(userData.size() <= 0){

                }else {

                    for(int i = 0; i < userData.size();i++){
                        User user = new User(userData.get(i).getId(),userData.get(i).getId(),userData.get(i).getName(),userData.get(i).getEmail(),userData.get(i).getGender(),userData.get(i).getStatus());
                        users.add(user);

                    }

                    userDao.insertAll(users);
                    homeRcvAdapter.addItems(userDao.getAll());



                }
            }
        });

    }
    private void setRecyclerView() {
        homeRcvAdapter = new HomeRcvAdapter(getActivity(),userDao.getAll(), new HomeRcvAdapter.ItemClickAdapterListener() {
            @Override
            public void itemCommentButtonClick(View v, int position,int userid) {
                BottomSheetDialog bottomSheet = new BottomSheetDialog(database,userid);
                bottomSheet.show(getFragmentManager(),
                        "ModalBottomSheet");

            }

            @Override
            public void itemClick(View v, int position, int userid) {
                Bundle bundle = new Bundle();
                bundle.putInt("user_id",userid);

                Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_navigation_details,bundle);


            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        binding.rcvHome.setLayoutManager(layoutManager);
        binding.rcvHome.setAdapter(homeRcvAdapter);
        if(page==1){
            if(CheckNetworkConnectivity()){
                getUserDetails();
            }else {
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
            }
        }
        binding.rcvHome.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                Log.d("homeFragment","Page="+page);
                page++;
                if(CheckNetworkConnectivity()){
                    getUserDetails();
                }else {
                    Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


  public boolean CheckNetworkConnectivity(){
      return !Utils.NoInternetConnection(getActivity());
  }
}