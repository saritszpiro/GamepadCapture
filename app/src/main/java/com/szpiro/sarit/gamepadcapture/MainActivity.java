package com.szpiro.sarit.gamepadcapture;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.lang.String;
import java.io.IOException;
import android.os.Environment;


public class MainActivity extends AppCompatActivity  implements
        View.OnClickListener {
    private static String logtag = "ButtonApp";//for use as the tag when logging
    private String filename = "SampleFile.txt";
    private String filepath = "system";
    File myExternalFile = new File(Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOCUMENTS), "mydata.txt");
    //String myData = ""


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.szpiro.sarit.gamepadcapture.R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(com.szpiro.sarit.gamepadcapture.R.id.toolbar);
        setSupportActionBar(toolbar);

        Button good_y = (Button) findViewById(R.id.good_y);
        good_y.setOnClickListener(this); // calling onClick() method
        Button great_b = (Button) findViewById(R.id.great_b);
        great_b.setOnClickListener(this); // calling onClick() method
        Button notuseful_a = (Button) findViewById(R.id.notuseful_a);
        notuseful_a.setOnClickListener(this); // calling onClick() method
        Button knewit_x = (Button) findViewById(R.id.knewit_x);
        knewit_x.setOnClickListener(this); // calling onClick() method

        checkExternalMedia();
        //myExternalFile = new File(getExternalFilesDir(filepath), filename);


    }

    private void checkExternalMedia(){
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Can't read or write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        Log.d(logtag,"\n\nExternal Media: readable="
                +mExternalStorageAvailable+" writable="+mExternalStorageWriteable);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.great_b:
                Toast.makeText(getApplicationContext(),
                        "Great_b Clicked at " + updateTime() , Toast.LENGTH_SHORT).show();
                break;

            case R.id.good_y:
                File root = android.os.Environment.getExternalStorageDirectory();
                Log.d(logtag,"\nExternal file system root: "+root);

                // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

                //File dir = new File (root.getAbsolutePath() + "/download");
                //dir.mkdirs();
                //File file = new File(dir, "myData.txt");
                Log.d(logtag,"onClick() called -  good_y");
                String textmsg = "good_y\t" + updateTime();
                try {
                    FileOutputStream outputWriter = new FileOutputStream(myExternalFile, true);
                    Toast.makeText(getApplicationContext(),
                            "msg to be written" + textmsg, Toast.LENGTH_SHORT).show();

                    outputWriter.write(textmsg.toString().getBytes());
                    PrintWriter pw = new PrintWriter(outputWriter);
                    pw.println("Hi , How are you");
                    pw.println("Hello");
                    pw.flush();
                    pw.close();
                    Log.d(logtag, textmsg);
                    outputWriter.close();
                    Log.d(logtag, "file closed");
                    Toast.makeText(getApplicationContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Log.i(logtag, "******* File not found. Did you" +
                            " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(logtag,"\n\nFile written to");

                break;


            case R.id.notuseful_a:
                Toast.makeText(getBaseContext(),
                        "NotUseful_A Clicked!", Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), "Hi! a!", Toast.LENGTH_SHORT).show();
                updateTime();
                Toast.makeText(getApplicationContext(), "not useful at all",
                        Toast.LENGTH_SHORT).show();

                break;


            default:
                break;
        }

    }


    public String updateTime() {
        //java.util.Date() method is used to get the current time
        String time = new Date().toString();
        Toast.makeText(getApplicationContext(),
                time, Toast.LENGTH_SHORT).show();
        return time;
    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    /*    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        boolean handled = false;
        if ((event.getSource() & android.view.InputDevice.SOURCE_GAMEPAD)
                == android.view.InputDevice.SOURCE_GAMEPAD) {
            if (event.getRepeatCount() == 0) {
                switch (keyCode) {
                    // Handle gamepad and D-pad button presses to
                    // navigate the ship
                    //...
                    case KeyEvent.KEYCODE_BUTTON_Y:
                        Toast.makeText(getApplicationContext(),
                                "Button_Y Clicked!", Toast.LENGTH_SHORT).show();
                        Log.d(logtag, "onClick() ended -  button1");
                        String time = updateTime();
                        String textmsg = "button_y\t" + time;
                        try {
                            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                            outputWriter.write(textmsg);
                            Log.d(logtag, textmsg);
                            outputWriter.close();
                            Log.d(logtag, "file closed");

                            //display file saved message
                            Toast.makeText(getApplicationContext(), "File saved successfully!",
                                    Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    case KeyEvent.KEYCODE_BUTTON_B:
                        Toast.makeText(getApplicationContext(),
                                "Button_B Clicked!", Toast.LENGTH_SHORT).show();
                        Log.d(logtag, "onClick() ended -  button b");
                        updateTime();

                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/
}

/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

/*
    @Override
    protected void onStart() {//activity is started and visible to the user
        Log.d(logtag,"onStart() called");
        super.onStart();
    }
    @Override
    protected void onResume() {//activity was resumed and is visible again
        Log.d(logtag,"onResume() called");
        super.onResume();

    }
    @Override
    protected void onPause() { //device goes to sleep or another activity appears
        Log.d(logtag,"onPause() called");//another activity is currently running (or user has pressed Home)
        super.onPause();

    }
    @Override
    protected void onStop() { //the activity is not visible anymore
        Log.d(logtag,"onStop() called");
        super.onStop();

    }
    @Override
    protected void onDestroy() {//android has killed this activity
        Log.d(logtag,"onDestroy() called");
        super.onDestroy();
    }*/
