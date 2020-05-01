package ru.mpt.convertor.model;

public enum Socket {
    SOCKET_AM4("AM4"), SOCKET_1151V2("1151 v2");

    public String title;
    Socket(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
