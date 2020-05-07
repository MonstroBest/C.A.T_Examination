package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileuploadServlet
 */
public class FileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//接收上传文件
			//1、创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建文件上传的核心类 
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			//3、解析request，获得文件项集合
			List<FileItem> parseRequest = upload.parseRequest(request);
			
			//4、遍历文件项集合
			for(FileItem item : parseRequest) {
				//5、判断普通表单项/文件上传项
				boolean formField = item.isFormField();
				if(formField) {
					//普通表单项
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					System.out.println(fieldName+":"+fieldValue);
				}else {
					//文件上传项
					//获得上传文件的名称
					String fileName = item.getName();
					//获得上传文件的内容
					InputStream in = item.getInputStream();
					//将in中的数据拷贝到服务器上
					String path = this.getServletContext().getRealPath("upload");
					OutputStream out = new FileOutputStream(path+"/"+fileName);
					int len = 0;
					byte[] buffer = new byte[1024];
					while((len=in.read(buffer))>0) {
						out.write(buffer,0,len);
					}
					in.close();
					out.close();
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
