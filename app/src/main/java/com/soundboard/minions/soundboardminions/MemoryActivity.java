package com.soundboard.minions.soundboardminions;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MemoryActivity extends ActionBarActivity implements MemoryWonListener {

    private List<MemoryPiece> memoryPieces;
    private GridView soundListView;
    private final int memorySize = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        setUIReferences();
        InitSoundList();
    }

    private void InitSoundList() {
        memoryPieces = new ArrayList<>();
        generateNewGame();
        MemoryPieceAdapter adapter = new MemoryPieceAdapter(this, memoryPieces, this);
        soundListView.setAdapter(adapter);
    }

    private void generateNewGame() {
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

    @Override
    public void memoryWon() {
        Toast.makeText(this.getApplicationContext(), "Jeu terminé", Toast.LENGTH_LONG).show();
    }
}
