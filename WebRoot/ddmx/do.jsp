<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>

<%
/*
������Ӻ�̨
*/
DBO db=new DBO();//��ʼ���ݿ�����
request.setCharacterEncoding("gb2312");//�����ַ���
ResultSet rs=null;//���������
	String keyid =(String)request.getParameter("keyid");
String ddh=(String)request.getParameter("ddh");// ���� �� :������
String xdsj=(String)request.getParameter("xdsj");// ���� �� :�µ�ʱ��
String yh=(String)request.getParameter("yh");// ���� �� :�û�
String zjg=(String)request.getParameter("zjg");// ���� �� :�ܼ۸�
String lxdh=(String)request.getParameter("lxdh");// ���� �� :��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");// ���� �� :��ϵ��ַ
String zt=(String)request.getParameter("zt");// ���� �� :״̬
String bld=(String)request.getParameter("bld");// ���� �� :������

String sql="";//����sql

int row=1;///����ִ�н��
		
try{
	sql="update ddmx set dd='"+ddh+"' where dd='' and yh='"+yh+"'";
	db.update(sql);
	
	
	 sql="insert into dingdan(ddh,xdsj,yh,zjg,lxdh,lxdz,zt,bld) values('"+ddh+"','"+xdsj+"','"+yh+"','"+zjg+"','"+lxdh+"','"+lxdz+"','"+zt+"','"+bld+"')";
;//����SQL ���
		//int row =0;
		row=db.update(sql);//ִ��sql���
}catch(Exception e){
System.out.println(e.toString());}
if(row==1){//�����ɹ�
out.println("<script>");
out.println("alert('�����ɹ�');");
out.println("window.location='list.jsp'");
out.println("</script>");
}else{//����ʧ��
out.println("<script>");
out.println("alert('����ʧ��');");
out.println("window.location='list.jsp'");
out.println("</script>");
}
db.close();
%>

