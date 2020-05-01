package ru.mpt.convertor.model;

public enum CoolingSystemType {
    WATER("Водяная"), ACTIVE("Активная"), PASSIVE("Пассивная");

    private String title;

    CoolingSystemType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
