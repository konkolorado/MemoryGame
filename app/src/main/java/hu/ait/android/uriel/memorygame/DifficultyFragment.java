package hu.ait.android.uriel.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;


public class DifficultyFragment extends SupportBlurDialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "DIFFICULTYDIALOG";

    public interface OptionsFragmentInterface {
        public void onOptionsFragmentResult(String difficulty);
    }

    private OptionsFragmentInterface optionsFragmentInterface;

    private String[] options = {"Easy", "Medium", "Difficult"};

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            optionsFragmentInterface = (OptionsFragmentInterface) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OptionsFragmentInterface");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder
                (new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
        builder.setTitle("Choose difficulty level");
        builder.setItems(options, this);
        AlertDialog alert = builder.create();

        return alert;
    }

    @Override
    public void onClick(DialogInterface dialog, int choice) {
        optionsFragmentInterface.onOptionsFragmentResult(options[choice]);
    }


}

