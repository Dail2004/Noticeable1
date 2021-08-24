package com.example.noticeable.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.noticeable.model.NoteModel;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM notemodel")
    LiveData<List<NoteModel>> getAll();

    @Insert
    void insertModel(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);
}
