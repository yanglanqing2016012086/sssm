package JunitTest;

import lessionman.classlession_operation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class classlession_operation_test {
    static classlession_operation classlession_operation = null;
    @BeforeClass
    public static void beforeClass(){
        classlession_operation = new classlession_operation();
    }
    @Test
    public void getCoreClassLessionRs_test() throws SQLException {
        ResultSet resultSet = classlession_operation.getCoreClassLessionRs(1,1);
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
    @Test
    public void getClassLessionCloseStatus_test(){
        String status = classlession_operation.getClassLessionCloseStatus(1,1);
        Assert.assertNotNull(status);
    }
}
