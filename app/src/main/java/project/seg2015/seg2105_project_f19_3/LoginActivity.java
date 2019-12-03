package project.seg2015.seg2105_project_f19_3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accountEdit;
    private EditText passwordEdit;

    private RadioButton admin;
    private RadioButton patient;
    private RadioButton employee;

    private Button signIn;
    private Button signUp;
    public static Context context;
    public static User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_login);

        accountEdit = findViewById(R.id.account_edit);
        passwordEdit = findViewById(R.id.password_edit);

        admin = findViewById(R.id.admin_sign_in);
        patient = findViewById(R.id.patient_sign_in);
        employee = findViewById(R.id.clinic_employee_sign_in);

        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.sign_up);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_up) {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.sign_in) {
            UserType type = admin.isChecked() ? UserType.Admin : (patient.isChecked() ? UserType.Patient : UserType.ClinicEmployee);
            user = UserManager.getInstance().signIn(type, accountEdit.getText().toString(), Util.stringToHash(passwordEdit.getText().toString()));
            if (user != null) {
                if (type == UserType.Admin){
                    Intent intent = new Intent(this, AdminFunction.class);
                    startActivity(intent);
                    this.finish();
                } else if (type == UserType.ClinicEmployee){
                    Intent intent = new Intent(this, ClinicEmployeeFunction.class);
                    intent.putExtra("account", accountEdit.getText().toString());
                    startActivity(intent);
                    this.finish();
                } else {
                    Intent intent = new Intent(this, PatientFunction.class);
                    intent.putExtra("account", accountEdit.getText().toString());
                    startActivity(intent);
                    this.finish();
                }
            } else {
                Toast.makeText(this, "Account or password is wrong.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
