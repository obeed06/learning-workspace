package android.love;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class HaikuDisplay extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haiku_display);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_haiku_display, menu);
        return true;
    }
    
    public void onLoveButtonClicked(View view)  {
    	TextView textView = (TextView) findViewById(R.id.haikuTextView);
    	textView.setVisibility(View.VISIBLE);
    }
    
}
