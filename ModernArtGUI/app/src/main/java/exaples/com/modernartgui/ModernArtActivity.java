package exaples.com.modernartgui;

import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        sb.setMax(100);
        final View cuadros[]=  {findViewById(R.id.cuadro1),findViewById(R.id.cuadro2),findViewById(R.id.cuadro3),findViewById(R.id.cuadro4),findViewById(R.id.cuadro5),findViewById(R.id.cuadro6)};
        for(View cuadro: cuadros){
            ColorDrawable color = (ColorDrawable) cuadro.getBackground() ;
            int c=color.getColor();
            cuadro.setTag(c);
        }
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for(View cuadro:cuadros) {

                    int c=(int)cuadro.getTag();
                    int r=Color.red(c),g=Color.green(c),b=Color.blue(c);
                    System.out.println("RGB: "+r+" "+g+" "+b);
                    int prom= (r+g+b)/3;
                    if(prom<170) {
                        r=r==0?1:r;
                        g=g==0?1:g;
                        b=b==0?1:b;
                        int pr=(progress*r)/(10);
                        int pg=(progress*g)/(20);
                        int pb=(progress*b)/(30);
                        c=Color.argb(255,pr,pg,pb);
                        cuadro.setBackgroundColor(c);
                        //String hex = String.format("#%06X", (0XFFFF & c));
                        //cuadro.setBackgroundColor(Color.parseColor(hex));
                    }

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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.moreinfor:
            default:
                AlertDialog.Builder b= new AlertDialog.Builder(ModernArtActivity.this);
                b.setTitle("Inspired by the work of artists");
                b.setMessage("Click below for more");
                b.setCancelable(true);
                b.setPositiveButton("Visit MOMA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent browser= new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.moma.org/"));
                        startActivity(browser);
                    }
                });
                b.setNegativeButton("Not now", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ModernArtActivity.this, "Enjoy the app", Toast.LENGTH_SHORT).show();
                    }
                });
                b.show();

        }

        return super.onOptionsItemSelected(item);
    }
}
