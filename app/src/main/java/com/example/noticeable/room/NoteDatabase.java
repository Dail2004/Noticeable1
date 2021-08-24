package com.example.noticeable.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.noticeable.model.NoteModel;

@Database(entities = {NoteModel.class}, version = 2, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao getDao();
}
