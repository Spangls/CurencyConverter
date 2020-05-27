package ru.mpt.convertor.exception;

public class UnknownEntityException extends CustomNotValidException{

    private final Class<?> clazz;
    private final int entityId;

    public UnknownEntityException(Class<?> clazz, int entityId) {
        super("NotExist", clazz.getSimpleName(), "id");
        this.clazz = clazz;
        this.entityId = entityId;
    }

    public String getEntityName() {
        return clazz.getSimpleName();
    }

    public int getEntityId() {
        return entityId;
    }
}
