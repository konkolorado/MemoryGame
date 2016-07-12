package hu.ait.android.uriel.memorygame;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "GAMEFRAGMENT";
    private View oldView;
    private Integer guessedImageLocation = -1;
    private Integer currImage = 0;
    private int turn = 0;
    private long startTime = SystemClock.uptimeMillis();
    private long timeInMilliseconds = 0;
    private TextView timer;
    private Handler customHandler = new Handler();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final GameActivity gameActivity = ((GameActivity) getActivity());
        final String level = gameActivity.getLevel();
        final View rootView = inflater.inflate(R.layout.fragment_game, container, false);

        TextView tvLevel = (TextView) rootView.findViewById(R.id.tv_level);
        tvLevel.setText(level);

        timer = (TextView) rootView.findViewById(R.id.timer);
        //customHandler.postDelayed(updateTimerThread, 0);

        Integer rows = getRows(level);
        Integer columns = getColumns(level);

        final GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        gridview.setNumColumns(columns);

        final ImageAdapter imageAdapter = new ImageAdapter(getActivity());
        SharedPreferences sp = getActivity().getSharedPreferences("MySettings", 0);
        String card_type = sp.getString("cardType", "");
        imageAdapter.determineImages(card_type);
        imageAdapter.updateImages(rows, columns);

        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                YoYo.with(Techniques.ZoomIn).duration(500).playOn(v);
                customHandler.postDelayed(updateTimerThread, 0);
                Integer[] hiddens = imageAdapter.getHiddenIcons();
                Integer[] images = imageAdapter.getmThumbIds();
                if (turn == 0) {
                    if (hiddens[position].equals(imageAdapter.unknown)) {
                        hiddens[position] = images[position];
                        guessedImageLocation = position;
                        currImage = images[position];
                        imageAdapter.notifyDataSetChanged();
                        oldView = v;
                        turn = 1;
                    }
                } else if (turn == 1) {
                    if (hiddens[position].equals(imageAdapter.unknown)) {
                        hiddens[position] = images[position];
                        imageAdapter.notifyDataSetChanged();

                        if (images[position].equals(currImage)) {
                            waitTimeSame(gameActivity, v, 500);

                        } else {
                            waitTimeDiff(v, 500);
                            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            Integer temp = position;
                            Integer oldGuessItemLocation = guessedImageLocation;
                            delayNotify(imageAdapter, temp, hiddens, oldGuessItemLocation);
                        }
                        guessedImageLocation = -1;
                        oldView = null;
                        currImage = 0;
                        turn = 0;
                    }
                }
                if (isWinner(imageAdapter, hiddens)) {
                    customHandler.removeCallbacks(updateTimerThread);
                    updateHighscores(timer.getText().toString(), level);
                    createUIEffects(rootView);
                }
            }
        });
        return rootView;
    }

    private int getRows(String level) {
        if (level.equals("Easy")) {
            return 2;
        }
        if (level.equals("Medium")) {
            return 3;
        }
        return 4;
    }

    private int getColumns(String level) {
        if (level.equals("Easy")) {
            return 3;
        }
        if (level.equals("Medium")) {
            return 4;
        }
        return 5;
    }

    private void waitTimeSame(final GameActivity gameActivity, final View v, int time) {
        final View holdOldView = oldView;
        Handler handle = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Pulse).duration(500).playOn(holdOldView);
                YoYo.with(Techniques.Pulse).duration(500).playOn(v);
                gameActivity.vibrate();
                holdOldView.setOnClickListener(null);
                v.setOnClickListener(null);
            }
        };
        handle.postDelayed(r, time);
    }


    private void waitTimeDiff(final View v, int time) {
        final View holdOldView = oldView;
        Handler handle = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.Pulse).duration(500).playOn(holdOldView);
                YoYo.with(Techniques.Pulse).duration(500).playOn(v);
            }
        };
        handle.postDelayed(r, time);
    }

    private void delayNotify(final ImageAdapter imageAdapter,
                             final int position, final Integer[] hiddens, final Integer oldGuess) {
        Handler handler = new Handler(Looper.getMainLooper());
        final Runnable r = new Runnable() {
            public void run() {
                hiddens[oldGuess] = imageAdapter.unknown;
                hiddens[position] = imageAdapter.unknown;
                imageAdapter.notifyDataSetChanged();
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        };
        handler.postDelayed(r, 1000);
    }

    private boolean isWinner(ImageAdapter imageAdapters, Integer[] hiddens) {
        for (int i = 0; i < hiddens.length; i++) {
            if (hiddens[i].equals(imageAdapters.unknown)) {
                return false;
            }
        }
        return true;
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            int secs = (int) (timeInMilliseconds / 1000);
            int milliseconds = (int) (timeInMilliseconds % 1000);
            String s = String.format("%04d", secs) + ":" + String.format("%03d", milliseconds);
            timer.setText(s);
            customHandler.postDelayed(this, 0);
        }
    };

    private void updateHighscores(final String time, final String level) {

        // Updates high scores for each difficulty
        final double double_time = stringToInt(time);
        final ParseQuery<ParseObject> pq = ParseQuery.getQuery("HighScores");
        final ParseObject newPo = createNewParseObject(double_time, level);
        pq.whereEqualTo("Level", level);
        pq.orderByAscending("Time");

        pq.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Collections.sort(parseObjects, new Comparator<ParseObject>() {
                    @Override
                    public int compare(ParseObject p1, ParseObject p2) {
                        if (Double.parseDouble(p1.get("Time").toString()) > Double.parseDouble(p2.get("Time").toString())) {
                            return 1;
                        }
                        if (Double.parseDouble(p1.get("Time").toString()) < Double.parseDouble(p2.get("Time").toString())) {
                            return -1;
                        }
                        return 0;
                    }
                });
                int i = 1;
                for (ParseObject po : parseObjects) {
                    if (parseObjects.size() < 10 ) {
                        createHighScoreDialog(time,level);
                        break;
                    }
                    if (i == parseObjects.size() ) {
                        if (po.hasSameId(newPo)) {//Do nothing
                        }
                        if (!po.hasSameId(newPo)) {
                            createHighScoreDialog(time,level);
                        }
                        po.deleteInBackground();
                        break;
                    }
                    else {
                        i++;
                    }
                }
            }
        });


    }

    private ParseObject createNewParseObject(double double_time, String level) {
        SharedPreferences sp = getActivity().getSharedPreferences("MySettings", 0);
        String userName = sp.getString("userName","");

        ParseObject po = new ParseObject("HighScores");
        po.put("Username", userName);
        po.put("Level", level);
        po.put("Time", double_time);
        po.saveInBackground();
        return po;
    }

    private void createUIEffects(View rootView) {
        Button buttonMainMenu = (Button) rootView.findViewById(R.id.main_menu_button);
        TextView tvWinner = (TextView) rootView.findViewById(R.id.tv_winner);
        buttonMainMenu.setVisibility(View.VISIBLE);
        tvWinner.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeInUp).duration(1000).playOn(buttonMainMenu);
        YoYo.with(Techniques.FadeInDown).duration(1000).playOn(tvWinner);
        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    private double stringToInt(String time) {
        String secs = time.replaceFirst("^0+(?!$)", "");
        int end = secs.indexOf(':');
        secs = secs.substring(0,end);

        String mill = time.substring(5,8);
        String new_time = secs + "." + mill;
        return Double.parseDouble(new_time);
    }

    private void createHighScoreDialog(String time, String level) {
        NewHighScoreFragment newHighScore = new NewHighScoreFragment();
        newHighScore.setTime(time);
        newHighScore.setLevel(level);
        SharedPreferences sp = getActivity().getSharedPreferences("MySettings", 0);
        newHighScore.setName(sp.getString("userName",""));

        newHighScore.show(getActivity().getSupportFragmentManager(), NewHighScoreFragment.TAG);
    }

}
