package util;

public interface Subject {

    boolean registerObserver(Observer o);

    boolean removeObserver(Observer o);
}
