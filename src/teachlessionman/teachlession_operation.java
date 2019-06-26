package teachlessionman;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import db.dbconn;
/**
 * ��װ�Խ�ʦ�ڿα�����в���
 */
public class teachlession_operation {
    /**
     * ����:����ʦ�ڿα�����һ����¼
     * �������:lession_idΪ�γ�ID,teacher_idΪ��ʦID
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3��˽�ʦ�ڿ���Ϣ�Ѵ��ڣ�����4���������lession_id��teacher_idΪ��;
     */
    public int teachlession_add_one(int lession_id,int teacher_id){
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        //--------�����������ȷ---------
        if(lession_id==0||teacher_id==0)
           return 4;
        //--------�����¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from teachlession where lession_id=? and teacher_id=?";
        try{   
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setInt(1,lession_id);
           preSQLSelect.setInt(2,teacher_id);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ˽�ʦ�ڿμ�¼
           sqlString="insert into teachlession(lession_id,teacher_id) values(?,?)";
           PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
           preSQLInsert.setInt(1,lession_id);
           preSQLInsert.setInt(2,teacher_id);  
           preSQLInsert.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }             
    }
    /**
     * ����:��ѯ�����еĽ�ʦ�ڿ���Ϣ
     * �����������
     * ���:���н�ʦ�ڿεļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet teachlession_select_all(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select distinct teachlession.teachlession_id as teachlession_id," +
           		"teachlession.teacher_id as teacher_id," +
           		"teachlession.lession_id as lession_id," +
           		"teacher.teacher_name as teacher_name," +
           		"lession.lession_name as lession_name"+
           		" from teachlession,teacher,lession " +
           		"where lession.lession_id=teachlession.lession_id" +
           		" and teacher.teacher_id=teachlession.teacher_id"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:����������ѯ����ʦ�ڿε���Ϣ
     * ���������lession_idΪ�γ�ID�ţ�teacher_idΪ��ʦID��
     * ���:��ѯ��Ҫ�Ľ�ʦ�ڿεļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet teachlession_select_part(int lession_id,int teacher_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
            sqlString="select distinct teachlession.teachlession_id as teachlession_id," +
       			"teachlession.teacher_id as teacher_id," +
       			"teachlession.lession_id as lession_id," +
       			"teacher.teacher_name as teacher_name," +
       			"lession.lession_name as lession_name"+
       			" from teachlession,teacher,lession " +
       			"where lession.lession_id=teachlession.lession_id" +
       			" and teacher.teacher_id=teachlession.teacher_id"; 
           if(lession_id!=0)
               sqlString=sqlString+" and lession.lession_id="+lession_id+" and " +
               		"teachlession.lession_id="+lession_id;
           if(teacher_id!=0)
               sqlString=sqlString+" and teacher.teacher_id="+teacher_id+" and " +
               		"teachlession.teacher_id="+teacher_id;   
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ���ܣ�ɾ��һ����ʦ�ڿα��е���Ϣ
     * �����������ʦ�ڿα��е�ID��
     * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
     * ����4���������teachlession_idΪ0���������������ȷ
     */
    public int teachlession_delete(long teachlession_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(teachlession_id==0) return 4;//�����������ȷ
        //---------ɾ������--------------
        sqlString="delete from teachlession where teachlession_id=?";
        try{
            PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
            preSQLDelete.setLong(1,teachlession_id);
            preSQLDelete.executeUpdate();
            return 1;
          }catch(Exception e){
            System.out.print(e);
            return 2;
          }   
    }
    /**
     * ����:��ѯ��ĳһ����ʦ�ڿα��е���Ϣ
     * �������:��ʦ�ڿα��е�ID��
     * ���:������ʦ�ڿα��еļ�¼,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet teachlession_select_one(long teachlession_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        if(teachlession_id==0) return null;//�����������ȷ
        try{
           //-------��ѯ������------------
           sqlString="select * from teachlession where teachlession_id=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setLong(1,teachlession_id);
           rs=preSQLSelect.executeQuery();
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:���½�ʦ�ڿα��һ����¼
     * �������:teachlession_idΪ��ʦ�ڿ���ţ�lession_idΪ�γ�ID,teacher_idΪ��ʦID
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
     */
    public int teachlession_update(long teachlession_id,int lession_id,int teacher_id){
        String sqlString=null;//SQL����ַ���
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(teachlession_id==0||lession_id==0||teacher_id==0){//�����������ȷ
            return 4;
        }
        //--------���¼�¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from teachlession where lession_id=? and teacher_id=? and teachlession_id<>"+teachlession_id;
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setInt(1,lession_id);
            preSQLSelect.setInt(2,teacher_id);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 3;//�Ѵ��ڴ˽�ʦ�ڿμ�¼
            //--------���¼�¼-----------
            sqlString="update teachlession set lession_id=?,teacher_id=? where teachlession_id=?";
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setInt(1,lession_id);
            preSQLUpdate.setInt(2,teacher_id);
            preSQLUpdate.setLong(3,teachlession_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }     
    }  
}
