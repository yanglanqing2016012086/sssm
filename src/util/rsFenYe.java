
package util;
import java.sql.ResultSet;
public class rsFenYe {
    ResultSet rs=null;//Ҫ��ҳ����ļ�¼��
    int currentPage=1;//��ǰҳ��
    int pageSize=10;//ÿҳ�ļ�¼������Ĭ��Ϊ10
    /**
     * �õ���ҳ�ִ������Ѽ�¼ָ���ƶ�����ǰҳ�ĵ�һ����¼��ǰ��λ��
     * ������������ص�JSPҳ��,refName����Ϊ�������м�currentPage֮��ģ���������refValueΪ��Ӧ��ֵ
     * ����������ַ�������2ҳ����ǰ��1ҳ  ��һҳ ĩҳ��
     */
    public String earn_fenyi_string(String returnJSP,String[] refName,String[] refValue){
        String returnString=new String("");
        if(rs==null) return(returnString);
        int pageCount=0;//��ҳ��
        int rowCount=0;//rs���ܼ�¼����
        if(pageSize<=0) //ÿҳ��¼��������ȷ
            return(returnString);
        try{
            //------�õ��ܼ�¼����------
            rs.last();
            rowCount=rs.getRow();
            rs.beforeFirst();
            //------��¼ָ�붨λ--------
            int RecordPosition=(currentPage-1)*pageSize;
            if(RecordPosition==0) rs.beforeFirst();
            else rs.absolute(RecordPosition);
        }catch(Exception e){
            System.out.println(e);
            return(returnString);
        }
        //---------�õ���ҳ��----------
        if(rowCount%pageSize==0) pageCount=rowCount/pageSize;
        else pageCount=rowCount/pageSize+1;
        //---------�õ������ַ���--------
        returnString="��"+pageCount+"ҳ����ǰ��"+currentPage+"ҳ&nbsp;&nbsp;";
        if(currentPage!=1&&pageCount!=0){//������ҳ����ҳ����Ϊ0
            returnString=returnString+"<a href='"+returnJSP+"?currentPage=1";
            for(int i=0;i<refName.length;i++)
                returnString=returnString+"&"+refName[i]+"="+refValue[i];
            returnString=returnString+"'>��ҳ</a>&nbsp;";
            returnString=returnString+"<a href='"+returnJSP+"?currentPage="+(currentPage-1);
            for(int i=0;i<refName.length;i++)
                returnString=returnString+"&"+refName[i]+"="+refValue[i];
            returnString=returnString+"'>��һҳ</a>&nbsp;";
        }
        if(currentPage!=pageCount&&pageCount!=0){//����ĩҳ����ҳ����Ϊ0
            returnString=returnString+"<a href='"+returnJSP+"?currentPage="+(currentPage+1);
            for(int i=0;i<refName.length;i++)
                returnString=returnString+"&"+refName[i]+"="+refValue[i];
            returnString=returnString+"'>��һҳ</a>&nbsp;";               
            returnString=returnString+"<a href='"+returnJSP+"?currentPage="+pageCount;
            for(int i=0;i<refName.length;i++)
                returnString=returnString+"&"+refName[i]+"="+refValue[i];
            returnString=returnString+"'>ĩҳ</a>&nbsp;";               
        }   
        return returnString;
    }  
    public ResultSet getRs() {
        return rs;
    }
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
