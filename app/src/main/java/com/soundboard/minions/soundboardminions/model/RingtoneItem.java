package com.soundboard.minions.soundboardminions.model;

import com.soundboard.minions.soundboardminions.utilities.Constants;

public class RingtoneItem {

    private String typeName;

    private Constants.TypeRingtone type;

    private Sound sound;

    public RingtoneItem(String typeName, Constants.TypeRingtone type) {
        this.typeName = typeName;
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public Constants.TypeRingtone getType() {
        return type;
    }

}
