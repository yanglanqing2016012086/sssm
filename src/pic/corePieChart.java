package pic;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.data.general.DefaultPieDataset;

import classman.class_operation;

import core.core_operation;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lessionman.lession_operation;
public class corePieChart extends HttpServlet{
    public void init(ServletConfig config) throws ServletException
    {
      super.init(config);
    }
    public  void  doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        //-------����lession_id��class_id����------------
      	int lession_id=0;
      	int class_id=0;
      	ResultSet rs=null;
    	try{
    	    try{
    	        lession_id=Integer.parseInt(request.getParameter("lession_id"));
    	        class_id=Integer.parseInt(request.getParameter("class_id"));
    	    }catch(Exception e)
    	    {}
            core_operation coreOp=new core_operation();
            rs=coreOp.getClassLessionCore(class_id,lession_id);
            //------����ͼ����Ҫ��ʾ�����ݼ�------
            String coreName[]=new String[]{"","","","",""};
            int coreValueCount[]={0,0,0,0,0};
            getDataFen(rs,coreName,coreValueCount);
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            for(int i=0;i<coreName.length;i++)
                pieDataset.setValue(coreName[i],coreValueCount[i]);
            String titleString=getTilteString(class_id,lession_id);
            //------����ͼ��------
            JFreeChart chart = ChartFactory.createPieChart(titleString,pieDataset,true,true,false);
            //------��ʾͼ��------
            OutputStream out=response.getOutputStream();
            response.setContentType("image/jpg");
            ChartUtilities.writeChartAsJPEG(out,chart,500,300);
        }catch (Exception e) {
            System.out.println("����ͼ��ʧ��.");
            System.out.println(e);
        }
    }
    //---0:60������--1:60-70--2:70-80--3:80-90--4:90-100
    public void getDataFen(ResultSet rs,String[] coreName,int[] coreValueCount){
        try{
            rs.beforeFirst();
            coreName[0]="60������";
            coreName[1]="60-69��";
            coreName[2]="70-79��";
            coreName[3]="80-89��";
            coreName[4]="90-100��";
            float core=0;
            while(rs.next()){
                core=rs.getFloat("core");
                if(core<60) coreValueCount[0]++;
                if(core>=60&&core<70) coreValueCount[1]++;
                if(core>=70&&core<80) coreValueCount[2]++;
                if(core>=80&&core<90) coreValueCount[3]++;
                if(core>=90&&core<=100) coreValueCount[4]++;      
            }
            rs.beforeFirst();
        }catch (Exception e) {
            System.out.println(e);
        }
    }
    //--�õ���ͼ�����ַ���-----------
    public String getTilteString(int class_id,int lession_id){
        String returnString=new String("");
        class_operation classOp=new class_operation();
        lession_operation lessionOp=new lession_operation();
        ResultSet rs=null;
        try{
            if(class_id==0||lession_id==0){
                if(!(lession_id==0&&class_id==0)){
                    if(class_id==0){
                        rs=lessionOp.lession_select_one(lession_id);
                        if(rs.next())
                            returnString="���а༶��"+rs.getString("lession_name")+"�ĳɼ�ͳ�Ʊ�״ͼ";
                    }
                    if(lession_id==0){
                        rs=classOp.class_select_one(class_id);
                        if(rs.next())
                            returnString=rs.getString("class_name")+"�����пγ̵ĳɼ�ͳ�Ʊ�״ͼ";
                    }
                }   
                else
                    returnString="����ѧ�����пγ̳ɼ�ͳ�Ʊ�״ͼ";
            }else{
                rs=classOp.class_select_one(class_id);
                if(rs.next())
                    returnString=rs.getString("class_name")+"��";
                rs=lessionOp.lession_select_one(lession_id);
                if(rs.next())
                    returnString=returnString+rs.getString("lession_name")+"�ĳɼ�ͳ�Ʊ�״ͼ";     
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return returnString;
    }
}
