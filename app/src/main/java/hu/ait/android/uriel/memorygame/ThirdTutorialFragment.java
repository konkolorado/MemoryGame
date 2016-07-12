package hu.ait.android.uriel.memorygame;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class ThirdTutorialFragment extends android.support.v4.app.Fragment {

    private ViewGroup rootView;
    private ImageView empty_screen;
    private ImageView birds_screen;
    private ImageView soccer_screen;
    private TextView message;
    private ImageView finger;

    private Handler handler;

    private Runnable screen1_intro1;
    private Runnable screen1_intro2;
    private Runnable screen2_intro1;
    private Runnable screen2_intro2;
    private Runnable show_bird_screen;
    private Runnable show_soccer_screen;
    private Runnable moves1;
    private Runnable moves2;
    private Runnable moves3;
    private Runnable moves4;
    private Runnable moves5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handler = new Handler(Looper.getMainLooper());
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_third_tutorial, container, false);

        empty_screen = (ImageView) rootView.findViewById(R.id.blank_screen);
        birds_screen = (ImageView) rootView.findViewById(R.id.birds_screen);
        soccer_screen = (ImageView) rootView.findViewById(R.id.soccer_screen);
        message = (TextView) rootView.findViewById(R.id.message_box);
        finger = (ImageView) rootView.findViewById(R.id.crazy_finger);

        screen1Intro();
        crazyMoves();
        showBird();
        screen2Intro();
        showSoccer();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(screen1_intro1);
        handler.removeCallbacks(screen1_intro2);
        handler.removeCallbacks(screen2_intro1);
        handler.removeCallbacks(screen2_intro2);
        handler.removeCallbacks(show_bird_screen);
        handler.removeCallbacks(show_soccer_screen);
        handler.removeCallbacks(moves1);
        handler.removeCallbacks(moves2);
        handler.removeCallbacks(moves3);
        handler.removeCallbacks(moves4);
        handler.removeCallbacks(moves5);
    }

    private void screen1Intro() {
        screen1_intro1 = new Runnable() {
            @Override
            public void run() {
                message.setText("Race to win");
                message.setBackgroundResource(R.drawable.buttonshape_yellow);
                message.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(message);
            }
        };
        handler.postDelayed(screen1_intro1, 700);
        screen1_intro2 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(message);
            }
        };
        handler.postDelayed(screen1_intro2, 5700);
    }

    private void crazyMoves() {
        moves1 = new Runnable() {
            @Override
            public void run() {
                finger.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.BounceInUp).duration(1000).playOn(finger);
            }
        };
        handler.postDelayed(moves1, 1700);
        moves2 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.RubberBand).duration(1000).playOn(finger);
            }
        };
        handler.postDelayed(moves2, 2700);
        moves3 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Wobble).duration(2000).playOn(finger);
            }
        };
        handler.postDelayed(moves3, 3700);
        moves4 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Swing).duration(2000).playOn(finger);
            }
        };
        handler.postDelayed(moves4, 3700);
        moves5 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.TakingOff).duration(1000).playOn(finger);
            }
        };
        handler.postDelayed(moves5, 5700);
    }

    private void showBird() {
        show_bird_screen = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(500).playOn(empty_screen);
                empty_screen.setVisibility(View.INVISIBLE);
                //YoYo.with(Techniques.FadeIn).duration(500).playOn(birds_screen);
                birds_screen.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(show_bird_screen, 6500);
    }

    private void screen2Intro() {
        screen2_intro1 = new Runnable() {
            @Override
            public void run() {
                message.setText("Customize your game");
                message.setBackgroundResource(R.drawable.buttonshape_orange);
                message.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(message);
            }
        };
        handler.postDelayed(screen2_intro1, 7000);
        screen2_intro2 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(message);
            }
        };
        handler.postDelayed(screen2_intro2, 11600);
    }

    private void showSoccer() {
        show_soccer_screen = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(500).playOn(birds_screen);
                birds_screen.setVisibility(View.INVISIBLE);
                YoYo.with(Techniques.FadeIn).duration(500).playOn(soccer_screen);
                soccer_screen.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(show_soccer_screen, 9300);
    }



}
