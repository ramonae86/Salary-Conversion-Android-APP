package edu.gatech.seclass.sdpsalarycalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;

public class SDPSalaryCalcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdpsalary_calc);

        final HashMap<String, Float> map = new HashMap<String, Float>();
        map.put(getResources().getString(R.string.city1), (float)158);
        map.put(getResources().getString(R.string.city2), (float)140);
        map.put(getResources().getString(R.string.city3), (float)227);
        map.put(getResources().getString(R.string.city4), (float)151);
        map.put(getResources().getString(R.string.city5), (float)197);
        map.put(getResources().getString(R.string.city6), (float)242);
        map.put(getResources().getString(R.string.city7), (float)220);
        map.put(getResources().getString(R.string.city8), (float)201);
        map.put(getResources().getString(R.string.city9), (float)143);
        map.put(getResources().getString(R.string.city10), (float)148);

        final Spinner initialCitySpinner = findViewById(R.id.initialCity);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initialCitySpinner.setAdapter(adapter);

        final Spinner destinationCitySpinner = findViewById(R.id.destinationCity);
        destinationCitySpinner.setAdapter(adapter);

        Button runButton = findViewById(R.id.runButton);
        runButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText salaryText = findViewById(R.id.salary);
                String salaryInput = salaryText.getText().toString();
                int salary = 0;

                String initialCity = initialCitySpinner.getSelectedItem().toString();
                String destinationCity = destinationCitySpinner.getSelectedItem().toString();
                TextView labelDestinationCity = findViewById(R.id.labelDestinationCity);
                TextView resultView = findViewById(R.id.resultSalary);
                resultView.setText(null);

                if(initialCity.equals(destinationCity)) {
                    labelDestinationCity.setError(getResources().getString(R.string.error_city_equal));
                }
                else{
                    labelDestinationCity.setError(null);
                }

                if(salaryInput.matches("")){
                    salaryText.setError(getResources().getString(R.string.error_salary));
                }
                else {
                    salary = Integer.parseInt(salaryText.getText().toString());
                    if(salary <= 0){
                        salaryText.setError(getResources().getString(R.string.error_salary));
                    }
                    else{
                        salaryText.setError(null);
                    }
                }

                if(!initialCity.equals(destinationCity) && salary > 0){
                    int result = Math.round(salary * map.get(destinationCity) / map.get(initialCity));
                    resultView.setText(String.valueOf(result));
                }
            }
        });
    }
}
