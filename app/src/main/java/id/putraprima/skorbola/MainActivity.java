package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String HOMETEAM_KEY = "hometeam";
    public static final String AWAYTEAM_KEY = "awayteam";
    public static final String HOMELOGO_KEY = "homelogo";
    public static final String AWAYLOGO_KEY = "awaylogo";

    private EditText homeTeamInput;
    private EditText awayTeamInput;

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1&2;

    private ImageView avatarHomeLogo;
    private ImageView avatarAwayLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
        homeTeamInput = findViewById(R.id.home_team);
        awayTeamInput = findViewById(R.id.away_team);
        avatarHomeLogo = findViewById(R.id.home_logo);
        avatarAwayLogo = findViewById(R.id.away_logo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }

        if (requestCode == 1) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarHomeLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == 2) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarAwayLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void btn_nextMatch(View view) {
        String hometeam = homeTeamInput.getText().toString();
        String awayteam = awayTeamInput.getText().toString();

        if((hometeam).equals("") || (awayteam).equals("")){
            Toast.makeText(this, "Data harus terisi semua",Toast.LENGTH_SHORT).show();;
        }
        else{
            Intent intent = new Intent(this, MatchActivity.class);
            avatarHomeLogo.buildDrawingCache();
            avatarAwayLogo.buildDrawingCache();
            Bitmap homelogo = avatarHomeLogo.getDrawingCache();
            Bitmap awaylogo = avatarAwayLogo.getDrawingCache();
            Bundle bundle = new Bundle();
            bundle.putParcelable(HOMELOGO_KEY, homelogo);
            bundle.putParcelable(AWAYLOGO_KEY, awaylogo);
            intent.putExtras(bundle);
            intent.putExtra(HOMETEAM_KEY, hometeam);
            intent.putExtra(AWAYTEAM_KEY, awayteam);
            startActivity(intent);
        }
    }

    public void btn_homeLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void btn_awayLogo(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
