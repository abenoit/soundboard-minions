package com.soundboard.minions.soundboardminions;

import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {

    public static final String LOGTAG = "MinionsSoundboardLog";

    public static final List<Sound> SOUNDS_LIST = Collections.unmodifiableList(
            new ArrayList<Sound>() {{
                int id = 0;
                add(new Sound(R.raw.yay, "Yay", id++, R.drawable.avatar_happy_brown));
                add(new Sound(R.raw.what, "Whaaat ?!", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.aww, "Aww", id++, R.drawable.avatar_amused_brown));
                add(new Sound(R.raw.fight, "Fight", id++, R.drawable.avatar_mad_brown));
                add(new Sound(R.raw.bottom2, "Bottom", id++, R.drawable.avatar_down_brown));
                add(new Sound(R.raw.okok, "Ok", id++, R.drawable.avatar_surprised_brown));
                add(new Sound(R.raw.oh, "Oh", id++, R.drawable.avatar_down_brown));
                add(new Sound(R.raw.bananashort, "Banana", id++, R.drawable.avatar_amused_brown));
                add(new Sound(R.raw.bananalong, "Banana (long)", id++, R.drawable.avatar_amused_brown));
                add(new Sound(R.raw.tadaaa, "Tadaaa", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.laughing, "Laughing", id, R.drawable.avatar_laughing_brown));
            }}
    );

}
