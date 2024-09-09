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

public class peisongServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//�������
			insertM(request, response);
		}else if(method.equals("list")){//�����б�
			ListM(request, response);
		}else if(method.equals("delete")){//����ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//�����޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//�����޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//������ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//�����б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//�������
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//���ͱ�����
String dh=(String)request.getParameter("dh");//����
String cfd=(String)request.getParameter("cfd");//������
String cfsj=(String)request.getParameter("cfsj");//����ʱ��
String mdd=(String)request.getParameter("mdd");//Ŀ�ĵ�
String ddsj=(String)request.getParameter("ddsj");//����ʱ��

		response.setCharacterEncoding("gb2312");
 
		int row=1;
		try {
			
			sql="update dingdan set zt='������' where ddh='"+dh+"'";
			
			db.update(sql);
			
			sql="insert into peisong(dh,cfd,cfsj,mdd,ddsj) values('"+dh+"','"+cfd+"','"+cfsj+"','"+mdd+"','"+ddsj+"')";
			;//����SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			request.getRequestDispatcher("peisong/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//������Ϣ�б�
		ResultSet rs=null;
		String sql="select * from peisong where 1=1 ";//��ѯ���� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//������Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("peisong/peisonglist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//���ͷ�ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from peisong where 1=1 ";//��ѯ���� sql���
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
		

		request.getRequestDispatcher("peisong/peisongPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�����޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//���ͱ�����
String dh=(String)request.getParameter("dh");//����
String cfd=(String)request.getParameter("cfd");//������
String cfsj=(String)request.getParameter("cfsj");//����ʱ��
String mdd=(String)request.getParameter("mdd");//Ŀ�ĵ�
String ddsj=(String)request.getParameter("ddsj");//����ʱ��

	
		String sql="select * from peisong where psid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("peisong/peisongmodify.jsp").forward(request, response);
			//response.sendRedirect("peisong/peisongmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//������ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//���ͱ�����
String dh=(String)request.getParameter("dh");//����
String cfd=(String)request.getParameter("cfd");//������
String cfsj=(String)request.getParameter("cfsj");//����ʱ��
String mdd=(String)request.getParameter("mdd");//Ŀ�ĵ�
String ddsj=(String)request.getParameter("ddsj");//����ʱ��

	
		String sql="select * from peisong where psid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("peisong/peisongdetail.jsp").forward(request, response);
			//response.sendRedirect("peisong/peisongmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//����ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from peisong where psid='"+keyid+"'";//ɾ������ ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("peisongServlet?method=list").forward(request, response);
		
			//response.sendRedirect("peisongServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�����޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//���ͱ�����
String dh=(String)request.getParameter("dh");//����
String cfd=(String)request.getParameter("cfd");//������
String cfsj=(String)request.getParameter("cfsj");//����ʱ��
String mdd=(String)request.getParameter("mdd");//Ŀ�ĵ�
String ddsj=(String)request.getParameter("ddsj");//����ʱ��


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update peisong set dh='"+dh+"',cfd='"+cfd+"',cfsj='"+cfsj+"',mdd='"+mdd+"',ddsj='"+ddsj+"' where psid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("peisongServlet?method=list").forward(request, response);
			
			//response.sendRedirect("peisongServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
