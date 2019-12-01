package project.seg2015.seg2105_project_f19_3;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClinicEmployeeFunction extends AppCompatActivity implements View.OnClickListener {
    private EditText addressEdit;
    private EditText phoneEdit;
    private EditText clinicNameEdit;
    private CheckBox cashBox;
    private CheckBox creditBox;
    private CheckBox debitBox;
    private CheckBox type1Box;
    private CheckBox type2Box;
    private CheckBox type3Box;
    private TableLayout tableLayout;
    private MyDBHandler db;
    private Button cancel;
    private Button update;
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private ClinicEmployee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_employee_function);
        db = new MyDBHandler(this);
        addressEdit = findViewById(R.id.address);
        phoneEdit = findViewById(R.id.phone);
        clinicNameEdit = findViewById(R.id.clinic_name);
        cashBox = findViewById(R.id.cash_box);
        creditBox = findViewById(R.id.credit_card_box);
        debitBox = findViewById(R.id.debit_card_box);
        type1Box = findViewById(R.id.insurance_type1_box);
        type2Box = findViewById(R.id.insurance_type2_box);
        type3Box = findViewById(R.id.insurance_type3_box);
        tableLayout = findViewById(R.id.profile_table_layout);
        startTimePicker = findViewById(R.id.start_time);
        endTimePicker = findViewById(R.id.end_time);
        update = findViewById(R.id.update);
        cancel = findViewById(R.id.cancel);
        update.setOnClickListener(this);
        cancel.setOnClickListener(this);
        employee = (ClinicEmployee)LoginActivity.user;
        db.findEmployeeProfile(employee);

        if (employee.getStartTime().split(":").length == 2) {
            startTimePicker.setHour(Integer.valueOf(employee.getStartTime().split(":")[0]));
            startTimePicker.setMinute(Integer.valueOf(employee.getStartTime().split(":")[1]));
        }
        if (employee.getEndTime().split(":").length == 2) {
            endTimePicker.setHour(Integer.valueOf(employee.getEndTime().split(":")[0]));
            endTimePicker.setMinute(Integer.valueOf(employee.getEndTime().split(":")[1]));
        }
        ArrayList<Service> services = db.findAllServices();
        for (Service service : services) {
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("service", service.getServiceName() + " " + service.getServiceCost());
            boolean selected = false;
            if (employee.getServices() != null) {
                for (int i = 0; i < employee.getServices().length; i++) {
                    if (employee.getServices()[i].equals(service.getServiceName() + " " + service.getServiceCost()))
                        selected = true;
                }
            }
            bundle.putBoolean("selected", selected);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
        addressEdit.setText(employee.getAddress());
        phoneEdit.setText(employee.getPhone());
        clinicNameEdit.setText(employee.getClinicName());
        if (employee.getPaymentMethods() != null) {
            for (int i = 0; i < employee.getPaymentMethods().length; i++) {
                if (cashBox.getText().equals(employee.getPaymentMethods()[i]))
                    cashBox.setChecked(true);
                if (creditBox.getText().equals(employee.getPaymentMethods()[i]))
                    creditBox.setChecked(true);
                if (debitBox.getText().equals(employee.getPaymentMethods()[i]))
                    debitBox.setChecked(true);
            }
        }
        if (employee.getInsuranceTypes() != null) {
            for (int i = 0; i < employee.getInsuranceTypes().length; i++) {
                if (type1Box.getText().equals(employee.getInsuranceTypes()[i]))
                    type1Box.setChecked(true);
                if (type2Box.getText().equals(employee.getInsuranceTypes()[i]))
                    type2Box.setChecked(true);
                if (type3Box.getText().equals(employee.getInsuranceTypes()[i]))
                    type3Box.setChecked(true);
            }
        }
    }

    private Handler handler = new Handler() {
      @Override
      public void handleMessage(Message msg) {
          TableRow row = new TableRow(getApplicationContext());
          CheckBox service = new CheckBox(getApplicationContext());
          service.setText(msg.getData().getString("service"));
          service.setChecked(msg.getData().getBoolean("selected"));
          row.addView(service, 0);
          tableLayout.addView(row);
      }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.update) {
            if (!addressEdit.getText().toString().trim().equals("")
                    && !phoneEdit.getText().toString().trim().equals("")
                    && !clinicNameEdit.getText().toString().trim().equals("")
                    && (cashBox.isChecked() || creditBox.isChecked() || debitBox.isChecked())
                    && (type1Box.isChecked() || type2Box.isChecked() || type3Box.isChecked())) {

                String phoneRegx = "[0-9]{3}-[0-9]{3}-[0-9]{4}";
                Pattern pattern = Pattern.compile(phoneRegx);
                if (pattern.matcher(phoneEdit.getText().toString()).matches()) {
                    employee.setAddress(addressEdit.getText().toString());
                    employee.setPhone(phoneEdit.getText().toString());
                    employee.setClinicName(clinicNameEdit.getText().toString());
                    String services = "";
                    String types = "";
                    String payments = "";
                    if (cashBox.isChecked())
                        payments += cashBox.getText().toString() + "_";
                    if (creditBox.isChecked())
                        payments += creditBox.getText().toString() + "_";
                    if (debitBox.isChecked())
                        payments += debitBox.getText().toString() + "_";
                    if (type1Box.isChecked())
                        types += type1Box.getText().toString() + "_";
                    if (type2Box.isChecked())
                        types += type2Box.getText().toString() + "_";
                    if (type3Box.isChecked())
                        types += type3Box.getText().toString() + "_";
                    for (int i = 14; i < tableLayout.getChildCount(); i++) {
                        System.out.println(((CheckBox) ((TableRow) tableLayout.getChildAt(i)).getChildAt(0)).getText().toString());
                        if (((CheckBox) ((TableRow) tableLayout.getChildAt(i)).getChildAt(0)).isChecked())
                            services += ((CheckBox) ((TableRow) tableLayout.getChildAt(i)).getChildAt(0)).getText().toString() + "_";
                    }
                    employee.setServices(services.split("_"));
                    employee.setPaymentMethods(payments.split("_"));
                    employee.setInsuranceTypes(types.split("_"));
                    employee.setStartTime(startTimePicker.getHour() + ":" + startTimePicker.getMinute());
                    employee.setEndTime(endTimePicker.getHour() + ":" + endTimePicker.getMinute());
                    db.updateEmployeeProfile(employee);
                    Toast.makeText(this, "Update success!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Invalid phone format(xxx-xxx-xxxx)", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.cancel) {
            this.finish();
        }
    }
}
