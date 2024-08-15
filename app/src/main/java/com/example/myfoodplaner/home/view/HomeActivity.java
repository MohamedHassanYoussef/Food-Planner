package com.example.myfoodplaner.home.view;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myfoodplaner.R;
import com.example.myfoodplaner.login.LoginScreen1;
import com.example.myfoodplaner.model.Dtopresenter.MealsItem;
import com.example.myfoodplaner.model.MealRepositoryImpl;
import com.example.myfoodplaner.model.MealRepositoryView;
import com.example.myfoodplaner.model.netowark.MealRemoteDataSourceImpl;
import com.example.myfoodplaner.model.netowark.database.MealLocalDataSourceImpl;
import com.example.myfoodplaner.model.netowark.remotedb.DBHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    BottomNavigationView navigationView;
    NavController navController;
  //  FirebaseAuth firebaseAuth;
    public boolean isGuestMode = false;
    private MealRepositoryView mealRepositoryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navigationView = findViewById(R.id.navigation_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey("guestMode")) {
            isGuestMode = extras.getBoolean("guestMode", false);


        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(  R.id.homeFragment,R.id.favoriteFragment).build();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        navController = Navigation.findNavController(this, R.id.nav_fragment);
        NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView,navController);
        mealRepositoryView = MealRepositoryImpl.getInstance(MealRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(this));

        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            new DBHelper().getAllFavorite(this);
            new DBHelper().getAllFavoriteWeelPlan(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!isGuestMode) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.top_bar, menu);
            return true;
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            clearLocalFavoriteData();
            clearLocalCalendarData();

            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getApplicationContext(), LoginScreen1.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearLocalFavoriteData() {
        mealRepositoryView.deleteAllTheFavoriteList();
    }

    private void clearLocalCalendarData() {
        mealRepositoryView.deleteAllTheCalenderList();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawerLayout) || super.onSupportNavigateUp();
    }

    public boolean checkGuestMode() {
        return isGuestMode;
    }


    public void onItemClickListener(MealsItem mealsItem) {
        if (isGuestMode) {
            showGuestModeAlert();
        } else {
            Bundle bundle = new Bundle();
            bundle.putSerializable("item", mealsItem);
            navController.navigate(R.id.favoriteFragment, bundle);
        }
    }



    public void showGuestModeAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Guest Mode");
        builder.setMessage("You need to sign in to perform this action. Do you want to sign in?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(HomeActivity.this, LoginScreen1.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    public boolean isGuestMode() {
        return isGuestMode;
    }
}
