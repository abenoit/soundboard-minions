package com.soundboard.minions.soundboardminions.listener;

import com.soundboard.minions.soundboardminions.model.Sound;

public interface SoundboardEvents {
    void displayRingtonePopup(Sound sound);
    void disablePopup();

    void setAsRingtone(Sound sound);
    void setAsNotification(Sound sound);
}
