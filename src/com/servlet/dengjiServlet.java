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

public class dengjiServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//�ȼ����
			insertM(request, response);
		}else if(method.equals("list")){//�ȼ��б�
			ListM(request, response);
		}else if(method.equals("delete")){//�ȼ�ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//�ȼ��޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//�ȼ��޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//�ȼ���ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//�ȼ��б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//�ȼ����
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//�ȼ�������
String dj=(String)request.getParameter("dj");//�ȼ�

		response.setCharacterEncoding("gb2312");
 		sql="insert into dengji(dj) values('"+dj+"')";
;//�ȼ�SQL ���
		int row=1;
		try {
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			request.getRequestDispatcher("dengji/dengjiadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//�ȼ���Ϣ�б�
		ResultSet rs=null;
		String sql="select * from dengji where 1=1 ";//��ѯ�ȼ� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		String dj=(String)request.getParameter("dj");//�ȼ�
		if(dj!=null&&!dj.equals("")){
		sql+=" and dj like '%"+dj+"%'";//�������� ƴ��sql �ȼ�
		}
		
		//�ȼ���Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("dengji/dengjilist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//�ȼ���ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from dengji where 1=1 ";//��ѯ�ȼ� sql���
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
		

		request.getRequestDispatcher("dengji/dengjiPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�ȼ��޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//�ȼ�������
String dj=(String)request.getParameter("dj");//�ȼ�

	
		String sql="select * from dengji where djid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("dengji/dengjimodify.jsp").forward(request, response);
			//response.sendRedirect("dengji/dengjimodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//�ȼ���ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//�ȼ�������
String dj=(String)request.getParameter("dj");//�ȼ�

	
		String sql="select * from dengji where djid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("dengji/dengjidetail.jsp").forward(request, response);
			//response.sendRedirect("dengji/dengjimodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//�ȼ�ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from dengji where djid='"+keyid+"'";//ɾ���ȼ� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("dengjiServlet?method=list").forward(request, response);
		
			//response.sendRedirect("dengjiServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�ȼ��޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//�ȼ�������
String dj=(String)request.getParameter("dj");//�ȼ�


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update dengji set dj='"+dj+"' where djid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("dengjiServlet?method=list").forward(request, response);
			
			//response.sendRedirect("dengjiServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
