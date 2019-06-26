package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import db.dbconn;

public class commonTag {
    /**
     * ���ܣ��õ��༶�������ַ���
     * ����������༶ID��
     * ������༶�������ַ���
     */
    public String getClassTag(int class_id){
        String returnString=new String("");
        returnString="<select name='class_id'>";
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn!=null)//���ӳɹ�
            //-------��ѯ������------------
            try{
                sqlString="select * from class"; 
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=sql.executeQuery(sqlString); 
                returnString=returnString+"<option value='0'>==����==</option>";
                if(class_id==0){//���а༶
                    while(rs.next()){
                        returnString=returnString+"<option value='" +
                        		rs.getLong("class_id")+"'>" +
                        		rs.getString("class_name")+"</option>";
                    }
            	}else{//ѡ����ĳһ���༶
            	    while(rs.next()){
                        if(rs.getLong("class_id")==(long)class_id){
                            returnString=returnString+"<option value='" +
                    		rs.getLong("class_id")+"' selected>" +
                    		rs.getString("class_name")+"</option>";
                        }else{
                            returnString=returnString+"<option value='" +
                    		rs.getLong("class_id")+"'>" +
                    		rs.getString("class_name")+"</option>";
                        }
            	    }
            	}
            }catch(Exception e){
                System.out.print(e);
            }
        returnString=returnString+"</select>";
        return returnString;
    }
    /**
     * ���ܣ��õ��γ��������ַ���
     * ����������γ�ID��
     * ������γ��������ַ���
     */
    public String getLessionTag(int lession_id){
        String returnString=new String("");
        returnString="<select name='lession_id'>";
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn!=null)//���ӳɹ�
            //-------��ѯ������------------
            try{
                sqlString="select * from lession"; 
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=sql.executeQuery(sqlString); 
                returnString=returnString+"<option value='0'>==����==</option>";
                if(lession_id==0){//���пγ�
                    while(rs.next()){
                        returnString=returnString+"<option value='" +
                        		rs.getLong("lession_id")+"'>" +
                        		rs.getString("lession_name")+"</option>";
                    }
            	}else{//ѡ����ĳһ���γ�
            	    while(rs.next()){
                        if(rs.getLong("lession_id")==(long)lession_id){
                            returnString=returnString+"<option value='" +
                    		rs.getLong("lession_id")+"' selected>" +
                    		rs.getString("lession_name")+"</option>";
                        }else{
                            returnString=returnString+"<option value='" +
                    		rs.getLong("lession_id")+"'>" +
                    		rs.getString("lession_name")+"</option>";
                        }
            	    }
            	}
            }catch(Exception e){
                System.out.print(e);
            }
        returnString=returnString+"</select>";
        return returnString;
    }
    /**
     * ���ܣ��õ���ʦ�������ַ���
     * �����������ʦID��
     * �������ʦ�������ַ���
     */
    public String getTeacherTag(int teacher_id){
        String returnString=new String("");
        returnString="<select name='teacher_id'>";
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn!=null)//���ӳɹ�
            //-------��ѯ������------------
            try{
                sqlString="select * from teacher"; 
                sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                rs=sql.executeQuery(sqlString); 
                returnString=returnString+"<option value='0'>==����==</option>";
                if(teacher_id==0){//���пγ�
                    while(rs.next()){
                        returnString=returnString+"<option value='" +
                        		rs.getLong("teacher_id")+"'>" +
                        		rs.getString("teacher_name")+"</option>";
                    }
            	}else{//ѡ����ĳһ���γ�
            	    while(rs.next()){
                        if(rs.getLong("teacher_id")==(long)teacher_id){
                            returnString=returnString+"<option value='" +
                    		rs.getLong("teacher_id")+"' selected>" +
                    		rs.getString("teacher_name")+"</option>";
                        }else{
                            returnString=returnString+"<option value='" +
                    		rs.getLong("teacher_id")+"'>" +
                    		rs.getString("teacher_name")+"</option>";
                        }
            	    }
            	}
            }catch(Exception e){
                System.out.print(e);
            }
        returnString=returnString+"</select>";
        return returnString;
    }    
    /**
     * ���ܣ��õ���ɫ�������ַ���
     * �����������ɫID��
     * �������ɫ�������ַ���
     */
    public String getRoleTag(){
        String returnString=new String("");
        returnString="<select name='role_id'>";
        returnString=returnString+"<option value='1'>ϵͳ����Ա</option>";
        returnString=returnString+"<option value='2'>�������Ա</option>";
        returnString=returnString+"</select>";
        return returnString;
    }      
}
