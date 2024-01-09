package uz.cosinus.restaurantorderingsystem.exception;
public class ExceededLimitException extends RuntimeException{
    public ExceededLimitException(String message){
        super(message);
    }
}
