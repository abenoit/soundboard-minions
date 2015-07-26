package com.soundboard.minions.soundboardminions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.soundboard.minions.soundboardminions.model.Sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utilities {

    /**
     * Shows the progress UI and hides the formView.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(final boolean show, final View formView, final View progressView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = 200;

            formView.setVisibility(show ? View.GONE : View.VISIBLE);
            formView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            formView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static Sound getSoundFrom(int id) {
        return Constants.SOUNDS_LIST.get(id);
    }

    public static void setAsRingtone(Sound sound, Context context){
        File file = new File(Environment.getExternalStorageDirectory(),
                "/myRingtonFolder/Audio/");
        if (!file.exists()) {
            file.mkdirs();
        }

        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/myRingtonFolder/Audio/";

        File f = new File(path + "/", sound.getTitle() + ".mp3");

        Uri mUri = Uri.parse("android.resource://"
                + context.getPackageName() + "/raw/" + sound.getResourceName());
        ContentResolver mCr = context.getContentResolver();
        AssetFileDescriptor soundFile;
        try {
            soundFile = mCr.openAssetFileDescriptor(mUri, "r");
        } catch (FileNotFoundException e) {
            soundFile = null;
        }

        try {
            byte[] readData = new byte[1024];
            FileInputStream fis = soundFile.createInputStream();
            FileOutputStream fos = new FileOutputStream(f);
            int i = fis.read(readData);

            while (i != -1) {
                fos.write(readData, 0, i);
                i = fis.read(readData);
            }

            fos.close();
        } catch (IOException io) {
        }
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DATA, f.getAbsolutePath());
        values.put(MediaStore.MediaColumns.TITLE, sound.getTitle());
        values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
        values.put(MediaStore.MediaColumns.SIZE, f.length());
        values.put(MediaStore.Audio.Media.ARTIST, R.string.app_name);
        values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
        //values.put(MediaStore.Audio.Media.IS_MUSIC, true);

        Uri uri = MediaStore.Audio.Media.getContentUriForPath(f
                .getAbsolutePath());
        Uri newUri = mCr.insert(uri, values);

        try {
            RingtoneManager.setActualDefaultRingtoneUri(context,
                    RingtoneManager.TYPE_RINGTONE, newUri);
            Settings.System.putString(mCr, Settings.System.RINGTONE,
                    newUri.toString());
            Toast.makeText(context.getApplicationContext(), context.getResources().getString(R.string.set_ringtone_done), Toast.LENGTH_LONG).show();
        } catch (Throwable t) {

        }
    }
}
