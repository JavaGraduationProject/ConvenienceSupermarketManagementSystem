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

public class gysServlet extends HttpServlet  {
	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("gb2312");
		System.out.println("method"+request.getParameter("method"));
		String method=(String)request.getParameter("method");
		if(method.equals("insert")){//��Ӧ�����
			insertM(request, response);
		}else if(method.equals("list")){//��Ӧ���б�
			ListM(request, response);
		}else if(method.equals("delete")){//��Ӧ��ɾ��
			deleteM(request, response);
		}else if(method.equals("modifyindex")){//��Ӧ���޸�ҳ��
			modifyIndex(request, response);
		}
		else if(method.equals("modify")){//��Ӧ���޸�
			modifyM(request, response);
		}else if(method.equals("detail")){//��Ӧ����ϸ��Ϣ
			detailM(request, response);
		}else if(method.equals("Plist")){//��Ӧ���б���Ϣ
			PListM(request, response);
		}
	}
	private void insertM(HttpServletRequest request, HttpServletResponse response){//��Ӧ�����
		DBO db=new DBO();//��ʼ���������ӻ�����
		String sql="";//sql����ʼ��
			String keyid =(String)request.getParameter("keyid");//��Ӧ�̱�����
String gysmc=(String)request.getParameter("gysmc");//��Ӧ������
String js=(String)request.getParameter("js");//����
String jyfw=(String)request.getParameter("jyfw");//��Ӫ��Χ
String dj=(String)request.getParameter("dj");//�ȼ�

		response.setCharacterEncoding("gb2312");
 	
		int row=1;
		ResultSet rs=null;
		int num=0;
		try {
			sql="select count(1) as num from gys where gysmc='"+gysmc+"'";
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
			}
			if(num>0){
			
				request.setAttribute("flag", "����ʧ��,�����ظ�"); 
			}else{
			
			sql="insert into gys(gysmc,js,jyfw,dj) values('"+gysmc+"','"+js+"','"+jyfw+"','"+dj+"')";
			;//��Ӧ��SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			}
			
			
			request.getRequestDispatcher("gys/gysadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//��Ӧ����Ϣ�б�
		ResultSet rs=null;
		String sql="select * from gys where 1=1 ";//��ѯ��Ӧ�� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		String gysmc=(String)request.getParameter("gysmc");//��Ӧ������
		if(gysmc!=null&&!gysmc.equals("")){
		sql+=" and gysmc like '%"+gysmc+"%'";//�������� ƴ��sql ��Ӧ������
		}
		
		String js=(String)request.getParameter("js");//����
		if(js!=null&&!js.equals("")){
		sql+=" and js like '%"+js+"%'";//�������� ƴ��sql ����
		}
		
		//��Ӧ����Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("gys/gyslist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//��Ӧ�̷�ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from gys where 1=1 ";//��ѯ��Ӧ�� sql���
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
		

		request.getRequestDispatcher("gys/gysPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//��Ӧ���޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//��Ӧ�̱�����
String gysmc=(String)request.getParameter("gysmc");//��Ӧ������
String js=(String)request.getParameter("js");//����
String jyfw=(String)request.getParameter("jyfw");//��Ӫ��Χ
String dj=(String)request.getParameter("dj");//�ȼ�

	
		String sql="select * from gys where gysid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("gys/gysmodify.jsp").forward(request, response);
			//response.sendRedirect("gys/gysmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//��Ӧ����ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//��Ӧ�̱�����
String gysmc=(String)request.getParameter("gysmc");//��Ӧ������
String js=(String)request.getParameter("js");//����
String jyfw=(String)request.getParameter("jyfw");//��Ӫ��Χ
String dj=(String)request.getParameter("dj");//�ȼ�

	
		String sql="select * from gys where gysid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("gys/gysdetail.jsp").forward(request, response);
			//response.sendRedirect("gys/gysmodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void deleteM(HttpServletRequest request, HttpServletResponse response){//��Ӧ��ɾ��
		
		DBO db=new DBO();
	  response.setCharacterEncoding("gb2312");
	int row=1;
	String keyid=(String)request.getParameter("keyid"); 
	String sql="";
  sql="delete from gys where gysid='"+keyid+"'";//ɾ����Ӧ�� ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("gysServlet?method=list").forward(request, response);
		
			//response.sendRedirect("gysServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//��Ӧ���޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//��Ӧ�̱�����
String gysmc=(String)request.getParameter("gysmc");//��Ӧ������
String js=(String)request.getParameter("js");//����
String jyfw=(String)request.getParameter("jyfw");//��Ӫ��Χ
String dj=(String)request.getParameter("dj");//�ȼ�


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update gys set gysmc='"+gysmc+"',js='"+js+"',jyfw='"+jyfw+"',dj='"+dj+"' where gysid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("gysServlet?method=list").forward(request, response);
			
			//response.sendRedirect("gysServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
