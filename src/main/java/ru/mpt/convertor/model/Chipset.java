package ru.mpt.convertor.model;

public enum Chipset {
    INTEL("Intel"), AMD("AMD");

    private String title;

    Chipset(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
