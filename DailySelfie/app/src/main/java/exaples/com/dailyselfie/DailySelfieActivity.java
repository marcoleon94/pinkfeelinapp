package exaples.com.dailyselfie;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DailySelfieActivity extends AppCompatActivity {

    private int CAMERA_PIC_REQUEST=1;
    private ListView mListView;
    public Context mContext;
    private String mCurrentPhotoPath;
    private Intent cameraApp;
    private PictureAdapter mPictureAdapter;
    private File photoFile;
    private String mPath;
    private static final int notification_ID=123456789;
    public NotificationCompat.Builder notification;
    public NotificationManager mNotificationManager;
    private Notifications service;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
    private void getPrevious(){
        mPath= Environment.getExternalStorageDirectory().toString()+"/Android/data/exaples.com.dailyselfie/files/Pictures";
        System.out.println("Path for pictures: "+ mPath);
        File directory = new File(mPath);
        File [] files= directory.listFiles();
        if(files!=null) {
            for (int x = 0; x < files.length; x++) {
                mPictureAdapter.add(files[x]);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_selfie);
        mListView= (ListView) findViewById(R.id.photo_list);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent largeView = new Intent(DailySelfieActivity.this,LargeViewActivity.class);
                Bundle b=new Bundle();
                File f=(File)mPictureAdapter.getItem(position);
                b.putString("path",f.getAbsolutePath());
                largeView.putExtra("info",b);
                startActivity(largeView);
            }
        });
        mPictureAdapter=new PictureAdapter(this);
        mListView.setAdapter(mPictureAdapter);
        getPrevious();
        mContext=this.getBaseContext();
        notification=new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        mNotificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        service= new Notifications();
        service.execute();



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_PIC_REQUEST && resultCode == RESULT_OK){
            Toast.makeText(this, "Photo saved", Toast.LENGTH_SHORT).show();
            mPictureAdapter.add(photoFile);


        }else{
            if(photoFile.delete()){
                Toast.makeText(this, "Photo not taken", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= this.getMenuInflater();
        inflater.inflate(R.menu.daily_selfie_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.camera:
                cameraApp=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(this,
                            "com.example.android.fileprovider",
                            photoFile);
                    cameraApp.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(cameraApp, CAMERA_PIC_REQUEST);

                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
    public class Notifications extends AsyncTask<Void, Void, Void> {
        private int TWO_MINUTES=2*(1000*60);
        private int TEN_SEC=1000*30;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(TEN_SEC);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notification.setSmallIcon(R.mipmap.ic_launcher);
            notification.setTicker("You should take another picture");
            notification.setContentTitle("Daily Selfie");
            notification.setContentText("Get a new daily selfie");
            Intent intent= new Intent(DailySelfieActivity.this, DailySelfieActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(DailySelfieActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pendingIntent);
            mNotificationManager.notify(notification_ID,notification.build());
            return null;
        }
    }

}

