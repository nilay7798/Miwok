package com.example.npatel.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class color extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                release();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mcompletelistener = new MediaPlayer.OnCompletionListener() {
        @Override

        public void onCompletion(MediaPlayer mediaPlayer) {
            release();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<word> color = new <word>ArrayList();
        color.add(new word("Red", "abc", R.drawable.color_red, R.raw.color_red));
        color.add(new word("Yello", "bcd", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        color.add(new word("Green", "cde", R.drawable.color_green, R.raw.color_green));
        color.add(new word("brown", "def", R.drawable.color_brown, R.raw.color_brown));
        color.add(new word("Gray", "efg", R.drawable.color_gray, R.raw.color_gray));
        color.add(new word("Black", "fgh", R.drawable.color_black, R.raw.color_black));
        color.add(new word("White", "ghi", R.drawable.color_white, R.raw.color_white));
        color.add(new word("mustard yello", "hij", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        color.add(new word("dusty yello", "ijk", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        color.add(new word("blue", "jkl", R.color.colorPrimaryDark, R.raw.color_black));


        ListView numrootview = (ListView) findViewById(R.id.numrootview);

       /*Using for loop and create various textview
        for(int i=0;i<number.size();i++) {
            TextView disnum = new TextView(this);
            disnum.setText(number.get(i));
            numrootview.addView(disnum);
        }*/

        WordAdapter numberadapter = new WordAdapter(this, color, R.color.violate);
        numrootview.setAdapter(numberadapter);
        numrootview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word get = color.get(i);
                release();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(color.this, get.getAudioresid());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mcompletelistener);
                }

            }
        });
    }

    private void release() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    protected void onStop() {
        super.onStop();
        release();
    }
}

