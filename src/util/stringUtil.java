package util;
public class stringUtil {
    public String codeToString(String str)
    {//���������ַ����ĺ���
      String s=str;
      try
        {
        byte tempB[]=s.getBytes("ISO-8859-1");
        s=new String(tempB);
        return s;
       }
      catch(Exception e)
       {
        return s;
       }  
    }
    public String getJSString(int role){//�õ��û���ɫ
        String returnString=new String("");
        switch(role){
            case 1:
            	returnString="ϵͳ����Ա";break;
            case 2:
                returnString="�������Ա";break;
            case 3:
                returnString="��ʦ";break;
            case 4:
                returnString="ѧ��";break;
            default:
                returnString="δ֪";break;
        }
        return returnString;
    }
}
