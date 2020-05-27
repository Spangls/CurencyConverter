package ru.mpt.convertor.model;

public enum Service {
    PAINTING_CASE("Покраска корпуса"),
    PAINTING_PARTS("Покраска комплектующих"),
    CUSTOM_CS("Заказная система охлаждения"),
    CLEARING("Отчистка ПК"),
    CUSTOM_ASSEMBLE("Сборка ПК на заказ");

    private String title;

    Service(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
