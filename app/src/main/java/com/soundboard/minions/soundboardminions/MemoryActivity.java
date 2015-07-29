package com.soundboard.minions.soundboardminions;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import com.soundboard.minions.soundboardminions.adapter.MemoryPieceAdapter;
import com.soundboard.minions.soundboardminions.adapter.SoundsAdapter;
import com.soundboard.minions.soundboardminions.listener.MemoryWonListener;
import com.soundboard.minions.soundboardminions.model.MemoryPiece;
import com.soundboard.minions.soundboardminions.model.Sound;
import com.soundboard.minions.soundboardminions.utilities.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryActivity extends ActionBarActivity implements MemoryWonListener {

    private List<MemoryPiece> memoryPieces;
    private GridView soundListView;
    private final int memorySize = 12;
    private MemoryPieceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        Utilities.trackNavigation(this);
        setUIReferences();
        InitSoundList();
    }

    private void InitSoundList() {
        memoryPieces = new ArrayList<>();
        generateNewGame();
        adapter = new MemoryPieceAdapter(this, memoryPieces, this);
        soundListView.setAdapter(adapter);
    }

    private void generateNewGame() {
        memoryPieces.clear();
        List<Sound> shuffledSoundList = Constants.SHORT_SOUNDS_LIST;
        Collections.shuffle(shuffledSoundList);

        for(int i = 0; i < memorySize / 2; i++){
            // get n first sounds after they were mixed
            memoryPieces.add(new MemoryPiece(shuffledSoundList.get(i)));
            memoryPieces.add(new MemoryPiece(shuffledSoundList.get(i)));
        }

        Collections.shuffle(memoryPieces); // mix pieces

        for(MemoryPiece mp : memoryPieces){ // get pair position
            mp.setPosition(memoryPieces.indexOf(mp));

            if(mp.getPairPosition() == 0){
                for(int j = mp.getPosition() + 1; j < memoryPieces.size() ; j++){
                    if(mp.getSound().getId().equals(memoryPieces.get(j).getSound().getId())){
                        mp.setPairPosition(j);
                        memoryPieces.get(j).setPairPosition(mp.getPosition());
                    }
                }
            }
        }
    }

    private void setUIReferences() {
        soundListView = (GridView) findViewById(R.id.memoryPieceLayout);
    }

    private void launchGame(){
        generateNewGame();
        adapter.reloadGame();
    }

    private void goToMainMenu(){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        this.finish();
    }

    @Override
    public void memoryWon() {
        new AlertDialog.Builder(this)
                .setTitle(getResources().getString(R.string.congratulations))
                .setMessage(getResources().getString(R.string.game_is_over))
                .setPositiveButton(R.string.retry, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        launchGame();
                    }
                })
                .setNegativeButton(R.string.menu, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        goToMainMenu();
                    }
                })
                .show();
    }
}
