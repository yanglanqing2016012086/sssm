package JunitTest;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import teacherman.teacher_operation;

public class teacher_operation_test {
	static teacher_operation teacher_operation = null;
    @BeforeClass
    public static void beforeClass(){
        teacher_operation = new teacher_operation();
    }
    @Test
    public void teacher_add_one_test(){
        int i = teacher_operation.teacher_add_one("Сѩ");
        Assert.assertEquals(i,1);
        int j = teacher_operation.teacher_add_one("Сѩ");
        Assert.assertEquals(i,3);
        int k = teacher_operation.teacher_add_one("");
        Assert.assertEquals(i,4);
    }
}
