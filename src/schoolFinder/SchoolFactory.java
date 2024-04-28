/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;

// Factory for creating School instances
public class SchoolFactory {
    public static School createSchool(String name, String district, String leaderName) {
        return new School(name, district, leaderName);
    }
}
