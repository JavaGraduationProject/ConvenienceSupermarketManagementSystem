<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.util.*"%>
<%
request.setCharacterEncoding("gb2312");//����ҳ���ַ���
DBO db=new DBO();//��ʼ�������ݿ���
String sql="select * from shangpin where 1=1 ";//��ѯ ��Ʒsql���
db.open();//�����ݿ�����
	String spmc=(String)request.getParameter("spmc");//��Ʒ����
	if(spmc!=null&&!spmc.equals("")){
	sql+=" and spmc like '%"+spmc+"%'";//�������� ƴ��sql ��Ʒ����
	}
	
	String js=(String)request.getParameter("js");//����
	if(js!=null&&!js.equals("")){
	sql+=" and js like '%"+js+"%'";//�������� ƴ��sql ����
	}
	
	String tp=(String)request.getParameter("tp");//ͼƬ
	if(tp!=null&&!tp.equals("")){
	sql+=" and tp like '%"+tp+"%'";//�������� ƴ��sql ͼƬ
	}
	
	String jg=(String)request.getParameter("jg");//�۸�
	if(jg!=null&&!jg.equals("")){
	sql+=" and jg like '%"+jg+"%'";//�������� ƴ��sql �۸�
	}
	
	String kc=(String)request.getParameter("kc");//���
	if(kc!=null&&!kc.equals("")){
	sql+=" and kc like '%"+kc+"%'";//�������� ƴ��sql ���
	}
	
	String bld=(String)request.getParameter("bld");//������
	if(bld!=null&&!bld.equals("")){
	sql+=" and bld like '%"+bld+"%'";//�������� ƴ��sql ������
	}
	
ResultSet rs=db.query(sql);//��ѯ���
//��Ʒ��Ϣ�б�
%>

<HTML ><HEAD id=Head1><TITLE>��Ʒ��Ϣ�б�</TITLE>

<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/layui.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/view.css"/>
	<link href="<%=request.getContextPath()%>/assets/tablecloth.css" rel="stylesheet" type="text/css" media="screen" />

<META http-equiv=Content-Type content="text/html; charset=gb2312">


</HEAD>
<body class="layui-view-body">

<form name="form" method="post" action="shangpinlist.jsp">
 <div class="layui-content">
        <div class="layui-page-header">
            <div class="pagewrap">
             
                <h2 class="title">��Ʒ�б�</h2>
            </div>
        </div>
        <div class="layui-row">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div class="form-box">
                        <div class="layui-form layui-form-item">
                            <div class="layui-inline">
							
            	��Ʒ���ƣ�
               <input name="spmc" type="text">
            	�����꣺
               <input name="bld" type="text">

                                <button class="layui-btn layui-btn-blue">��ѯ</button>   <button class="layui-btn layui-btn-blue" type="button" onClick="window.location='cart.jsp'">���ﳵ</button>
                             
                            </div>
                        </div>
                      
                        <table id="table1" class="layui-table">
						<tr  >
						<th>���</th>
			
					         <th >��Ʒ����</th>
			
					    
			
					         <th >ͼƬ</th>
			
					         <th >�۸�</th>
			
					         <th >���</th>
			
					         <th >������</th>
        <th >����</th>
						</tr>
						
						  <TBODY id="table2">

						<%
					int i=1;
					if(rs!=null){
					while(rs.next()){
					%>
						<tr>
											
						
						    <td ><%=i%></td>
					        <td   ><%=rs.getString("spmc")%></td>
					       
					
					       
					        <td   ><img src="..//upload/<%=rs.getString("tp")%>" width="60" height="60"></td>
					       
					        <td   ><%=rs.getString("jg")%></td>
					       
					        <td   ><%=rs.getString("kc")%></td>
					       
					        <td   ><%=rs.getString("bld")%></td>
					       
						<td >
						
									
						
						
					
						 <a href="detail.jsp?keyid=<%=rs.getString("spid")%>"  >�鿴</a>
						
						
						</td>
						</tr>
						
												<%
				i++;
				}
				}
				%>
						    </TBODY>
						</table>
							 <span id="spanFirst">��һҳ</span> <span id="spanPre">��һҳ</span> <span id="spanNext">��һҳ</span> <span id="spanLast">���һҳ</span> ��<span id="spanPageNum"></span>ҳ/��<span id="spanTotalPage"></span>ҳ
							 
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="../assets/layui.all.js"></script>
    
