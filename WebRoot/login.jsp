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

    <div align="center">   <h1>�������й���ϵͳ</h1></div>

    <form class="login-form" method="post" action="logincheck.jsp">
        <div class="input-group">
            <input type="text" id="yhm" name="yhm" class="input-field" placeholder="�����û���" required  lay-verify="required" >
            <label for="username" class="input-label">

            </label>
        </div>
        <div class="input-group">
            <input type="password" id="mm" name="mm" placeholder="��������" class="input-field">
            <label for="password" class="input-label">

            </label>
        </div>
        <div class="input-group">
           <select name="qx">
               <option value="�û�">�û�</option>
			   <option value="Ա��">Ա��</option>
               <option value="����Ա">����Ա</option>
           </select>
            <label for="username" class="input-label">

            </label>
        </div>
        <button type="submit" class="login-button">��¼<i class="ai ai-enter"></i></button>
		 <button type="button" class="login-button" onClick="window.location='reg.jsp'">ע��<i class="ai ai-enter"></i></button>
    </form>
</div>
</body>
<script src="assets/layui.js"></script>

</html>