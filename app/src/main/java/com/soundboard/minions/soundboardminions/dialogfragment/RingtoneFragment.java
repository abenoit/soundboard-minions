package com.soundboard.minions.soundboardminions.dialogfragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.adapter.RingtoneAdapter;
import com.soundboard.minions.soundboardminions.listener.SetRingtoneListener;
import com.soundboard.minions.soundboardminions.listener.SoundboardEvents;
import com.soundboard.minions.soundboardminions.model.RingtoneItem;
import com.soundboard.minions.soundboardminions.model.Sound;
import com.soundboard.minions.soundboardminions.utilities.Utilities;

import java.util.List;

public class RingtoneFragment extends DialogFragment implements SetRingtoneListener{

    private List<RingtoneItem> ringtoneItemList;
    private Context context;
    private Sound sound;
    private SoundboardEvents events;

    public RingtoneFragment() {
    }
    public void setSound(Sound sound) {
        this.sound = sound;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popup_ringtone, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextView setAs = (TextView) view.findViewById(R.id.set_ringtone_as);
        setAs.setText(getResources().getString(R.string.define_as, this.sound.getTitle()));

        ListView ringtoneTypeList = (ListView) view.findViewById(R.id.ringtone_list);
        RingtoneAdapter adapter = new RingtoneAdapter(this.context, this.ringtoneItemList, this);
        ringtoneTypeList.setAdapter(adapter);

        return view;
    }

    public void initFragment(List<RingtoneItem> ringtoneItemList, Context context, Sound sound, SoundboardEvents events) {
        this.ringtoneItemList = ringtoneItemList;
        this.context = context;
        this.sound = sound;
        this.events = events;
    }

    @Override
    public void setAsRingtone() {
        Utilities.setAsRingtone(this.sound, this.context);
        this.events.disablePopup();
        Toast.makeText(context, context.getResources().getString(R.string.set_ringtone_done), Toast.LENGTH_LONG).show();
    }

    @Override
    public void setAsNotification() {
        Utilities.setAsNotification(this.sound, this.context);
        this.events.disablePopup();
        Toast.makeText(context, context.getResources().getString(R.string.set_ringtone_done), Toast.LENGTH_LONG).show();
    }
}
