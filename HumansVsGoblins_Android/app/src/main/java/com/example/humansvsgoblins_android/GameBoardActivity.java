package com.example.humansvsgoblins_android;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class GameBoardActivity extends AppCompatActivity {
    String userName;
    private Human userObj;

    LandTile landTile;
    private static final int ROWS = 10;
    private static final int COLUMNS = 10;
    public TableLayout tileMapTable;

    private static final int BACKGROUND_TILE = R.drawable.tilegrass;
    private static final int HUMAN_TILE = R.drawable.tilehuman;
    private static final int GOBLIN_TILE = R.drawable.tilegoblin;

    public int numOfGoblin;
    public List<Object> goblinArr = new ArrayList<>();
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        if(goblinArr.isEmpty()){
            Intent intent = getIntent();
            userName = intent.getStringExtra("passedName");

            userObj= (Human) generateHuman();
            userObj.generateStartLocation(ROWS, COLUMNS);
            generateGoblinObjects();
        }

        setHumanStatsDisplay();
        createLandMapTable();
        listenForButtons();
    }


    private Object generateHuman() {
        return new Human(userName);
    }

    public void generateGoblinObjects(){
        numOfGoblin = (int) ((Math.random() * 10) + 10);
        for( int i = 0; i < numOfGoblin; i++){
            Goblin goblinEle = new Goblin(Goblin.generateGoblinName());
            setGoblinObjRandomCords(goblinEle);
            goblinArr.add(goblinEle);
        }
    }

    private void setGoblinObjRandomCords(Goblin goblinEle) {
        int isChecking = 0;

        while (isChecking == 0){
            int r = (int) (Math.random() * ROWS);
            int c = (int) (Math.random() * COLUMNS);

            if(!(r == userObj.getX() && c == userObj.getY())){
                goblinEle.setX(r);
                goblinEle.setY(c);
                isChecking ++;
            }
        }
    }

    private void setHumanStatsDisplay() {
        TextView usrObjName = (TextView) findViewById(R.id.hObjNameTextView);
        usrObjName.setText(String.valueOf(userObj.getName()));

        TextView usrObjHealth =  (TextView) findViewById(R.id.hObjHealthTextView);
        String healthConcat = "Health: " + userObj.getHealth() +"/100";
        usrObjHealth.setText(healthConcat);

        TextView usrObjStrength = (TextView) findViewById(R.id.hObjStrengthTextView);
        String strengthConcat = "Strength: " + userObj.getStrength();
        usrObjStrength.setText(strengthConcat);

        TextView userObjLives = (TextView) findViewById(R.id.hObjLivesTextView);
        String heatConcat =convertLivesToHeartChars(userObj.getLives());
        userObjLives.setText(heatConcat);

        TextView goblinTextInfo = (TextView) findViewById(R.id.goblinCountTextView);
        String goblinTextConcat = "Goblins left: " + goblinArr.size() + " / " + numOfGoblin + "  " + userObj.getX() +","+userObj.getY();//user cords for testing
        goblinTextInfo.setText(goblinTextConcat);
    }

    private String convertLivesToHeartChars(int lives) {
        StringBuilder heartString = new StringBuilder();
        for(int i = lives;  0 < i; i--){
           heartString.append("❤");
        }
        return heartString.toString();
    }

    void createLandMapTable() {
        tileMapTable = (TableLayout) findViewById(R.id.tileMapTable);
        tileMapTable.setStretchAllColumns(true);

        int hX = userObj.getX();
        int hY = userObj.getY();

        for(int i = 0; i < ROWS; i++) {
            TableRow row = new TableRow(this);
            row.setId(i);
            TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(tableParams);
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);

            for(int j = 0; j < COLUMNS; j++) {
                ImageView cellView = new ImageView(this);
                cellView.setLayoutParams(rowParams);
                cellView.setTag(new LandTile(i, j));
                if(i == hX && j == hY){
                    cellView.setImageResource(HUMAN_TILE);
                    userObj.setView(cellView);
                }else {
                    //loop over goblin array and place where need else place blank tile
                    cellView.setImageResource(BACKGROUND_TILE);
                    for(int g = 0; g < goblinArr.size(); g++){
                        Goblin gobEle = (Goblin) goblinArr.get(g);
                        if (i == gobEle.getX() && j == gobEle.getY()){
                            cellView.setImageResource(GOBLIN_TILE);
                        }
                    }
                }
                cellView.setScaleType(ImageView.ScaleType.FIT_XY);
                row.addView(cellView);
            }
            tileMapTable.addView(row);
        }
    }

    private void listenForButtons() {



        Button up = (Button) findViewById(R.id.upBtn);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("UP");
            }
        });

        Button down = (Button) findViewById(R.id.downBtn);
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("DOWN");
            }
        });

        Button right = (Button) findViewById(R.id.rightBtn);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("RIGHT");
            }
        });

        Button left = (Button) findViewById(R.id.leftBtn);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move("LEFT");
            }
        });
 }

    private void move(String dir){
        int[] delta = direction(dir);
        int xDelta = delta[0];
        int yDelta = delta[1];


        Boolean inbound = checkBounds(userObj.getX()+ xDelta, userObj.getY() + yDelta);

        if(inbound){
            updateLocationsOnMap(xDelta, yDelta);
            setHumanStatsDisplay();
            checkForGoblin(userObj.getX(), userObj.getY());
        }
    }
    private int[] direction(String d) {
        int xDelta = 0;
        int yDelta = 0;

        switch (d){
            case "UP":
                xDelta = -1;
                break;
            case "DOWN":
                xDelta = 1;
                break;
            case "RIGHT":
                yDelta = 1;
                break;
            case "LEFT":
                yDelta = -1;
                break;
        }
        return new int[] {xDelta, yDelta};
    }




    private void updateLocationsOnMap(int xD, int yD) {
        userObj.setX(userObj.getX()+ xD);
        userObj.setY(userObj.getY()+ yD);

        ViewGroup row = (ViewGroup) tileMapTable.getChildAt(userObj.getX());
        ImageView newCellView = (ImageView) row.getChildAt(userObj.getY());
        newCellView.setImageResource(HUMAN_TILE);

        ViewGroup oldRow = (ViewGroup) tileMapTable.getChildAt(userObj.getX() - xD);
        ImageView oldCellView = (ImageView) oldRow.getChildAt(userObj.getY() - yD);
        oldCellView.setImageResource(BACKGROUND_TILE);

    }

    private Boolean checkBounds(int x, int y) {
        return (x >= 0 && x < ROWS) && (y >= 0 && y < COLUMNS);
    }

    private void checkForGoblin(int x, int y) {
        for(int g = 0; g < goblinArr.size(); g++){
            Goblin gobEle = (Goblin) goblinArr.get(g);
            if (x == gobEle.getX() && y == gobEle.getY()){
                switchToBattle(gobEle);
                //goblinArr.remove(g);
            }
        }
    }

    private void switchToBattle(Goblin gobEle) {
        Intent battleIntent = new Intent((getApplicationContext()), BattleSceneActivity.class);
        //battleIntent.putExtra("passedGob", (Parcelable) gobEle);
        startActivity(battleIntent);
    }

}

