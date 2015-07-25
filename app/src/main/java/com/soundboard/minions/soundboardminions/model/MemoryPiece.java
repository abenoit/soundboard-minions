package com.soundboard.minions.soundboardminions.model;

import com.soundboard.minions.soundboardminions.Constants;

public class MemoryPiece {

    public MemoryPiece(Sound sound) {
        this.sound = sound;
        this.state = Constants.MemoryPieceState.HIDDEN;
    }

    private Sound sound;

    private int position;

    private int pairPosition;

    private Constants.MemoryPieceState state;

    /**
     * Getter and setters
     */

    public int getPairPosition() {
        return pairPosition;
    }

    public void setPairPosition(int pairPosition) {
        this.pairPosition = pairPosition;
    }

    public void setState(Constants.MemoryPieceState state) {
        this.state = state;
    }

    public int getPosition() {
        return position;
    }

    public Sound getSound() {
        return sound;
    }

    public Constants.MemoryPieceState getState() {
        return state;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
