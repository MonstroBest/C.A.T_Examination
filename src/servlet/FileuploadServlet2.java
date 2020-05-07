package servlet;

import java.io.File;
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
import org.apache.tomcat.util.http.fileupload.IOUtils;

import dao.impl.PlayerDaoImpl;
import entity.PlayerInfo;
import entity.UserLog;
import service.impl.PlayerServiceImpl;
import utils.impl.PlayerInfoUtils;

/**
 * Servlet implementation class FileuploadServlet2
 */
public class FileuploadServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			try {
				//1�����������ļ����-----
				//���ã����û����ļ��Ĵ�С��������ʱ�ļ��洢λ�á�
				String path_temp = this.getServletContext().getRealPath("temp");
				System.out.println(path_temp);
//				DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(path));
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024*1024);
				factory.setRepository(new File(path_temp));
				//2�������ļ��ϴ��ĺ�����
				ServletFileUpload upload = new ServletFileUpload(factory);
				//�����ϴ��ļ������Ƶı���
				upload.setHeaderEncoding("UTF-8");
				//ServletFileUpload��API
				boolean multipartContent = upload.isMultipartContent(request);//�жϱ��Ƿ����ļ��ϴ��ı�
				//����utils����
				PlayerInfoUtils utils = new PlayerInfoUtils();
				PlayerInfo player = new PlayerInfo();
				UserLog user = new UserLog();
				if(multipartContent) {
					//���ļ��ϴ��ı�
					//����request����ļ����
					List<FileItem> parseRequest = upload.parseRequest(request);
					//�ǿ��ж�
					if(parseRequest!=null) {
						for(FileItem item : parseRequest) {
							
							//�ж��ǲ���һ����ͨ����
							boolean formField = item.isFormField();
							if(formField) {
								//��ͨ����
								String fieldName = item.getFieldName();
								String fieldValue = item.getString("UTF-8");
								//��װ��bean������
								utils.isWant(fieldName,fieldValue, player,user);
								//����ͨ��������ݽ��б��루�������룩
								System.out.println(fieldName+"::"+fieldValue);
								//������enctype="multipart/form-data"ʱ��request.getParameter��ط�����ʧЧ
							}else {
								//�ļ��ϴ���
								//�ļ�����
								String fileName = item.getName();
								fileName = fileName.substring(fileName.lastIndexOf("\\")+1);//�����ļ���
								//��������Ҫ�ĳ�������ӣ����������ֵ��item.getName()�Ļ���ֵʵ������������ļ���·�������ǵ������ļ���
								//����ϴ��ļ�������
								InputStream in = item.getInputStream();
								String path_store = this.getServletContext().getRealPath("upload");
								
								String real_store_path = path_store+"/"+fileName;
								System.out.println("real_store_path:"+real_store_path);
								OutputStream out = new FileOutputStream(real_store_path);
								utils.isHeadPath(real_store_path, player);
								IOUtils.copy(in, out);
								in.close();
								out.close();
								//ɾ����ʱ�ļ�
								item.delete();
							}
						}
					}
				}else {
					//�����ļ��ϴ��ı�
					//ʹ��ԭʼ�ı����ݵĻ�÷�ʽ��request.getParameter();
				}
				PlayerServiceImpl playerService = new PlayerServiceImpl();
				playerService.PlayerRegister(player,user);
				request.setAttribute("playerInfo", player);
				request.getRequestDispatcher("jsp/playerHome.jsp").forward(request, response);
			} catch (FileUploadException e) {
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
