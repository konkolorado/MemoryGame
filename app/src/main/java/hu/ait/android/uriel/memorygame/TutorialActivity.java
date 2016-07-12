package hu.ait.android.uriel.memorygame;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.ToxicBakery.viewpager.transforms.DepthPageTransformer; //***

public class TutorialActivity extends ActionBarActivity {
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        pager = (ViewPager) findViewById(R.id.pager);
        final FragmentManager fm = getSupportFragmentManager();
        final MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(fm);
        pager.setAdapter(myFragmentAdapter);
        pager.setPageTransformer(true, new DepthPageTransformer() {
        });

        PagerTitleStrip pts = (PagerTitleStrip) findViewById(R.id.page_title_strip);
        pts.setGravity(Gravity.LEFT);
        pts.setTextColor(getResources().getColor(R.color.DarkBlue));
        pts.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Cancel the Settings update
        if (id == R.id.brain_icon) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}