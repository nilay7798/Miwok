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

public class phrases extends AppCompatActivity {
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
        final ArrayList<word> phrases = new <word>ArrayList();
        phrases.add(new word("A", "abc", R.raw.phrase_are_you_coming));
        phrases.add(new word("B", "bcd", R.raw.phrase_come_here));
        phrases.add(new word("C", "cde", R.raw.phrase_how_are_you_feeling));
        phrases.add(new word("D", "def", R.raw.phrase_im_coming));
        phrases.add(new word("E", "efg", R.raw.phrase_im_feeling_good));
        phrases.add(new word("F", "fgh", R.raw.phrase_lets_go));
        phrases.add(new word("G", "ghi", R.raw.phrase_my_name_is));
        phrases.add(new word("H", "hij", R.raw.phrase_what_is_your_name));
        phrases.add(new word("I", "ijk", R.raw.phrase_where_are_you_going));
        phrases.add(new word("J", "jkl", R.raw.phrase_yes_im_coming));


        ListView numrootview = (ListView) findViewById(R.id.numrootview);

       /*Using for loop and create various textview
        for(int i=0;i<number.size();i++) {
            TextView disnum = new TextView(this);
            disnum.setText(number.get(i));
            numrootview.addView(disnum);
        }*/

        WordAdapter numberadapter = new WordAdapter(this, phrases, R.color.skyblue);
        numrootview.setAdapter(numberadapter);
        numrootview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                word get = phrases.get(i);
                release();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(phrases.this, get.getAudioresid());
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
