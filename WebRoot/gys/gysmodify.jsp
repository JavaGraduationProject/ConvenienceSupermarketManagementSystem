<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
DBO db=new DBO();
String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
request.setCharacterEncoding("gb2312");
ResultSet rs=null;
String sql="";
String keyid=(String)request.getParameter("keyid");
int row=0;

String   gysmc="",js="",jyfw="",dj="";

rs=(ResultSet)request.getAttribute("rs");
if(rs.next()){
 gysmc=rs.getString("gysmc");
js=rs.getString("js");
jyfw=rs.getString("jyfw");
dj=rs.getString("dj");

}
%>

<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.gysmc.value==""){
alert("��Ӧ�����Ʋ���Ϊ��");
return false;
form.gysmc.focus();
}

if(form.js.value==""){
alert("���ܲ���Ϊ��");
return false;
form.js.focus();
}

if(form.jyfw.value==""){
alert("��Ӫ��Χ����Ϊ��");
return false;
form.jyfw.focus();
}

if(form.dj.value==""){
alert("�ȼ�����Ϊ��");
return false;
form.dj.focus();
}


form.action="<%=path %>gysServlet";
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
                <div class="layui-card-header">�޸Ĺ�Ӧ��</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/gysServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">��Ӧ������</label>
                    <div class="layui-input-block">
                      <input type="text" name="gysmc" required  lay-verify="required" placeholder="���빩Ӧ������" autocomplete="off" class="layui-input" value="<%=gysmc%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      
					    <textarea   name="js" required   lay-verify="required" placeholder="�������" cols="80" rows="5"><%=js%></textarea>
					  
					  
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��Ӫ��Χ</label>
                    <div class="layui-input-block">
                      <input type="text" name="jyfw" required  lay-verify="required" placeholder="���뾭Ӫ��Χ" autocomplete="off" class="layui-input" value="<%=jyfw%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�ȼ�</label>
                    <div class="layui-input-block">
                      <select name="dj">
					 
					 <%
					 sql="select * from dengji";
					 rs=db.query(sql);
					 while(rs.next()){
					 %>
					 <option value="<%=rs.getString("dj")%>" <%if(rs.getString("dj").equals(dj)) out.println("selected");%>><%=rs.getString("dj")%></option>
					 <% }%>
					 </select>
                    </div>
                  </div>


					<input type="hidden" name="keyid" value="<%=keyid%>">
		<input type=hidden name=method value="modify">
						
	
	

              
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

