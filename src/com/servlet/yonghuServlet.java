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

public class yonghuServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//�û����
			insertM(request, response);
		}else if(method.equals("list")){//�û��б�
			ListM(request, response);
		}else if(method.equals("delete")){//�û�ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//�û��޸�ҳ��
			modifyIndex(request, response);
		}else if(method.equals("mod")){//�û��޸�ҳ��
			mod(request, response);
		}
		else if(method.equals("update")){//�û��޸�ҳ��
			update(request, response);
		}
		else if(method.equals("modify")){//�û��޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//�û���ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//�û��б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//�û����
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//�û�������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ

		response.setCharacterEncoding("gb2312");
 	
		int num=0;
		int row=1;
		ResultSet rs=null;
		try {
				
			sql="select count(1) as num from yonghu where yhm='"+yhm+"'";
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
			}
			if(num>0){
				request.setAttribute("flag", "����ʧ��,�û����ظ�"); 
			}else{
			
			
			
			
				sql="insert into yonghu(yhm,mm,xm,lxdh,lxdz) values('"+yhm+"','"+mm+"','"+xm+"','"+lxdh+"','"+lxdz+"')";
;//�û�SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
			
			}
			
			
			request.getRequestDispatcher("yonghu/yonghuadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//�û���Ϣ�б�
		ResultSet rs=null;
		String sql="select * from yonghu where 1=1 ";//��ѯ�û� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		
		String yhm=(String)request.getParameter("yhm");//�û���
		if(yhm!=null&&!yhm.equals("")){
		sql+=" and yhm like '%"+yhm+"%'";//�������� ƴ��sql �û���
		}
		
		
		//�û���Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("yonghu/yonghulist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//�û���ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from yonghu where 1=1 ";//��ѯ�û� sql���
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
		

		request.getRequestDispatcher("yonghu/yonghuPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�û��޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//�û�������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ

	
		String sql="select * from yonghu where yhid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("yonghu/yonghumodify.jsp").forward(request, response);
			//response.sendRedirect("yonghu/yonghumodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	
	private void mod(HttpServletRequest request, HttpServletResponse response){//�û��޸���ҳ
		ResultSet rs=null;
response.setCharacterEncoding("gb2312");

	
	
	String keyid =(String)request.getSession().getAttribute("id");//�û�������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ


	String sql="select * from yonghu where yhid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
	DBO db=new DBO();//��ʼ���������ӻ�����
	
	try{
		
		db.open();//��ʼ�����ݿ�����
		rs=db.query(sql);//��ѯsql���
		PrintWriter out = response.getWriter();
		request.setAttribute("rs", rs); 
		
		request.getRequestDispatcher("yonghu/modify.jsp").forward(request, response);
		//response.sendRedirect("yonghu/yonghumodify.jsp");
	}catch(Exception e){
		e.toString();
		e.printStackTrace();
	}
	
}
	
	private void detailM(HttpServletRequest request, HttpServletResponse response){//�û���ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//�û�������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ

	
		String sql="select * from yonghu where yhid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("yonghu/yonghudetail.jsp").forward(request, response);
			//response.sendRedirect("yonghu/yonghumodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//�û�ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from yonghu where yhid='"+keyid+"'";//ɾ���û� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("yonghuServlet?method=list").forward(request, response);
		
			//response.sendRedirect("yonghuServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�û��޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//�û�������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update yonghu set yhm='"+yhm+"',mm='"+mm+"',xm='"+xm+"',lxdh='"+lxdh+"',lxdz='"+lxdz+"' where yhid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("yonghuServlet?method=list").forward(request, response);
			
			//response.sendRedirect("yonghuServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	private void update(HttpServletRequest request, HttpServletResponse response){//�û��޸Ĺ���
		DBO db=new DBO();
			String keyid =(String)request.getSession().getAttribute("id");//�û�������
	String yhm=(String)request.getParameter("yhm");//�û���
	String mm=(String)request.getParameter("mm");//����
	String xm=(String)request.getParameter("xm");//����
	String lxdh=(String)request.getParameter("lxdh");//��ϵ�绰
	String lxdz=(String)request.getParameter("lxdz");//��ϵ��ַ


		response.setCharacterEncoding("gb2312");
		String sql="";
	       
	sql="update yonghu set yhm='"+yhm+"',mm='"+mm+"',xm='"+xm+"',lxdh='"+lxdh+"',lxdz='"+lxdz+"' where yhid='"+keyid+"'";

		int row=1;
			try {
					System.out.println(sql);	
			 row=db.update(sql);
				
				if(row==1){
				request.setAttribute("flag", "�����ɹ�"); 
				}else{
				request.setAttribute("flag", "����ʧ��"); 
				}
				request.getRequestDispatcher("yonghuServlet?method=mod").forward(request, response);
				
				//response.sendRedirect("yonghuServlet?method=list");
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
}
