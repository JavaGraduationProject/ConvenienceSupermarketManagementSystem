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
			int all=0;
			int num=0;
			String bld="";
			
			if(session.getAttribute("yhm")!=null){
			
			sql="select sum(sl*jg) as num from ddmx where dd='' and yh='"+session.getAttribute("yhm")+"'";
			rs=db.query(sql);
			if(rs.next()){
			
			num=rs.getInt("num");
			}
			
				sql="select * from ddmx where dd='' and yh='"+session.getAttribute("yhm")+"'";
				
				rs=db.query(sql);
			if(rs.next()){
			
			bld=rs.getString("bld");
			}
			
			}
			
			
			
			
%>


<!DOCTYPE html>
<html>
<head>
<script language="javascript">
function checkDo(){
if(form.ddh.value==""){
alert("�����Ų���Ϊ��");
return false;
form.ddh.focus();
}

if(form.xdsj.value==""){
alert("�µ�ʱ�䲻��Ϊ��");
return false;
form.xdsj.focus();
}

if(form.yh.value==""){
alert("�û�����Ϊ��");
return false;
form.yh.focus();
}

if(form.zjg.value==""){
alert("�ܼ۸���Ϊ��");
return false;
form.zjg.focus();
}

if(form.lxdh.value==""){
alert("��ϵ�绰����Ϊ��");
return false;
form.lxdh.focus();
}

if(form.lxdz.value==""){
alert("��ϵ��ַ����Ϊ��");
return false;
form.lxdz.focus();
}

if(form.zt.value==""){
alert("״̬����Ϊ��");
return false;
form.zt.focus();
}

if(form.bld.value==""){
alert("�����겻��Ϊ��");
return false;
form.bld.focus();
}


form.action="<%=path %>/dingdanServlet";
form.submit();
}
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-view-body">

	<%
	java.util.Date   now=new   java.util.Date();   
			  java.text.SimpleDateFormat   formatter=new   java.text.SimpleDateFormat("yyyyMMddHHmmssssss");
			//  System.out.print(formatter.format(now));
			  String ddid="order"+formatter.format(now);

	
	%>

    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">��������</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/ddmx/do.jsp">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="ddh" required  lay-verify="required" placeholder="���붩����" autocomplete="off" class="layui-input" value="<%=ddid%>" readonly="">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�µ�ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="xdsj" required  lay-verify="required" placeholder="�����µ�ʱ��" autocomplete="off" class="layui-input" value="<%=StaticMethod.getStringDate()%>" readonly="">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�û�</label>
                    <div class="layui-input-block">
                      <input type="text" name="yh" required  lay-verify="required" placeholder="�����û�" autocomplete="off" class="layui-input" value="<%=session.getAttribute("yhm")%>">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�ܼ۸�</label>
                    <div class="layui-input-block">
                      <input type="text" name="zjg" required  lay-verify="required" placeholder="�����ܼ۸�" autocomplete="off" class="layui-input" value="<%=num%>">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ�绰</label>
                    <div class="layui-input-block">
                      <input type="text" name="lxdh" required  lay-verify="required" placeholder="������ϵ�绰" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ��ַ</label>
                    <div class="layui-input-block">
                      <input type="text" name="lxdz" required  lay-verify="required" placeholder="������ϵ��ַ" autocomplete="off" class="layui-input">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">״̬</label>
                    <div class="layui-input-block">
                      <input type="text" name="zt" required  lay-verify="required" placeholder="����״̬" autocomplete="off" class="layui-input" value="δ����" readonly="">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="bld" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="<%=bld%>" readonly="">
                    </div>
                  </div>


					
						
		<input type=hidden name=method value="insert">
	

              
                  <div class="layui-form-item">
                    <div class="layui-input-block">
                      <button type="submit" class="layui-btn layui-btn-blue" lay-submit lay-filter="formDemo">�ύ</button>
                      <button type="button" onClick="window.location='list.jsp'" class="layui-btn layui-btn-primary">��������</button>
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
