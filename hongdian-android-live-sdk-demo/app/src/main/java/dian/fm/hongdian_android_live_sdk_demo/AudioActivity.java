package dian.fm.hongdian_android_live_sdk_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import hd.hdmedia.HDMediaModule;

public class AudioActivity extends AppCompatActivity {

    private EditText _roomIDEditText;
    private EditText _selfIDEditText;
    private EditText _userIDEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        _roomIDEditText = (EditText)findViewById(R.id.roomIDEditText);
        _selfIDEditText = (EditText)findViewById(R.id.selfIDEditText);
        _userIDEditText = (EditText)findViewById(R.id.userIDEditText);

        _selfIDEditText.setText("" + (int)(Math.random() * 100000));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_audio, menu);
        return true;
    }

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

    public void onClickButton(View v){
        try {
            switch (v.getId()) {
                case R.id.audioRecordStart:
                    HDMediaModule.getInstance().startAudioRecord(_roomIDEditText.getText().toString(), _selfIDEditText.getText().toString());
                    break;
                case R.id.audioRecordStop:
                    HDMediaModule.getInstance().stopAudioRecord();
                    break;
                case R.id.audioPlayStart:
                    HDMediaModule.getInstance().startAudioPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
                    break;
                case R.id.audioPlayStop:
//                HDMediaModule.getInstance().stopAudioPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
                    HDMediaModule.getInstance().stopAudioPlay();
                    break;
                default:

            }
        } catch (Exception e) {
            Log.e("hd-onClickAudio", e.getMessage());
        }
    }

}
