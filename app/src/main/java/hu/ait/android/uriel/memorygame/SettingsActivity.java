package hu.ait.android.uriel.memorygame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class SettingsActivity extends ActionBarActivity {

    public static final String KEYNAME = "KEYNAME";
    public static final String KEYAGE = "KEYAGE";
    public static final String KEYGENDER = "KEYGENDER";
    public static final String KEYCARD = "KEYCARD";

    private String etName = "";
    private String etAge = "";
    private String gender = "";
    private String cards = "";


    public String getEtName() {return etName;}
    public void setEtName(String etName) {this.etName = etName;}
    public String getEtAge() {return etAge;}
    public void setEtAge(String etAge) {this.etAge = etAge;}
    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}
    public String getCards() {return cards;}
    public void setCards(String cards) {this.cards = cards;}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        showFragment(SettingsFragment.TAG);
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

    private SettingsFragment showFragment(String fragmentTag) {
        if (SettingsFragment.TAG.equals(fragmentTag)) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            SettingsFragment settingsFragment = new SettingsFragment();
            fragmentTransaction.replace(R.id.layoutContainer, settingsFragment, SettingsFragment.TAG);
            fragmentTransaction.commit();
            return settingsFragment;
        }
        return null;
    }

    public void setSuccessful() {
        Intent intentResult = new Intent();
        intentResult.putExtra(KEYNAME,etName);
        intentResult.putExtra(KEYAGE,etAge);
        intentResult.putExtra(KEYGENDER, gender);
        intentResult.putExtra(KEYCARD, cards);
        setResult(RESULT_OK, intentResult);
        finish();
    }

    public void setFields() {
        SharedPreferences sp = getSharedPreferences("MySettings", MODE_PRIVATE);
        etName = sp.getString("userName","");
        etAge = sp.getString("userAge", "");
        gender = sp.getString("userGender", "");
        cards = sp.getString("cardType", "");
    }

    public boolean isFirstRun() {
        SharedPreferences sp = getSharedPreferences("MySettings", MODE_PRIVATE);
        return sp.getBoolean("firstRun", true);
    }
}
