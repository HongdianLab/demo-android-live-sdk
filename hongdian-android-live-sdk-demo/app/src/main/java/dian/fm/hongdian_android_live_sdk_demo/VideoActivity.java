package dian.fm.hongdian_android_live_sdk_demo;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import hd.hdmedia.HDMediaModule;

public class VideoActivity extends AppCompatActivity {

    private EditText _roomIDEditText;
    private EditText _selfIDEditText;
    private EditText _userIDEditText;
    private GLSurfaceView _localView;
    private GLSurfaceView _netView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        _roomIDEditText = (EditText)findViewById(R.id.roomIDEditText);
        _selfIDEditText = (EditText)findViewById(R.id.selfIDEditText);
        _userIDEditText = (EditText)findViewById(R.id.userIDEditText);

        _selfIDEditText.setText("" + (int) (Math.random() * 100000));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
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
        RelativeLayout.LayoutParams params;
        switch (v.getId()){
            case  R.id.videoRecordStart:
                if (_localView != null){
                    ((RelativeLayout)findViewById(R.id.localView)).removeView(_localView);
                }

                _localView = new GLSurfaceView(getApplicationContext());
                params =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 390);
                _localView.setLayoutParams(params);

                ((RelativeLayout)findViewById(R.id.localView)).addView(_localView);
                ((RelativeLayout)findViewById(R.id.localView)).bringChildToFront(findViewById(R.id.toolLayout));

                GLSurfaceView tempView = new GLSurfaceView(getApplicationContext());
                _localView.setEGLContextClientVersion(2);
                HDMediaModule.getInstance().bindPreview(tempView, _localView);

                HDMediaModule.getInstance().startVideoRecord(_roomIDEditText.getText().toString(), _selfIDEditText.getText().toString());
                break;
            case  R.id.videoRecordStop:
                HDMediaModule.getInstance().stopVideoRecord();
                if (_localView != null){
                    ((RelativeLayout)findViewById(R.id.localView)).removeView(_localView);
                }
                break;
            case R.id.videoPlayStart:
                if (_netView != null){
                    ((RelativeLayout)findViewById(R.id.netView)).removeView(_netView);
                }
                _netView = new GLSurfaceView(this);
                params =  new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 390);
                _netView.setLayoutParams(params);

                ((RelativeLayout)findViewById(R.id.netView)).addView(_netView);
                ((RelativeLayout)findViewById(R.id.netView)).bringChildToFront(findViewById(R.id.startPlayLayout));

                HDMediaModule.getInstance().bindViewToUserId(_userIDEditText.getText().toString(), _netView);
                HDMediaModule.getInstance().startVideoPlay(_roomIDEditText.getText().toString(), _userIDEditText.getText().toString(), _selfIDEditText.getText().toString());
                break;
            case R.id.videoPlayStop:
                HDMediaModule.getInstance().stopVideoPlay();
                if (_netView != null){
                    ((RelativeLayout)findViewById(R.id.netView)).removeView(_netView);
                }
                break;
            case R.id.changeCamera:
                break;
            case R.id.flash:
                break;
            default:

        }
    }
}
