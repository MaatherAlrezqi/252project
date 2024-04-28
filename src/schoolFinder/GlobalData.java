package schoolFinder;

import java.util.*;

public class GlobalData {
    // Singleton instance
    private static GlobalData instance;

    public static synchronized GlobalData getInstance() {
        if (instance == null) {
            instance = new GlobalData();
        }
        return instance;
    }

    private final Map<String, School> schoolsMap;
    private final Map<String, Disability> disabilitiesMap;
    private final List<Request> requests;
    private final Map<String, List<String>> schoolsDisabilitiesMap;

    private GlobalData() {
        schoolsMap = new HashMap<>();
        disabilitiesMap = new HashMap<>();
        requests = new ArrayList<>();
        schoolsDisabilitiesMap = new HashMap<>();
    }

    public Map<String, School> getSchoolsMap() {
        return schoolsMap;
    }

    public Map<String, Disability> getDisabilitiesMap() {
        return disabilitiesMap;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public Map<String, List<String>> getSchoolsDisabilitiesMap() {
        return schoolsDisabilitiesMap;
    }
}
