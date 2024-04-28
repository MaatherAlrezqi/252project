/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolFinder;
// Factory for creating Request instances
public class RequestFactory {
    public static Request createRequest(int requestId, Child child, School school, String status) {
        return new Request(requestId, child, school, status);
    }
}