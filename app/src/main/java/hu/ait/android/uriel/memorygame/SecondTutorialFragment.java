package hu.ait.android.uriel.memorygame;

import android.media.Image;
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


public class SecondTutorialFragment extends android.support.v4.app.Fragment {

    private ViewGroup rootView;
    private ImageView mainScreen;
    private ImageView oneClickScreen;
    private ImageView twoClickRight;
    private ImageView twoClickWrong;
    private TextView screenMessage;
    private ImageView finger_single;
    private ImageView finger_single_click;
    private ImageView finger_wrong;
    private ImageView finger_wrong_click;
    private ImageView finger_right;
    private ImageView finger_right_click;


    private Handler handler;
    private Runnable screen1_intro1;
    private Runnable screen1_intro2;
    private Runnable screen2_intro1;
    private Runnable screen2_intro2;
    private Runnable moveFinger;
    private Runnable moveFinger2;
    private Runnable moveFinger3;
    private Runnable clickFinger;
    private Runnable clickFinger2;
    private Runnable clickFinger3;
    private Runnable setOneClickScreen;
    private Runnable setTwoClickScreenWrong;
    private Runnable setTwoClickScreenRight;
    private Runnable returnOneClickScreen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        handler = new Handler(Looper.getMainLooper());
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_second_tutorial, container, false);
        mainScreen = (ImageView) rootView.findViewById(R.id.image_mainscreen);
        oneClickScreen = (ImageView) rootView.findViewById(R.id.onemovescreen);
        twoClickRight = (ImageView) rootView.findViewById(R.id.twomovescreen_right);
        twoClickWrong = (ImageView) rootView.findViewById(R.id.twomovescreen_wrong);
        screenMessage = (TextView) rootView.findViewById(R.id.choose_field);
        finger_single = (ImageView) rootView.findViewById(R.id.finger1);
        finger_single_click = (ImageView) rootView.findViewById(R.id.finger2);
        finger_wrong = (ImageView) rootView.findViewById(R.id.finger_wrong);
        finger_wrong_click = (ImageView) rootView.findViewById(R.id.finger_wrong_click);
        finger_right = (ImageView) rootView.findViewById(R.id.finger_right);
        finger_right_click = (ImageView) rootView.findViewById(R.id.finger_right_click);

        screen1Intro();
        moveFinger();
        clickFinger();
        setOneClickScreen();
        screen2Intro();
        moveFinger2();
        clickFinger2();
        setTwoClickScreenWrong();
        returnOneClickScreen();
        moveFinger3();
        clickFinger3();
        setTwoClickScreenRight();

        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(screen1_intro1);
        handler.removeCallbacks(screen1_intro2);
        handler.removeCallbacks(screen2_intro1);
        handler.removeCallbacks(screen2_intro2);
        handler.removeCallbacks(moveFinger);
        handler.removeCallbacks(moveFinger2);
        handler.removeCallbacks(moveFinger3);
        handler.removeCallbacks(clickFinger);
        handler.removeCallbacks(clickFinger2);
        handler.removeCallbacks(clickFinger3);
        handler.removeCallbacks(setOneClickScreen);
        handler.removeCallbacks(setTwoClickScreenWrong);
        handler.removeCallbacks(setTwoClickScreenRight);
        handler.removeCallbacks(returnOneClickScreen);
    }

    private void screen1Intro() {
        screen1_intro1 = new Runnable() {
            @Override
            public void run() {
                screenMessage.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(screenMessage);
            }
        };
        handler.postDelayed(screen1_intro1, 700);
        screen1_intro2 = new Runnable() {
            @Override
            public void run() {
                screenMessage.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(screenMessage);
            }
        };
        handler.postDelayed(screen1_intro2, 3000);
    }

    private void moveFinger() {
        moveFinger = new Runnable() {
            @Override
            public void run() {
                if (finger_single != null) {
                    finger_single.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.moveupdiagonal_choose));
                }
            }
        };
        handler.postDelayed(moveFinger, 3500);
    }

    private void clickFinger() {
        clickFinger = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(finger_single);
                finger_single_click.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipOutX).duration(1000).playOn(finger_single_click);
            }
        };
        handler.postDelayed(clickFinger, 4600);
    }

    private void setOneClickScreen() {
        setOneClickScreen = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(mainScreen);
                oneClickScreen.setVisibility(View.VISIBLE);
                rootView.removeView(mainScreen);
            }
        };
        handler.postDelayed(setOneClickScreen, 4700);
    }

    private void screen2Intro() {
        screen2_intro1 = new Runnable() {
            @Override
            public void run() {
                screenMessage.setText("Match 2 tiles");
                screenMessage.setBackgroundResource(R.drawable.buttonshape_red);
                screenMessage.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideInLeft).duration(1000).playOn(screenMessage);
            }
        };
        handler.postDelayed(screen2_intro1, 5400);

        screen2_intro2 = new Runnable() {
            @Override
            public void run() {
                screenMessage.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.SlideOutRight).duration(1000).playOn(screenMessage);
            }
        };
        handler.postDelayed(screen2_intro2, 7400);
    }

    private void moveFinger2() {
        moveFinger2 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeIn).duration(100).playOn(finger_wrong);
                if (finger_wrong != null) {
                    finger_wrong.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.moveright_choose));
                }
                }
        };
        handler.postDelayed(moveFinger2, 7900);

    }

    private void clickFinger2() {
        clickFinger2 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(finger_wrong);
                finger_wrong_click.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipOutX).duration(1000).playOn(finger_wrong_click);
            }
        };
        handler.postDelayed(clickFinger2, 9000);
    }

    private void setTwoClickScreenWrong() {
        setTwoClickScreenWrong = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(oneClickScreen);
                twoClickWrong.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(setTwoClickScreenWrong, 9100);
    }

    private void returnOneClickScreen() {
        returnOneClickScreen = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(0).playOn(twoClickWrong);
                twoClickWrong.setVisibility(View.INVISIBLE);
                YoYo.with(Techniques.FadeIn).duration(0).playOn(oneClickScreen);
                oneClickScreen.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(returnOneClickScreen, 11000);
    }

    private void moveFinger3() {
        moveFinger3 = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeIn).duration(100).playOn(finger_right);
                if (finger_right != null) {
                    finger_right.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.moveleft_choose));
                }
            }
        };
        handler.postDelayed(moveFinger3, 12000);
    }

    private void clickFinger3() {
        clickFinger3 = new Runnable() {
        @Override
        public void run() {
            YoYo.with(Techniques.FadeOut).duration(100).playOn(finger_right);
            finger_right_click.setVisibility(View.VISIBLE);
            YoYo.with(Techniques.FlipOutX).duration(1000).playOn(finger_right_click);
        }
    };
    handler.postDelayed(clickFinger3, 13100);
    }

    private void setTwoClickScreenRight() {
        setTwoClickScreenRight = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.FadeOut).duration(100).playOn(oneClickScreen);
                twoClickRight.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(setTwoClickScreenRight, 13200);
    }

}





