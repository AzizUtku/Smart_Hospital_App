package org.azutka.akllhastanesistemi.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.azutka.akllhastanesistemi.AddPatientActivity;
import org.azutka.akllhastanesistemi.MainActivity;
import org.azutka.akllhastanesistemi.R;
import org.azutka.akllhastanesistemi.adapters.PatientAdapter;
import org.azutka.akllhastanesistemi.core.App;
import org.azutka.akllhastanesistemi.models.Patient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatientsFragment extends Fragment implements SearchView.OnQueryTextListener {

    private View mView;

    Unbinder unbinder;


    public static RecyclerView recyclerList;

    @BindView(R.id.fragment_patients_txt_info)
    TextView txtInfo;

    @BindView(R.id.fragment_patients_txt_time)
    TextView txtTime;

    @OnClick(R.id.fragment_patients_fab_add_patient)
    public void onAddPatient(){
        Intent intent = new Intent(getContext(), AddPatientActivity.class);
        startActivity(intent);
    }


    public PatientsFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
            String date = sdf.format(new Date());
            txtTime.setText(date);

            App.getPatients(App.currentPersonel.getTc(), PatientsFragment.recyclerList, getContext());

            mHandler.postDelayed(this,1000);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        mView =  inflater.inflate(R.layout.fragment_patients, container, false);
        unbinder = ButterKnife.bind(this, mView);

        recyclerList = mView.findViewById(R.id.fragment_patients_recycler);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerList.setLayoutManager(layoutManager);



        updateRecycler();




        if(!App.startedToGetDataPerSecond) {
            mHandler.postDelayed(mRunnable, 0 );
            App.startedToGetDataPerSecond = true;
        }

        return mView;
    }


    private void updateRecycler(){
        if(App.patientList.size() > 0){
            txtInfo.setVisibility(View.GONE);
            recyclerList.setAdapter(new PatientAdapter(getContext(), App.patientList, recyclerList));
            synchronized (recyclerList) {
                recyclerList.notifyAll();
            }
        } else {
            txtInfo.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        if(!newText.isEmpty() && newText != null){
            List<Patient> patientsSearch = new ArrayList<>();
            //collectionsSearch.add(null);
            PatientAdapter patientAdapterSearch = new PatientAdapter(getContext(), patientsSearch, recyclerList);

            for(Patient p: App.patientList){

                try{
                    if ( (p.getName() + p.getSurname()).toLowerCase().trim().contains(newText.toLowerCase().trim())
                            || p.getTc().toLowerCase().trim().contains(newText.toLowerCase().trim())
                            || p.getRoom_no().toLowerCase().trim().contains(newText.toLowerCase().trim()) ){

                        patientsSearch.add(p);

                    }
                } catch(Exception e){

                }
            }

            recyclerList.setAdapter(patientAdapterSearch);
            synchronized(recyclerList){
                recyclerList.notifyAll();
            }

        } else {

            recyclerList.setAdapter(new PatientAdapter(getContext(), App.patientList, recyclerList));
            synchronized(recyclerList){
                recyclerList.notifyAll();
            }

        }


        return false;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        try {

            MenuItem menuItemSearch = menu.findItem(R.id.menu_main_item_search);

            SearchView searchView = (android.support.v7.widget.SearchView) menuItemSearch.getActionView();
            EditText searchEditText = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
            searchEditText.setHintTextColor(getResources().getColor(R.color.search_hint));
            searchView.setOnQueryTextListener(this);

        } catch (Exception e){

        }
    }


}
