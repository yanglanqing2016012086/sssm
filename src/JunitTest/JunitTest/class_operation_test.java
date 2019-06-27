package JunitTest;

import classman.class_operation;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class class_operation_test {
    public static class_operation class_operation = null;
    @BeforeClass
    public static void beforClass(){
        class_operation = new class_operation();
    }
    @Test
    public void class_add_one_test(){
        int i = class_operation.class_add_one("三年级1班");
        Assert.assertEquals(i,1);
        int j = class_operation.class_add_one("三年级1班");
        Assert.assertEquals(j,3);
        int k = class_operation.class_add_one("");
        Assert.assertEquals(k,4);
    }
    @Test
    public void class_select_all_test() throws SQLException {
        ResultSet resultSet = class_operation.class_select_all();
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount>0);
    }
    @Test
    public void class_delete_test(){
        int i = class_operation.class_delete(2);
        Assert.assertEquals(i,1);
        int j = class_operation.class_delete(0);
        Assert.assertEquals(j,4);
    }
    @Test
    public void class_select_one_test() throws SQLException {
        ResultSet resultSet = class_operation.class_select_one(9);
        resultSet.last();
        int rowCount=resultSet.getRow();
        Assert.assertTrue(rowCount == 1);
    }
    @Test
    public void class_update_test(){

        int i = class_operation.class_update(2,"123");
        Assert.assertEquals(i,1);
        int j = class_operation.class_update(2,"三年级1班");
        Assert.assertEquals(i,3);
        int k = class_operation.class_update(2,"");
        Assert.assertEquals(i,4);
    }
}
