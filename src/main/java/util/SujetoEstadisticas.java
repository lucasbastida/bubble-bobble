package util;

public interface SujetoEstadisticas {
    boolean registerObserver(ObserverEstadisticas o);
    boolean removeObserver(ObserverEstadisticas o);
}
