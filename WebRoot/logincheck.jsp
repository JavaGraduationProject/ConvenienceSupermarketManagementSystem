<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*"%>
<%@ page import="com.util.*"%>
<%
DBO db=new DBO();
request.setCharacterEncoding("gb2312");
String yhm=(String)request.getParameter("yhm");
String mm=(String)request.getParameter("mm");
    String qx=(String)request.getParameter("qx");

String sql="";

if(qx.equals("����Ա")) {
    sql = "select * from gly where yhm='" + yhm + "' and mm='" + mm + "' ";

}

    if(qx.equals("�û�")) {
        sql = "select * from yonghu where yhm='" + yhm + "' and mm='" + mm + "' ";

    }
 if(qx.equals("Ա��")) {
        sql = "select * from yuangong where yhm='" + yhm + "' and mm='" + mm + "' ";

    }

System.out.println("sql="+sql);
db.open();
ResultSet rs=db.query(sql);
out.println("<script>");
if(rs.next()){

    if(qx.equals("����Ա")) {
        session.setAttribute("id",rs.getString("glyid"));

    }

    if(qx.equals("Ա��")) {
        session.setAttribute("id",rs.getString("ygid"));

    }
	   if(qx.equals("�û�")) {
        session.setAttribute("id",rs.getString("yhid"));

    }


session.setAttribute("yhm",rs.getString("yhm"));
session.setAttribute("mm",rs.getString("mm"));
session.setAttribute("qx",qx);
;

//out.println("alert('��¼�ɹ�');");
out.println("window.location='index.jsp'");
}else{
out.println("alert('�û��������������');");
out.println("window.location='login.jsp'");
}
out.println("</script>");
%>
