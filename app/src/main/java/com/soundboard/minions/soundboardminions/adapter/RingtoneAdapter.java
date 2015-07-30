package com.soundboard.minions.soundboardminions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soundboard.minions.soundboardminions.R;
import com.soundboard.minions.soundboardminions.listener.SetRingtoneListener;
import com.soundboard.minions.soundboardminions.model.RingtoneItem;
import com.soundboard.minions.soundboardminions.utilities.Constants;

import java.util.List;

public class RingtoneAdapter extends ArrayAdapter<RingtoneItem> {

    private SetRingtoneListener ringtoneListener;

    public RingtoneAdapter(Context context, List<RingtoneItem> resource, SetRingtoneListener soundboardEvents) {
        super(context, 0, resource);
        this.ringtoneListener = soundboardEvents;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final RingtoneItem ringtoneItem = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ringtone, parent, false);
        }

        TextView itemType = (TextView) convertView.findViewById(R.id.ringtone_item_type);
        itemType.setText(ringtoneItem.getTypeName());

        ImageView image = (ImageView) convertView.findViewById(R.id.img_ringtone);
        image.setImageDrawable(getContext().getResources().getDrawable(ringtoneItem.getImage()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ringtoneItem.getType() == Constants.TypeRingtone.RINGTONE)
                    ringtoneListener.setAsRingtone();
                else if (ringtoneItem.getType() == Constants.TypeRingtone.NOTIFICATION)
                    ringtoneListener.setAsNotification();
            }
        });

        return convertView;
    }
//
//
//    public void setRingtone(final Sound sound){
//        new AlertDialog.Builder(this)
//                .setTitle(getResources().getString(R.string.ringtone))
//                .setMessage(getResources().getString(R.string.set_as_ringtone_question, sound.getTitle()))
//                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        Utilities.setAsRingtone(sound, getApplicationContext());
//                    }
//                })
//                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                })
//                .show();
//    }
}