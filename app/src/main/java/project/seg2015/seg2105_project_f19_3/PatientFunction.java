package project.seg2015.seg2105_project_f19_3;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class PatientFunction extends AppCompatActivity implements View.OnClickListener {
    MyDBHandler dbHandler;
    ListView list;
    EditText addressInputField;
    Spinner startHourSpinner;
    Spinner startMinuteSpinner;
    Spinner endHourSpinner;
    Spinner endMinuteSpinner;
    Button filter;
    List<ClinicEmployee> clinics;
    TableLayout servicesTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        addressInputField = findViewById(R.id.address_filter);
        startHourSpinner = findViewById(R.id.start_hour_spinner);
        startMinuteSpinner = findViewById(R.id.start_minute_spinner);
        endHourSpinner = findViewById(R.id.end_hour_spinner);
        endMinuteSpinner = findViewById(R.id.end_minute_spinner);
        list = findViewById(R.id.clinic_list_view_patient);
        filter = findViewById(R.id.filter);
        filter.setOnClickListener(this);
        servicesTable = findViewById(R.id.services_table);
        dbHandler = new MyDBHandler(this);
        ArrayList<Service> services = dbHandler.findAllServices();
        for (Service service : services) {
            TableRow row = new TableRow(getApplicationContext());
            CheckBox serviceBox = new CheckBox(getApplicationContext());
            serviceBox.setText(service.getServiceName());
            row.addView(serviceBox, 0);
            servicesTable.addView(row);
        }
        dbHandler = new MyDBHandler(this);
        clinics = dbHandler.findAllClinics();
        list.setAdapter(new ClinicAdapter(this, clinics, true, dbHandler));
    }

    @Override
    public void onClick(View v) {
        List<ClinicEmployee> filtered = new ArrayList<>();
        for (ClinicEmployee clinic : clinics) {
            boolean show = true;
            if (!addressInputField.getText().toString().equals("")) {
                if (!clinic.getAddress().contains(addressInputField.getText().toString())) {
                    show = false;
                }
            }
            String[] startTime = clinic.getStartTime().split(":");
            if (Integer.valueOf(startTime[0]) > startHourSpinner.getSelectedItemPosition()
                    || (Integer.valueOf(startTime[0]) == startHourSpinner.getSelectedItemPosition()
                    && Integer.valueOf(startTime[1]) > startMinuteSpinner.getSelectedItemPosition())) {
                show = false;
            }
            String[] endTime = clinic.getEndTime().split(":");
            if (Integer.valueOf(endTime[0]) < endHourSpinner.getSelectedItemPosition()
                    || (Integer.valueOf(endTime[0]) == endHourSpinner.getSelectedItemPosition()
                    && Integer.valueOf(endTime[1]) < endMinuteSpinner.getSelectedItemPosition())) {
                show = false;
            }
            String servicesString = "";
            for (int i = 0; i < servicesTable.getChildCount(); i++) {
                if (((CheckBox) ((TableRow) servicesTable.getChildAt(i)).getChildAt(0)).isChecked())
                    servicesString += ((CheckBox) ((TableRow) servicesTable.getChildAt(i)).getChildAt(0)).getText().toString() + "_";
            }
            String[] services = servicesString.split("_");
            for (int i = 0; i < services.length; i++) {
                if (!clinic.getServicesString().contains(services[i])) {
                    show = false;
                }
            }
            if (show) {
                filtered.add(clinic);
            }
        }
        list.setAdapter(new ClinicAdapter(this, filtered, true, dbHandler));
    }
}
