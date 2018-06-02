package com.example.npatel.myapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.provider.UserDictionary;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class number extends AppCompatActivity {
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
        final ArrayList<word> number = new <word>ArrayList();
        number.add(new word("one", "abc", R.drawable.number_one, R.raw.number_one));
        number.add(new word("Two", "bcd", R.drawable.number_two, R.raw.number_two));
        number.add(new word("Three", "cde", R.drawable.number_three, R.raw.number_three));
        number.add(new word("Four", "def", R.drawable.number_four, R.raw.number_four));
        number.add(new word("Five", "efg", R.drawable.number_five, R.raw.number_five));
        number.add(new word("Six", "fgh", R.drawable.number_six, R.raw.number_six));
        number.add(new word("Seven", "ghi", R.drawable.number_seven, R.raw.number_seven));
        number.add(new word("Eight", "hij", R.drawable.number_eight, R.raw.number_eight));
        number.add(new word("Nine", "ijk", R.drawable.number_nine, R.raw.number_nine));
        number.add(new word("Ten", "jkl", R.drawable.number_ten, R.raw.number_ten));


        ListView numrootview = (ListView) findViewById(R.id.numrootview);

       /*Using for loop and create various textview
        for(int i=0;i<number.size();i++) {
            TextView disnum = new TextView(this);
            disnum.setText(number.get(i));
            numrootview.addView(disnum);
        }*/

        WordAdapter numberadapter = new WordAdapter(this, number, R.color.orange);
        numrootview.setAdapter(numberadapter);
        numrootview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                release();
                word w = number.get(i);

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(number.this, w.getAudioresid());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mcompletelistener);
                }

            }
        });
    }
        private void release()
        {
           if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
                mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
            }
        }
    protected void onStop()
    {
        super.onStop();
        release();
    }

}
