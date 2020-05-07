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
				//1、创建磁盘文件项工厂-----
				//作用：设置缓存文件的大小、设置临时文件存储位置、
				String path_temp = this.getServletContext().getRealPath("temp");
				System.out.println(path_temp);
//				DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024,new File(path));
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(1024*1024);
				factory.setRepository(new File(path_temp));
				//2、创建文件上传的核心类
				ServletFileUpload upload = new ServletFileUpload(factory);
				//设置上传文件的名称的编码
				upload.setHeaderEncoding("UTF-8");
				//ServletFileUpload的API
				boolean multipartContent = upload.isMultipartContent(request);//判断表单是否是文件上传的表单
				//创建utils对象
				PlayerInfoUtils utils = new PlayerInfoUtils();
				PlayerInfo player = new PlayerInfo();
				UserLog user = new UserLog();
				if(multipartContent) {
					//是文件上传的表单
					//解析request获得文件项集合
					List<FileItem> parseRequest = upload.parseRequest(request);
					//非空判断
					if(parseRequest!=null) {
						for(FileItem item : parseRequest) {
							
							//判断是不是一个普通表单项
							boolean formField = item.isFormField();
							if(formField) {
								//普通表单项
								String fieldName = item.getFieldName();
								String fieldValue = item.getString("UTF-8");
								//封装进bean对象里
								utils.isWant(fieldName,fieldValue, player,user);
								//对普通表单项的内容进行编码（中文乱码）
								System.out.println(fieldName+"::"+fieldValue);
								//当表单是enctype="multipart/form-data"时，request.getParameter相关方法都失效
							}else {
								//文件上传项
								//文件名称
								String fileName = item.getName();
								fileName = fileName.substring(fileName.lastIndexOf("\\")+1);//修正文件名
								//上面这里要改成这个样子！！！，如果值是item.getName()的话，值实际上是你这个文件的路径而不是单纯的文件名
								//获得上传文件的内容
								InputStream in = item.getInputStream();
								String path_store = this.getServletContext().getRealPath("upload");
								
								String real_store_path = path_store+"/"+fileName;
								System.out.println("real_store_path:"+real_store_path);
								OutputStream out = new FileOutputStream(real_store_path);
								utils.isHeadPath(real_store_path, player);
								IOUtils.copy(in, out);
								in.close();
								out.close();
								//删除临时文件
								item.delete();
							}
						}
					}
				}else {
					//不是文件上传的表单
					//使用原始的表单数据的获得方式：request.getParameter();
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
