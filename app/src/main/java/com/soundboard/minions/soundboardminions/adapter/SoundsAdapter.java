package com.soundboard.minions.soundboardminions.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.Utilities;
import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.List;

public class SoundsAdapter extends ArrayAdapter<Sound> {

        private boolean isMusicPlaying;

        public SoundsAdapter(Context context, List<Sound> sounds) {
            super(context, 0, sounds);
            isMusicPlaying = false;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Sound sound = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sound, parent, false);
            }

            TextView title = (TextView) convertView.findViewById(R.id.soundTitle);
            title.setText(sound.getTitle());
            Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Young.ttf");
            title.setTypeface(font);

            final ImageView avatar = (ImageView) convertView.findViewById(R.id.soundAvatar);
            avatar.setImageResource(sound.getAvatarId());
            final View progressReading = convertView.findViewById(R.id.reading_progress);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playSound(sound, avatar, progressReading);
                }
            });

            return convertView;
        }

        private void setReadingState(ImageView playSoundImg, View progressReading) {
            Utilities.showProgress(true, playSoundImg, progressReading);
        }

        private void setStopReadingState(ImageView playSoundImg, View progressReading) {
            Utilities.showProgress(false, playSoundImg, progressReading);
        }

        protected void playSound(Sound sound, final ImageView playSoundImg, final View progressReading) {
            if (!isMusicPlaying) {
                setReadingState(playSoundImg, progressReading);
                MediaPlayer mPlayer = MediaPlayer.create(getContext(), sound.getResource());
                mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        isMusicPlaying = false;
                        setStopReadingState(playSoundImg, progressReading);
                    }
                });
                mPlayer.start();
                mPlayer.seekTo(0);
                isMusicPlaying = true;
            }
        }

}
