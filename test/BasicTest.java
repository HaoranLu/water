import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Before
    public void setup() {
        Fixtures.deleteDatabase();
    }

    @Test
    public void StaffTest() {
        //Create and Save
        new Staff("staff1", "secret", "小吴").save();

        //Retrieve staff with username
        Staff xiaowu = Staff.find("byUsername", "staff1").first();

        //Test
        assertNotNull(xiaowu);
        assertEquals("小吴", xiaowu.name);

    }

    @Test
    public void FullTest() {
        Staff xiaowu = new Staff("staff1", "secret", "小吴").save();
        User mrWu = new User("吴先生", "18622436888").save();

        mrWu.director = xiaowu;

    }

    @Test
    public void UserTest() {
        //Create and Save
        new User("吴先生", "18622436888").save();

        //Retrieve user with email
        User mrWu = User.find("byPhone", "18622436888").first();

        //Test
        assertNotNull(mrWu);
        assertEquals("吴先生", mrWu.name);
    }

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

}
