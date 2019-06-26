
package lessionman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import db.dbconn;
/**
 * ���ܣ���װ�԰༶��Ϳγ̱�����ϲ���
 */
public class classlession_operation {
    /**
     * ����:�õ�����¼���˳ɼ��İ༶��γ�����
     * ����:class_idΪ�༶ID��,lession_idΪ�γ�ID��
     * ���:�гɼ��İ༶��γ����ݼ�¼��
     */
    public ResultSet getCoreClassLessionRs(int class_id,int lession_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return null;//����ʧ��
        sqlString="select distinct core.lession_id as lession_id," +
        		"class.class_name as class_name,student.class_id as class_id," +
        		"lession.lession_name from core,student,lession,class" +
        		" where core.student_id=student.student_id and" +
        		" student.class_id=class.class_id and lession.lession_id=core.lession_id";
        if(class_id!=0)
            sqlString=sqlString+" and student.class_id="+class_id+"" +
            		" and class.class_id="+class_id;
        if(lession_id!=0)
            sqlString=sqlString+" and lession.lession_id="+lession_id+"" +
            		" and core.lession_id="+lession_id;
        sqlString=sqlString+" order by class_id";
        try{
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString);       
        }catch(Exception e){
            System.out.println(e);
        }
        return rs;
    }
    /**
     * ����:�õ��༶��ĳ�γ̵ķ��״̬
     * ����:class_idΪ�༶ID��,lession_idΪ�γ�ID��
     * ���:���״̬�ַ���
     * ��ʾ��ֻ�ڰ༶������ѧ���ĳɼ����õ��������ǳɹ����״̬
     */
    public String getClassLessionCloseStatus(int class_id,int lession_id){
        String returnString=new String("");
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return returnString;//����ʧ��
        if(class_id==0||lession_id==0)//����������Ϸ�
            return returnString;
        sqlString="select distinct core.lession_id as lession_id,student.class_id as class_id," +
        		"core.close_status as close_status from core,student " +
        		"where core.student_id=student.student_id and student.class_id="+class_id+
        		" and core.lession_id="+lession_id;
        try{
            sql=dbconn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=sql.executeQuery(sqlString); 
            rs.last();
            int rowCount=rs.getRow();
            rs.beforeFirst();
            switch(rowCount){
            	case 0:
            	    returnString="û�д˰༶�˿γ̵ĳɼ�����";break;
            	case 1://�鵽һ����¼,���ֶ���ȡ״̬
            	    rs.next();
                    switch(rs.getInt("close_status")){
                    	case 0:
                    	    returnString="δ���";break;
                    	case 1:
                    	    returnString="�ѷ��";break;
                    	case 2:
                    	    returnString="���ַ��";break;
                    	default:
                    	    returnString="δ֪";break;
                    }
                    break;
                case 2://�鵽2��,˵�����δ��ȫ
                    returnString="���ַ��";break;
                default:
                    returnString="δ֪";break;
            }
        }catch(Exception e){
            System.out.println(e);
            returnString="δ֪";
        }
        return returnString;         
    }
    /**
     * ����:��ĳ��ĳ�γ̵�״̬Ϊ���
     * ����:class_idΪ�༶ID��,lession_idΪ�γ�ID��
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����4�������������ȷ;
     */
    public int classLessionCloseSave(int class_id,int lession_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(class_id==0||lession_id==0)//����������Ϸ�
            return 4;
        //-------����״̬Ϊ���----------
        sqlString="update core set close_status=1" +
        		" where lession_id=? and student_id in" +
        		" (select student_id from student where class_id=?)";
        try{
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setInt(1,lession_id);
            preSQLUpdate.setInt(2,class_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }   
    }
    /**
     * ����:��ĳ��ĳ�γ̵�״̬Ϊ���
     * ����:class_idΪ�༶ID��,lession_idΪ�γ�ID��
     * ���:����1��ɹ�������2���������Ӳ������ò���ȷ���������ݿ�ʧ�ܻ����ݿ����ʧ�ܣ�
     * ����4�������������ȷ;
     */
    public int classLessionOpenSave(int class_id,int lession_id){
        String sqlString=null;//SQL����ַ���
        Statement sql=null;//SQL������
        ResultSet rs=null;//�����¼��
        dbconn dbconnOBject=new dbconn();//���ݿ����Ӷ���
        Connection dbconn=dbconnOBject.getDBConn();//�õ����ݿ�����
        if(dbconn==null) return 2;//����ʧ��
        if(class_id==0||lession_id==0)//����������Ϸ�
            return 4;
        //-------����״̬Ϊ���----------
        sqlString="update core set close_status=0" +
        		" where lession_id=? and student_id in" +
        		" (select student_id from student where class_id=?)";
        try{
            PreparedStatement preSQLUpdate=dbconn.prepareStatement(sqlString);
            preSQLUpdate.setInt(1,lession_id);
            preSQLUpdate.setInt(2,class_id);
            preSQLUpdate.executeUpdate();
            return 1;
        }catch(Exception e){
            System.out.print(e);
            return 2;
        }   
    }
}
