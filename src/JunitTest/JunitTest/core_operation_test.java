package JunitTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import core.core_operation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class core_operation_test {
	static core_operation core_operation = null;
    @BeforeClass
    public static void beforeClass(){
        core_operation = new core_operation();
    }
    @Test
    public void core_add_one_test(){
        int i = core_operation.core_add_one(1,1);
        Assert.assertEquals(i,1);
        int j = core_operation.core_add_one(1,1);
        Assert.assertEquals(j,3);
        int k = core_operation.core_add_one(0,0);
        Assert.assertEquals(k,4);
    }
    @Test
    public void core_select_all_test() throws SQLException {
        ResultSet resultSet = core_operation.core_select_all();
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
    @Test
    public void core_select_part_test() throws SQLException {
        ResultSet resultSet = core_operation.core_select_part(1,1);
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
    @Test
    public void core_delete_test(){
        int i = core_operation.core_delete(1);
        Assert.assertEquals(i,1);
        int j = core_operation.core_delete(0);
        Assert.assertEquals(j,4);
    }
    @Test
    public void core_select_one_test() throws SQLException {
        ResultSet resultSet = core_operation.core_select_one(1);
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
    @Test
    public void core_update_test(){
        int i = core_operation.core_update(1,1,1);
        Assert.assertEquals(i,1);
        int j = core_operation.core_update(0,0,0);
        Assert.assertEquals(j,4);
    }
}
