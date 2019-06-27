package JunitTest;

import db.dbconn;
import org.junit.Assert;
import org.junit.Test;

public class dbconn_test {
    @Test
    public void getDBConn_test(){
        dbconn dbconn = new dbconn();
        Assert.assertNotNull(dbconn);
    }
}
