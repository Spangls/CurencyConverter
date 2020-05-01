package ru.mpt.convertor.model;

public enum CaseFormFactor {
    MiniTOWER("Mini-Tower"), MidiTOWER("Midi-Tower"), BigTOWER("Full-Tower"), DESKTOP("Full-Tower");

    private String title;

    CaseFormFactor(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
