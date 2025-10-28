package ch.tbz.gino_goncalo.Gino.D3;

public class ExternalServiceException extends RuntimeException {
  public ExternalServiceException(String message) { super(message); }
  public ExternalServiceException(String message, Throwable cause) { super(message, cause); }
}
