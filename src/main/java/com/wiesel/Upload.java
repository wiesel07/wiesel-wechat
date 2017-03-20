package com.wiesel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		upload(request, response);
	}

	
	public void upload(HttpServletRequest request, HttpServletResponse response){
		String pathString=request.getRealPath("");
		//System.out.println(pathString);
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}  
        response.setCharacterEncoding("utf-8");  
        //1、创建一个DiskFileItemFactory工厂  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        //2、创建一个文件上传解析器  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        //解决上传文件名的中文乱码  
        upload.setHeaderEncoding("UTF-8");   
        factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K  
        File linshi = new File(pathString+"\\linshi");//当超过500K的时候，存到一个临时文件夹中  
        factory.setRepository(linshi);  
        upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M  
        try {  
            // 1. 得到 FileItem 的集合 items  
            List<FileItem> /* FileItem */items = upload.parseRequest(request);  
  
            // 2. 遍历 items:  
            for (FileItem item : items) {  
                // 若是一个一般的表单域, 打印信息  
                if (item.isFormField()) {  
                    String name = item.getFieldName();  
                    String value = null;
					try {
						value = item.getString("utf-8");
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
  
                    System.out.println(name + ": " + value);  
                      
                      
                }  
                // 若是文件域则把文件保存到 e:\\files 目录下.  
                else {  
                    String fileName = item.getName();  
                    long sizeInBytes = item.getSize();  
                    System.out.println(fileName);  
                    System.out.println(sizeInBytes);  
  
                    InputStream in = null;
					try {
						in = item.getInputStream();
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
                    byte[] buffer = new byte[1024];  
                    int len = 0;  
                    String realnameString=System.currentTimeMillis()+"_"+fileName;
                    String fileName2=fileName;
                    fileName = pathString+"\\file\\" + realnameString;//文件最终上传的位置
                    
                    System.out.println(fileName);  
                    OutputStream out = null;
					try {
						out = new FileOutputStream(fileName);
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
  
                    try {
						while ((len = in.read(buffer)) != -1) {  
						    out.write(buffer, 0, len);  
						}
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
  
                    try {
						out.close();
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
                    try {
						in.close();
					} catch (Exception e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}  
                //    dao.File file = sql.fService.getOne("1 order by id desc");
                   /* int id=1;
                    if(isnull(file)){
                    	
                    }else{
                    	id=file.getId()+1;
                    }*/
                   /* sql.fService.addOne("?,?,?",id,fileName2,realnameString);
                    dao.File file2=new dao.File();
                    file2.setId(id);
                    file2.setFileName(fileName2);
                    file2.setRealName(realnameString);
                    prWriter.print(toJson(file2));*/
                    System.out.println(fileName2);
                    System.out.println(realnameString);;
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        }  
	}
}
