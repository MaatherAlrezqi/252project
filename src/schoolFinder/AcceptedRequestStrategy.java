
package schoolFinder;


// Accepted Request Strategy
class AcceptedRequestStrategy implements RequestStrategy {
    @Override
    public void handleRequest(Request request) {
        request.setStatus("Accepted");
    }
}