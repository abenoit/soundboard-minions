package com.soundboard.minions.soundboardminions.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.utilities.Utilities;
import com.soundboard.minions.soundboardminions.listener.SoundboardEvents;
import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.List;

public class SoundsAdapter extends ArrayAdapter<Sound> {

    private SoundboardEvents soundboardEvents;
    private MediaPlayer mPlayer;
    private ImageView playingImgView;
    private View playingProgressBar;
    private View playingMainView;

    public SoundsAdapter(Context context, List<Sound> sounds, SoundboardEvents soundboardEvents) {
        super(context, 0, sounds);
        this.soundboardEvents = soundboardEvents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Sound sound = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sound, parent, false);
        }

        final ImageView avatar = (ImageView) convertView.findViewById(R.id.soundAvatar);
        final View progressReading = convertView.findViewById(R.id.reading_progress);

        TextView title = (TextView) convertView.findViewById(R.id.soundTitle);
        title.setText(sound.getTitle());
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Young.ttf");
        title.setTypeface(font);

        avatar.setImageResource(sound.getAvatarId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer == null || !mPlayer.isPlaying()) {
                    if (playingImgView != null && mPlayer != null) {
                        mPlayer.stop();
                        setStopReadingState(playingImgView, playingProgressBar, playingMainView);
                    }
                    playSound(sound, avatar, progressReading, v);
                    playingImgView = avatar;
                    playingMainView = v;
                    playingProgressBar = progressReading;
                } else {
                    mPlayer.stop();
                    setStopReadingState(avatar, progressReading, v);
                    playingImgView = null;
                    playingMainView = null;
                    playingProgressBar = null;
                }
            }
        });

        convertView.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        soundboardEvents.displayRingtonePopup(sound);
                        return true;
                    }
                });

        return convertView;
    }

    private void setReadingState(ImageView playSoundImg, View progressReading, View mainView) {
        Utilities.showProgress(true, playSoundImg, progressReading);
        ((CardView) mainView).setCardBackgroundColor(getContext().getResources().getColor(R.color.blue_highilight));
    }

    private void setStopReadingState(ImageView playSoundImg, View progressReading, View mainView) {
        Utilities.showProgress(false, playSoundImg, progressReading);
        ((CardView) mainView).setCardBackgroundColor(getContext().getResources().getColor(R.color.white_background));
    }

    protected void playSound(Sound sound, final ImageView playSoundImg, final View progressReading, final View mainView) {
        mPlayer = MediaPlayer.create(getContext(), sound.getResource());
        setReadingState(playSoundImg, progressReading, mainView);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                setStopReadingState(playSoundImg, progressReading, mainView);
            }
        });
        mPlayer.start();
        mPlayer.seekTo(0);
    }

}
