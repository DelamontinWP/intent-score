package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MatchActivity extends AppCompatActivity {

    private TextView getHometext;
    private TextView getAwaytext;
    private ImageView homelogo;
    private ImageView awaylogo;
    private Integer skorHome;
    private TextView HomeScore;
    private TextView AwayScore;
    private Integer skorAway;

    private static final String RESULT_KEY = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        getHometext = findViewById(R.id.txt_home);
        getAwaytext = findViewById(R.id.txt_away);
        homelogo = findViewById(R.id.home_logo);
        awaylogo = findViewById(R.id.away_logo);
        HomeScore = findViewById(R.id.score_home);
        AwayScore = findViewById(R.id.score_away);

        skorAway = 0;
        skorHome = 0;
        HomeScore.setText("0");
        AwayScore.setText("0");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Bundle bundle2 = getIntent().getExtras();
            Bitmap bitmap1 = bundle2.getParcelable("homelogo");
            Bitmap bitmap2 = bundle2.getParcelable("awaylogo");

            homelogo.setImageBitmap(bitmap1);
            awaylogo.setImageBitmap(bitmap2);

            getHometext.setText(bundle.getString("hometeam"));
            getAwaytext.setText(bundle.getString("awayteam"));
        }
    }

    public void btn_addHomeScore(View view) {
        skorHome++;
        HomeScore.setText(String.valueOf(skorHome));
    }

    public void btn_addAwayScore(View view) {
        skorAway++;
        AwayScore.setText(String.valueOf(skorAway));
    }

    public void btn_result(View view) {
        String result;
        if (skorHome > skorAway){
            result = getHometext.getText().toString();
        }
        else if(skorAway > skorHome){
            result = getAwaytext.getText().toString();
        }
        else{
            result = "DRAW";
        }
        Intent intent = new Intent( this, ResultActivity.class);
        intent.putExtra(RESULT_KEY, result);
        startActivity(intent);
    }
}
