package kazin.tedcheck;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import kazin.tedcheck.fragments.SectionsPagerAdapter;
import kazin.tedcheck.network.Rss;
import kazin.tedcheck.network.RssItem;


public class MainActivity extends ActionBarActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    Rss rss = new Rss();

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        rss.get("http://www.ted.com/themes/rss/id/6", new CallbackRss());
    }

    public void refreshAdapter(List<RssItem> list){
        mSectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), list);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mViewPager.setAdapter(mSectionsPagerAdapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            rss.get("http://www.ted.com/themes/rss/id/6", new CallbackRss());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class CallbackRss {
        public void OnLoaded(List<RssItem> articles) {
            Log.d("RssCallback", "OnLoaded fire");
            refreshAdapter(articles);
        }
    }
}
