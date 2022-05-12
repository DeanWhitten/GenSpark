package com.example.humansvsgoblins_android;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class GameBoardActivity extends AppCompatActivity {
    Land Land;
    private static final int TABLE_HEIGHT = 10;
    private static final int TABLE_WIDTH = 10;
    public TableLayout tileMapTable;
    private static final int BACKGROUND_TILE = R.drawable.tilegrass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_board);

        createLandMapTable();
    }

    void createLandMapTable() {
        tileMapTable = (TableLayout) findViewById(R.id.tileMapTable);
        tileMapTable.setStretchAllColumns(true);
        for(int i = 0; i < TABLE_HEIGHT; i++) {
            TableRow row = new TableRow(this);
            row.setId(i);
            TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(tableParams);
            TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT);

            for(int j = 0; j < TABLE_WIDTH; j++) {
                ImageView cellView = new ImageView(this);
                cellView.setLayoutParams(rowParams);
                cellView.setTag(new Land(i, j));
                cellView.setImageResource(BACKGROUND_TILE);
                cellView.setScaleType(ImageView.ScaleType.FIT_XY);
                row.addView(cellView);
            }
            tileMapTable.addView(row);
        }
    }
}