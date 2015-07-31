package com.soundboard.minions.soundboardminions.model;

import com.soundboard.minions.soundboardminions.utilities.Constants;

public class RingtoneItem {

    private String typeName;

    private Constants.TypeRingtone type;

    private Sound sound;

    private Integer image;

    public RingtoneItem(String typeName, Constants.TypeRingtone type, Integer image) {
        this.typeName = typeName;
        this.type = type;
        this.image = image;
    }

    public String getTypeName() {
        return typeName;
    }

    public Integer getImage() {
        return image;
    }

    public Constants.TypeRingtone getType() {
        return type;
    }


    public Sound getSound() {
        return sound;
    }
}
