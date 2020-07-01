package Exceptions;

/**
 * @author Tobias Lohmüller
 */
public class ParkhausVollException extends RuntimeException {

    public ParkhausVollException() { super("Das Parkhaus ist voll"); }

}
