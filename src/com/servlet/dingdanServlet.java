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

public class dingdanServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//�������
			insertM(request, response);
		}else if(method.equals("list")){//�����б�
			ListM(request, response);
		}else if(method.equals("mylist")){//�����б�
			mylist(request, response);
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
			String keyid =(String)request.getParameter("keyid");//����������
String ddh=(String)request.getParameter("ddh");//������
String xdsj=(String)request.getParameter("xdsj");//�µ�ʱ��
String yh=(String)request.getParameter("yh");//�û�
String zjg=(String)request.getParameter("zjg");//�ܼ۸�
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ
String zt=(String)request.getParameter("zt");//״̬
String bld=(String)request.getParameter("bld");//������

		response.setCharacterEncoding("gb2312");
 		sql="insert into dingdan(ddh,xdsj,yh,zjg,lxdh,lxdz,zt,bld) values('"+ddh+"','"+xdsj+"','"+yh+"','"+zjg+"','"+lxdh+"','"+lxdz+"','"+zt+"','"+bld+"')";
;//����SQL ���
		int row=1;
		try {
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			request.getRequestDispatcher("dingdan/dingdanadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//������Ϣ�б�
		ResultSet rs=null;
		String sql="select * from dingdan where 1=1 ";//��ѯ���� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		 if(request.getSession().getAttribute("yhm")!=null){
			 
			 sql+=" and bld in (select bld from yuangong where yhm='"+request.getSession().getAttribute("yhm")+"')";
			 }
		//������Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("dingdan/dingdanlist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	
	private void mylist(HttpServletRequest request, HttpServletResponse response){//������Ϣ�б�
		ResultSet rs=null;
		String sql="select * from dingdan where 1=1 ";//��ѯ���� sql���
		if(request.getSession().getAttribute("yhm")!=null){
			sql+=" and yh='"+request.getSession().getAttribute("yhm")+"'";
			
		}
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//������Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("dingdan/mylist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//������ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from dingdan where 1=1 ";//��ѯ���� sql���
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
		

		request.getRequestDispatcher("dingdan/dingdanPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�����޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//����������
String ddh=(String)request.getParameter("ddh");//������
String xdsj=(String)request.getParameter("xdsj");//�µ�ʱ��
String yh=(String)request.getParameter("yh");//�û�
String zjg=(String)request.getParameter("zjg");//�ܼ۸�
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ
String zt=(String)request.getParameter("zt");//״̬
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from dingdan where ddid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("dingdan/dingdanmodify.jsp").forward(request, response);
			//response.sendRedirect("dingdan/dingdanmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//������ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//����������
String ddh=(String)request.getParameter("ddh");//������
String xdsj=(String)request.getParameter("xdsj");//�µ�ʱ��
String yh=(String)request.getParameter("yh");//�û�
String zjg=(String)request.getParameter("zjg");//�ܼ۸�
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ
String zt=(String)request.getParameter("zt");//״̬
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from dingdan where ddid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("dingdan/dingdandetail.jsp").forward(request, response);
			//response.sendRedirect("dingdan/dingdanmodify.jsp");
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
  sql="delete from dingdan where ddid='"+keyid+"'";//ɾ������ ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("dingdanServlet?method=list").forward(request, response);
		
			//response.sendRedirect("dingdanServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�����޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//����������
String ddh=(String)request.getParameter("ddh");//������
String xdsj=(String)request.getParameter("xdsj");//�µ�ʱ��
String yh=(String)request.getParameter("yh");//�û�
String zjg=(String)request.getParameter("zjg");//�ܼ۸�
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ
String zt=(String)request.getParameter("zt");//״̬
String bld=(String)request.getParameter("bld");//������


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update dingdan set ddh='"+ddh+"',xdsj='"+xdsj+"',yh='"+yh+"',zjg='"+zjg+"',lxdh='"+lxdh+"',lxdz='"+lxdz+"',zt='"+zt+"',bld='"+bld+"' where ddid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("dingdanServlet?method=list").forward(request, response);
			
			//response.sendRedirect("dingdanServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
