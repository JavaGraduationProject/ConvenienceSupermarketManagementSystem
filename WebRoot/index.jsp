<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.main.util.*"%>

<%
request.setCharacterEncoding("gb2312");
String qx="";
if(session.getAttribute("qx")!=null){
qx=(String)session.getAttribute("qx");
}
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="gb2312">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="assets/css/layui.css">
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="icon" href="/favicon.ico">
    <title>�������й���ϵͳ</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"></head>
<body class="layui-layout-body">
    <div class="layui-layout layui-layout-admin">
        <div class="layui-header custom-header">
            
            <ul class="layui-nav layui-layout-left">
                <li class="layui-nav-item slide-sidebar" lay-unselect>
                    <a href="javascript:;" class="icon-font"><i class="ai ai-menufold"></i></a>
                </li>
            </ul>

            <ul class="layui-nav layui-layout-right">
                <li class="layui-nav-item">
                    <a href="javascript:;"><%=session.getAttribute("yhm")%></a>
                    <dl class="layui-nav-child">

                        <dd><a href="<%=request.getContextPath()%>/login.jsp">�˳�</a></dd>
                    </dl>
                </li>
            </ul>
        </div>

        <div class="layui-side custom-admin">
            <div class="layui-side-scroll">

                <div class="custom-logo">
                 
                    <h1>ϵͳ�����˵�</h1>
                </div>
                <ul id="Nav" class="layui-nav layui-nav-tree">

               
			   	<%
				if(qx.equals("����Ա")){
				%>
                       
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>�û�����</em>
                        </a>
                        <dl class="layui-nav-child">
						  <dd><a href="gly/glyadd.jsp">���ӹ���Ա</a></dd>
                            <dd><a href="glyServlet?method=list">�������Ա</a></dd>
						          <dd><a href="yuangong/yuangongadd.jsp">����Ա��</a></dd>
                            <dd><a href="yuangongServlet?method=list">����Ա��</a></dd>
                            <dd><a href="yonghu/yonghuadd.jsp">�����û�</a></dd>
                            <dd><a href="yonghuServlet?method=list">�����û�</a></dd>
				
                        </dl>
                    </li>
                
                       
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>���������</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="bld/bldadd.jsp">���ӱ�����</a></dd>
                            <dd><a href="bldServlet?method=list">���������</a></dd>
				
                        </dl>
                    </li>
                
                       
      
                       
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>��Ӧ�̹���</em>
                        </a>
                        <dl class="layui-nav-child">
						 <dd><a href="dengji/dengjiadd.jsp">���ӵȼ�</a></dd>
                            <dd><a href="dengjiServlet?method=list">����ȼ�</a></dd>
                            <dd><a href="gys/gysadd.jsp">���ӹ�Ӧ��</a></dd>
                            <dd><a href="gysServlet?method=list">����Ӧ��</a></dd>
				
                        </dl>
                    </li>
					
					<% }%>
                
                         	<%
				if(qx.equals("Ա��")){
				%>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>��Ʒ����</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="shangpin/shangpinadd.jsp">������Ʒ</a></dd>
                            <dd><a href="shangpinServlet?method=list">������Ʒ</a></dd>
				
                        </dl>
                    </li>
                
                       
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>������</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="ruku/rukuadd.jsp">�������</a></dd>
                            <dd><a href="rukuServlet?method=list">�������</a></dd>
				
                        </dl>
                    </li>
                
                       
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>�������</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="diaopei/diaopeiadd.jsp">���ӵ���</a></dd>
                            <dd><a href="diaopeiServlet?method=list">�������</a></dd>
				
                        </dl>
                    </li>
                
                       <% }%>
					   
					     	<%
				if(!qx.equals("����Ա")){
				%>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>��������</em>
                        </a>
                        <dl class="layui-nav-child">
                            	<%
				if(qx.equals("Ա��")){
				%>
                            <dd><a href="dingdanServlet?method=list">������</a></dd>
							<% }%>
							
							  	<%
				if(qx.equals("�û�")){
				%>
				    <dd><a href="dingdanServlet?method=mylist">�ҵĶ���</a></dd>
					<% }%>
                        </dl>
                    </li>
                
                         	<%
				if(qx.equals("�û�")){
				%>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>��Ʒ����</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="ddmx/list.jsp">�����Ʒ</a></dd>
							
							   <dd><a href="ddmx/cart.jsp">���ﳵ</a></dd>
                         
				
                        </dl>
                    </li>
					<% }%>
                
                       <% }%>
					   
					     	<%
				if(qx.equals("Ա��")){
				%>
                    <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>���͹���</em>
                        </a>
                        <dl class="layui-nav-child">
                            <dd><a href="peisong/list.jsp">��������</a></dd>
                            <dd><a href="peisongServlet?method=list">��������</a></dd>
				
                        </dl>
                    </li>
					<% }%>
                
				       <li class="layui-nav-item">
                        <a href="javascript:;">
                            <i class="layui-icon">&#xe857;</i>
                            <em>������Ϣ</em>
                        </a>
                        <dl class="layui-nav-child">
                            	<%
				if(qx.equals("����Ա")){
				%>
                            <dd><a href="glyServlet?method=mod">������Ϣ</a></dd>
							<% }%>
							  	<%
				if(qx.equals("�û�")){
				%>
							<dd><a href="yonghuServlet?method=mod">������Ϣ</a></dd>
							<% }%>
							
							  	<%
				if(qx.equals("Ա��")){
				%>
							<dd><a href="yuangongServlet?method=mod">������Ϣ</a></dd>
							<% }%>
				
                        </dl>
                    </li>
                    


                </ul>

            </div>
        </div>

        <div class="layui-body">
             <div class="layui-tab app-container" lay-allowClose="true" lay-filter="tabs">
                <ul id="appTabs" class="layui-tab-title custom-tab"></ul>
                <div id="appTabPage" class="layui-tab-content"></div>
            </div>
        </div>

        <div class="layui-footer">
            <p></p>
        </div>

        <div class="mobile-mask"></div>
    </div>
    <script src="assets/layui.js"></script>
    <script src="index.js" data-main="home"></script>
</body>
</html>






