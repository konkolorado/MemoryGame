package hu.ait.android.uriel.memorygame;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class FirstTutorialFragment extends android.support.v4.app.Fragment {

    private ViewGroup rootView;
    private ImageView fingerClick;
    private ImageView mainScreen;
    private ImageView finger;
    private TextView tvStartNewGame;
    private ImageView diffScreen;
    private Handler handler;
    private Runnable intro1; private Runnable intro2; private Runnable moveFinger;
    private Runnable clickFinger; private Runnable removeMainImage;
    private Runnable showNewIntro1; private Runnable showNewIntro2;
    private Runnable clickDifficulty; private Runnable nextPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handler = new Handler(Looper.getMainLooper());
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_first_tutorial, container, false);
        mainScreen = (ImageView) rootView.findViewById(R.id.image_mainscreen);
        fingerClick = (ImageView) rootView.findViewById(R.id.finger_click);
        finger = (ImageView) rootView.findViewById(R.id.finger);
        tvStartNewGame = (TextView) rootView.findViewById(R.id.start_new_game);
        diffScreen = (ImageView) rootView.findViewById(R.id.image_diffscreen);

        intro();
        moveFinger();
        clickFinger();
        removeMainImage();
        showNewIntro();
        clickDifficulty();
        return rootView;

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(intro1);
        handler.removeCallbacks(intro2);
        handler.removeCallbacks(moveFinger);
        handler.removeCallbacks(clickFinger);
        handler.removeCallbacks(moveFinger);
        handler.removeCallbacks(removeMainImage);
        handler.removeCallbacks(showNewIntro1);
        handler.removeCallbacks(showNewIntro2);
        handler.removeCallbacks(clickDifficulty);
        handler.removeCallbacks(nextPager);
    }

    private void intro() {
        intro1 = new Runnable() {
            @Override
            public void run() {
                tvStartNewGame.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(tvStartNewGame);
            }
        };
        handler.postDelayed(intro1, 700);
        intro2 = new Runnable() {
            @Override
            public void run() {
                tvStartNewGame.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(tvStartNewGame);
            }
        };
        handler.postDelayed(intro2, 3000);
    }

    private void moveFinger() {
        moveFinger = new Runnable() {
            @Override
            public void run() {
                if (finger != null) {
                    finger.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.moveupdiagonal));
                }
            }
        };
        handler.postDelayed(moveFinger, 3500);
    }

    private void clickFinger() {
        clickFinger = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(finger);
                fingerClick.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipOutX).duration(1000).playOn(fingerClick);
            }
        };
        handler.postDelayed(clickFinger, 4600);
    }

    private void removeMainImage() {
        removeMainImage = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(mainScreen);
                diffScreen.setVisibility(View.VISIBLE);
                rootView.removeView(mainScreen);
            }
        };
        handler.postDelayed(removeMainImage, 5500);
    }

    private void showNewIntro() {
        showNewIntro1 = new Runnable() {
            @Override
            public void run() {
                tvStartNewGame.setText("Choose a difficulty");
                tvStartNewGame.setBackgroundResource(R.drawable.buttonshape_yellow);
                tvStartNewGame.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(tvStartNewGame);
            }
        };
        handler.postDelayed(showNewIntro1, 5600);
        showNewIntro2 = new Runnable() {
            @Override
            public void run() {
                tvStartNewGame.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(tvStartNewGame);
            }
        };
        handler.postDelayed(showNewIntro2, 9000);
    }

    private void clickDifficulty() {
        clickDifficulty = new Runnable() {
            @Override
            public void run() {
                fingerClick.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipOutX).duration(1000).playOn(fingerClick);
            }
        };
        handler.postDelayed(clickDifficulty, 10000);
    }

    public void killHandler() {
        if(handler != null){
            handler.removeCallbacks(intro1);
            handler.removeCallbacks(intro2);
            handler.removeCallbacks(moveFinger);
            handler.removeCallbacks(clickFinger);
            handler.removeCallbacks(removeMainImage);
            handler.removeCallbacks(showNewIntro1);
            handler.removeCallbacks(showNewIntro2);
            handler.removeCallbacks(clickDifficulty);
            handler.removeCallbacks(nextPager);
            handler=null;
        }

    }

}

