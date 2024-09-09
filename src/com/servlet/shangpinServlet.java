package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.*;
import java.util.List;
import java.sql.ResultSet;

public class shangpinServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//��Ʒ���
			insertM(request, response);
		}else if(method.equals("list")){//��Ʒ�б�
			ListM(request, response);
		}else if(method.equals("delete")){//��Ʒɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//��Ʒ�޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//��Ʒ�޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//��Ʒ��ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//��Ʒ�б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//��Ʒ���
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//��Ʒ������
String spmc=(String)request.getParameter("spmc");//��Ʒ����
String js=(String)request.getParameter("js");//����
String tp=(String)request.getParameter("tp");//ͼƬ
String jg=(String)request.getParameter("jg");//�۸�
String kc=(String)request.getParameter("kc");//���
String bld=(String)request.getParameter("bld");//������
String gys=(String)request.getParameter("gys");
		response.setCharacterEncoding("gb2312");
 
		int row=1;
		int num=0;
		ResultSet rs=null;
		try {
			sql="select count(1) as num from shangpin where spmc='"+spmc+"'  and bld='"+bld+"'";
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
			}
			if(num>0){
				request.setAttribute("flag", "����ʧ�ܣ���Ʒ�����ظ�"); 
			}else{
			
			sql="insert into shangpin(spmc,js,tp,jg,kc,bld,gys) values('"+spmc+"','"+js+"','"+tp+"','"+jg+"','"+kc+"','"+bld+"','"+gys+"')";
			;//��ƷSQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			}
			
			
			request.getRequestDispatcher("shangpin/shangpinadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//��Ʒ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from shangpin where 1=1 ";//��ѯ��Ʒ sql���
		
		if(request.getSession().getAttribute("yhm")!=null){
			
			sql+=" and bld in (select bld from yuangong where yhm='"+request.getSession().getAttribute("yhm")+"')";
			
		}
		
		String spmc=(String)request.getParameter("spmc");//��Ʒ����
		if(spmc!=null&&!spmc.equals("")){
		sql+=" and spmc like '%"+spmc+"%'";//�������� ƴ��sql ��Ʒ����
		}
		
		String js=(String)request.getParameter("js");//����
		if(js!=null&&!js.equals("")){
		sql+=" and js like '%"+js+"%'";//�������� ƴ��sql ����
		}
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//��Ʒ��Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("shangpin/shangpinlist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//��Ʒ��ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from shangpin where 1=1 ";//��ѯ��Ʒ sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
	   
	    String str=(String)request.getParameter("Page");
		
		response.setCharacterEncoding("gb2312");
		try{
			
if(str==null){
				str="0";
				}
rs=db.query(sql);

			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

		request.getRequestDispatcher("shangpin/shangpinPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//��Ʒ�޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//��Ʒ������
String spmc=(String)request.getParameter("spmc");//��Ʒ����
String js=(String)request.getParameter("js");//����
String tp=(String)request.getParameter("tp");//ͼƬ
String jg=(String)request.getParameter("jg");//�۸�
String kc=(String)request.getParameter("kc");//���
String bld=(String)request.getParameter("bld");//������
String gys=(String)request.getParameter("gys");

	
		String sql="select * from shangpin where spid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("shangpin/shangpinmodify.jsp").forward(request, response);
			//response.sendRedirect("shangpin/shangpinmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//��Ʒ��ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//��Ʒ������
String spmc=(String)request.getParameter("spmc");//��Ʒ����
String js=(String)request.getParameter("js");//����
String tp=(String)request.getParameter("tp");//ͼƬ
String jg=(String)request.getParameter("jg");//�۸�
String kc=(String)request.getParameter("kc");//���
String bld=(String)request.getParameter("bld");//������
String gys=(String)request.getParameter("gys");
	
		String sql="select * from shangpin where spid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("shangpin/shangpindetail.jsp").forward(request, response);
			//response.sendRedirect("shangpin/shangpinmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//��Ʒɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from shangpin where spid='"+keyid+"'";//ɾ����Ʒ ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("shangpinServlet?method=list").forward(request, response);
		
			//response.sendRedirect("shangpinServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//��Ʒ�޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//��Ʒ������
String spmc=(String)request.getParameter("spmc");//��Ʒ����
String js=(String)request.getParameter("js");//����
String tp=(String)request.getParameter("tp");//ͼƬ
String jg=(String)request.getParameter("jg");//�۸�
String kc=(String)request.getParameter("kc");//���
String bld=(String)request.getParameter("bld");//������
String gys=(String)request.getParameter("gys");

	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update shangpin set spmc='"+spmc+"',js='"+js+"',tp='"+tp+"',jg='"+jg+"',kc='"+kc+"',bld='"+bld+"',gys='"+gys+"' where spid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("shangpinServlet?method=list").forward(request, response);
			
			//response.sendRedirect("shangpinServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
