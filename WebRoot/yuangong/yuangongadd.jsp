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
if(form.yhm.value==""){
alert("�û�������Ϊ��");
return false;
form.yhm.focus();
}

if(form.mm.value==""){
alert("���벻��Ϊ��");
return false;
form.mm.focus();
}

if(form.xm.value==""){
alert("��������Ϊ��");
return false;
form.xm.focus();
}

if(form.bld.value==""){
alert("�����겻��Ϊ��");
return false;
form.bld.focus();
}


form.action="<%=path %>/yuangongServlet";
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
                <div class="layui-card-header">����Ա��</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/yuangongServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">�û���</label>
                    <div class="layui-input-block">
                      <input type="text" name="yhm" required  lay-verify="required" placeholder="�����û���" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="mm" required  lay-verify="required" placeholder="��������" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="xm" required  lay-verify="required" placeholder="��������" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                   
					  
					  <select name="bld">
						<%
						sql="select * from bld";
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
