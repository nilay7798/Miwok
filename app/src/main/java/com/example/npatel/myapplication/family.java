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

public class family extends AppCompatActivity {

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
        color.add(new word("Father", "abc", R.drawable.family_father, R.raw.family_father));
        color.add(new word("mother", "bcd", R.drawable.family_mother, R.raw.family_mother));
        color.add(new word("son", "cde", R.drawable.family_son, R.raw.family_son));
        color.add(new word("daughter", "def", R.drawable.family_daughter, R.raw.family_daughter));
        color.add(new word("old brother", "efg", R.drawable.family_older_brother, R.raw.family_older_brother));
        color.add(new word("younger brother", "fgh", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        color.add(new word("grand mother", "ghi", R.drawable.family_grandmother, R.raw.family_grandmother));
        color.add(new word("older sister", "hij", R.drawable.family_older_sister, R.raw.family_older_sister));
        color.add(new word("younger sister", "ijk", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        color.add(new word("grand father", "jkl", R.drawable.family_grandfather, R.raw.family_grandfather));


        ListView numrootview = (ListView) findViewById(R.id.numrootview);

       /*Using for loop and create various textview
        for(int i=0;i<number.size();i++) {
            TextView disnum = new TextView(this);
            disnum.setText(number.get(i));
            numrootview.addView(disnum);
        }*/

        WordAdapter numberadapter = new WordAdapter(this, color, R.color.green);


        numrootview.setAdapter(numberadapter);
        numrootview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word get = color.get(i);
                release();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(family.this, get.getAudioresid());
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

