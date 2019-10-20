package project.seg2015.seg2105_project_f19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView welcome;
    private String account;
    private UserType type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.welcome);
        account = getIntent().getStringExtra("account");
        type = UserType.valueOf(getIntent().getStringExtra("type"));
        welcome.setText("Welcome " + account + "! You are logged in as " + type.toString());
    }
}
