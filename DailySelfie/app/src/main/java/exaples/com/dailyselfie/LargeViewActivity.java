package exaples.com.dailyselfie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class LargeViewActivity extends AppCompatActivity {
    private ImageView imagen;
    private String filepath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_view2);
        Bundle recived= getIntent().getBundleExtra("info");
        filepath=recived.getString("path");
        imagen=(ImageView)findViewById(R.id.image);
        Bitmap bitmap= BitmapFactory.decodeFile(filepath);
        imagen.setImageBitmap(bitmap);
    }
}
