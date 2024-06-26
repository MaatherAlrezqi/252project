/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;

// School class representing a school with various attributes
public class School {
    private String name;
    private String district;
    private String leaderName;

    public School(String name, String district, String leaderName) {
        this.name = name;
        this.district = district;
        this.leaderName = leaderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    @Override
    public String toString() {
        return "School Name: " + name + ", District: " + district + ", Leader Name: " + leaderName;
    }
}