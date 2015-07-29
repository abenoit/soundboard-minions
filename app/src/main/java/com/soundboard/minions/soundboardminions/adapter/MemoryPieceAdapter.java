package com.soundboard.minions.soundboardminions.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.soundboard.minions.soundboardminions.Constants;
import com.soundboard.minions.soundboardminions.FlipAnimation;
import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.listener.MemoryWonListener;
import com.soundboard.minions.soundboardminions.model.MemoryPiece;
import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.ArrayList;
import java.util.List;

public class MemoryPieceAdapter extends ArrayAdapter<MemoryPiece> {

    private static List<MemoryPiece> reversedPieces;
    private static List<FlipAnimation> reversedPieceEvents;
    private static List<View> reversedPieceRelative;
    private int wonPieces;
    private int nbPieces;
    private MemoryWonListener memoryWonListener;
    private static MediaPlayer mPlayer;

    public MemoryPieceAdapter(Context context, List<MemoryPiece> resource, MemoryWonListener listener) {
        super(context, 0, resource);
        reversedPieces = new ArrayList<>();
        reversedPieceRelative = new ArrayList<>();
        reversedPieceEvents = new ArrayList<>();
        wonPieces = 0;
        nbPieces = resource.size();
        this.memoryWonListener = listener;
    }

    public void reloadGame() {
        reversedPieceEvents.clear();
        reversedPieceRelative.clear();
        reversedPieces.clear();
        wonPieces = 0;
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MemoryPiece mp = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_memory, parent, false);
        }

        final ProgressBar reading_progress = (ProgressBar) convertView.findViewById(R.id.reading_progress);
        final ImageView play = (ImageView) convertView.findViewById(R.id.play);
        final View rootLayout = convertView.findViewById(R.id.relative);
        final CardView front = (CardView) convertView.findViewById(R.id.card_face);
        final CardView back = (CardView) convertView.findViewById(R.id.card_back);

        setCardAppearance(mp, reading_progress, play, front, back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.getState() == Constants.MemoryPieceState.DISPLAYED) {
                    play.setVisibility(View.GONE);
                    reading_progress.setVisibility(View.VISIBLE);
                    playSound(mp.getSound());
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mediaplayer) {
                            reading_progress.setVisibility(View.GONE);
                            play.setVisibility(View.VISIBLE);
                            notifyDataSetChanged();
                        }
                    });
                }
            };
        });

        front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlayer != null && mPlayer.isPlaying()) {
                    return;
                }
                if (mp.getState() == Constants.MemoryPieceState.HIDDEN) {
                    rootLayout.startAnimation(new FlipAnimation(front, back));
                    mp.setState(Constants.MemoryPieceState.DISPLAYED);
                    playSound(mp.getSound());
                    mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(final MediaPlayer mediaplayer) {
                            FlipAnimation backToFront = new FlipAnimation(back, front);
                            manageGame(rootLayout, backToFront, mp);

                        }
                    });
                }
            }
        });

        return convertView;
    }

    private void setCardAppearance(MemoryPiece mp, ProgressBar reading_progress, ImageView play, CardView front, CardView back) {
        if (mp.getState() == Constants.MemoryPieceState.WIN) {
            back.setCardBackgroundColor(getContext().getResources().getColor(R.color.green_win));
            play.setVisibility(View.VISIBLE);
            reading_progress.setVisibility(View.GONE);
            play.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_done_black_48dp));

            back.setVisibility(View.VISIBLE);
            front.setVisibility(View.GONE);
        } else if (mp.getState() == Constants.MemoryPieceState.DISPLAYED) {
            play.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_play_circle_outline_grey600_48dp));
            play.setVisibility(View.VISIBLE);
            reading_progress.setVisibility(View.GONE);

            back.setVisibility(View.VISIBLE);
            front.setVisibility(View.GONE);
        } else {
            back.setCardBackgroundColor(getContext().getResources().getColor(R.color.white_background));
            play.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_play_circle_outline_grey600_48dp));
            play.setVisibility(View.GONE);
            reading_progress.setVisibility(View.VISIBLE);

            back.setVisibility(View.GONE);
            front.setVisibility(View.VISIBLE);
        }
    }

    private void manageGame(View rootLayout, FlipAnimation backToFront, MemoryPiece mp) {
        // One piece was already turned
        if (reversedPieces.size() == 1) {
            // If the pair is valid, set the pair to the correct state
            if (reversedPieces.get(0).getPairPosition() == mp.getPosition()) {
                reversedPieces.get(0).setState(Constants.MemoryPieceState.WIN);
                mp.setState(Constants.MemoryPieceState.WIN);
                wonPieces += 2;
                if (wonPieces == nbPieces) {
                    memoryWonListener.memoryWon();
                }
                this.notifyDataSetChanged();
            } else {
                // If the pair is not valid, turn the pieces back
                reversedPieces.get(0).setState(Constants.MemoryPieceState.HIDDEN);
                mp.setState(Constants.MemoryPieceState.HIDDEN);
                reversedPieceRelative.get(0).startAnimation(reversedPieceEvents.get(0));
                rootLayout.startAnimation(backToFront);
            }
            reversedPieceRelative.clear();
            reversedPieces.clear();
            reversedPieceEvents.clear();
        } else {
            reversedPieceRelative.add(rootLayout);
            reversedPieces.add(mp);
            reversedPieceEvents.add(backToFront);
            this.notifyDataSetChanged();
        }

    }


    protected void playSound(Sound sound) {
        mPlayer = MediaPlayer.create(getContext(), sound.getResource());
        mPlayer.start();
        mPlayer.seekTo(0);
    }
}
