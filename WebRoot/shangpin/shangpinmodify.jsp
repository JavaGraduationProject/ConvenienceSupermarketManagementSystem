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

String   spmc="",js="",tp="",jg="",kc="",bld="",gys="";

rs=(ResultSet)request.getAttribute("rs");
if(rs.next()){
 spmc=rs.getString("spmc");
js=rs.getString("js");
tp=rs.getString("tp");
jg=rs.getString("jg");
kc=rs.getString("kc");
bld=rs.getString("bld");
gys=rs.getString("gys");
}
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


form.action="<%=path %>shangpinServlet";
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
                <div class="layui-card-header">�޸���Ʒ</div>
                <form name="form" method=post class="layui-form layui-card-body" action="<%=path %>/shangpinServlet">
                 

	  <div class="layui-form-item">
                    <label class="layui-form-label">��Ʒ����</label>
                    <div class="layui-input-block">
                      <input type="text" name="spmc" required  lay-verify="required" placeholder="������Ʒ����" autocomplete="off" class="layui-input" value="<%=spmc%>" >
                    </div>
					</div>
							  <div class="layui-form-item">
                    <label class="layui-form-label">��Ӧ��</label>
                    <div class="layui-input-block">
                  
				  <select name="gys">
				  	<%
					sql="select * from gys where 1=1 ";
					
					
					
					rs=db.query(sql);
					while(rs.next()){
					%>
					
					<option value="<%=rs.getString("gysmc")%>" <%if(rs.getString("gysmc").equals(gys)) out.println("selected");%>><%=rs.getString("gysmc")%></option>
					<% }%>
				  
				  </select>
					
                  </div>
	</div>
	  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                   
					   <textarea   name="js" required   lay-verify="required" placeholder="�������" cols="80" rows="5"><%=js%></textarea>
					  
					  
					  
					  
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">ͼƬ</label>
                    <div class="layui-input-block">
					
					 <table><tr><td>   <input type="text" name="tp" style="FONT-SIZE: 12px; WIDTH: 200px" required  lay-verify="required" placeholder="����ͼƬ" autocomplete="off" class="layui-input" value="<%=tp%>"></td><td><iframe id="ifr" name="ifr" width="400" height="40" frameborder="no" scrolling="no" src="<%=request.getContextPath()%>/shangpin/fileupload.jsp?kname=tp"></iframe></td></tr></table>
					
                  
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">�۸�</label>
                    <div class="layui-input-block">
                      <input type="number" name="jg" required  lay-verify="required" placeholder="����۸�" autocomplete="off" class="layui-input" value="<%=jg%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">���</label>
                    <div class="layui-input-block">
                      <input type="number" name="kc" required  lay-verify="required" placeholder="������" autocomplete="off" class="layui-input" value="<%=kc%>" >
                    </div>
                  </div>

	  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                      <input type="text" name="bld" required  lay-verify="required" placeholder="���������" autocomplete="off" class="layui-input" value="<%=bld%>"  readonly="">
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

