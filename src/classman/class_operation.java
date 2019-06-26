
package classman;
import db.dbconn;
import util.stringUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 * ��װ�԰༶������в���
 */
public class class_operation{
   /**
    * ����:���༶������һ����¼
    * �������:class_nameΪ�༶����
    * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
    * ����3��˰༶�Ѵ��ڣ�����4���������class_nameΪ��;
    */
   public int class_add_one(String class_name){
       dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
       String sqlString=null;//SQL����ַ���
       ResultSet rs=null;//�����¼��
       Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
       if(dbconn==null) return 2;//����ʧ��
       //--------�����������ȷ---------
       if(class_name==null||class_name.trim().length()==0)
          return 4;
       //--------�����������ת��-------
       stringUtil stringCode=new stringUtil();
       class_name=stringCode.codeToString(class_name.trim());
       //--------�����¼�����ж��Ƿ��Ѵ���------
       sqlString="select * from class where class_name=?";
       try{
          PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
          preSQLSelect.setString(1,class_name);
          rs=preSQLSelect.executeQuery();
          if(rs.next()) return 3;//�Ѵ��ڴ˰༶
          sqlString="insert into class(class_name) values(?)";
          PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
          preSQLInsert.setString(1,class_name);
          preSQLInsert.executeUpdate();
          return 1;
        }catch(Exception e){
          System.out.print(e);
          return 2;
        }             
   }
   /**
    * ����:��ѯ�����еİ༶
    * �����������
    * ���:���а༶�ļ�¼��,���û�м�¼�����ʧ�ܷ���null
    */
   public ResultSet class_select_all(){
       String sqlString=null;//SQL����ַ���
       Statement sql=null;//SQL������
       ResultSet rs=null;//�����¼��
       dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
       Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
       if(dbconn==null) return null;//����ʧ��
       try{
          //-------��ѯ������------------
          sqlString="select * from class"; 
          sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
          rs=sql.executeQuery(sqlString);
          return rs;
       }catch(Exception e){
          System.out.print(e);
          return null;
       }  
   }
   /**
    * ���ܣ�ɾ��һ���༶����Ϣ
    * ����������༶��ID��
    * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
    * ����4���������class_idΪ0���������������ȷ
    */
   public int class_delete(int class_id){
       String sqlString=null;//SQL����ַ���
       ResultSet rs=null;//�����¼��
       dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
       Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
       if(dbconn==null) return 2;//����ʧ��
       if(class_id==0) return 4;//�����������ȷ
       //---------ɾ������--------------
       sqlString="delete from class where class_id=?";
       try{
           PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
           preSQLDelete.setInt(1,class_id);
           preSQLDelete.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }   
   }
   /**
    * ����:��ѯ��ĳһ�༶����Ϣ
    * �������:�༶��ID
    * ���:�����༶��¼,���û�м�¼�����ʧ�ܷ���null
    */
   public ResultSet class_select_one(int class_id){
       String sqlString=null;//SQL����ַ���
       ResultSet rs=null;//�����¼��
       dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
       Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
       if(dbconn==null) return null;//����ʧ��
       if(class_id==0) return null;//�����������ȷ
       try{
          //-------��ѯ������------------
          sqlString="select * from class where class_id=?"; 
          PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
          preSQLSelect.setInt(1,class_id);
          rs=preSQLSelect.executeQuery();
          return rs;
       }catch(Exception e){
          System.out.print(e);
          return null;
       }  
   }
   /**
    * ����:���°༶���һ����¼
    * �������:class_idΪ�༶�ţ�class_nameΪ�༶����
    * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
    * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
    */
   public int class_update(int class_id,String class_name){
       String sqlString=null;//SQL����ַ���
       ResultSet rs=null;//�����¼��
       dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
       Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
       if(dbconn==null) return 2;//����ʧ��
       if(class_id==0||class_name.trim().length()==0) return 4;//�����������ȷ
       //--------�����������ת��-------
       stringUtil stringCode=new stringUtil();
       class_name=stringCode.codeToString(class_name.trim());
       //--------�����¼�����ж��Ƿ��Ѵ���------
       sqlString="select * from class where class_name=? and class_id<>"+class_id;
       try{
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setString(1,class_name);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ˰༶
           //--------���¼�¼-----------
           sqlString="update class set class_name=? where class_id=?";
           PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
           preSQLUpdate.setString(1,class_name);
           preSQLUpdate.setInt(2,class_id);
           preSQLUpdate.executeUpdate();
           return 1;
       }catch(Exception e){
           System.out.print(e);
           return 2;
       }     
   }  
}
