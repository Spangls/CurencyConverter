package ru.mpt.convertor.model;

public enum HDType {
    HDD("HDD (Жесткий диск)"), SSD("SSD (Твердотельный накопитель)");

    private String title;

    HDType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
