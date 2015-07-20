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
                add(new Sound(R.raw.yay, "Yay", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.what, "Whaaat ?!", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.aww, "Aww", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.fight, "Fight", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.bottom2, "Bottom", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.okok, "Ok", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.oh, "Oh", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.bananashort, "Banana", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.bananalong, "Banana (long)", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.tadaaa, "Tadaaa", id++, R.drawable.ic_play_circle_outline_black_48dp));
                add(new Sound(R.raw.laughing, "Laughing", id, R.drawable.ic_play_circle_outline_black_48dp));
            }}
    );

}
