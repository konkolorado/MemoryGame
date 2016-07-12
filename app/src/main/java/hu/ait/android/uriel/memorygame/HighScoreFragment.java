package hu.ait.android.uriel.memorygame;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.codec.binary.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class HighScoreFragment extends android.support.v4.app.Fragment {

    public static final String TAG = "HIGHSCOREFRAGMENT";

    public static final String HIGH_SCORES_TABLENAME = "HighScores";

    ListView list_easy;
    ListView list_medium;
    ListView list_diff;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_high_score, container, false);

        list_easy = (ListView) rootView.findViewById(R.id.high_scores_easy);
        list_medium = (ListView) rootView.findViewById(R.id.high_scores_medium);
        list_diff = (ListView) rootView.findViewById(R.id.high_scores_difficult);

        getEasyScores();
        getMediumScores();
        getDiffScores();

        return rootView;
    }

    private void getDiffScores() {
        ParseQuery<ParseObject> pq = ParseQuery.getQuery(HIGH_SCORES_TABLENAME);
        pq.whereEqualTo("Level", "Difficult");


        pq.findInBackground(new FindCallback<ParseObject>() {
            final ArrayList<String> diff_scores = new ArrayList<>();
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects == null) {
                    return;
                }
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
                    String t = po.get("Time").toString().substring(0,4);
                    String s = "\t" + Integer.toString(i) + ". " + po.get("Username") + " -- Time: " + t + " sec";
                    diff_scores.add(s);
                    i++;

                }
                ArrayAdapter<String> arrayAdapterDiff =
                        new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, diff_scores);
                list_diff.setAdapter(arrayAdapterDiff);
                arrayAdapterDiff.notifyDataSetChanged();
                Utility.setListViewHeightBasedOnChildren(list_diff);
            }
        });
    }

    private void getMediumScores() {
        ParseQuery<ParseObject> pq = ParseQuery.getQuery(HIGH_SCORES_TABLENAME);
        pq.whereEqualTo("Level", "Medium");


        pq.findInBackground(new FindCallback<ParseObject>() {
            final ArrayList<String> medium_scores = new ArrayList<>();

            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects == null) {
                return;
                 }
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
                    String t = po.get("Time").toString().substring(0,4);
                    String s = "\t" + Integer.toString(i) + ". " + po.get("Username") + " -- Time: " + t + " sec";
                    medium_scores.add(s);
                    i++;

                }
                ArrayAdapter<String> arrayAdapterMedium =
                        new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, medium_scores);
                list_medium.setAdapter(arrayAdapterMedium);
                arrayAdapterMedium.notifyDataSetChanged();
                Utility.setListViewHeightBasedOnChildren(list_medium);

            }
        });

    }

    private void getEasyScores() {
        ParseQuery<ParseObject> pq = ParseQuery.getQuery(HIGH_SCORES_TABLENAME);
        pq.whereEqualTo("Level", "Easy");


        pq.findInBackground(new FindCallback<ParseObject>() {
            final ArrayList<String> easy_scores = new ArrayList<>();
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (parseObjects == null) {
                    return;
                }
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
                    String t = po.get("Time").toString().substring(0,4);
                    String s = "\t" + Integer.toString(i) + ". " + po.get("Username") + " -- Time: " + t + " sec";
                    easy_scores.add(s);
                    i++;

                }
                ArrayAdapter<String> arrayAdapterEasy =
                        new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, easy_scores);
                list_easy.setAdapter(arrayAdapterEasy);
                arrayAdapterEasy.notifyDataSetChanged();
                Utility.setListViewHeightBasedOnChildren(list_easy);

            }
        });
    }

    // Online Resource
    public static class Utility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                // pre-condition
                return;
            }

            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    }
}
