package ru.mpt.convertor.model;

public enum RamTech {
    DDR4("DDR4"), DDR3("DDR3"), DDR3L("DDR3L");

    private String title;

    RamTech(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
