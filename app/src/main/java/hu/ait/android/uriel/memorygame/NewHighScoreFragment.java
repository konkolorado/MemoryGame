package hu.ait.android.uriel.memorygame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;


public class NewHighScoreFragment extends SupportBlurDialogFragment implements DialogInterface.OnClickListener{


    public static final String TAG = "NEWHIGHSCOREDIALOG";

    private String name;
    private String time;
    private String level;

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
        String secs = getTime().replaceFirst("^0+(?!$)", "");
        int end = secs.indexOf(':');
        secs = secs.substring(0,end);

        String mill = getTime().substring(5,8);
        String info = "\n" + getName() + "\nLevel: " + getLevel() + "\nTime: " + secs + "." + mill + " seconds!\n";

        AlertDialog.Builder builder = new AlertDialog.Builder
                (new ContextThemeWrapper(getActivity(), R.style.AlertDialogCustom));
        builder.setTitle("New High Score!");
        builder.setMessage(info);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });

        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int choice) {
        optionsFragmentInterface.onOptionsFragmentResult();
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}
    public String getLevel() {return level;}
    public void setLevel(String level) {this.level = level;}
}
