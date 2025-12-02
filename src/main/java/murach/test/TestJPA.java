package murach.test;

import murach.business.User;
import murach.data.UserDB;

public class TestJPA {
    public static void main(String[] args) {
        System.out.println("Testing JPA...");
        
        // Create a new user
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("test" + System.currentTimeMillis() + "@example.com");
        
        // Test Insert
        System.out.println("Inserting user: " + user.getEmail());
        int result = UserDB.insert(user);
        if (result == 1) {
            System.out.println("Insert successful!");
        } else {
            System.out.println("Insert failed!");
        }
        
        // Test Select
        System.out.println("Selecting user...");
        User retrievedUser = UserDB.selectUser(user.getEmail());
        if (retrievedUser != null) {
            System.out.println("Found user: " + retrievedUser.getFirstName() + " " + retrievedUser.getLastName());
        } else {
            System.out.println("User not found!");
        }
        
        // Test Exists
        if (UserDB.emailExists(user.getEmail())) {
            System.out.println("Email exists check passed.");
        } else {
            System.out.println("Email exists check failed.");
        }
        
        System.out.println("Test complete.");
        
        // Clean up (Optional, but good for testing)
        // UserDB.delete(user);
    }
}
