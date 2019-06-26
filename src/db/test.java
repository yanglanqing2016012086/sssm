package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.Properties;
/**
 * ���ݿ�������
 * @author ������
 */
public class test {
    String driverName=null;//���ݿ�������
	String connString=null;//�����ַ���
	String userName=null;//�û���
	String password=null;//����
	String propertyFileName=null;//.properties�ļ����� 
	public test() {
	}
	public Connection getDBConn()
	{//�õ����ݿ����Ӷ���
	    this.setPropertyFileName("/dbconn.properties");
	    driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		connString=this.getPropertyFromFile("jdbc:sqlserver://localhost:1433;databasename=chengji");
		userName=this.getPropertyFromFile("sa");
		password=this.getPropertyFromFile("9293");
		System.out.println("999");
		System.out.println(driverName);
		if(driverName==null||connString==null||userName==null) return null;
		try{
			Connection connDBObject=null;
			Class.forName(driverName);
			connDBObject = DriverManager.getConnection(connString,userName,password);
			System.out.println("92");
			return connDBObject;
			//return DriverManager.getConnection(connString,userName,password);
		}catch(Exception e){	
			e.printStackTrace();
			System.out.println("93");
			return null;
		}
	}
	
	public static void main(String args[]){
		test dbconnOBject=new test();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) System.out.println("91");//����ʧ��
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
	{//��.properties�ļ��еõ�refName���Ե�ֵ
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
