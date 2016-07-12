package hu.ait.android.uriel.memorygame;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements DifficultyFragment.OptionsFragmentInterface{
    public static final int SETTINGS_EDIT_CODE = 101;
    private final String PREF_NAME = "MySettings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFragment(HomeFragment.TAG);

        // Parse.initialize(this, Application ID, Client Key)
        Parse.initialize(this,
                "Ry5UIG3BjiBzIcEd713oXMhlu6DSMxOAcwYVVpko",
                "sSgjCjkPns3sGGvyqloBUDQP0MJWudx83Y7ka2MT");
        ParseInstallation.getCurrentInstallation().saveInBackground();
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

        if (id == R.id.brain_icon) {
            // Create Dialog Box Fragment
            AboutFragment about = new AboutFragment();
            about.show(getSupportFragmentManager(), AboutFragment.TAG);
            return true;
        }
        if (id == R.id.action_settings) {
            // Start Settings Activity
            startSettingsActivity();
        }

        return super.onOptionsItemSelected(item);
    }

    private void showFragment(String fragmentTag) {
        if (HomeFragment.TAG.equals(fragmentTag)) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            HomeFragment homeFragment = new HomeFragment();
            fragmentTransaction.replace(R.id.layoutContainer, homeFragment, HomeFragment.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_CANCELED:
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                break;
            case RESULT_OK:
                if (requestCode == SETTINGS_EDIT_CODE) {
                    storeUserData(data);
                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    protected void storeUserData(Intent data) {
        String name = data.getStringExtra(SettingsActivity.KEYNAME);
        String age = data.getStringExtra(SettingsActivity.KEYAGE);
        String gender = data.getStringExtra(SettingsActivity.KEYGENDER);
        String cards = data.getStringExtra(SettingsActivity.KEYCARD);

        SharedPreferences sp = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong("lastLoginTimestamp", Calendar.getInstance().getTimeInMillis());
        editor.putString("userName", name);
        editor.putString("userAge", age);
        editor.putString("userGender", gender);
        editor.putString("cardType", cards);
        editor.putBoolean("firstRun", false);
        editor.commit();
    }

    public void startSettingsActivity() {
        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(i, SETTINGS_EDIT_CODE);

    }

    public void startHighScoresActivity() {
        Intent i = new Intent(MainActivity.this, HighScoreActivity.class);
        startActivity(i);
    }

    public void startTutorialActivity() {
        Intent i = new Intent(MainActivity.this, TutorialActivity.class);
        this.overridePendingTransition(R.anim.movedown,R.anim.moveup);

        startActivity(i);
    }

    @Override
    public void onOptionsFragmentResult(String level) {
        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("level", level);
        startActivity(i);
    }
}
