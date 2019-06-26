package teacherman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import util.stringUtil;
import db.dbconn;
/**
 * ��װ�Խ�ʦ������в���
 */
public class teacher_operation {
    /**
     * ����:����ʦ������һ����¼
     * �������:teacher_nameΪ��ʦ����
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3��˰༶�Ѵ��ڣ�����4���������class_nameΪ��;
     */
    public int teacher_add_one(String teacher_name){
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        //--------�����������ȷ---------
        if(teacher_name==null||teacher_name.trim().length()==0)
           return 4;
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        teacher_name=stringCode.codeToString(teacher_name.trim());
        //--------�����¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from teacher where teacher_name=?";
        try{
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setString(1,teacher_name);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ˽�ʦ
           sqlString="insert into teacher(teacher_name) values(?)";
           PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
           preSQLInsert.setString(1,teacher_name);
           preSQLInsert.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }             
    }
    /**
     * ����:��ѯ�����еĽ�ʦ
     * �����������
     * ���:���н�ʦ�ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet teacher_select_all(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from teacher"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ���ܣ�ɾ��һ����ʦ����Ϣ
     * �����������ʦ��ID��
     * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
     * ����4���������teacher_idΪ0���������������ȷ
     */
    public int teacher_delete(int teacher_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(teacher_id==0) return 4;//�����������ȷ
        //---------ɾ������--------------
        sqlString="delete from teacher where teacher_id=?";
        try{
            PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
            preSQLDelete.setInt(1,teacher_id);
            preSQLDelete.executeUpdate();
            return 1;
          }catch(Exception e){
            System.out.print(e);
            return 2;
          }   
    }
    /**
     * ����:��ѯ��ĳһ��ʦ����Ϣ
     * �������:��ʦ��ID
     * ���:������ʦ��¼,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet teacher_select_one(int teacher_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        if(teacher_id==0) return null;//�����������ȷ
        try{
           //-------��ѯ������------------
           sqlString="select * from teacher where teacher_id=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setInt(1,teacher_id);
           rs=preSQLSelect.executeQuery();
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:���½�ʦ���һ����¼
     * �������:teacher_idΪ��ʦ�ţ�teacher_nameΪ��ʦ����
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
     */
    public int teacher_update(int teacher_id,String teacher_name){
        String sqlString=null;//SQL����ַ���
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(teacher_id==0||teacher_name.trim().length()==0) return 4;//�����������ȷ
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        teacher_name=stringCode.codeToString(teacher_name.trim());
        //--------���¼�¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from teacher where teacher_name=? and teacher_id<>"+teacher_id;
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setString(1,teacher_name);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 3;//�Ѵ��ڴ˽�ʦ
            //--------���¼�¼-----------
            sqlString="update teacher set teacher_name=? where teacher_id=?";
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setString(1,teacher_name);
            preSQLUpdate.setInt(2,teacher_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }     
    }  
    /**
     * ����:����������ѯ��������ʦ����Ϣ
     * �����������ʦ����
     * ���:���ֽ�ʦ�ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     * ˵��:֧��ģ����ѯ
     */
    public ResultSet teacher_select_part_by_name(String teacher_name){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        if(teacher_name==null||teacher_name.trim().length()==0)
            return teacher_select_all();
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from teacher where teacher_name like '%"+teacher_name+"%'"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
}
