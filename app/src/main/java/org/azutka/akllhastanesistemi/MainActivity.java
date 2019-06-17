package org.azutka.akllhastanesistemi;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.fragments.CrucialsFragment;
import org.azutka.akllhastanesistemi.fragments.PatientsFragment;
import org.azutka.akllhastanesistemi.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_bottom_nav)
    BottomNavigationView bottomNav;

    private Fragment mCurrentFragment;

    //Fragments
    private PatientsFragment patientsFragment;
    private CrucialsFragment crucialsFragment;

    public static Context mainContext;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Set toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(getDrawable(R.drawable.ic_toolbar_logo2));

        mainContext = MainActivity.this;

        patientsFragment = new PatientsFragment();
        crucialsFragment = new CrucialsFragment();

        setFragment( patientsFragment,"patients");

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_nav_patients:
                        switchFragment(patientsFragment,"patients");
                        return true;
                    case R.id.menu_nav_crucials:
                        switchFragment(crucialsFragment,"crucials");
                        return true;
                    default:
                        switchFragment(patientsFragment,"patients");
                        return true;

                }
            }
        });




    }


    private void switchFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            fragmentTransaction.add(R.id.main_frame, fragment, tag);
        }

        fragmentTransaction.hide(mCurrentFragment);
        fragmentTransaction.show(fragment);
        mCurrentFragment = fragment;
        fragmentTransaction.commit();
    }

    private void setFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment, tag);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        //Set menu
        switch(item.getItemId()){
            case R.id.menu_main_about:
                AlertDialog.Builder dialogAbout = new AlertDialog.Builder(this);
                dialogAbout.setTitle("Hakkında");
                dialogAbout.setMessage("ŞehirdeHachathon için yapılmıştır.\n\nVersion: " + Constants.version);
                dialogAbout.setPositiveButton("Tamam", null );
                dialogAbout.show();
                break;
            default:
                break;
        }

        return true;
    }

}
