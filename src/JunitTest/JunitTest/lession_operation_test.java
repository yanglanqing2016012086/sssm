package JunitTest;

import lessionman.lession_operation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class lession_operation_test {
	static lession_operation lession_operation = null;
    @BeforeClass
    public static void beforClass(){
        lession_operation = new lession_operation();
    }
    @Test
    public void lession_add_one_test() throws SQLException {
        ResultSet resultSet = lession_operation.lession_select_one(1);

        Assert.assertNotNull(resultSet);
    }
}
