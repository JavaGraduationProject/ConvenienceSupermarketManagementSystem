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

public class ddmxServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//������ϸ���
			insertM(request, response);
		}else if(method.equals("list")){//������ϸ�б�
			ListM(request, response);
		}else if(method.equals("delete")){//������ϸɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//������ϸ�޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//������ϸ�޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//������ϸ��ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//������ϸ�б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//������ϸ���
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//������ϸ������
String dd=(String)request.getParameter("dd");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String sl=(String)request.getParameter("sl");//����
String jg=(String)request.getParameter("jg");//�۸�
String yh=(String)request.getParameter("yh");//�û�
String bld=(String)request.getParameter("bld");//������

		response.setCharacterEncoding("gb2312");
 		sql="insert into ddmx(dd,sp,sl,jg,yh,bld) values('"+dd+"','"+sp+"','"+sl+"','"+jg+"','"+yh+"','"+bld+"')";
;//������ϸSQL ���
		int row=1;
		try {
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			request.getRequestDispatcher("ddmx/ddmxadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//������ϸ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from ddmx where 1=1 ";//��ѯ������ϸ sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//������ϸ��Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("ddmx/ddmxlist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//������ϸ��ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from ddmx where 1=1 ";//��ѯ������ϸ sql���
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
		

		request.getRequestDispatcher("ddmx/ddmxPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//������ϸ�޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//������ϸ������
String dd=(String)request.getParameter("dd");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String sl=(String)request.getParameter("sl");//����
String jg=(String)request.getParameter("jg");//�۸�
String yh=(String)request.getParameter("yh");//�û�
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from ddmx where ddmxid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("ddmx/ddmxmodify.jsp").forward(request, response);
			//response.sendRedirect("ddmx/ddmxmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//������ϸ��ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//������ϸ������
String dd=(String)request.getParameter("dd");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String sl=(String)request.getParameter("sl");//����
String jg=(String)request.getParameter("jg");//�۸�
String yh=(String)request.getParameter("yh");//�û�
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from ddmx where ddmxid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("ddmx/ddmxdetail.jsp").forward(request, response);
			//response.sendRedirect("ddmx/ddmxmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//������ϸɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from ddmx where ddmxid='"+keyid+"'";//ɾ��������ϸ ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("ddmxServlet?method=list").forward(request, response);
		
			//response.sendRedirect("ddmxServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//������ϸ�޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//������ϸ������
String dd=(String)request.getParameter("dd");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String sl=(String)request.getParameter("sl");//����
String jg=(String)request.getParameter("jg");//�۸�
String yh=(String)request.getParameter("yh");//�û�
String bld=(String)request.getParameter("bld");//������


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update ddmx set dd='"+dd+"',sp='"+sp+"',sl='"+sl+"',jg='"+jg+"',yh='"+yh+"',bld='"+bld+"' where ddmxid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("ddmxServlet?method=list").forward(request, response);
			
			//response.sendRedirect("ddmxServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
