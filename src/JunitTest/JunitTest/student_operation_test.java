package JunitTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import studentman.student_operation;

public class student_operation_test {
	static student_operation student_operation = null;
    @BeforeClass
    public static void beforeClass(){
        student_operation = new student_operation();
    }
    @Test
    public void student_add_one(){
        int i = student_operation.student_add_one("小青",1);
        Assert.assertEquals(i,1);
        int j = student_operation.student_add_one("小青",1);
        Assert.assertEquals(j,3);
        int k = student_operation.student_add_one("",0);
        Assert.assertEquals(j,4);
    }
}
