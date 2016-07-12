package hu.ait.android.uriel.memorygame;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class GameActivity extends ActionBarActivity {
    public static String level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        level = i.getStringExtra("level");

        setContentView(R.layout.activity_game);
        showFragment(GameFragment.TAG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    private void showFragment(String fragmentTag) {
        if (GameFragment.TAG.equals(fragmentTag)) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            GameFragment gameFragment = new GameFragment();
            fragmentTransaction.replace(R.id.layoutContainer, gameFragment, GameFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    public String getLevel() {
        return level;
    }

    public void vibrate() {
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(100);
    }
}
