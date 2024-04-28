/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;

// Pending Request Strategy
class PendingRequestStrategy implements RequestStrategy {
    @Override
    public void handleRequest(Request request) {
        request.setStatus("Pending");
    }
}
