import java.awt.event.KeyListener;
import java.security.Key;

public interface Subject<T> extends KeyListener {
    //when create a ball object, it is needed to register the ball object into observer list.
    public void registerObserver(T t);

    // when restart a new game, it is needed to remove all observer from paintinglist
    public void removeObserver(T t);

    // when clicked keyboard, it is needed to notify all observers to update their state
    public void notifyObservers(char keyChar);

    // For task 2: when white ball moved, it is needed to notify all observers to update their state
    public void notifyObservers(MainPanel.GameStatus status);

    // or For task2
    public void notifyObservers(T t);
}
