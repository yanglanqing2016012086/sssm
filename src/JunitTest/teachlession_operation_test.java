package JunitTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import teachlessionman.teachlession_operation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class teachlession_operation_test {
	static teachlession_operation teachlession_operation = null;
    @BeforeClass
    public static void beforeClass(){
        teachlession_operation = new teachlession_operation();
    }
    @Test
    public void teachlession_select_all_test() throws SQLException {
        ResultSet resultSet = teachlession_operation.teachlession_select_all();
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
}
