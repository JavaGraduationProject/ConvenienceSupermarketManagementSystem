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

String   dh="",cfd="",cfsj="",mdd="",ddsj="";

rs=(ResultSet)request.getAttribute("rs");
if(rs.next()){
 dh=rs.getString("dh");
cfd=rs.getString("cfd");
cfsj=rs.getString("cfsj");
mdd=rs.getString("mdd");
ddsj=rs.getString("ddsj");

}
%>

<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.dh.value==""){
alert("���Ų���Ϊ��");
return false;
form.dh.focus();
}

if(form.cfd.value==""){
alert("�����ز���Ϊ��");
return false;
form.cfd.focus();
}

if(form.cfsj.value==""){
alert("����ʱ�䲻��Ϊ��");
return false;
form.cfsj.focus();
}

if(form.mdd.value==""){
alert("Ŀ�ĵز���Ϊ��");
return false;
form.mdd.focus();
}

if(form.ddsj.value==""){
alert("����ʱ�䲻��Ϊ��");
return false;
form.ddsj.focus();
}


form.action="<%=path %>peisongServlet";
form.submit();
}
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
		  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-view-body">


    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">�޸�����</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/peisongServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="dh" required  lay-verify="required" placeholder="���뵥��" autocomplete="off" class="layui-input" value="<%=dh%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="cfd" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="<%=cfd%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="cfsj" required  lay-verify="required" placeholder="�������ʱ��" autocomplete="off" class="layui-input" value="<%=cfsj%>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">Ŀ�ĵ�</label>
                    <div class="layui-input-block">
                      <input type="text" name="mdd" required  lay-verify="required" placeholder="����Ŀ�ĵ�" autocomplete="off" class="layui-input" value="<%=mdd%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="ddsj" required  lay-verify="required" placeholder="���뵽��ʱ��" autocomplete="off" class="layui-input" value="<%=ddsj%>"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
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

