<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.jspsmart.upload.*" %>
<%@ page import="java.util.Date"%>
<%
		request.setCharacterEncoding("gb2312");
		String kname=(String)request.getParameter("kname");
		System.out.println("kname="+kname);
		String savePath="upload/";
		String ext="";
		String trace="";
			String filename="";
		try {  
		//ʵ��������bean
    SmartUpload mySmartUpload=new SmartUpload();
    //��ʼ��

    mySmartUpload.initialize(pageContext);
    //�������ص����ֵ
   
    mySmartUpload.setMaxFileSize(500 * 1024*1024);
    
    //�����ļ�
	 mySmartUpload.upload();
   //ѭ��ȡ���������ص��ļ�
    
     for (int i=0;i<mySmartUpload.getFiles().getCount();i++){
   //ȡ�����ص��ļ�
   com.jspsmart.upload.File myFile = mySmartUpload.getFiles().getFile(i);
  
   if (!myFile.isMissing())
    {
   //ȡ�����ص��ļ����ļ���
    String myFileName=myFile.getFileName();
    //ȡ�ò�����׺���ļ���
    //String  suffix=name;
  // System.out.println("myFileName="+myFileName);
    String  suffix=myFileName.substring(0,myFileName.lastIndexOf('.')); //ȡ�ú�׺��
	//System.out.println("suffix="+suffix);
	String   exta=   mySmartUpload.getFiles().getFile(0).getFileExt();  
		mySmartUpload.setAllowedFilesList("doc,ppt,xls,jpg,jpeg,png");   
		if(true){//exta.equals("flv")||exta.equals("avi")||exta.equals("wmv")||exta.equals("jpg")||exta.equals("gif")||exta.equals("png") 
		 ext = myFileName.substring(myFileName.lastIndexOf('.')+1,myFileName.length());
    //ȡ���ļ��Ĵ�С
    int fileSize=myFile.getSize();
    //����·��
    String path=request.getRealPath("/")+savePath;
	System.out.println(path);
	//path="/files/";
	Date date = new Date();
String a=String.valueOf(date.getTime());
	 filename=a+"."+ext;
	String explain=(String)mySmartUpload.getRequest().getParameter("text");
    String send=(String)mySmartUpload.getRequest().getParameter("send");
    //���ļ������ڷ�������
	    trace=path+filename;
    myFile.saveAs(trace,mySmartUpload.SAVE_PHYSICAL);
	
	
	 }
   else{
   
     out.println(("����ʧ�ܣ�����").toString()); }
   }//��ǰ���if��Ӧ
	  
	  
	  }
	  
	 %>
	 <script language="javascript">
	 alert("�ϴ��ɹ�");
	 parent.document.form.<%=kname%>.value="<%=filename%>";
	 
	 window.location="fileupload.jsp";
	 </script>
	 <%
	   }
    catch (Exception ex) {
      out.print(ex.toString());
    }	
%>


