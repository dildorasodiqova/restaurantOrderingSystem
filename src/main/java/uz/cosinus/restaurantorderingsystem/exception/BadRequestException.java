package uz.cosinus.restaurantorderingsystem.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String str){
         super(str);
    }
}
