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

String   rkh="",sp="",bld="",rksl="",yh="",czsj="";

rs=(ResultSet)request.getAttribute("rs");
if(rs.next()){
 rkh=rs.getString("rkh");
sp=rs.getString("sp");
bld=rs.getString("bld");
rksl=rs.getString("rksl");
yh=rs.getString("yh");
czsj=rs.getString("czsj");

}
%>

<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.rkh.value==""){
alert("���Ų���Ϊ��");
return false;
form.rkh.focus();
}

if(form.sp.value==""){
alert("��Ʒ����Ϊ��");
return false;
form.sp.focus();
}

if(form.bld.value==""){
alert("�����겻��Ϊ��");
return false;
form.bld.focus();
}

if(form.rksl.value==""){
alert("�����������Ϊ��");
return false;
form.rksl.focus();
}

if(form.yh.value==""){
alert("�û�����Ϊ��");
return false;
form.yh.focus();
}

if(form.czsj.value==""){
alert("����ʱ�䲻��Ϊ��");
return false;
form.czsj.focus();
}


form.action="<%=path %>rukuServlet";
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
                <div class="layui-card-header">�޸����</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/rukuServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="rkh" required  lay-verify="required" placeholder="��������" autocomplete="off" class="layui-input" value="<%=rkh%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��Ʒ</label>
                    <div class="layui-input-block">
                      <input type="text" name="sp" required  lay-verify="required" placeholder="������Ʒ" autocomplete="off" class="layui-input" value="<%=sp%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="bld" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="<%=bld%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�������</label>
                    <div class="layui-input-block">
                      <input type="text" name="rksl" required  lay-verify="required" placeholder="�����������" autocomplete="off" class="layui-input" value="<%=rksl%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�û�</label>
                    <div class="layui-input-block">
                      <input type="text" name="yh" required  lay-verify="required" placeholder="�����û�" autocomplete="off" class="layui-input" value="<%=yh%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="czsj" required  lay-verify="required" placeholder="�������ʱ��" autocomplete="off" class="layui-input" value="<%=czsj%>" >
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

