<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%
request.setCharacterEncoding("gb2312");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="assets/css/layui.css">
    <link rel="stylesheet" href="assets/css/login.css">
  <style>
     body{
    background-image: url(bj.jpg); /*����ͼƬ·������*/
    background-repeat:no-repeat; /*������ƽ�̣�ͼƬ��Ӧ���ڴ�С*/
    background-attachment: fixed; /*����ͼƬ�̶����������ݹ���*/
    background-origin: border-box; /*�ӱ߿�������ʾ*/
    background-size:cover;/*ȫ������*/

}

    
    </style>
    <title>�������й���ϵͳ </title>
</head>
<body class="login-wrap">
<div class="login-container">

    <div align="center">   <h1>�û�ע��</h1></div>

    <form class="login-form" method="post" action="do.jsp">
      <div class="layui-form-item">
                    <label class="layui-form-label">�û���</label>
                    <div class="layui-input-block">
                      <input type="text" name="yhm" required  lay-verify="required" placeholder="�������û���" autocomplete="off" class="layui-input" value="">
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="mm" required  lay-verify="required" placeholder="����������" autocomplete="off" class="layui-input" value="">
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">����</label>
                    <div class="layui-input-block">
                      <input type="text" name="xm" required  lay-verify="required" placeholder="����������" autocomplete="off" class="layui-input" value="">
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ�绰</label>
                    <div class="layui-input-block">
                      <input type="text" name="lxdh" required  lay-verify="required" placeholder="��������ϵ�绰" autocomplete="off" class="layui-input" value="">
                    </div>
                  </div>
                  <div class="layui-form-item">
                    <label class="layui-form-label">��ϵ��ַ</label>
                    <div class="layui-input-block">
                      <input type="text" name="lxdz" required  lay-verify="required" placeholder="��������ϵ��ַ" autocomplete="off" class="layui-input" value="">
                    </div>
                  </div>
        <button type="submit" class="login-button">ע��<i class="ai ai-enter"></i></button>
		 <button type="button" class="login-button" onClick="window.location='login.jsp'">���ص�¼<i class="ai ai-enter"></i></button>
    </form>
</div>
</body>
<script src="assets/layui.js"></script>

</html>