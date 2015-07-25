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

import com.soundboard.minions.soundboardminions.Constants;
import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.Utilities;
import com.soundboard.minions.soundboardminions.listener.MemoryWonListener;
import com.soundboard.minions.soundboardminions.model.MemoryPiece;
import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.ArrayList;
import java.util.List;

public class MemoryPieceAdapter extends ArrayAdapter<MemoryPiece> {

    private static boolean isMusicPlaying;
    private static List<MemoryPiece> reversedPieces;
    private int wonPieces;
    private int nbPieces;
    private MemoryWonListener memoryWonListener;

    public MemoryPieceAdapter(Context context, List<MemoryPiece> resource, MemoryWonListener listener) {
        super(context, 0, resource);
        reversedPieces = new ArrayList<>();
        wonPieces = 0;
        nbPieces = resource.size();
        this.memoryWonListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MemoryPiece mp = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_memory, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.soundTitle);
        title.setText(mp.getSound().getTitle() + " " + mp.getPosition() + " " + mp.getPairPosition() + " " + mp.getState());
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Young.ttf");
        title.setTypeface(font);

        if (mp.getState() == Constants.MemoryPieceState.WIN) {
            ((CardView) convertView).setCardBackgroundColor(getContext().getResources().getColor(R.color.blue_highilight));
        } else {
            ((CardView) convertView).setCardBackgroundColor(getContext().getResources().getColor(R.color.white_background));
        }

        final View progressReading = convertView.findViewById(R.id.reading_progress);
        final ImageView avatar = (ImageView) convertView.findViewById(R.id.soundAvatar);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mp.getState() == Constants.MemoryPieceState.HIDDEN) {
                    playSound(mp.getSound(), avatar, progressReading);

                    mp.setState(Constants.MemoryPieceState.DISPLAYED);
                    if (reversedPieces.size() == 1) {
                        if (reversedPieces.get(0).getPairPosition() == mp.getPosition()) {
                            reversedPieces.get(0).setState(Constants.MemoryPieceState.WIN);
                            mp.setState(Constants.MemoryPieceState.WIN);
                            wonPieces += 2;

                            if (wonPieces == nbPieces) {
                                memoryWonListener.memoryWon();
                            }
                        } else {
                            reversedPieces.get(0).setState(Constants.MemoryPieceState.HIDDEN);
                            mp.setState(Constants.MemoryPieceState.HIDDEN);
                        }
                        reversedPieces.clear();
                    } else {
                        reversedPieces.add(mp);
                    }
                }
                notifyDataSetChanged();
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

    private MediaPlayer mPlayer;

    protected void playSound(Sound sound, final ImageView playSoundImg, final View progressReading) {
        if (!isMusicPlaying) {
            mPlayer = MediaPlayer.create(getContext(), sound.getResource());
            setReadingState(playSoundImg, progressReading);
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
        } else if (mPlayer != null) {
            mPlayer.stop();
            isMusicPlaying = false;
            setStopReadingState(playSoundImg, progressReading);
        }
    }
}
