package studentman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.stringUtil;
import db.dbconn;
/**
 * ��װ��ѧ��������в���
 */
public class student_operation {
    /**
     * ����:��ѧ��������һ����¼
     * �������:student_nameΪѧ������
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3��˰༶�Ѵ��ڣ�����4���������class_nameΪ��;
     */
    public int student_add_one(String student_name,int class_id){
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        //--------�����������ȷ---------
        if(student_name==null||student_name.trim().length()==0||class_id==0)
           return 4;
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        student_name=stringCode.codeToString(student_name.trim());
        //--------�����¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from student where student_name=? and class_id=?";
        try{   
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setString(1,student_name);
           preSQLSelect.setInt(2,class_id);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ�ѧ��
           sqlString="insert into student(student_name,class_id) values(?,?)";
           PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
           preSQLInsert.setString(1,student_name);
           preSQLInsert.setInt(2,class_id);  
           preSQLInsert.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }             
    }
    /**
     * ����:��ѯ�����е�ѧ����Ϣ
     * �����������
     * ���:����ѧ���ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet student_select_all(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select distinct student.student_id as student_id," +
           		"student.student_name as student_name," +
           		"student.class_id as class_id," +
           		"class.class_name as class_name from student,class " +
           		"where class.class_id=student.class_id"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:����������ѯ��ѧ������Ϣ
     * ���������class_idΪ�༶ID�ţ�student_nameΪѧ��������֧��ģ����ѯ��
     * ���:��ѯ����ѧ���ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet student_select_part(int class_id,String student_name){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select distinct student.student_id as student_id," +
          	   	"student.student_name as student_name," +
          	    "student.class_id as class_id," +
          	   	"class.class_name as class_name from student,class " +
          	   	"where class.class_id=student.class_id"; 
           if(class_id!=0)
               sqlString=sqlString+" and class.class_id="+class_id+" and " +
               		"student.class_id="+class_id;
           if(student_name!=null&&student_name.trim().length()!=0)
               sqlString=sqlString+" and student.student_name like '%" +
               		""+student_name+"%'";    
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ���ܣ�ɾ��һ��ѧ������Ϣ
     * ���������ѧ����ID��
     * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
     * ����4���������student_idΪ0���������������ȷ
     */
    public int student_delete(int student_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(student_id==0) return 4;//�����������ȷ
        //---------ɾ������--------------
        sqlString="delete from student where student_id=?";
        try{
            PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
            preSQLDelete.setInt(1,student_id);
            preSQLDelete.executeUpdate();
            return 1;
          }catch(Exception e){
            System.out.print(e);
            return 2;
          }   
    }
    /**
     * ����:��ѯ��ĳһѧ������Ϣ
     * �������:ѧ����ID
     * ���:����ѧ����¼,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet student_select_one(int student_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        if(student_id==0) return null;//�����������ȷ
        try{
           //-------��ѯ������------------
           sqlString="select * from student where student_id=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setInt(1,student_id);
           rs=preSQLSelect.executeQuery();
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:����ѧ�����һ����¼
     * �������:student_idΪѧ����ţ�student_nameΪѧ������,class_idΪ�����༶ID��
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
     */
    public int student_update(int student_id,String student_name,int class_id){
        String sqlString=null;//SQL����ַ���
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(student_id==0||class_id==0||student_name.trim().length()==0){//�����������ȷ
            return 4;
        }
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        student_name=stringCode.codeToString(student_name.trim());
        //--------���¼�¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from student where student_name=? and class_id=? and student_id<>"+student_id;
        try{   
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setString(1,student_name);
            preSQLSelect.setInt(2,class_id);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 3;//�Ѵ��ڴ�ѧ��
            //--------���¼�¼-----------
            sqlString="update student set student_name=?,class_id=? where student_id=?";
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setString(1,student_name);
            preSQLUpdate.setInt(2,class_id);
            preSQLUpdate.setInt(3,student_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }     
    }  
}
