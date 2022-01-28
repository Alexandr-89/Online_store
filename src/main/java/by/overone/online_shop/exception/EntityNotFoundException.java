package by.overone.online_shop.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(String errorCode){
        super(errorCode);
    }
}
