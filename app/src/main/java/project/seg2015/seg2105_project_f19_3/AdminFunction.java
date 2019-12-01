package project.seg2015.seg2105_project_f19_3;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AdminFunction extends AppCompatActivity {
    EditText nameBox;
    EditText costBox;
    Spinner roles;
    ListView list;
    MyDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        nameBox = findViewById(R.id.serviceName);
        costBox = findViewById(R.id.serviceCost);
        roles = findViewById(R.id.rolesSpinner);
        list = findViewById(R.id.clinic_list_view);
        dbHandler = new MyDBHandler(this);
        list.setAdapter(new ClinicAdapter(this, dbHandler.findAllClinics(), false, null));
    }



    public void newService (View view) {
        Service service = dbHandler.findService(nameBox.getText().toString());
        if (nameBox.getText().toString().equals("")){
            Toast.makeText(this, "Service name cannot be empty.", Toast.LENGTH_SHORT).show();
        }else {
            if (service != null) {
                Toast.makeText(this, "Service already added.", Toast.LENGTH_SHORT).show();
            } else {
                int cost = Integer.parseInt(costBox.getText().toString());
                String role = roles.getSelectedItem().toString();
                Service service2 = new Service(nameBox.getText().toString(), cost, role);

                dbHandler.addService(service2);

                nameBox.setText("");

                costBox.setText("");
            }
        }
    }
    public void editService (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this);

        Service service = dbHandler.findService(nameBox.getText().toString());

        if (service != null) {

            dbHandler.deleteProduct(nameBox.getText().toString());
            int cost = Integer.parseInt(costBox.getText().toString());
            String role = roles.getSelectedItem().toString();
            Service service1 = new Service(nameBox.getText().toString(), cost, role);

            dbHandler.addService(service1);

            nameBox.setText("");

            costBox.setText("");
        } else {
            Toast.makeText(this, "Doesn't have this service", Toast.LENGTH_SHORT).show();
        }
    }


    public void lookupService (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this);

        Service service = dbHandler.findService(nameBox.getText().toString());

        if (service != null) {
            //idView.setText(String.valueOf(product.getID()));
            costBox.setText(String.valueOf(service.getServiceCost()));
        } else {
            Toast.makeText(this, "Doesn't have this service", Toast.LENGTH_SHORT).show();
        }
    }


    public void removeService (View view) {
        MyDBHandler dbHandler = new MyDBHandler(this);

        boolean result = dbHandler.deleteProduct(nameBox.getText().toString());

        if (result) {
            nameBox.setText("");
            costBox.setText("");
        }
        else
            Toast.makeText(this, "Doesn't have this service", Toast.LENGTH_SHORT).show();
    }

}