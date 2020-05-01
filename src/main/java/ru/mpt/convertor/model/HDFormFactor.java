package ru.mpt.convertor.model;

public enum HDFormFactor {

    HDM2("M2"), HD25("2.5"), HD35("3.5");

    private String title;

    HDFormFactor(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
