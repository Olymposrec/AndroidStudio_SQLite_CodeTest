package com.melihakkose.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase database = this.openOrCreateDatabase("Games",MODE_PRIVATE,null);

            database.execSQL("DROP TABLE games");
            database.execSQL("CREATE TABLE IF NOT EXISTS games (id INTEGER PRIMARY KEY ,game VARCHAR" +
                    ",releasedate VARCHAR,developer VARCHAR,composer VARCHAR," +
                    "designers VARCHAR,platforms VARCHAR) ");

            database.execSQL("INSERT INTO games (game,releasedate,developer,composer,designers,platforms)" +
                    "VALUES ('Dark Souls III','March 24, 2016','FromSoftware, Inc.','Motoi Sakuraba'," +
                    "'Junya Ishizaki,Yuya Kimijima, Shigeto Hirai, Hiroshi Yoshida', 'PlayStation 4, Xbox One, " +
                    "Microsoft Windows')");
            database.execSQL("INSERT INTO games (game,releasedate,developer,composer,designers,platforms)" +
                    "VALUES ('The Wİtcher 3: Wild Hunt','May 18, 2015','CD Projekt RED','Sebastian Stępień'," +
                    "'Konrad Tomaszkiewicz', 'PlayStation 4, Xbox One, " +
                    "Microsoft Windows')");
            database.execSQL("INSERT INTO games (game,releasedate,developer,composer,designers,platforms)" +
                    "VALUES ('Red Dead Redemption 2','October 26, 2018','Rockstar Games','Dan Houser; Michael Unsworth; Rupert Humphries'," +
                    "'Michael Unsworth', 'PlayStation 4, Xbox One, " +
                    "Microsoft Windows, Google Stadia,')");

            //database.execSQL("UPDATE games SET game='League Of Legends' WHERE game='Dark Souls III'");


            Cursor cursor= database.rawQuery("SELECT * FROM games",null);
            //Cursor cursor= database.rawQuery("SELECT * FROM games WHERE platforms  LIKE '%Google%'",null);

            int nameIx=cursor.getColumnIndex("game");
            int developerIx=cursor.getColumnIndex("developer");
            int platformIx=cursor.getColumnIndex("platforms");
            int idIx=cursor.getColumnIndex("id");

            while(cursor.moveToNext()){
                System.out.println("Game: "+cursor.getString((nameIx)));
                System.out.println("Developer: "+cursor.getString((developerIx)));
                System.out.println("Platforms: "+cursor.getString((platformIx)));
                System.out.println("ID: "+cursor.getInt((idIx)));
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}