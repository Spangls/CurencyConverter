package ru.mpt.convertor.model;

public enum ItemType {
    CPU("Процессор"), GPU("Видеокарта"), RAM("Операивная память"), MB("Материнская плата"), PS("Блок питания"), CASE("Корпус"), HD("Хранилище данных"), CS("Система охлаждения");

    private String title;

    ItemType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
