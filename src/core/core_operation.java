package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import db.dbconn;

/**
 * ��װ��ѧ���ɼ���Ĳ���
 */
public class core_operation {
    /**
     * ����:��ѧ���ɼ�������һ����¼
     * �������::lession_idΪ�γ�ID,student_idΪѧ��ID
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���ѧ���ɼ���Ϣ�Ѵ��ڣ�����4���������lession_id��student_idΪ��;
     */
    public int core_add_one(int lession_id,long student_id){
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        //--------�����������ȷ---------
        if(lession_id==0||student_id==0)
           return 4;
        //--------�����¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from core where lession_id=? and student_id=?";
        try{   
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setInt(1,lession_id);
           preSQLSelect.setLong(2,student_id);
           rs=preSQLSelect.executeQuery();
           if(rs.next()) return 3;//�Ѵ��ڴ˽�ʦ�ڿμ�¼
           sqlString="insert into core(lession_id,student_id) values(?,?)";
           PreparedStatement preSQLInsert=dbconn.prepareStatement(sqlString);
           preSQLInsert.setInt(1,lession_id);
           preSQLInsert.setLong(2,student_id);  
           preSQLInsert.executeUpdate();
           return 1;
         }catch(Exception e){
           System.out.print(e);
           return 2;
         }             
    }
    /**
     * ����:��ѯ�����е�ѧ���ɼ���Ϣ
     * �����������
     * ���:����ѧ���ɼ��ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet core_select_all(){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select distinct core.core_id as core_id," +
           		"core.student_id as student_id," +
           		"core.lession_id as lession_id," +
           		"student.student_name as student_name," +
           		"lession.lession_name as lession_name"+
           		" from core,student,lession " +
           		"where lession.lession_id=core.lession_id" +
           		" and student.student_id=core.student_id"; 
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:����������ѯ��ѧ���ɼ�����Ϣ
     * ���������lession_idΪ�γ�ID�ţ�student_idΪѧ��ID��
     * ���:��ѯ����Ҫ��ѧ���ɼ��ļ�¼��,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet core_select_part(int lession_id,long student_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
            sqlString="select distinct core.core_id as core_id," +
       			"core.student_id as student_id," +
       			"core.lession_id as lession_id," +
       			"student.student_name as student_name," +
       			"lession.lession_name as lession_name"+
       			" from core,student,lession " +
       			"where lession.lession_id=core.lession_id" +
       			" and student.student_id=core.student_id"; 
           if(lession_id!=0)
               sqlString=sqlString+" and lession.lession_id="+lession_id+" and " +
               		"core.lession_id="+lession_id;
           if(student_id!=0)
               sqlString=sqlString+" and student.student_id="+student_id+" and " +
               		"core.student_id="+student_id;   
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ���ܣ�ɾ��һ��ѧ���ɼ����е���Ϣ
     * ���������ѧ���ɼ����е�ID��
     * ���������1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܣ�
     * ����4���������core_idΪ0���������������ȷ
     */
    public int core_delete(long core_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(core_id==0) return 4;//�����������ȷ
        //---------ɾ������--------------
        sqlString="delete from core where core_id=?";
        try{
            PreparedStatement preSQLDelete=dbconn.prepareStatement(sqlString);
            preSQLDelete.setLong(1,core_id);
            preSQLDelete.executeUpdate();
            return 1;
          }catch(Exception e){
            System.out.print(e);
            return 2;
          }   
    }
    /**
     * ����:��ѯ��ĳһ��ѧ���ɼ����е���Ϣ
     * �������:ѧ���ɼ����е�ID��
     * ���:����ѧ���ɼ��еļ�¼,���û�м�¼�����ʧ�ܷ���null
     */
    public ResultSet core_select_one(long core_id){
        String sqlString=null;//SQL����ַ���
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        if(core_id==0) return null;//�����������ȷ
        try{
           //-------��ѯ������------------
           sqlString="select * from core where core_id=?"; 
           PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
           preSQLSelect.setLong(1,core_id);
           rs=preSQLSelect.executeQuery();
           return rs;
        }catch(Exception e){
           System.out.print(e);
           return null;
        }  
    }
    /**
     * ����:����ѧ���ɼ����һ����¼
     * �������:core_idΪѧ���ɼ���ţ�lession_idΪ�γ�ID,student_idΪѧ��ID
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����3���¼�Ѵ��ڣ�����4���������Ϊ�ջ���ȷ;
     */
    public int core_update(long core_id,int lession_id,long student_id){
        String sqlString=null;//SQL����ַ���
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(core_id==0||lession_id==0||student_id==0){//�����������ȷ
            return 4;
        }
        //--------���¼�¼�����ж��Ƿ��Ѵ���------
        sqlString="select * from core where lession_id=? and student_id=? and core_id<>"+core_id;
        try{
            PreparedStatement preSQLSelect=dbconn.prepareStatement(sqlString);
            preSQLSelect.setInt(1,lession_id);
            preSQLSelect.setLong(2,student_id);
            rs=preSQLSelect.executeQuery();
            if(rs.next()) return 3;//�Ѵ��ڴ�ѧ���ɼ���¼
            //--------���¼�¼-----------
            sqlString="update core set lession_id=?,student_id=? where core_id=?";
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setInt(1,lession_id);
            preSQLUpdate.setLong(2,student_id);
            preSQLUpdate.setLong(3,core_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }     
    }  
    /**
     * ����:����ѧ��ID���γ�ID�õ�ѧ���ɼ�
     * �������:student_idѧ��ID��,lession_idΪ�γ�ID��
     * �������:�ɼ��ַ���,���Ҳ���������ء���
     */
    public String getStudent_lession_core(long student_id,int lession_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        ResultSet rs=null;//�����¼��
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return "";//����ʧ��
        if(student_id==0||lession_id==0) return "";//�����������ȷ
        sqlString="select core from core where lession_id="+lession_id+" and" +
        		" student_id="+student_id;
        try{
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);
            if(rs.next()) return(rs.getString("core"));
            else return "";
        }catch(Exception e){
            System.out.print(e);
            return "";
        }      	
    }
    /**
     * ����:��������ѧ���ɼ�
     * ���룺refNameΪҪ�޸ĵ�ѧ��ID�����飬refValueΪѧ��ID�����������Ӧ�ĳɼ�ֵ��
     *      lession_idΪҪ�޸ĳɼ��Ŀγ�ID��
     * ������޸ĳɹ�������Ϣ��ʾ�ַ���
     */
    public String saveStudent_core(String refName[],String refValue[],int lession_id){
        String returnString=new String("");
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return returnString;//����ʧ��
        if(refName.length==0||refValue.length==0||lession_id==0)//û��ѧ���ɼ�����
            return returnString;
        PreparedStatement preSQLUpdate=null;//����SQL������
        for(int i=0;i<refName.length;i++){
            if(refName[i]!=null&&refValue[i]!=null)
              try{
                float core=Float.parseFloat(refValue[i]);
                if(core>=0&&core<=100){//����ɼ����ݺϷ�
                    sqlString="select * from core where lession_id=" +
            		""+lession_id+" and student_id="+refName[i];
                    sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    rs=sql.executeQuery(sqlString);
                    if(rs.next()){
                        sqlString="update core set core=? where lession_id=" +
                        	""+lession_id+" and student_id="+refName[i];
                        sqlString=sqlString+" and close_status=0";
                        preSQLUpdate=dbconn.prepareStatement(sqlString);
                        preSQLUpdate.setFloat(1,Float.parseFloat(refValue[i]));
                        int updateCount=preSQLUpdate.executeUpdate();
                        if(updateCount==1)
                            returnString=returnString+"�������Ϊ"+refName[i]+"�Ŀγ̳ɼ��ɹ�!<br>";
                        else
                            returnString=returnString+"�������Ϊ"+refName[i]+"�Ŀγ̳ɼ�ʧ��!ԭ��:״̬������!<br>";
                    }else{
                        sqlString="insert into core(lession_id,student_id,core) values(" +
                			""+lession_id+","+refName[i]+","+refValue[i]+")";
                        preSQLUpdate=dbconn.prepareStatement(sqlString);
                        preSQLUpdate.executeUpdate();
                        returnString=returnString+"�������Ϊ"+refName[i]+"�Ŀγ̳ɼ��ɹ�!<br>";
                    }
                    System.out.println(sqlString);
                }
                else{
                    returnString=returnString+"�����������Ϊ"+refName[i]+"�Ŀγ̳ɼ�ʧ��,ԭ��:�������ݷǷ�!<br>";
                }
              }catch(Exception e){
                System.out.println(e);
                System.out.println(sqlString);
                returnString=returnString+"�������Ϊ"+refName[i]+"�Ŀγ̳ɼ�ʧ��!<br>";
              }    
        }
        return returnString;
    }
    /**
     * ���ܣ�ѧ����ѧ���ɼ���ѯ����
     * ���룺class_idΪѧ�����ڰ༶ID�ţ�student_nameΪѧ������
     * �������ѧ���ĳɼ���¼��
     * ˵����ֻ�ܲ�ѯ��һ��ѧ���ĳɼ�
     */
    public ResultSet student_core_view(int class_id,String student_name){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        if(student_name==null||student_name.length()==0)
            return null;
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        try{
           //-------��ѯ������------------
           sqlString="select student.student_name as student_name," +
           		"lession.lession_name as lession_name,core.core as core" +
           		" from student,core,lession,class" +
           		" where core.student_id=student.student_id and" +
           		" core.lession_id=lession.lession_id"; 
           if(class_id!=0)
               sqlString=sqlString+" and class.class_id="+class_id+" and " +
               		"student.class_id="+class_id;
           if(student_name!=null&&student_name.trim().length()!=0)
               sqlString=sqlString+" and student.student_name='" +
               		""+student_name+"'";    
           sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
           //System.out.println(sqlString);
           rs=sql.executeQuery(sqlString);
           return rs;
        }catch(Exception e){
           e.printStackTrace();
           return null;
        }  
    }
    /**
     * ���ܣ��õ��༶ĳ���ε����гɼ�
     * @param class_id
     * @param lession_id
     * @return
     */
    public ResultSet getClassLessionCore(int class_id,int lession_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        sqlString="select * from core";
        if(class_id==0||lession_id==0){
            if(!(lession_id==0&&class_id==0)){
                if(class_id==0)
                    sqlString=sqlString+" where lession_id="+lession_id;
                if(lession_id==0)
                    sqlString="select core.core as core from core,student " +
                    		" where student.student_id=core.student_id" +
                    		" and student.class_id="+class_id;
            }        
        }else{
            sqlString="select core.core as core from core,student " +
    			" where student.student_id=core.student_id" +
    			" and student.class_id="+class_id +"" +
    			" and core.lession_id="+lession_id;
        }
        try{  
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);
            return rs;
        }catch(Exception e){
            System.out.print(e);
            return null;
         } 
        
    }
}
