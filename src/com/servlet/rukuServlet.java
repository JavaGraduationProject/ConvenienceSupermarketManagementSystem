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

public class rukuServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//������
			insertM(request, response);
		}else if(method.equals("list")){//����б�
			ListM(request, response);
		}else if(method.equals("delete")){//���ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//����޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//����޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//�����ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//����б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//������
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//��������
String rkh=(String)request.getParameter("rkh");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String bld=(String)request.getParameter("bld");//������
String rksl=(String)request.getParameter("rksl");//�������
String yh=(String)request.getParameter("yh");//�û�
String czsj=(String)request.getParameter("czsj");//����ʱ��

		response.setCharacterEncoding("gb2312");

		int row=1;
		try {
			sql="update shangpin set kc=kc+"+rksl+" where spid='"+sp+"' and bld='"+bld+"'";
			db.update(sql);
			
			
	 		sql="insert into ruku(rkh,sp,bld,rksl,yh,czsj) values('"+rkh+"','"+sp+"','"+bld+"','"+rksl+"','"+yh+"','"+czsj+"')";
	 		;//���SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			request.getRequestDispatcher("ruku/rukuadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//�����Ϣ�б�
		ResultSet rs=null;
		String sql="select a.*,b.spmc from ruku a,shangpin b  where 1=1 and a.sp=b.spid ";//��ѯ��� sql���

		
		if(request.getSession().getAttribute("yhm")!=null){
			
			sql+=" and a.bld in (select bld from yuangong where yhm='"+request.getSession().getAttribute("yhm")+"')";
			
		}
		
		String rkh=(String)request.getParameter("rkh");//����
		if(rkh!=null&&!rkh.equals("")){
		sql+=" and a.rkh like '%"+rkh+"%'";//�������� ƴ��sql ����
		}
		
		String sp=(String)request.getParameter("sp");//��Ʒ
		if(sp!=null&&!sp.equals("")){
		sql+=" and b.spmc like '%"+sp+"%'";//�������� ƴ��sql ��Ʒ
		}
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//�����Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("ruku/rukulist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//����ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select a.*,b.spmc from ruku a,shangpin b  where 1=1 and a.sp=b.spid ";//��ѯ��� sql���
		
		
		if(request.getSession().getAttribute("yhm")!=null){
			
			sql+=" and a.bld in (select bld from yuangong where yhm='"+request.getSession().getAttribute("yhm")+"')";
			
		}
		
		String rkh=(String)request.getParameter("rkh");//����
		if(rkh!=null&&!rkh.equals("")){
		sql+=" and a.rkh like '%"+rkh+"%'";//�������� ƴ��sql ����
		}
		
		String sp=(String)request.getParameter("sp");//��Ʒ
		if(sp!=null&&!sp.equals("")){
		sql+=" and b.spmc like '%"+sp+"%'";//�������� ƴ��sql ��Ʒ
		}
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
		

		request.getRequestDispatcher("ruku/rukuPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//����޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//��������
String rkh=(String)request.getParameter("rkh");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String bld=(String)request.getParameter("bld");//������
String rksl=(String)request.getParameter("rksl");//�������
String yh=(String)request.getParameter("yh");//�û�
String czsj=(String)request.getParameter("czsj");//����ʱ��

	
		String sql="select * from ruku where rkid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("ruku/rukumodify.jsp").forward(request, response);
			//response.sendRedirect("ruku/rukumodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//�����ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//��������
String rkh=(String)request.getParameter("rkh");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String bld=(String)request.getParameter("bld");//������
String rksl=(String)request.getParameter("rksl");//�������
String yh=(String)request.getParameter("yh");//�û�
String czsj=(String)request.getParameter("czsj");//����ʱ��

	
		String sql="select * from ruku where rkid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("ruku/rukudetail.jsp").forward(request, response);
			//response.sendRedirect("ruku/rukumodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//���ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from ruku where rkid='"+keyid+"'";//ɾ����� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("rukuServlet?method=list").forward(request, response);
		
			//response.sendRedirect("rukuServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//����޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//��������
String rkh=(String)request.getParameter("rkh");//����
String sp=(String)request.getParameter("sp");//��Ʒ
String bld=(String)request.getParameter("bld");//������
String rksl=(String)request.getParameter("rksl");//�������
String yh=(String)request.getParameter("yh");//�û�
String czsj=(String)request.getParameter("czsj");//����ʱ��


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update ruku set rkh='"+rkh+"',sp='"+sp+"',bld='"+bld+"',rksl='"+rksl+"',yh='"+yh+"',czsj='"+czsj+"' where rkid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("rukuServlet?method=list").forward(request, response);
			
			//response.sendRedirect("rukuServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
