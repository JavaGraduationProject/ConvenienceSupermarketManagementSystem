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
			
			
			String   ddh="",xdsj="",yh="",zjg="",lxdh="",lxdz="",zt="",bld="";//��ʼ������
String keyid=(String)request.getParameter("keyid");//����
String sql="select * from dingdan where ddid="+keyid;//�����ѯsql���
ResultSet rs=null;//�����ѯ�����
db.open();//�����ݿ�����
rs=db.query(sql);//ִ�в�ѯ���

/*
������Ϣ����ҳ��
*/
if(rs.next()){//�����������ѯ ��Ӧ�ı���
 ddh=rs.getString("ddh");//������
xdsj=rs.getString("xdsj");//�µ�ʱ��
yh=rs.getString("yh");//�û�
zjg=rs.getString("zjg");//�ܼ۸�
lxdh=rs.getString("lxdh");//��ϵ�绰
lxdz=rs.getString("lxdz");//��ϵ��ַ
zt=rs.getString("zt");//״̬
bld=rs.getString("bld");//������

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


form.action="<%=path %>/peisongServlet";
form.submit();
}
</script>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
	  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js">
	  </script>
    <title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-view-body">


    <div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">��������</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/peisongServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="dh" required  lay-verify="required" placeholder="���뵥��" autocomplete="off" class="layui-input" value="<%=ddh%>"  readonly="">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="cfd" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="<%=bld%>">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="cfsj" required  lay-verify="required" placeholder="�������ʱ��" autocomplete="off" class="layui-input"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">Ŀ�ĵ�</label>
                    <div class="layui-input-block">
                      <input type="text" name="mdd" required  lay-verify="required" placeholder="����Ŀ�ĵ�" autocomplete="off" class="layui-input" value="<%=lxdh%>">
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">����ʱ��</label>
                    <div class="layui-input-block">
                      <input type="text" name="ddsj" required  lay-verify="required" placeholder="���뵽��ʱ��" autocomplete="off" class="layui-input"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
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
