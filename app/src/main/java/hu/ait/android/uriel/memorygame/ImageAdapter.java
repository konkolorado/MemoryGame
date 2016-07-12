package hu.ait.android.uriel.memorygame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by urielmandujano on 4/12/15.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public Integer unknown = R.drawable.ic_action_help;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(180, 180));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(hiddenIcons[position]);
        return imageView;
    }
    private Integer[] mThumbIds;

    // references to our images
    private Integer[] mThumbIds1 = {
            R.drawable.angry_bird1, R.drawable.angry_bird2,
            R.drawable.angry_bird3, R.drawable.angry_bird4,
            R.drawable.angry_bird5, R.drawable.angry_bird6,
            R.drawable.angry_bird7, R.drawable.angry_bird8,
            R.drawable.angry_bird9, R.drawable.angry_bird10,
    };

    private Integer[] mThumbIds2 = {
            R.drawable.argentina, R.drawable.brasil,
            R.drawable.germany, R.drawable.mexico,
            R.drawable.netherlands, R.drawable.salvador,
            R.drawable.southkorea, R.drawable.spain,
            R.drawable.turkey, R.drawable.usa
    };

    private Integer[] hiddenIcons = {
            R.drawable.ic_action_help, R.drawable.ic_action_help,
            R.drawable.ic_action_help, R.drawable.ic_action_help,
            R.drawable.ic_action_help, R.drawable.ic_action_help,
            R.drawable.ic_action_help, R.drawable.ic_action_help,
            R.drawable.ic_action_help, R.drawable.ic_action_help,
    };

    public Integer[] getImages() {
        return mThumbIds;
    }

    public void setImages(Integer[] newImages) {
        mThumbIds = newImages;
    }

    public void updateImages(Integer rows, Integer columns) {
        Integer numImages = rows * columns / 2;
        Integer[] newThumbIds = new Integer[numImages*2];
        ArrayList properImages = new ArrayList();

        // Put old images into array list to be shuffled
        ArrayList al = new ArrayList();
        for (int i = 0; i < mThumbIds.length; i++) {
            al.add(mThumbIds[i]);
        }
        Collections.shuffle(al);

        //Choose the first numImages from the ArrayList as new images
        // and add them to a new array list
        for (int i = 0; i < numImages; i++) {
            properImages.add(al.get(i));
            properImages.add(al.get(i));
        }

        Collections.shuffle(properImages);
        // Convert the arraylist into an int array
        properImages.toArray(newThumbIds);

        mThumbIds = newThumbIds;
        notifyDataSetChanged();

        // Update the number of hidden icons
        ArrayList newHiddens = new ArrayList();
        Integer[] newHiddenArray = new Integer[numImages*2];
        for (int i = 0; i < mThumbIds.length; i++) {
            newHiddens.add(hiddenIcons[0]);
        }
        newHiddens.toArray(newHiddenArray);
        hiddenIcons = newHiddenArray;
    }

    public Integer[] getHiddenIcons() {
        return hiddenIcons;
    }

    public Integer[] getmThumbIds() {
        return mThumbIds;
    }


    public void determineImages(String card_type) {
        if (card_type.equals("Angry Birds")) {
            mThumbIds = mThumbIds1;
        }
        else {
            mThumbIds = mThumbIds2;
        }
    }
}
