package ru.mpt.convertor.model;

public enum CaseWindow {
    NONE("Не прозрачная"), PLASTIC("Акрил"), GLASS("Закаленное стекло");

    private String title;

    CaseWindow(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
