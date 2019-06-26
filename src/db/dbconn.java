package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.Properties;
/**
 * 数据库连接类
 */
public class dbconn {
    String driverName=null;//数据库驱动名
	String connString=null;//连接字符串
	String userName=null;//用户名
	String password=null;//密码
	String propertyFileName=null;//.properties文件名称 
	public dbconn() {
	}
	public Connection getDBConn()
	{//得到数据库连接对象
	    //this.setPropertyFileName("/dbconn.properties");
	    driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		connString="jdbc:sqlserver://localhost:1433;databaseName=chengji";
		userName="sa";
		password="9293";
		if(driverName==null||connString==null||userName==null) return null;
		try{
			Connection connDBObject=null;
			Class.forName(driverName);
			return DriverManager.getConnection(connString,userName,password);
		}catch(Exception e){	
			e.printStackTrace();
			return null;
		}	
	}
	
	public static void main(String args[]){
		dbconn dbconnOBject=new dbconn();//数据库连接对象
        Connection dbconn=dbconnOBject.getDBConn();//得到数据库连接
        if(dbconn!=null) System.out.println("9");//连接
		}
	
	public String getConnString() {
		return connString;
	}
	public String getDriverName() {
		return driverName;
	}
	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}
	public void setPropertyFileName(String propertyFileName) {
		this.propertyFileName = propertyFileName;
	}
	public String getPropertyFileName() {
		return propertyFileName;
	}
	public String getPropertyFromFile(String refName)
	{//从.properties文件中得到refName属性的值
		if(this.getPropertyFileName()==null) return new String("");
		try{
			InputStream fin = getClass().getResourceAsStream(this.getPropertyFileName());
			Properties props = new Properties();
			props.load(fin);
			return (String)props.getProperty(refName);
		}catch(Exception e){
		    e.printStackTrace();
			return new String("");
		}
	}
}
