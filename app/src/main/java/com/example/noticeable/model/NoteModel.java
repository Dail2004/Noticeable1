package com.example.noticeable.model;

import java.io.Serializable;

public class NoteModel implements Serializable {
    String textNote;

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public NoteModel(String textNote) {
        this.textNote = textNote;
    }
}