<script>
      var theTable = document.getElementById("table2");
      var totalPage = document.getElementById("spanTotalPage");
      var pageNum = document.getElementById("spanPageNum");


      var spanPre = document.getElementById("spanPre");
      var spanNext = document.getElementById("spanNext");
      var spanFirst = document.getElementById("spanFirst");
      var spanLast = document.getElementById("spanLast");


      var numberRowsInTable = theTable.rows.length;
      var pageSize = 20;
      var page = 1;


      //��һҳ
     function next() {


          hideTable();


          currentRow = pageSize * page;
          maxRow = currentRow + pageSize;
          if (maxRow > numberRowsInTable) maxRow = numberRowsInTable;
          for (var i = currentRow; i < maxRow; i++) {
              theTable.rows[i].style.display = '';
          }
          page++;


          if (maxRow == numberRowsInTable) { nextText(); lastText(); }
          showPage();
          preLink();
          firstLink();
      }


      //��һҳ
     function pre() {


          hideTable();


          page--;


          currentRow = pageSize * page;
          maxRow = currentRow - pageSize;
          if (currentRow > numberRowsInTable) currentRow = numberRowsInTable;
          for (var i = maxRow; i < currentRow; i++) {
              theTable.rows[i].style.display = '';
          }




          if (maxRow == 0) { preText(); firstText(); }
          showPage();
          nextLink();
          lastLink();
      }


      //��һҳ
     function first() {
          hideTable();
          page = 1;
          for (var i = 0; i < pageSize; i++) {
              theTable.rows[i].style.display = '';
          }
          showPage();


          preText();
          nextLink();
          lastLink();
      }


      //���һҳ
     function last() {
          hideTable();
          page = pageCount();
          currentRow = pageSize * (page - 1);
          for (var i = currentRow; i < numberRowsInTable; i++) {
              theTable.rows[i].style.display = '';
          }
          showPage();


          preLink();
          nextText();
          firstLink();
      }


      function hideTable() {
          for (var i = 0; i < numberRowsInTable; i++) {
              theTable.rows[i].style.display = 'none';
          }
      }


      function showPage() {
          pageNum.innerHTML = page;
      }


      //�ܹ�ҳ��
     function pageCount() {
          var count = 0;
          if (numberRowsInTable % pageSize != 0) count = 1;
          return parseInt(numberRowsInTable / pageSize) + count;
      }


      //��ʾ����
     function preLink() { spanPre.innerHTML = "<a href='javascript:pre();'>��һҳ</a>"; }
      function preText() { spanPre.innerHTML = "��һҳ"; }


      function nextLink() { spanNext.innerHTML = "<a href='javascript:next();'>��һҳ</a>"; }
      function nextText() { spanNext.innerHTML = "��һҳ"; }


      function firstLink() { spanFirst.innerHTML = "<a href='javascript:first();'>��һҳ</a>"; }
      function firstText() { spanFirst.innerHTML = "��һҳ"; }


      function lastLink() { spanLast.innerHTML = "<a href='javascript:last();'>���һҳ</a>"; }
      function lastText() { spanLast.innerHTML = "���һҳ"; }


      //���ر��
     function hide() {
          for (var i = pageSize; i < numberRowsInTable; i++) {
              theTable.rows[i].style.display = 'none';
          }


          totalPage.innerHTML = pageCount();
          pageNum.innerHTML = '1';


          nextLink();
          lastLink();
      }


      hide();
 </script>


</form>
</BODY></HTML>

<%
db.close();
%>


