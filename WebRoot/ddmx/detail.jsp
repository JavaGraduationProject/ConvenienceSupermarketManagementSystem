<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
request.setCharacterEncoding("gb2312");//����ҳ���ַ���
DBO db=new DBO();//���ݿ�����
String   spmc="",js="",tp="",jg="",kc="",bld="";//��ʼ������
String keyid=(String)request.getParameter("keyid");//����
String sql="select * from shangpin where spid="+keyid;//�����ѯsql���
ResultSet rs=null;//�����ѯ�����
db.open();//�����ݿ�����
rs=db.query(sql);//ִ�в�ѯ���

/*
��Ʒ��Ϣ����ҳ��
*/
if(rs.next()){//�����������ѯ ��Ӧ�ı���
 spmc=rs.getString("spmc");//��Ʒ����
js=rs.getString("js");//����
tp=rs.getString("tp");//ͼƬ
jg=rs.getString("jg");//�۸�
kc=rs.getString("kc");//���
bld=rs.getString("bld");//������

}
%>

<HTML ><HEAD id=Head1><TITLE>��Ʒ</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
    <link rel="stylesheet" href="../assets/css/layui.css">
    <link rel="stylesheet" href="../assets/css/view.css"/>



</HEAD>
<BODY class="layui-view-body">




<div class="layui-content">
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-header">�鿴��Ʒ</div>
                <form class="layui-form layui-card-body" action="cart.jsp" method="post">
                  <div class="layui-form-item">
                    <label class="layui-form-label">��Ʒ����</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <%=spmc%> </div>
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <%=js%> </div>
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">ͼƬ</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <img src="../upload/<%=tp%>" width="200" height="200"> </div>
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">�۸�</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <%=jg%> </div>
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">���</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <%=kc%> </div>
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">������</label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">   <%=bld%> </div>
                    </div>
                  </div>
				
			    <div class="layui-form-item">
                    <label class="layui-form-label"></label>
                    <div class="layui-input-block">
                <div class="layui-form-mid layui-word-aux">    ��������   <input type="number" name="sl" value="1" min=1 max=<%=kc%>>  <br> 
	  
	  <input type="hidden" name="keyid" value="<%=keyid%>" />
	  <input type="hidden" name="bld" value="<%=bld%>" />
	  <input type="hidden" name="jg" value="<%=jg%>" /> </div>
                    </div>
                  </div>
				
                  <div class="layui-form-item">
                    <div class="layui-input-block">
                      <button class="layui-btn layui-btn-blue" type="button" onClick="window.history.go(-1);">����</button>
					  
					  
					  <%
if(session.getAttribute("yhm")!=null){
%>



<input type="submit" value="����" name="B11" class="layui-btn layui-btn-blue"  id="B11"  />

<% } else {%>





<font color="red" size="3">��¼��ɹ���</font>
<% }%>
                    </div>
                  </div><input type="hidden" name="keyid" value="<%=keyid%>">
                </form>  
            </div>
        </div>
    </div>
    <script src="../assets/layui.all.js"></script>
    <script>
      var form = layui.form
        ,layer = layui.layer;
    </script>






</BODY></HTML>


<%
db.close();
%>




