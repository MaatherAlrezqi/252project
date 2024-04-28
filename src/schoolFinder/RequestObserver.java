/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;

// Example Observer implementation for requests
class RequestObserver implements Observer {
    @Override
    public void update(String eventType, Request request) {
        System.out.println("Observer received notification for event type: " + eventType);
        System.out.println("Request ID: " + request.getRequestId() + ", Status: " + request.getStatus());
    }
}
