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

public class diaopeiServlet extends HttpServlet  {
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
			String keyid =(String)request.getParameter("keyid");//���������
String sp=(String)request.getParameter("sp");//��Ʒ
String ybld=(String)request.getParameter("ybld");//ԭ������
String xbld=(String)request.getParameter("xbld");//�±�����
String drsl=(String)request.getParameter("drsl");//��������
String czsj=(String)request.getParameter("czsj");//����ʱ��
String yh=(String)request.getParameter("yh");//�û�

		response.setCharacterEncoding("gb2312");
 		
		ResultSet rs=null;
		
		int row=1;
		int num=0;
		
		int sl=0;
		try {
			
			
			sql="select count(1) as num from shangpin where spmc='"+sp+"' and bld='"+xbld+"'";
			
			rs=db.query(sql);
			if(rs.next()){
				num=rs.getInt("num");
			}
			
			if(num>0){
			
				
				sql="select * from shangpin where spmc='"+sp+"' and bld='"+ybld+"'";
				
				rs=db.query(sql);
				if(rs.next()){
					
					sl=rs.getInt("kc");
				}
				if(sl>Integer.parseInt(drsl)){//��ǰ������ �����������Ե���
				
			sql="update shangpin set kc=kc-'"+drsl+"'  where  spmc='"+sp+"' and bld='"+ybld+"'";//ԭʼ  ���� 
			
			db.update(sql);
			
			sql="update shangpin set kc=kc+'"+drsl+"'  where  spmc='"+sp+"' and bld='"+xbld+"'";//��   ����
			
			db.update(sql);
			
			
			sql="insert into diaopei(sp,ybld,xbld,drsl,czsj,yh) values('"+sp+"','"+ybld+"','"+xbld+"','"+drsl+"','"+czsj+"','"+yh+"')";
			;//����SQL ���
			row=db.update(sql);//ִ��sql��� insert���
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			
				}else{
					
					request.setAttribute("flag", "����ʧ��,��治��"); 
					
				}
			
			
			
			
			
			
			}else{
				
				
				request.setAttribute("flag", "����ʧ��,�±������޴���Ʒ"); 
				
			}
			
			
			request.getRequestDispatcher("diaopei/diaopeiadd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	private void ListM(HttpServletRequest request, HttpServletResponse response){//������Ϣ�б�
		ResultSet rs=null;
		String sql="select * from diaopei where 1=1 ";//��ѯ���� sql���
		DBO db=new DBO();//��ʼ���������ӻ�����
		db.open();
		
		//������Ϣ��ѯ
		response.setCharacterEncoding("gb2312");
		try{
			rs=db.query(sql);//��ѯ���

			//PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
		

			request.getRequestDispatcher("diaopei/diaopeilist.jsp").forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void PListM(HttpServletRequest request, HttpServletResponse response){//�����ҳ��Ϣ�б�
		ResultSet rs=null;
		String sql="select * from diaopei where 1=1 ";//��ѯ���� sql���
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
		

		request.getRequestDispatcher("diaopei/diaopeiPlist.jsp?Page="+str).forward(request, response);
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void modifyIndex(HttpServletRequest request, HttpServletResponse response){//�����޸���ҳ
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		
		String keyid =(String)request.getParameter("keyid");//���������
String sp=(String)request.getParameter("sp");//��Ʒ
String ybld=(String)request.getParameter("ybld");//ԭ������
String xbld=(String)request.getParameter("xbld");//�±�����
String drsl=(String)request.getParameter("drsl");//��������
String czsj=(String)request.getParameter("czsj");//����ʱ��
String yh=(String)request.getParameter("yh");//�û�

	
		String sql="select * from diaopei where dpid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();//��ʼ�����ݿ�����
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("diaopei/diaopeimodify.jsp").forward(request, response);
			//response.sendRedirect("diaopei/diaopeimodify.jsp");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}
	private void detailM(HttpServletRequest request, HttpServletResponse response){//������ϸ��Ϣҳ��
		
			ResultSet rs=null;
	response.setCharacterEncoding("gb2312");

		
		String keyid =(String)request.getParameter("keyid");//���������
String sp=(String)request.getParameter("sp");//��Ʒ
String ybld=(String)request.getParameter("ybld");//ԭ������
String xbld=(String)request.getParameter("xbld");//�±�����
String drsl=(String)request.getParameter("drsl");//��������
String czsj=(String)request.getParameter("czsj");//����ʱ��
String yh=(String)request.getParameter("yh");//�û�

	
		String sql="select * from diaopei where dpid="+keyid;//��ѯһ��Ҫ�޸ĵ���Ϣ
		DBO db=new DBO();//��ʼ���������ӻ�����
		
		try{
			
			db.open();
			rs=db.query(sql);//��ѯsql���
			PrintWriter out = response.getWriter();
			request.setAttribute("rs", rs); 
			
			request.getRequestDispatcher("diaopei/diaopeidetail.jsp").forward(request, response);
			//response.sendRedirect("diaopei/diaopeimodify.jsp");
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
  sql="delete from diaopei where dpid='"+keyid+"'";//ɾ������ ��Ϣsql���
		try{
					 row=db.update(sql);// ִ��sql���

				if(row==1){
				request.setAttribute("flag", "ɾ���ɹ�"); 
				}else{
				request.setAttribute("flag", "ɾ��ʧ��"); 
				}
		request.getRequestDispatcher("diaopeiServlet?method=list").forward(request, response);
		
			//response.sendRedirect("diaopeiServlet?method=list");
		}catch(Exception e){
			e.toString();
			e.printStackTrace();
		}
		
	}

	private void modifyM(HttpServletRequest request, HttpServletResponse response){//�����޸Ĺ���
	DBO db=new DBO();
		String keyid =(String)request.getParameter("keyid");//���������
String sp=(String)request.getParameter("sp");//��Ʒ
String ybld=(String)request.getParameter("ybld");//ԭ������
String xbld=(String)request.getParameter("xbld");//�±�����
String drsl=(String)request.getParameter("drsl");//��������
String czsj=(String)request.getParameter("czsj");//����ʱ��
String yh=(String)request.getParameter("yh");//�û�


	response.setCharacterEncoding("gb2312");
	String sql="";
       
sql="update diaopei set sp='"+sp+"',ybld='"+ybld+"',xbld='"+xbld+"',drsl='"+drsl+"',czsj='"+czsj+"',yh='"+yh+"' where dpid='"+keyid+"'";

	int row=1;
		try {
					
		 row=db.update(sql);
			
			if(row==1){
			request.setAttribute("flag", "�����ɹ�"); 
			}else{
			request.setAttribute("flag", "����ʧ��"); 
			}
			request.getRequestDispatcher("diaopeiServlet?method=list").forward(request, response);
			
			//response.sendRedirect("diaopeiServlet?method=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
}
