package exaples.com.modernartgui;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class ModernArtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modern_art);
        SeekBar sb= (SeekBar) findViewById(R.id.seekBar2);
        final View cuadros[]=  {findViewById(R.id.cuadro1),findViewById(R.id.cuadro2),findViewById(R.id.cuadro3),findViewById(R.id.cuadro4),findViewById(R.id.cuadro5),findViewById(R.id.cuadro6)};
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(View cuadro:cuadros) {

                    String hex= Integer.toHexString(cuadro.getBackground().getAlpha());
                    cuadro.setBackgroundColor(Color.parseColor("#"+hex));


                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
