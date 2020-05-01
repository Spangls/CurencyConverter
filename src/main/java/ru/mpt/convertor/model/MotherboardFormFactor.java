package ru.mpt.convertor.model;

public enum MotherboardFormFactor {
    ATX("ATX"), MiniATX("Mini-ITX"), MicroATX("mATX");

    private String title;

    MotherboardFormFactor(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
