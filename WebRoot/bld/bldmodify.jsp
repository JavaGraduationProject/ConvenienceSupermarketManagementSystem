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

String   mc="",dz="",lxdh="",fzr="";

rs=(ResultSet)request.getAttribute("rs");
if(rs.next()){
 mc=rs.getString("mc");
dz=rs.getString("dz");
lxdh=rs.getString("lxdh");
fzr=rs.getString("fzr");

}
%>

<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.mc.value==""){
alert("���Ʋ���Ϊ��");
return false;
form.mc.focus();
}

if(form.dz.value==""){
alert("��ַ����Ϊ��");
return false;
form.dz.focus();
}

if(form.lxdh.value==""){
alert("��ϵ�绰����Ϊ��");
return false;
form.lxdh.focus();
}

if(form.fzr.value==""){
alert("�����˲���Ϊ��");
return false;
form.fzr.focus();
}


form.action="<%=path %>bldServlet";
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
                <div class="layui-card-header">�޸ı�����</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/bldServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="mc" required  lay-verify="required" placeholder="��������" autocomplete="off" class="layui-input" value="<%=mc%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ַ</label>
                    <div class="layui-input-block">
                      <input type="text" name="dz" required  lay-verify="required" placeholder="�����ַ" autocomplete="off" class="layui-input" value="<%=dz%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ�绰</label>
                    <div class="layui-input-block">
                      <input type="text" name="lxdh" required  lay-verify="required" placeholder="������ϵ�绰" autocomplete="off" class="layui-input" value="<%=lxdh%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="fzr" required  lay-verify="required" placeholder="���븺����" autocomplete="off" class="layui-input" value="<%=fzr%>" >
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

