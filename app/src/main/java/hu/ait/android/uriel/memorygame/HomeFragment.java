package hu.ait.android.uriel.memorygame;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.skyfishjy.library.RippleBackground;


public class HomeFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "HOMEFRAGMENT";
    private Button buttonNewGame;
    private Button buttonEditProfile;
    private Button buttonTutorial;
    private Button buttonHighscores;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        buttonNewGame = (Button) rootView.findViewById(R.id.new_game);
        buttonEditProfile = (Button) rootView.findViewById(R.id.change_profile);
        buttonTutorial = (Button) rootView.findViewById(R.id.tutorial);
        buttonHighscores = (Button) rootView.findViewById(R.id.high_scores);

        // Create Ripple Animation
        final RippleBackground rippleBackground = (RippleBackground) rootView.findViewById(R.id.content);
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.embedded_image);
        rippleBackground.startRippleAnimation();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Remove onClick listener
                imageView.setOnClickListener(null);

                // Clear ripple effect
                rippleBackground.stopRippleAnimation();
                ((ViewManager) container.getParent()).removeView(rippleBackground);

                // Move image up
                imageView.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.moveup));

                // Create Button Animations
                animateButtons();

                buttonNewGame.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // New Game Activity
                        DifficultyFragment difficulty = new DifficultyFragment();
                        difficulty.show(getActivity().getSupportFragmentManager(), DifficultyFragment.TAG);
                    }
                });

                buttonEditProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).startSettingsActivity();
                    }
                });

                buttonTutorial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).startTutorialActivity();
                    }
                });

                buttonHighscores.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((MainActivity) getActivity()).startHighScoresActivity();
                    }
                });
            }
        });
        return rootView;
    }

    private void animateButtons() {
        setAnimationTiming(buttonNewGame, 100);
        setAnimationTiming(buttonEditProfile, 300);
        setAnimationTiming(buttonTutorial, 500);
        setAnimationTiming(buttonHighscores, 700);
    }

    private void setAnimationTiming(Button button, int time) {
        final Button button1 = button;
        Handler handler = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            public void run() {
                fadeInButton(button1);
            }
        };
        handler.postDelayed(r, time);
    }

    private void fadeInButton(Button button) {
        button.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInRight).duration(1000).playOn(button);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
