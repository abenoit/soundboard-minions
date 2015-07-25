package com.soundboard.minions.soundboardminions;

import com.soundboard.minions.soundboardminions.model.Sound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {

    public static final String LOGTAG = "MinionsSoundboardLog";

    /**
     * Hidden = initial state, not displayed yer
     * Displayed = user clicked on the piece but the pair is not found yet
     * Win = user found the pair
     */
    public enum MemoryPieceState {HIDDEN, DISPLAYED, WIN}


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
                add(new Sound(R.raw.laughing, "Laughing", id++, R.drawable.avatar_laughing_brown));
                add(new Sound(R.raw.papaya, "Papaya", id++, R.drawable.avatar_blase_brown));
                add(new Sound(R.raw.tikka_massala, "Tikka Massala", id++, R.drawable.avatar_surprised_brown));
                add(new Sound(R.raw.bat_bat, "Bat Bat ?", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.hehehe, "He he he", id++, R.drawable.avatar_laughing_brown));
                add(new Sound(R.raw.look_at_you, "Look at you !", id++, R.drawable.avatar_down_brown));
                add(new Sound(R.raw.yup_la_lo_doo, "Yup La Lo Doo", id++, R.drawable.avatar_happy_brown));
                add(new Sound(R.raw.monster, "Monster", id++, R.drawable.avatar_super_mad_brown));
                add(new Sound(R.raw.go_to_sleep, "Go to sleep", id, R.drawable.avatar_tired_brown));
            }}
    );


    public static final List<Sound> SHORT_SOUNDS_LIST =
            new ArrayList<Sound>() {{
                int id = 0;
                add(new Sound(R.raw.yay, "Yay", id++, R.drawable.avatar_happy_brown));
                add(new Sound(R.raw.what, "Whaaat ?!", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.aww, "Aww", id++, R.drawable.avatar_amused_brown));
                add(new Sound(R.raw.bottom2, "Bottom", id++, R.drawable.avatar_down_brown));
                add(new Sound(R.raw.okok, "Ok", id++, R.drawable.avatar_surprised_brown));
                add(new Sound(R.raw.oh, "Oh", id++, R.drawable.avatar_down_brown));
                add(new Sound(R.raw.bananashort, "Banana", id++, R.drawable.avatar_amused_brown));
                add(new Sound(R.raw.tadaaa, "Tadaaa", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.laughing, "Laughing", id++, R.drawable.avatar_laughing_brown));
                add(new Sound(R.raw.bat_bat, "Bat Bat ?", id++, R.drawable.avatar_normal_brown));
                add(new Sound(R.raw.hehehe, "He he he", id++, R.drawable.avatar_laughing_brown));
                add(new Sound(R.raw.monster, "Monster", id, R.drawable.avatar_super_mad_brown));
            }};



}
