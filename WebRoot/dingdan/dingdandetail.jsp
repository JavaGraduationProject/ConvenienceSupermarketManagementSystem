<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
DBO db=new DBO();
db.open();
ResultSet rs=null;
request.setCharacterEncoding("gb2312");
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
String keyid=(String)request.getParameter("keyid");
String sql="";
String   ddh="",xdsj="",yh="",zjg="",lxdh="",lxdz="",zt="",bld="";


		
		if(keyid!=null&&!keyid.equals("")){
			
			sql+=" and ddid='"+keyid+"'";
			
		}
		
		rs=(ResultSet)request.getAttribute("rs");

if(rs.next()){
 ddh=rs.getString("ddh");
xdsj=rs.getString("xdsj");
yh=rs.getString("yh");
zjg=rs.getString("zjg");
lxdh=rs.getString("lxdh");
lxdz=rs.getString("lxdz");
zt=rs.getString("zt");
bld=rs.getString("bld");

}
%>




<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-view-body">


    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">�鿴����</div>
                <form name="form" method=post class="layui-form layui-card-body" action="">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=ddh%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�µ�ʱ��</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=xdsj%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�û�</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=yh%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�ܼ۸�</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=zjg%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ�绰</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=lxdh%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ��ַ</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=lxdz%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">״̬</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=zt%></div>

                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
		     <div class="layui-form-mid layui-word-aux"><%=bld%></div>

                    </div>
                  </div>

<h2>������ϸ</h2>

  <table id="table1"  class="layui-table">
						<tr  >
						<th>���</th>
			
					   
			
					         <th >��Ʒ</th>
			
					         <th >����</th>
			
					         <th >�۸�</th>
			
					
			
					         <th >������</th>

						</tr>
						
						  <TBODY id="table2">

						<%
						 sql="select a.*,b.spmc  from ddmx a,shangpin b where 1=1 and a.sp=b.spid  and a.dd='"+ddh+"' ";//��ѯ ������ϸsql���
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
					       
					
						</tr>
						
												<%
				i++;
				}
				}
				%>
						    </TBODY>
						</table>
						<h2>������Ϣ</h2>
						        <table  class="layui-table"  id="table1">
			<tr class="layui-table-header">
			  <th>��� </th>
					        <th >����</th>
					       
					        <th >������</th>
					       
					        <th >����ʱ��</th>
					       
					        <th >Ŀ�ĵ�</th>
					       
					        <th >����ʱ��</th>
					       

			
					       
    
		
                        </tr>
			 <TBODY id="table2">			

			  
			  <%
			  
			sql="select * from peisong where dh='"+ddh+"'";
			rs=db.query(sql);
			   i=1;
			  while(rs.next()){
			  %>
			  
			  <tr align="left">
			  <td><%=i%></td>
					        <td ><%=rs.getString("dh")%></td>
					       
					        <td ><%=rs.getString("cfd")%></td>
					       
					        <td ><%=rs.getString("cfsj")%></td>
					       
					        <td ><%=rs.getString("mdd")%></td>
					       
					        <td ><%=rs.getString("ddsj")%></td>
					       




			</tr>
			
    <%
	
		i++;
			
}
				 %>
			  
			  
	    </TBODY>		
						
						</table>
            <input type="hidden" name="keyid" value="<%=keyid%>">
		<input type=hidden name=method value="modify">
                  <div class="layui-form-item">
                    <div class="layui-input-block">
                      <button type="button" class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemo" onClick="history.go(-1)">����</button>
                
                    </div>
                  </div>
                </form>  
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/assets/layui.all.js"></script>


</body>
</html>


