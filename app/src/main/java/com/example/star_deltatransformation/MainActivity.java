package com.example.star_deltatransformation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private EditText edtTextRa, edtTextRb, edtTextRc, edtTextR1, edtTextR2, edtTextR3;
    private Button clearButton;
    private RadioButton wye_to_delta_rdButton, delta_to_wye_rdButton;
    private RadioGroup rdGroup;
    float Rd_1 = 0, Rd_2 = 0, Rd_3 = 0, Ry_1 = 0, Ry_2 = 0, Ry_3 = 0;

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

        rdGroup = findViewById(R.id.rdGroup);

        wye_to_delta_rdButton = findViewById(R.id.wye_to_delta_rdButton);
        delta_to_wye_rdButton = findViewById(R.id.delta_to_wye_rdButton);


    }


    public void onClickCalcBtnMethod(View view) {
        float Rd_1 = 0, Rd_2 = 0, Rd_3 = 0;

        if (wye_to_delta_rdButton.isChecked()) {

            wye_to_delta();

            edtTextRa.setText(String.valueOf(this.Rd_1));
            edtTextRb.setText(String.valueOf(this.Rd_2));
            edtTextRc.setText(String.valueOf(this.Rd_3));

        }

        if (delta_to_wye_rdButton.isChecked()) {

            delta_to_wye();

            edtTextRa.setText(String.valueOf(this.Ry_1));
            edtTextRb.setText(String.valueOf(this.Ry_2));
            edtTextRc.setText(String.valueOf(this.Ry_3));

        }


    }

    public void onClickClrBtnMethod(View view){
        String empty = " ";

        edtTextR1.setText(empty);
        edtTextR2.setText(empty);
        edtTextR3.setText(empty);
        edtTextRa.setText(empty);
        edtTextRb.setText(empty);
        edtTextRc.setText(empty);
    }


    public void wye_to_delta() {

        float Ry_1 = Float.valueOf(edtTextR1.getText().toString());
        float Ry_2 = Float.valueOf(edtTextR2.getText().toString());
        float Ry_3 = Float.valueOf(edtTextR3.getText().toString());

        float numerator = Ry_1 * Ry_2 + Ry_1 * Ry_3 + Ry_2 * Ry_3;

        Rd_1 = numerator / Ry_1;
        Rd_2 = numerator / Ry_2;
        Rd_3 = numerator / Ry_3;

    }


    public void delta_to_wye() {

        float Rd_1 = Float.valueOf(edtTextR1.getText().toString());
        float Rd_2 = Float.valueOf(edtTextR2.getText().toString());
        float Rd_3 = Float.valueOf(edtTextR3.getText().toString());

        float denominator = Rd_1 + Rd_2 + Rd_3;

        Ry_1 = Rd_2 * Rd_3 / denominator;
        Ry_2 = Rd_1 * Rd_3 / denominator;
        Ry_3 = Rd_1 * Rd_2 / denominator;

    }


}