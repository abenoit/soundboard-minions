package com.soundboard.minions.soundboardminions;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundboard.minions.soundboardminions.adapter.SoundsAdapter;
import com.soundboard.minions.soundboardminions.dialogfragment.RingtoneFragment;
import com.soundboard.minions.soundboardminions.listener.SetRingtoneListener;
import com.soundboard.minions.soundboardminions.listener.SoundboardEvents;
import com.soundboard.minions.soundboardminions.model.RingtoneItem;
import com.soundboard.minions.soundboardminions.model.Sound;
import com.soundboard.minions.soundboardminions.utilities.Constants;
import com.soundboard.minions.soundboardminions.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;

public class SoundboardActivity extends ActionBarActivity
        implements SoundboardEvents {

    private RingtoneFragment ringtonePopup;
    private List<RingtoneItem> ringtoneItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        Utilities.trackNavigation(this);
        ringtoneItems = new ArrayList<>();
        ringtoneItems.add(new RingtoneItem(getString(R.string.ringtone), Constants.TypeRingtone.RINGTONE, R.drawable.ic_ring_volume_blue_36dp));
        ringtoneItems.add(new RingtoneItem(getString(R.string.notification), Constants.TypeRingtone.NOTIFICATION, R.drawable.ic_messenger_black_36dp));
        setUIReferences();
    }

    private void setUIReferences() {
        TextView tutorial = (TextView) findViewById(R.id.tutorial);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Young.ttf");
        tutorial.setTypeface(font);

        GridView soundListView = (GridView) findViewById(R.id.soundsListLayout);
        SoundsAdapter adapter = new SoundsAdapter(this, Constants.SOUNDS_LIST, this);
        soundListView.setAdapter(adapter);
    }



    @Override
    public void displayRingtonePopup(Sound sound) {
        displayPopup(sound);
    }

    @Override
    public void disablePopup() {
        dismissPopup();
    }

    /**
     * POPUP
     */
    public void displayPopup(Sound sound) {
        android.app.FragmentManager fragmentManager = this.getFragmentManager();
        ringtonePopup = new RingtoneFragment();
        ringtonePopup.initFragment(ringtoneItems, this.getApplicationContext(), sound, this);
        ringtonePopup.show(fragmentManager, "Tag");
    }

    public void dismissPopup() {
        if (ringtonePopup != null) {
            ringtonePopup.dismiss();
        }
    }
    /**
     * End Popup
     */


    @Override
    public void setAsRingtone(Sound sound) {
        disablePopup();
        Utilities.setAsRingtone(sound, getApplicationContext());
        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.set_ringtone_done), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAsNotification(Sound sound) {
        disablePopup();
        Utilities.setAsNotification(sound, getApplicationContext());
        Toast.makeText(getApplicationContext(), getApplicationContext().getResources().getString(R.string.set_ringtone_done), Toast.LENGTH_LONG).show();
    }
}
