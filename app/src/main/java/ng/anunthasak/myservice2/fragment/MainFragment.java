package ng.anunthasak.myservice2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ng.anunthasak.myservice2.MainActivity;
import ng.anunthasak.myservice2.R;

/**
 * Created by USER on 16/12/2017.
 */

public class MainFragment extends Fragment{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Create toolbar
        createToolbar();



//        Create menu Icon
        setHasOptionsMenu(true);


    } // main method

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.manu_main,menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

//        tha
        if (item.getItemId()== R.id.itemTHA) {
            myReplaceFragment("THB",0.031);
        }

//        usa
        if (item.getItemId()== R.id.itemUSD) {
            myReplaceFragment("USD",31);
        }
        return super.onOptionsItemSelected(item);
    }

    private void myReplaceFragment(String moneyString, double factorDouble) {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMainFragment,
                        CalculateFragment.calculateInstance(moneyString,factorDouble))
                .addToBackStack(null)
                .commit();

    }

    private void createToolbar() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarMain);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        return view;
    }
} //Main Class
