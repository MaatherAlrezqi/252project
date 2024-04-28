package schoolFinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InclusiveSchoolFinder {
    public static void main(String[] args) {
        GlobalData globalData = GlobalData.getInstance();

        // Initialize data from files
        try {
            FileManagment.readSchoolsFromFile(globalData.getSchoolsMap());
            FileManagment.readDisabilitiesFromFile(globalData.getDisabilitiesMap());
            FileManagment.readRequestsFromFile(globalData.getRequests());
        } catch (IOException e) {
            System.err.println("Error reading initial data from files: " + e.getMessage());
           
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Select an option:");
            System.out.println("1. Register Child");
            System.out.println("2. Delete Requests");
            System.out.println("3. Update Child Data");
            System.out.println("4. Show Request Status");
            System.out.println("0. Exit");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerChild(scanner, globalData);
                    break;
                case 2:
                    FileManagment.deleteRequests(scanner);
                    break;
                case 3:
                    FileManagment.updateChildData(scanner);
                    break;
                case 4:
                    FileManagment.showRequestStatus(scanner);
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    private static void registerChild(Scanner scanner, GlobalData globalData) {
        System.out.println("Choose the type of disability:");
        String disabilityName = chooseDisability(scanner, globalData);

        if (disabilityName != null) {
            School chosenSchool = chooseSchool(scanner, disabilityName, globalData);

            if (chosenSchool != null) {
                System.out.println("Enter Child ID:");
                int childId = scanner.nextInt();

                System.out.println("Enter Child Name:");
                String childName = scanner.next();

                System.out.println("Enter Child Age:");
                int age = scanner.nextInt();

                System.out.println("Enter Academic Year:");
                int academicYear = scanner.nextInt();

                Child child = ChildFactory.createChild(childId, childName, age, academicYear);

                Request request = RequestFactory.createRequest(Request.generateRequestId(), child, chosenSchool, "Pending");

                globalData.getRequests().add(request);

                FileManagment.saveRequestsToFile(globalData.getRequests());  // Ensure this method exists

                System.out.println("Child registered successfully! Request ID: " + request.getRequestId());
            }
        }
    }

    private static String chooseDisability(Scanner scanner, GlobalData globalData) {
        System.out.println("Available Disabilities:");
        int index = 1;

        for (Disability disability : globalData.getDisabilitiesMap().values()) {
            System.out.println(index + ". " + disability.getName());
            index++;
        }

        System.out.println("Enter the number corresponding to the desired disability:");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= globalData.getDisabilitiesMap().size()) {
            int currentIndex = 1;

            for (Disability disability : globalData.getDisabilitiesMap().values()) {
                if (currentIndex == choice) {
                    return disability.getName();
                }
                currentIndex++;
            }
        }

        System.out.println("Invalid choice. Please try again.");
        return null;
    }

    private static School chooseSchool(Scanner scanner, String disabilityName, GlobalData globalData) {
        System.out.println("Available Schools that accept " + disabilityName + ":");
        List<School> eligibleSchools = new ArrayList<>();
        int index = 1;

        for (School school : globalData.getSchoolsMap().values()) {
            if (globalData.getSchoolsDisabilitiesMap().get(school.getName()).contains(disabilityName)) {
                eligibleSchools.add(school);
                System.out.println(index + ". " + school.getName());
                index++;
            }
        }

        if (eligibleSchools.isEmpty()) {
            System.out.println("No schools found that accept the given disability.");
            return null;
        }

        System.out.println("Enter the number corresponding to the desired school:");
        int choice = scanner.nextInt();

        if (choice >= 1 && choice <= eligibleSchools.size()) {
            return eligibleSchools.get(choice - 1);
        }

        System.out.println("Invalid choice. Please try again.");
        return null;
    }
}
