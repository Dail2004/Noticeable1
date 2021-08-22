package com.example.noticeable.model;

import java.io.Serializable;

public class NoteModel implements Serializable {
    private String textNote;

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NoteModel(String textNote, String date) {
        this.textNote = textNote;
        this.date = date;
    }

    String date;
}
