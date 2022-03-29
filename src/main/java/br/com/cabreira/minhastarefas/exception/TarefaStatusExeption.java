package br.com.cabreira.minhastarefas.exception;

public class TarefaStatusExeption extends RuntimeException {

    public TarefaStatusExeption() {
    }

    public TarefaStatusExeption(String message) {
        super(message);
    }

    public TarefaStatusExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public TarefaStatusExeption(Throwable cause) {
        super(cause);
    }

    public TarefaStatusExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
