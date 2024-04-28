/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;

// Factory for creating Child instances
public class ChildFactory {
    public static Child createChild(int childId, String name, int age, int academicYear) {
        return new Child(childId, name, age, academicYear);
    }
}