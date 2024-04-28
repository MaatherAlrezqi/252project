
package schoolFinder;


// Rejected Request Strategy
 class RejectedRequestStrategy implements RequestStrategy {
    @Override
    public void handleRequest(Request request) {
        request.setStatus("Rejected");
    }
}
