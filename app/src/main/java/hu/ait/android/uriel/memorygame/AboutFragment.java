package hu.ait.android.uriel.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;

/**
 * Created by urielmandujano on 4/12/15.
 */

public class AboutFragment extends SupportBlurDialogFragment implements DialogInterface.OnClickListener {
    public static final String TAG = "ABOUTDIALOG";

    public interface OptionsFragmentInterface {
        public void onOptionsFragmentResult();
    }

    private OptionsFragmentInterface optionsFragmentInterface;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String info = "Lead Developer: Uriel Mandujano" + "\tMemory Mash v1.0\n" ;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("About the Developer");
        builder.setMessage(info);
        AlertDialog alert = builder.create();
        return alert;
    }

    @Override
    public void onClick(DialogInterface dialog, int choice) {
        optionsFragmentInterface.onOptionsFragmentResult();
    }
}
