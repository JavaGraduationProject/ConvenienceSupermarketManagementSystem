<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>

<%

DBO db=new DBO();//�������ݿ�
request.setCharacterEncoding("gb2312"); //����ҳ���ַ���
ResultSet rs=null;//��������
String sql="";//����sql
int row=1;
String keyid=(String)request.getParameter("keyid"); //����id
String sl=(String)request.getParameter("sl");
String sp=(String)request.getParameter("sp");
/*
.������ϸɾ��ҳ��


*/	


try{
	
	sql="update shangpin set kc=kc+"+sl+" where spid='"+sp+"'";
	
	db.update(sql);
			
			  sql="delete from ddmx where ddmxid='"+keyid+"'";//ɾ��sql���
// int row=0;
		 row=db.update(sql);//ִ�� ɾ���Ķ�����ϸ�Ĳ���
}catch(Exception e){
System.out.println(e.toString());}
if(row==1){//�ɹ�
out.println("<script>");
out.println("alert('�����ɹ�');");
out.println("window.location='list.jsp'");
out.println("</script>");
}else{//ʧ��
out.println("<script>");
out.println("alert('����ʧ��');");
out.println("window.location='list.jsp'");
out.println("</script>");
}
db.close();//�ر����ݿ�����
%>

