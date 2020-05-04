package Exceptions;

public class ParkhausLeerException extends RuntimeException {

    public ParkhausLeerException() { super("Es gibt keinen Kunden mehr, der ausfahren könnte"); }

}
