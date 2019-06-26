package lessionman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import util.stringUtil;
import db.dbconn;
/**
 * ��װ�Կγ̱�����в���
 */
public class lession_operation {
    /**
     * ����:���γ̱�����һ����¼
     * �������:lession_nameΪ�γ�����
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3��˰༶�Ѵ��ڣ�����4���������class_nameΪ��;
     */
    public int lession_add_one(String lession_name){
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        //--------�����������ȷ---------
        if(lession_name==null||lession_name.trim().length()==0)
           return 4;
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        lession_name=stringCode.codeToString(lession_name.trim());
        //--------�����¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from lession where lession_name=?";
        try{
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setString(1,lession_name);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ˿γ�
           sqlString="insert into lession(lession_name) values(?)";
           PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
           preSQLInsert.setString(1,lession_name);
           preSQLInsert.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }             
    }
    /**
     * ����:��ѯ�����еĿγ�
     * �����������
     * ���:���пγ̵ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet lession_select_all(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select * from lession"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ���ܣ�ɾ��һ���γ̵���Ϣ
     * ����������γ̵�ID��
     * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
     * ����4���������lession_idΪ0���������������ȷ
     */
    public int lession_delete(int lession_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(lession_id==0) return 4;//�����������ȷ
        //---------ɾ������--------------
        sqlString="delete from lession where lession_id=?";
        try{
            PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
            preSQLDelete.setInt(1,lession_id);
            preSQLDelete.executeUpdate();
            return 1;
          }catch(Exception e){
            System.out.print(e);
            return 2;
          }   
    }
    /**
     * ����:��ѯ��ĳһ�γ̵���Ϣ
     * �������:�γ̵�ID
     * ���:�����γ̼�¼,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet lession_select_one(int lession_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        if(lession_id==0) return null;//�����������ȷ
        try{
           //-------��ѯ������------------
           sqlString="select * from lession where lession_id=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setInt(1,lession_id);
           rs=preSQLSelect.executeQuery();
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:���¿γ̱��һ����¼
     * �������:lession_idΪ�γ̺ţ�lession_nameΪ�γ�����
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
     */
    public int lession_update(int lession_id,String lession_name){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(lession_id==0||lession_name.trim().length()==0) return 4;//�����������ȷ
        //--------�����������ת��-------
        stringUtil stringCode=new stringUtil();
        lession_name=stringCode.codeToString(lession_name.trim());
        //--------���¼�¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from lession where lession_name=? and lession_id<>"+lession_id;
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setString(1,lession_name);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 3;//�Ѵ��ڴ˿γ�
            //--------���¼�¼-----------
            sqlString="update lession set lession_name=? where lession_id=?";
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setString(1,lession_name);
            preSQLUpdate.setInt(2,lession_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }     
    }  
}
