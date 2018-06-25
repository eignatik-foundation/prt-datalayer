package space.eignatik.prt.datalayer.dataTools.dao;

import org.testng.annotations.Test;
import space.eignatik.prt.datalayer.modelEntities.User;

public class UserDAOTest {
    private IDAO<User> dao = new UserDAO();

    @Test
    public void testWhenAddWithItem_thenItWillBeCreatedInDB() {
        dao.add(new User()
                    .setId(1000)
                    .setFname("John")
                    .setLname("Dorian")
        );
        dao.get(User.class, 1000);
    }
}