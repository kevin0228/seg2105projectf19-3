package project.seg2015.seg2105_project_f19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText accountEdit;
    private EditText passwordEdit;
    private EditText confirmPasswordEdit;

    private RadioButton patient;

    private Button confirm;
    private Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        accountEdit = findViewById(R.id.account_edit_sign_up);
        passwordEdit = findViewById(R.id.password_edit_sign_up);
        confirmPasswordEdit = findViewById(R.id.password_confirm_edit);

        patient = findViewById(R.id.patient);
        confirm = findViewById(R.id.confirm_sign_up);
        cancel = findViewById(R.id.cancel_sign_up);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cancel_sign_up)
            this.finish();
        else if (v.getId() == R.id.confirm_sign_up) {
            if (!passwordEdit.getText().toString().equals(confirmPasswordEdit.getText().toString())) {
                Toast.makeText(this, "Password is not equal to confirm password", Toast.LENGTH_SHORT).show();
            } else {
                UserType type = patient.isChecked() ? UserType.Patient : UserType.ClinicEmployee;
                boolean success = UserManager.getInstance().signUp(type, accountEdit.getText().toString(), Util.stringToHash(passwordEdit.getText().toString()));
                if (success)
                    this.finish();
                else {
                    Toast.makeText(this, "Account is already exist.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
