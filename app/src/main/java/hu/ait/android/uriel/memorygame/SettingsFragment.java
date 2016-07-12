package hu.ait.android.uriel.memorygame;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class SettingsFragment extends android.support.v4.app.Fragment {
    public static final String TAG = "SETTINGSFRAGMENT";

    EditText etName;
    EditText etAge;
    Spinner spinnerGender;
    Spinner spinnerCards;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        final SettingsActivity settingsActivity = (SettingsActivity) getActivity();

        etName = (EditText) rootView.findViewById(R.id.settings_name);
        etAge = (EditText) rootView.findViewById(R.id.settings_age);


        // Set Gender Spinner items
        spinnerGender = (Spinner) rootView.findViewById(R.id.settings_gender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.genders_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        // Set Card Type Spinner items
        spinnerCards = (Spinner) rootView.findViewById(R.id.settings_cards);
        ArrayAdapter<CharSequence> cardAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cards_array, android.R.layout.simple_spinner_item);
        cardAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCards.setAdapter(cardAdapter);

        if (!settingsActivity.isFirstRun()) {
            settingsActivity.setFields();
            etName.setText(settingsActivity.getEtName());
            etAge.setText(settingsActivity.getEtAge());

            int spinnerPostion = adapter.getPosition(settingsActivity.getGender());
            spinnerGender.setSelection(spinnerPostion);

            int cardSpinnerPosition = cardAdapter.getPosition(settingsActivity.getCards());
            spinnerCards.setSelection(cardSpinnerPosition);
        }

        // Set onClickListener for Save button
        Button buttonSave = (Button) rootView.findViewById(R.id.settings_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save things
                settingsActivity.setEtName(etName.getText().toString());
                settingsActivity.setEtAge(etAge.getText().toString());
                settingsActivity.setGender(spinnerGender.getSelectedItem().toString());
                settingsActivity.setCards(spinnerCards.getSelectedItem().toString());

                // Set code to ok
                settingsActivity.setSuccessful();
            }
        });

        return rootView;
    }
}
