package uz.cosinus.restaurantorderingsystem.exception;

public class CannotBeChangedException extends RuntimeException{
    public CannotBeChangedException(String msg) {
        super(msg);
    }
}
