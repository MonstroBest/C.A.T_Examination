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
			//�����ϴ��ļ�
			//1�����������ļ����
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2�������ļ��ϴ��ĺ����� 
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");
			//3������request������ļ����
			List<FileItem> parseRequest = upload.parseRequest(request);
			
			//4�������ļ����
			for(FileItem item : parseRequest) {
				//5���ж���ͨ����/�ļ��ϴ���
				boolean formField = item.isFormField();
				if(formField) {
					//��ͨ����
					String fieldName = item.getFieldName();
					String fieldValue = item.getString("UTF-8");
					System.out.println(fieldName+":"+fieldValue);
				}else {
					//�ļ��ϴ���
					//����ϴ��ļ�������
					String fileName = item.getName();
					//����ϴ��ļ�������
					InputStream in = item.getInputStream();
					//��in�е����ݿ�������������
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
