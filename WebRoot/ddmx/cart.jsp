<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
request.setCharacterEncoding("gb2312");//����ҳ���ַ���
DBO db=new DBO();//��ʼ�������ݿ���
String sql="";
ResultSet rs=null;
String keyid=(String)request.getParameter("keyid");//��Ʒ���
String jg=(String)request.getParameter("jg");//��ǰ�۸�

String bld=(String)request.getParameter("bld");//�̼�
String sl=(String)request.getParameter("sl");
System.out.println("sl-:"+sl);


String yhm="";

if(session.getAttribute("yhm")!=null){
yhm=(String)session.getAttribute("yhm");
}


 	if(keyid!=null&&!keyid.equals("")){
  sql="insert into ddmx(dd,sp,sl,jg,yh,bld) values('','"+keyid+"','"+sl+"','"+jg+"','"+yhm+"','"+bld+"')";
;//������ϸSQL ���
db.update(sql);

	sql="update shangpin set kc=kc-'"+sl+"' where spid='"+keyid+"'";
	
	db.update(sql);
	
	}

%>

<HTML ><HEAD id=Head1><TITLE>������ϸ��Ϣ�б�</TITLE>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
    <link rel="stylesheet" href="../assets/css/layui.css">
    <link rel="stylesheet" href="../assets/css/view.css"/>
	<link href="../assets/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />

<META http-equiv=Content-Type content="text/html; charset=gb2312">


</HEAD>
<body class="layui-view-body">

<form name="form" method="post" action="ddmxlist.jsp">
 <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
             
                <h2 class="title">���ﳵ</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <div class="layui-form layui-form-item">
                            <div class="layui-inline">
							
            <button class="layui-btn layui-btn-blue" type="button" onClick="window.location='list.jsp'">��������</button>
                             
                            </div>
                        </div>
                      
                        <table id="table1"  class="layui-table">
						<tr  >
						<th>���</th>
			
					   
			
					         <th >��Ʒ</th>
			
					         <th >����</th>
			
					         <th >�۸�</th>
			
					
			
					         <th >������</th>
        <th >����</th>
						</tr>
						
						  <TBODY id="table2">

						<%
						 sql="select a.*,b.spmc  from ddmx a,shangpin b where 1=1 and a.sp=b.spid and a.yh='"+session.getAttribute("yhm")+"' and a.dd='' ";//��ѯ ������ϸsql���
db.open();//�����ݿ�����
	
	
 rs=db.query(sql);//��ѯ���
//������ϸ��Ϣ�б�
	int a=0;
	String blda="";
						
					int i=1;
					if(rs!=null){
					while(rs.next()){
						
						if(!blda.equals(rs.getString("bld"))){
						blda=rs.getString("bld");
						a++;
						}
					
					
					%>
						<tr>
											
						
						    <td ><%=i%></td>
					
					       
					        <td   ><%=rs.getString("spmc")%></td>
					       
					        <td   ><%=rs.getString("sl")%></td>
					       
					        <td   ><%=rs.getString("jg")%></td>
					       
			
					       
					        <td   ><%=rs.getString("bld")%></td>
					       
						<td >
						
									
						
						
					
					
						 <a href="ddmxdelete.jsp?keyid=<%=rs.getString("ddmxid")%>&sp=<%=rs.getString("sp")%>&sl=<%=rs.getString("sl")%>" onClick="javascript:if(confirm('�Ƿ�ɾ��')){return true;}else{return false;}">ɾ��</a>
						 <a href="detail.jsp?keyid=<%=rs.getString("sp")%>"  >�鿴</a>
						
						
						</td>
						</tr>
						
												<%
				i++;
				}
				}
				%>
						    </TBODY>
						</table>
							<%
							System.out.println("a="+a);
							if(a==1){
							if(i>0){
							%>
							
							<input type="button" value="�ύ����" class="layui-btn layui-btn-blue" onClick="window.location='dingdanadd.jsp'">
							<% }
							}else {
							
							%>
							
							<font color="#FF0000">һ�����������ж����������޹�����ϸ</font>
							<% }%>
							 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../assets/layui.all.js"></script>
    



</form>
</BODY></HTML>

<%
db.close();
%>


