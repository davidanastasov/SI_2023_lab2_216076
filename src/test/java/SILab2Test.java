import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {

    public User createUser(String username, String password, String email) {
        return new User(username, password, email);
    }

    public List<User> getUsers(){
        List<User> users = new ArrayList<>();

        User user1 = createUser("peralta", "DieHard123!", "jake.peralta@nyc.gov");
        User user2 = createUser("boyle", "Nikolaj +", "charles.boyle@nyc.gov");
        User user3 = createUser("diaz", "diaz", "rosa.diaz@nyc.gov");
        User user4 = createUser("holt", "Kevin", "raymondholt@nycgov");
    
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);

        return users;
    }
    
    @Test
    void testEveryBranch() {
        List<User> readOnlyUsers = getUsers();

        // Checks for already existing username and email
        assertFalse(SILab2.function(createUser("peralta", "DieHard123!", "jake.peralta@nyc.gov.org"), getUsers()));

        // Checks if the password has special characters and no spaces
        assertFalse(SILab2.function(readOnlyUsers.get(0)), getUsers());
        
        // Checks if the password has empty spaces
        assertFalse(SILab2.function(readOnlyUsers.get(1)), getUsers());
        
        // Checks if the username and the password are the same
        // Also checks if the password is less than 8 characters
        assertFalse(SILab2.function(readOnlyUsers.get(2)), getUsers());
        
        // Checks if the email is not in the correct format
        assertFalse(SILab2.function(readOnlyUsers.get(3)), getUsers());
        
        // Checks if all information is correct and user doesn't exist
        assertTrue(SILab2.function(createUser("santiago", "paSSword10!+", "amy.santiago@nyc.gov"), getUsers())); 
    }

    @Test
    void testMultipleConditions(){
        String expectedException = "Mandatory information missing!";
        RuntimeException exc;
        
        // Checks for null user
        exc = assertThrows(RuntimeException.class, () -> SILab2.function(null, getUsers()));
        assertTrue(ex.getMessage().contains(expectedException));

        // Checks for null password
        exc = assertThrows(RuntimeException.class, () -> SILab2.function(createUser("test", null, "test@email.com"), getUsers()));
        assertTrue(ex.getMessage().contains(expectedException));
        
        // Checks for null email
        exc = assertThrows(RuntimeException.class, () -> SILab2.function(createUser("parotz", "crackmeup", null), getUsers()));
        assertTrue(ex.getMessage().contains(expectedException));
        
        // Checks for all information correct
        assertTrue(SILab2.function(createUser("santiago", "paSSword10!+", "amy.santiago@nyc.gov"), getUsers()));
    }

}
