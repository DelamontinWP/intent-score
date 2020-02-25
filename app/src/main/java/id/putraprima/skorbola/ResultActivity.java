package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView getwinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getwinner = findViewById(R.id.winner);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String result = getIntent().getExtras().getString("result");
            getwinner.setText(result);
        }
    }
}
