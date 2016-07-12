package hu.ait.android.uriel.memorygame;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class HighScoreActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        showFragment(HighScoreFragment.TAG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.brain_icon) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(String fragmentTag) {
        if (HighScoreFragment.TAG.equals(fragmentTag)) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            HighScoreFragment highScoreFragment = new HighScoreFragment();
            fragmentTransaction.replace(R.id.layoutContainer, highScoreFragment, HighScoreFragment.TAG);
            fragmentTransaction.commit();
        }
    }

}
