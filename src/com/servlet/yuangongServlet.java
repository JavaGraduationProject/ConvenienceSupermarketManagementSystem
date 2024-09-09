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

public class yuangongServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//Ա�����
			insertM(request, response);
		}else if(method.equals("list")){//Ա���б�
			ListM(request, response);
		}else if(method.equals("delete")){//Ա��ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//Ա���޸�ҳ��
			modifyIndex(request, response);
		}else if(method.equals("mod")){//Ա���޸�ҳ��
			mod(request, response);
		}else if(method.equals("update")){//Ա���޸�ҳ��
			update(request, response);
		}
		else if(method.equals("modify")){//Ա���޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//Ա����ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//Ա���б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//Ա�����
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//Ա��������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String bld=(String)request.getParameter("bld");//������

		response.setCharacterEncoding("gb2312");
 	
		
		int num=0;
		int row=1;
		ResultSet rs=null;
		try {
				
			sql="select count(1) as num from yuangong where yhm='"+yhm+"'";
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
			}
			if(num>0){
				request.setAttribute("flag", "����ʧ��,�û����ظ�"); 
			}else{
				sql="insert into yuangong(yhm,mm,xm,bld) values('"+yhm+"','"+mm+"','"+xm+"','"+bld+"')";
				;//Ա��SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			}
			
			request.getRequestDispatcher("yuangong/yuangongadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//Ա����Ϣ�б�
		ResultSet rs=null;
		String sql="select * from yuangong where 1=1 ";//��ѯԱ�� sql���
		
		String yhm=(String)request.getParameter("yhm");//�û���
		if(yhm!=null&&!yhm.equals("")){
		sql+=" and yhm like '%"+yhm+"%'";//�������� ƴ��sql �û���
		}
		
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//Ա����Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("yuangong/yuangonglist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//Ա����ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from yuangong where 1=1 ";//��ѯԱ�� sql���
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
		

		request.getRequestDispatcher("yuangong/yuangongPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//Ա���޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//Ա��������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from yuangong where ygid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("yuangong/yuangongmodify.jsp").forward(request, response);
			//response.sendRedirect("yuangong/yuangongmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void mod(HttpServletRequest request, HttpServletResponse response){//Ա���޸���ҳ
		ResultSet rs=null;
response.setCharacterEncoding("gb2312");

	
	
	String keyid =(String)request.getSession().getAttribute("id");//Ա��������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String bld=(String)request.getParameter("bld");//������


	String sql="select * from yuangong where ygid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
	DBO db=new DBO();//��ʼ���������ӻ�����
	
	try{
		
		db.open();//��ʼ�����ݿ�����
		rs=db.query(sql);//��ѯsql���
		PrintWriter out = response.getWriter();
		request.setAttribute("rs", rs); 
		
		request.getRequestDispatcher("yuangong/modify.jsp").forward(request, response);
		//response.sendRedirect("yuangong/yuangongmodify.jsp");
	}catch(Exception e){
		e.toString();
		e.printStackTrace();
	}
	
}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//Ա����ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//Ա��������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String bld=(String)request.getParameter("bld");//������

	
		String sql="select * from yuangong where ygid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("yuangong/yuangongdetail.jsp").forward(request, response);
			//response.sendRedirect("yuangong/yuangongmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//Ա��ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from yuangong where ygid='"+keyid+"'";//ɾ��Ա�� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("yuangongServlet?method=list").forward(request, response);
		
			//response.sendRedirect("yuangongServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response){//Ա���޸Ĺ���
		DBO db=new DBO();
		String keyid =(String)request.getSession().getAttribute("id");//�û�������
	String yhm=(String)request.getParameter("yhm");//�û���
	String mm=(String)request.getParameter("mm");//����
	String xm=(String)request.getParameter("xm");//����
	String bld=(String)request.getParameter("bld");//������


		response.setCharacterEncoding("gb2312");
		String sql="";
	       
	sql="update yuangong set yhm='"+yhm+"',mm='"+mm+"',xm='"+xm+"',bld='"+bld+"' where ygid='"+keyid+"'";

		int row=1;
			try {
						
			 row=db.update(sql);
				
				if(row==1){
				request.setAttribute("flag", "�����ɹ�"); 
				}else{
				request.setAttribute("flag", "����ʧ��"); 
				}
				request.getRequestDispatcher("yuangongServlet?method=mod").forward(request, response);
				
				//response.sendRedirect("yuangongServlet?method=list");
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}
	private void modifyM(HttpServletRequest request, HttpServletResponse response){//Ա���޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//Ա��������
String yhm=(String)request.getParameter("yhm");//�û���
String mm=(String)request.getParameter("mm");//����
String xm=(String)request.getParameter("xm");//����
String bld=(String)request.getParameter("bld");//������


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update yuangong set yhm='"+yhm+"',mm='"+mm+"',xm='"+xm+"',bld='"+bld+"' where ygid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("yuangongServlet?method=list").forward(request, response);
			
			//response.sendRedirect("yuangongServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
