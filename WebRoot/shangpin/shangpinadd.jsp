<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
request.setCharacterEncoding("gb2312");
DBO db=new DBO();
db.open();
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			String sql="";
			ResultSet rs=null;
%>


<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.spmc.value==""){
alert("��Ʒ���Ʋ���Ϊ��");
return false;
form.spmc.focus();
}

if(form.js.value==""){
alert("���ܲ���Ϊ��");
return false;
form.js.focus();
}

if(form.tp.value==""){
alert("ͼƬ����Ϊ��");
return false;
form.tp.focus();
}

if(form.jg.value==""){
alert("�۸���Ϊ��");
return false;
form.jg.focus();
}

if(form.kc.value==""){
alert("��治��Ϊ��");
return false;
form.kc.focus();
}

if(form.bld.value==""){
alert("�����겻��Ϊ��");
return false;
form.bld.focus();
}


form.action="<%=path %>/shangpinServlet";
form.submit();
}
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-view-body">


    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">������Ʒ</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/shangpinServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">��Ʒ����</label>
                    <div class="layui-input-block">
                      <input type="text" name="spmc" required  lay-verify="required" placeholder="������Ʒ����" autocomplete="off" class="layui-input">
                    </div>
					  </div>
					
						  <div class="layui-form-item">
                    <label class="layui-form-label">��Ӧ��</label>
                    <div class="layui-input-block">
                  
				  <select name="gys">
				  	<%
					sql="select * from gys ";
					rs=db.query(sql);
					while(rs.next()){
					%>
					
					<option value="<%=rs.getString("gysmc")%>"><%=rs.getString("gysmc")%></option>
					<% }%>
				  
				  </select>
				  
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                    	  <textarea   name="js" required   lay-verify="required" placeholder="�������" cols="80" rows="5"></textarea>
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">ͼƬ</label>
                    <div class="layui-input-block">
                   <table><tr><td>   <input type="text" name="tp" style="FONT-SIZE: 12px; WIDTH: 200px" required  lay-verify="required" placeholder="����ͼƬ" autocomplete="off" class="layui-input"></td><td><iframe id="ifr" name="ifr" width="400" height="40" frameborder="no" scrolling="no" src="<%=request.getContextPath()%>/shangpin/fileupload.jsp?kname=tp"></iframe></td></tr></table>
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�۸�</label>
                    <div class="layui-input-block">
                      <input type="number" name="jg" required  lay-verify="required" placeholder="����۸�" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">���</label>
                    <div class="layui-input-block">
                      <input type="number" name="kc" required value="0"  lay-verify="required" placeholder="������" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                     
					  
					  <select name="bld">
					  
					 <%
					 sql="select * from bld where 1=1";
					 if(session.getAttribute("yhm")!=null){
					 
					 sql+=" and mc in (select bld from yuangong where yhm='"+session.getAttribute("yhm")+"')";
					 }
					 rs=db.query(sql);
					 while(rs.next()){
					 %> 
					 
					 <option value="<%=rs.getString("mc")%>"><%=rs.getString("mc")%></option>
					 <% }%>
					  
					  </select>
					  
					  
                    </div>
                  </div>


					
						
		<input type=hidden name=method value="insert">
	

              
                  <div class="layui-form-item">
                    <div class="layui-input-block">
                      <button type="submit" class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemo">�ύ</button>
                      <button type="reset" class="layui-btn layui-btn-primary">����</button>
                    </div>
                  </div>
                </form>  
            </div>
        </div>
    </div>
    <script src="<%=request.getContextPath()%>/assets/layui.all.js" charset="utf-8"></script>
<font color="#FF0000">
<%
if(request.getAttribute("flag")!=null){
out.println("������ʾ��"+(String)request.getAttribute("flag"));
}
%>
</font>


</body>
</html>




<%
db.close();
%>
