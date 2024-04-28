package schoolFinder;

import java.io.*;
import java.util.*;

public class FileManagment {

    public static final String SCHOOLS_FILE = "schools.txt";  // Correct initialization
    public static final String DISABILITIES_FILE = "disabilities.txt";
    public static final String REQUESTS_FILE = "requests.txt";
    public static final String CHILD_DATA_FILE = "childData.txt";

    // Read schools from a file and populate a map
    public static void readSchoolsFromFile(Map<String, School> schoolsMap) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(SCHOOLS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String district = parts[1].trim();
                String leaderName = parts[2].trim();

                School school = new School(name, district, leaderName);
                schoolsMap.put(name, school);
            }
        } catch (IOException e) {
            System.err.println("Error reading schools from file.");

        }
    }

    // Read disabilities from a file and populate a map
    public static void readDisabilitiesFromFile(Map<String, Disability> disabilitiesMap) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(DISABILITIES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String disabilityName = line.trim();
                Disability disability = new Disability(disabilityName);
                disabilitiesMap.put(disabilityName, disability);
            }
        } catch (IOException e) {
            System.err.println("Error reading disabilities from file.");

        }
    }

    // Read requests from a file
    public static void readRequestsFromFile(List<Request> requests) throws IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(REQUESTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int requestId = Integer.parseInt(parts[0]);
                int childId = Integer.parseInt(parts[1]);
                String schoolName = parts[2];
                String status = parts[3];

                Child child = new Child(childId, "Unknown", 0, 0);
                School school = GlobalData.getInstance().getSchoolsMap().get(schoolName);
                Request request = new Request(requestId, child, school, status);
                requests.add(request);
            }
        } catch (IOException e) {
            System.err.println("Error reading requests from file.");

        }
    }

    // Show request status
    public static void showRequestStatus(Scanner scanner) {
        GlobalData globalData = GlobalData.getInstance();
        List<Request> requests = globalData.getRequests();

        System.out.print("Enter the Request ID to check status: ");
        int requestId = scanner.nextInt();

        boolean found = false;

        for (Request request : requests) {
            if (request.getRequestId() == requestId) {
                System.out.println("Request Status:");
                System.out.println("Request ID: " + request.getRequestId());
                System.out.println("Child Information: " + request.getChild());
                System.out.println("School Information: " + request.getSchool());
                System.out.println("Status: " + request.getStatus());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Request with ID " + requestId + " not found.");
        }
    }

    // Delete requests based on user input
    public static void deleteRequests(Scanner scanner) {
        GlobalData globalData = GlobalData.getInstance();
        List<Request> requests = globalData.getRequests();

        System.out.print("Enter child ID to delete requests: ");
        int childIdToDelete = scanner.nextInt();

        List<Request> matchingRequests = new ArrayList<>();
        for (Request request : requests) {
            if (request.getChild().getChildId() == childIdToDelete) {
                matchingRequests.add(request);
            }
        }

        if (matchingRequests.isEmpty()) {
            System.out.println("No requests found for Child ID " + childIdToDelete);
        } else {
            System.out.println("Requests for Child ID " + childIdToDelete + ":");
            for (Request request : matchingRequests) {
                System.out.println("Request ID: " + request.getRequestId());
            }

            System.out.print("Enter request ID to delete: ");
            int requestIdToDelete = scanner.nextInt();

            boolean found = false;
            for (Request request : matchingRequests) {
                if (request.getRequestId() == requestIdToDelete) {
                    requests.remove(request);
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Request deleted successfully.");
                saveRequestsToFile(requests);
            } else {
                System.out.println("Request not found.");
            }
        }
    }

    // Update child data based on user input
    public static void updateChildData(Scanner scanner) {
        GlobalData globalData = GlobalData.getInstance();
        List<Child> children = readChildDataFromFile();

        System.out.print("Enter Child ID to update: ");
        int childId = scanner.nextInt();

        boolean found = false;
        for (Child child : children) {
            if (child.getChildId() == childId) {
                System.out.println("Updating data for Child ID: " + childId);

                System.out.print("Enter new name: ");
                String newName = scanner.next();;

                System.out.print("Enter new age: ");
                int newAge = scanner.nextInt();;

                System.out.print("Enter new academic year: ");
                int newAcademicYear = scanner.nextInt();;

                child.setName(newName);

                child.setAge(newAge);
                child.setAcademicYear(newAcademicYear);

                found = true;
                break;
            }
        }

        if (found) {
            writeChildDataToFile(children);  // Save updated data to the file
            System.out.println("Child data updated successfully.");
        } else {
            System.out.println("Child with ID " + childId + "not found.");
        }
    }

    // Save requests to a file
    public static void saveRequestsToFile(List<Request> requests) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter(REQUESTS_FILE))) {
            for (Request request : requests) {
                writer.println(request.toFormattedStringForFile());
            }
        } catch (IOException e) {
            System.err.println("Error saving requests to file.");

        }
    }

    // Read child data from a file and return a list of Child objects
    public static List<Child> readChildDataFromFile() {
        List<Child> children = new ArrayList<>();  // Initialize the list of children
        try ( BufferedReader reader = new BufferedReader(new FileReader(CHILD_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {  // Loop through each line in the file
                String[] parts = line.split(",");
                int childId = Integer.parseInt(parts[0]);  // First part is the child ID
                String name = parts[1];  // Second part is the child name
                int age = Integer.parseInt(parts[2]);  // Third part is the child's age
                int academicYear = Integer.parseInt(parts[3]);  // Fourth part is the academic year

                // Create a Child object and add it to the list
                Child child = new Child(childId, name, age, academicYear);
                children.add(child);  // Add the child to the list
            }
        } catch (IOException e) {
            System.err.println("Error reading child data from file: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Invalid data format in child data file: " + e.getMessage());

        }
        return children;  // Return the list of children
    }

    // Write a list of Child objects to a file
    public static void writeChildDataToFile(List<Child> children) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter(CHILD_DATA_FILE))) {
            for (Child child : children) {  // Loop through each child in the list
                writer.println(String.format("%d,%s,%d,%d",
                        child.getChildId(), // Child ID
                        child.getName(), // Child's name
                        child.getAge(), // Child's age
                        child.getAcademicYear()));  // Child's academic year
            }
        } catch (IOException e) {
            System.err.println("Error writing child data to file: " + e.getMessage());

        }
    }
}
