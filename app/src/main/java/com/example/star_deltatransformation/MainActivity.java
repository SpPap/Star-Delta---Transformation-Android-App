package com.example.star_deltatransformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtTextRa, edtTextRb, edtTextRc, edtTextR1, edtTextR2, edtTextR3;
    private Button clearButton, calculateButton;
    private RadioButton wye_to_delta_rdButton, delta_to_wye_rdButton;
    private RadioGroup rdGroup;
    private Spinner R1_spinner, R2_spinner, R3_spinner;


    float Rd_1 = 0, Rd_2 = 0, Rd_3 = 0, Ry_1 = 0, Ry_2 = 0, Ry_3 = 0;
    int unit_1 = 1, unit_2 = 1, unit_3 = 1;
    String common_unit = "";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.calculateButton:


                if (wye_to_delta_rdButton.isChecked()) {

                    wye_to_delta();

                    edtTextRa.setText(String.valueOf(this.Rd_1) + " " + common_unit);
                    edtTextRb.setText(String.valueOf(this.Rd_2) + " " + common_unit);
                    edtTextRc.setText(String.valueOf(this.Rd_3) + " " + common_unit);

                }

                if (delta_to_wye_rdButton.isChecked()) {

                    delta_to_wye();

                    edtTextRa.setText(String.valueOf(this.Ry_1));
                    edtTextRb.setText(String.valueOf(this.Ry_2));
                    edtTextRc.setText(String.valueOf(this.Ry_3));


                }
                break;

            case R.id.clearButton:
                String zero = "0.0";
                String empty = "";

                edtTextR1.setText(empty);
                edtTextR2.setText(empty);
                edtTextR3.setText(empty);
                edtTextRa.setText(zero);
                edtTextRb.setText(zero);
                edtTextRc.setText(zero);
                break;

            default:
                break;


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define elements
        edtTextR1 = findViewById(R.id.edtTextR1);
        edtTextR2 = findViewById(R.id.edtTextR2);
        edtTextR3 = findViewById(R.id.edtTextR3);
        edtTextRa = findViewById(R.id.edtTextRa);
        edtTextRb = findViewById(R.id.edtTextRb);
        edtTextRc = findViewById(R.id.edtTextRc);

        clearButton = findViewById(R.id.clearButton);
        calculateButton = findViewById(R.id.calculateButton);

        rdGroup = findViewById(R.id.rdGroup);

        wye_to_delta_rdButton = findViewById(R.id.wye_to_delta_rdButton);
        delta_to_wye_rdButton = findViewById(R.id.delta_to_wye_rdButton);

        calculateButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);

        R1_spinner = findViewById(R.id.R1_spinner);
        R2_spinner = findViewById(R.id.R2_spinner);
        R3_spinner = findViewById(R.id.R3_spinner);


        final HashMap<String, Integer> ohmUnitsMap = new HashMap<String, Integer>();
        ohmUnitsMap.put("Ω", 1);
        ohmUnitsMap.put("kΩ", 1000);
        ohmUnitsMap.put("MΩ", 1000000);

        //get keys (ohm units) from hashmap
        Set<String> keysUnits = ohmUnitsMap.keySet();

        final ArrayList<String> unitsList = new ArrayList<String>(keysUnits);

        ArrayAdapter<String> ohmUnitsAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                unitsList
        );

        R1_spinner.setAdapter(ohmUnitsAdapter);
        R2_spinner.setAdapter(ohmUnitsAdapter);
        R3_spinner.setAdapter(ohmUnitsAdapter);

        R1_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (R1_spinner.getSelectedItem() == unitsList.get(0)) {
                    unit_1 = ohmUnitsMap.get("Ω");
                }
                if (R1_spinner.getSelectedItem() == unitsList.get(1)) {
                    unit_1 = ohmUnitsMap.get("kΩ");
                }
                if (R1_spinner.getSelectedItem() == unitsList.get(2)) {
                    unit_1 = ohmUnitsMap.get("MΩ");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        R2_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (R2_spinner.getSelectedItem() == unitsList.get(0)) {
                    unit_2 = ohmUnitsMap.get("Ω");
                }
                if (R2_spinner.getSelectedItem() == unitsList.get(1)) {
                    unit_2 = ohmUnitsMap.get("kΩ");
                }
                if (R2_spinner.getSelectedItem() == unitsList.get(2)) {
                    unit_2 = ohmUnitsMap.get("MΩ");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        R3_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (R3_spinner.getSelectedItem() == unitsList.get(0)) {
                    unit_3 = ohmUnitsMap.get("Ω");
                }
                if (R3_spinner.getSelectedItem() == unitsList.get(1)) {
                    unit_3 = ohmUnitsMap.get("kΩ");
                }
                if (R3_spinner.getSelectedItem() == unitsList.get(2)) {
                    unit_3 = ohmUnitsMap.get("MΩ");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if ((R1_spinner.getSelectedItem().toString()).equals(R2_spinner.getSelectedItem().toString()) && (R1_spinner.getSelectedItem().toString()).equals(R3_spinner.getSelectedItem().toString())) {
            common_unit = R1_spinner.getSelectedItem().toString();
        }

    }


    public void wye_to_delta() {

        float Ry_1 = unit_1 * Float.parseFloat(edtTextR1.getText().toString());
        float Ry_2 = unit_2 * Float.parseFloat(edtTextR2.getText().toString());
        float Ry_3 = unit_3 * Float.parseFloat(edtTextR3.getText().toString());

        float numerator = Ry_1 * Ry_2 + Ry_1 * Ry_3 + Ry_2 * Ry_3;

        Rd_1 = numerator / Ry_1;
        Rd_2 = numerator / Ry_2;
        Rd_3 = numerator / Ry_3;

    }


    public void delta_to_wye() {

        float Rd_1 = unit_1 * Float.parseFloat(edtTextR1.getText().toString());
        float Rd_2 = unit_2 * Float.parseFloat(edtTextR2.getText().toString());
        float Rd_3 = unit_3 * Float.parseFloat(edtTextR3.getText().toString());

        float denominator = Rd_1 + Rd_2 + Rd_3;

        Ry_1 = Rd_2 * Rd_3 / denominator;
        Ry_2 = Rd_1 * Rd_3 / denominator;
        Ry_3 = Rd_1 * Rd_2 / denominator;

    }


}