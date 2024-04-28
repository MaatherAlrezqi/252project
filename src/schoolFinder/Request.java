// Request class representing a registration request
package schoolFinder;

import java.util.Random;

public class Request {
    private static int nextRequestId = 1;

    private final int requestId;
    private final Child child;
    private final School school;
    private String status;

    public Request(int requestId, Child child, School school, String status) {
        this.requestId = requestId;
        this.child = child;
        this.school = school;
        this.status = status;
    }

    public static synchronized int generateRequestId() {
        return nextRequestId++;
    }

    public int getRequestId() {
        return requestId;
    }

    public Child getChild() {
        return child;
    }

    public School getSchool() {
        return school;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String determineFinalStatus() {
        Random random = new Random();
        this.status = random.nextBoolean() ? "Accepted" : "Rejected";
        return this.status;
    }

    public String toFormattedString() {
        return String.format(
            "Request ID: %d, Child ID: %d, School: %s, Status: %s",
            requestId, child.getChildId(), school.getName(), status
        );
    }

    public String toFormattedStringForFile() {
        return String.format(
            "%d,%d,%s,%s",
            requestId, child.getChildId(), school.getName(), status
        );
    }
}