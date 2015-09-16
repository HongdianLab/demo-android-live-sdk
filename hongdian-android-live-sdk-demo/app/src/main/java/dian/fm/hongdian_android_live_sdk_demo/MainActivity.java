package dian.fm.hongdian_android_live_sdk_demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import hd.hdmedia.HDMediaModule;

public class MainActivity extends ListActivity {

    private static final String[] items = new String[]{"音频", "视频"};

    static {
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("openh264");
        System.loadLibrary("opustool");
        System.loadLibrary("hdcodec");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));

        HDMediaModule.getInstance().setAppId("hongdianhongdian");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, "你单击了" + items[position], Toast.LENGTH_SHORT).show();
        String itemName = items[position];
        Intent intent = null;
        if (itemName.equals(items[0])){
            intent = new Intent(MainActivity.this, AudioActivity.class);
        }else if (itemName.equals(items[1])){
            intent = new Intent(MainActivity.this, VideoActivity.class);
        }
        startActivity(intent);

    }
}
