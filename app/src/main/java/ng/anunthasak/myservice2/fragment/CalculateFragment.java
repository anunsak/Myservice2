package ng.anunthasak.myservice2.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import ng.anunthasak.myservice2.MainActivity;
import ng.anunthasak.myservice2.R;

/**
 * Created by USER on 17/12/2017.
 */

public class CalculateFragment extends Fragment{
    //    Explicit
    private String moneyString;
    private double factorADouble;
    private EditText editText;




    public static CalculateFragment calculateInstance(String moneyString,
                                                      double factorDouble) {
        CalculateFragment calculateFragment = new CalculateFragment();
        Bundle bundle = new Bundle();
        bundle.putString("money", moneyString);
        bundle.putDouble("Factor",factorDouble);
        calculateFragment.setArguments(bundle);
        return calculateFragment;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Get valu form argument
        getValuFormArgument();
//        show HintMoney
        showHintMoney();

//        create Toolber
        createToolber();

        //        calculate Controler
        calculateControler();
    } //main method

    private void showHintMoney() {
        editText = getView().findViewById(R.id.edmoney);
        editText.setHint(moneyString);
    }

    private void getValuFormArgument() {
        moneyString = getArguments().getString("Money");
        factorADouble = getArguments().getDouble("Factor");
        String tag = "17DecV1";
        Log.d(tag, "moneyString ==>" + moneyString);
        Log.d(tag, "facter ==>" + factorADouble);
    }

    private void calculateControler() {

        Button button = getView().findViewById(R.id.btnCalculate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                get Value Form EditText
                String myMoneyString = editText.getText().toString().trim();
//                check Space
                if (myMoneyString.isEmpty()) {
//                    have Space change เป็นภาษาไทย ใสสร้างตัวแปร
                    myAlertDialoe(getString(R.string.have_space), getString(R.string.message_space));
                } else {
//                    no Space
                    double moneyADouble = Double.parseDouble(myMoneyString);
                    double abswerADouble = moneyADouble * factorADouble;
                    String answerStrin = Double.toString(abswerADouble); //Double mac
                    myAlertDialoe("Your Money",answerStrin);
                }

            }

        });


    }

    private void myAlertDialoe(String titleString, String messageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.alert);
        builder.setCancelable(false);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss(); //mac user dialog --but-- window user dialoginterface

            }
        });
        builder.show();


    }

    private void createToolber() {

        Toolbar toolbar = getView().findViewById(R.id.toolbarCalculate);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar()
                .setTitle("Calcurate");
        ((MainActivity) getActivity()).getSupportActionBar()
                .setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar()
                .setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        return view;
    }
}
