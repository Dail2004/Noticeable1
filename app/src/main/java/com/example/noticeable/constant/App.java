package com.example.noticeable.constant;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.noticeable.room.NoteDatabase;

public class App extends Application {
    private Context context;
    public static NoteDatabase database = null;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static NoteDatabase initDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, NoteDatabase.class, "database-note")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

//    public static NoteDatabase getDatabase(Context context) {
//        return database;
//    }
}
