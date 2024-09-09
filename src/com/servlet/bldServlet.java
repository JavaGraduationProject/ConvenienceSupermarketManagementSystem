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

public class bldServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//���������
			insertM(request, response);
		}else if(method.equals("list")){//�������б�
			ListM(request, response);
		}else if(method.equals("delete")){//������ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//�������޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//�������޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//��������ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//�������б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//���������
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//�����������
String mc=(String)request.getParameter("mc");//����
String dz=(String)request.getParameter("dz");//��ַ
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String fzr=(String)request.getParameter("fzr");//������

		response.setCharacterEncoding("gb2312");
 	
		int row=1;
		int num=0;
		ResultSet rs=null;
		
		try {
			sql="select count(1) as num from bld where mc='"+mc+"'";
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
				
			}
			if(num>0){
				request.setAttribute("flag", "����ʧ�ܡ������ظ�"); 
			}else{
			
			
			
			sql="insert into bld(mc,dz,lxdh,fzr) values('"+mc+"','"+dz+"','"+lxdh+"','"+fzr+"')";
			;//������SQL ���
			
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			}
			
			
			request.getRequestDispatcher("bld/bldadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//��������Ϣ�б�
		ResultSet rs=null;
		String sql="select * from bld where 1=1 ";//��ѯ������ sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		String mc=(String)request.getParameter("mc");//����
		if(mc!=null&&!mc.equals("")){
		sql+=" and mc like '%"+mc+"%'";//�������� ƴ��sql ����
		}
		
		String dz=(String)request.getParameter("dz");//��ַ
		if(dz!=null&&!dz.equals("")){
		sql+=" and dz like '%"+dz+"%'";//�������� ƴ��sql ��ַ
		}
		//��������Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("bld/bldlist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//�������ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from bld where 1=1 ";//��ѯ������ sql���
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
		

		request.getRequestDispatcher("bld/bldPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�������޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//�����������
String mc=(String)request.getParameter("mc");//����
String dz=(String)request.getParameter("dz");//��ַ
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String fzr=(String)request.getParameter("fzr");//������

	
		String sql="select * from bld where bldid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("bld/bldmodify.jsp").forward(request, response);
			//response.sendRedirect("bld/bldmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//��������ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//�����������
String mc=(String)request.getParameter("mc");//����
String dz=(String)request.getParameter("dz");//��ַ
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String fzr=(String)request.getParameter("fzr");//������

	
		String sql="select * from bld where bldid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("bld/blddetail.jsp").forward(request, response);
			//response.sendRedirect("bld/bldmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//������ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from bld where bldid='"+keyid+"'";//ɾ�������� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("bldServlet?method=list").forward(request, response);
		
			//response.sendRedirect("bldServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�������޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//�����������
String mc=(String)request.getParameter("mc");//����
String dz=(String)request.getParameter("dz");//��ַ
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String fzr=(String)request.getParameter("fzr");//������


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update bld set mc='"+mc+"',dz='"+dz+"',lxdh='"+lxdh+"',fzr='"+fzr+"' where bldid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("bldServlet?method=list").forward(request, response);
			
			//response.sendRedirect("bldServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
