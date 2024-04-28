/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;
// Child class representing a child with basic attributes
public class Child {
    private int childId;
    private String name;
    private int age;
    private int academicYear;

    public Child(int childId, String name, int age, int academicYear) {
        this.childId = childId;
        this.name = name;
        this.age = age;
        this.academicYear = academicYear;
    }

    public int getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public String toString() {
        return String.format("Child ID: %d, Name: %s, Age: %d, Academic Year: %d", childId, name, age, academicYear);
    }
}

