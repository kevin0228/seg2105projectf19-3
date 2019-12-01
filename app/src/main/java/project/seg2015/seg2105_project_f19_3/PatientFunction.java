package project.seg2015.seg2105_project_f19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class PatientFunction extends AppCompatActivity {
    MyDBHandler dbHandler;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        list = findViewById(R.id.clinic_list_view_patient);
        dbHandler = new MyDBHandler(this);
        list.setAdapter(new ClinicAdapter(this, dbHandler.findAllClinics(), true));
    }
}
