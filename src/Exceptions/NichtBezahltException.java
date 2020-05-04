package Exceptions;

public class NichtBezahltException extends RuntimeException {

    public NichtBezahltException() { super("Der Kunde hat noch nicht gezahlt."); }

}
