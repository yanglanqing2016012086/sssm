package user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.stringUtil;
import db.dbconn;
/**
 * �û���
 */
public class user_operation {
    /**
     * ����:�ж��Զ����ɵĽ�ɫ�Ƿ��Ѿ������û�
     * ����:foreign_idΪ��ʦ���ѧ�����е�ID��,sysuser_roleΪ�û���ɫ
     * ���:����0��δ����,����1��������,����3��δ֪
     * ˵��:ֻҪ��sysuser���пɲ鵽��¼,����ʾ�Ѿ�����
     */
    public int isAutoGenOK(long foreign_id,int sysuser_role){
        if(foreign_id==0||sysuser_role==0||sysuser_role==1||sysuser_role==2)
        //�û���ɫΪ1��ϵͳ����Ա,Ϊ2�������Ա,�����ֽ�ɫ�ֹ�����,���Բ�Ϊ�Զ�����
            return 3;
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 3;//����ʧ��
        sqlString="select * from sysuser where foreign_id=? and sysuser_role=?";
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setLong(1,foreign_id);
            preSQLSelect.setInt(2,sysuser_role);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 1;
            else return 0;
          }catch(Exception e){
            System.out.print(e);
            return 3;
          }   
    }
    public int isAdminGenOK(long foreign_id,int sysuser_role){
        if(foreign_id==0||sysuser_role==0)
            return 3;
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 3;//����ʧ��
        sqlString="select * from sysuser where foreign_id=? and sysuser_role=?";
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setLong(1,foreign_id);
            preSQLSelect.setInt(2,sysuser_role);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 1;
            else return 0;
          }catch(Exception e){
            System.out.print(e);
            return 3;
          }   
    }
    /**
     * ���ܣ�������ʦ�û�
     */
    public String genTeacherUser(String teacher_id[]){
        String returnString=new String("");
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        ResultSet rs1=null;//��һ�����¼��
        Statement sql=null;//SQL������
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return returnString;//����ʧ��
        try{
            sqlString="select * from teacher";
            System.out.println(teacher_id.length);
            int j=0;//�ӳ�SQL���Ĵ���
            if(teacher_id.length!=0){//���ɲ��ֽ�ʦ�û�
                for(int i=0;i<teacher_id.length;i++)
                    if(teacher_id[i]!=null&&teacher_id[i].length()!=0&&!teacher_id[i].equalsIgnoreCase("null")){
                        if(j==0)
                            {sqlString=sqlString+" where teacher_id="+teacher_id[i];j++;}
                        else
                            sqlString=sqlString+" or teacher_id="+teacher_id[i];
                    }
                
            }
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);
            while(rs.next()){
                sqlString="select * from sysuser where foreign_id=" +
                		rs.getLong("teacher_id")+" and sysuser_role=3";
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1=sql.executeQuery(sqlString);
                if(!rs1.next()){//�����ڲ��룬����������
                    sqlString="insert into sysuser(sysuser_name,sysuser_password,sysuser_role" +
                    		",foreign_id) values(?,?,3,?)";
                    PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
                    preSQLUpdate.setString(1,rs.getString("teacher_name"));
                    preSQLUpdate.setString(2,"111111");
                    preSQLUpdate.setLong(3,rs.getLong("teacher_id"));
                    preSQLUpdate.executeUpdate();
                    returnString=returnString+"���ɽ�ʦ"+rs.getString("teacher_name")+"�û��ɹ���<BR>";
                }else
                    returnString=returnString+"��ʦ"+rs.getString("teacher_name")+"�û��Ѿ����ɣ����������ɣ�<BR>";
                rs1.close();
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return returnString;
    }
    
    /**
     * ���ܣ�����ѧ���û�
     */
    public String genStudentUser(String student_id[]){
        String returnString=new String("");
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        ResultSet rs1=null;//��һ�����¼��
        Statement sql=null;//SQL������
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return returnString;//����ʧ��
        try{
            sqlString="select * from student";
            System.out.println(student_id.length);
            int j=0;//�ӳ�SQL���Ĵ���
            if(student_id.length!=0){//���ɲ��ֽ�ʦ�û�
                for(int i=0;i<student_id.length;i++)
                    if(student_id[i]!=null&&student_id[i].length()!=0&&!student_id[i].equalsIgnoreCase("null")){
                        if(j==0)
                            {sqlString=sqlString+" where student_id="+student_id[i];j++;}
                        else
                            sqlString=sqlString+" or student_id="+student_id[i];
                    }
                
            }
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);
            while(rs.next()){
                sqlString="select * from sysuser where foreign_id=" +
                		rs.getLong("student_id")+" and sysuser_role=4";
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1=sql.executeQuery(sqlString);
                if(!rs1.next()){//�����ڲ��룬����������
                    sqlString="insert into sysuser(sysuser_name,sysuser_password,sysuser_role" +
                    		",foreign_id) values(?,?,4,?)";
                    PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
                    preSQLUpdate.setString(1,rs.getString("student_name"));
                    preSQLUpdate.setString(2,"111111");
                    preSQLUpdate.setLong(3,rs.getLong("student_id"));
                    preSQLUpdate.executeUpdate();
                    returnString=returnString+"����ѧ��"+rs.getString("student_name")+"�û��ɹ���<BR>";
                }else
                    returnString=returnString+"ѧ��"+rs.getString("student_name")+"�û��Ѿ����ɣ����������ɣ�<BR>";
                rs1.close();
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return returnString;
    }
    /**
     * �õ������û���¼��,ϵͳ����Ա�ͽ������Ա�ǹ����û�
     */
    public ResultSet getAdminUsers(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from sysuser where sysuser_role=1 or sysuser_role=2"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        } 
    }
    /**
     * ���ܣ����ɹ����û�
     * ����:����teacher_idΪ���ɹ����û��Ľ�ʦID,ֻ�н�ʦ�ܳ�Ϊ�����û�,role_idΪ����Ľ�ɫ
     */
    public String genAdminUser(int teacher_id,int role_id){
        String returnString=new String("");
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        ResultSet rs1=null;//��һ�����¼��
        Statement sql=null;//SQL������
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null||teacher_id==0||role_id==0) return returnString;//����ʧ��
        try{
            sqlString="select * from teacher where teacher_id="+teacher_id;
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);
            while(rs.next()){
                sqlString="select * from sysuser where foreign_id=" +
                		rs.getLong("teacher_id")+" and sysuser_role="+role_id;
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs1=sql.executeQuery(sqlString);
                if(!rs1.next()){//�����ڲ��룬����������
                    sqlString="insert into sysuser(sysuser_name,sysuser_password,sysuser_role" +
                    		",foreign_id) values(?,?,"+role_id+",?)";
                    System.out.println(sqlString);
                    PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
                    preSQLUpdate.setString(1,rs.getString("teacher_name"));
                    preSQLUpdate.setString(2,"111111");
                    preSQLUpdate.setLong(3,rs.getLong("teacher_id"));
                    preSQLUpdate.executeUpdate();
                    returnString=returnString+"���ɹ����û�"+rs.getString("teacher_name")+"�û��ɹ���<BR>";
                }else
                    returnString=returnString+rs.getString("teacher_name")+"�����û��Ѿ����ɣ����������ɣ�<BR>";
                rs1.close();
            }
        }catch(Exception e){
            System.out.print(e);
        }
        return returnString;
    }
    /**
     * �õ�һ���û���¼
     */
    public ResultSet getUserByPrimKey(int sysuser_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null||sysuser_id==0) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from sysuser where sysuser_id="+sysuser_id; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        } 
    }
    /**
     * �����û��������ͽ�ɫ�õ��û�ID��
     */
    public long getUserId(int foreign_id,int sysuser_role){
        if(foreign_id==0||sysuser_role==0) return 0;
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 0;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from sysuser where foreign_id="+foreign_id+
           			  " and sysuser_role="+sysuser_role; 
           //System.out.println(sqlString);
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           if(rs.next())
               return rs.getLong("sysuser_id");
           else
               return 0;
        }catch(Exception e){
           e.printStackTrace();
           return 0;
        }            
    }
    /**
     * ����ϵͳ�û���Ϣ
     */
    public int update_sysuser(int user_id,String user_name,String user_password,int user_role){
        if(user_role==0||user_id==0) return 0;
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        user_name=stringCode.codeToString(user_name.trim());
        user_password=stringCode.codeToString(user_password.trim());
        if(dbconn==null) return 0;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="update sysuser set sysuser_name='" + user_name +
 		  			"',sysuser_password='"+user_password+"',sysuser_role="+
 		  			user_role+" where sysuser_id="+user_id;
           //System.out.println(sqlString);
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           return sql.executeUpdate(sqlString);         
        }catch(Exception e){
           e.printStackTrace();
           return 0;
        }
    }
    /**
     * ɾ��һ���û���¼
     */
    public int deleteUserByPrimKey(int sysuser_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null||sysuser_id==0) return 0;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="delete from sysuser where sysuser_id="+sysuser_id; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           return sql.executeUpdate(sqlString);
        }catch(Exception e){
           System.out.print(e);
           return 0;
        } 
    }
    /**
     * �õ�һ���û���¼
     */
    public ResultSet getUserOne(String sysuser_name,String sysuser_password,int sysuser_role){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        sysuser_name=stringCode.codeToString(sysuser_name.trim());
        sysuser_password=stringCode.codeToString(sysuser_password.trim());
        try{
           //-------��ѯ������------------
           sqlString="select * from sysuser where sysuser_name=? and sysuser_password=? and sysuser_role=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setString(1,sysuser_name);
           preSQLSelect.setString(2,sysuser_password);
           preSQLSelect.setInt(3,sysuser_role);
           rs=preSQLSelect.executeQuery();
           System.out.println("select * from sysuser where sysuser_name='"+sysuser_name+"' and sysuser_password='"+ sysuser_password+"' and sysuser_role="+sysuser_role);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        } 
    }
}
