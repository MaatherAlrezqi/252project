
package schoolFinder;

// Event Notifier (Subject) with a list of observers

import java.util.ArrayList;
import java.util.List;

class EventNotifier {
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String eventType, Request request) {
        for (Observer observer : observers) {
            observer.update(eventType, request);
        }
    }
}
