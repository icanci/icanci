package cn.icanci.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import cn.icanci.exception.LogicException;

public class FileUtil {
	// 可以接收的图片类型
	private static final String UPLOAD_IMAGES_TYPE = "bmp,jpg,png,tif,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw,WMF,webp";

	@SuppressWarnings({ "unused", "unchecked" })
	public static void upload(HttpServletRequest req, Map<String, String> map) throws ServletException, IOException {
		req.setCharacterEncoding(StaticResources.encoding);
		try {
			// 解析和检查请求 方式是不是POST 请求编码是不是 ....
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			// 创建FileItemFactory对象
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 缓存大小 默认值是10k
			factory.setSizeThreshold(1024 * 200); // 20kb
			// 创建文件上传的处理器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大上传文件大小为 7M
			upload.setFileSizeMax(1024 * 1024 * 7);
			List<FileItem> items = upload.parseRequest(req);
			for (FileItem fileItem : items) {
				String fileName = fileItem.getFieldName();
				if (fileItem.isFormField()) {
					// 普通表单控件 获取普通表单控件的参数值
					map.put(fileName, fileItem.getString(StaticResources.encoding));
				} else {
					// 当前文件的MIME类型
					// req.getServletContext().getMimeType(fileItem.getName());
					// 上传文件的拓展名
					String ext = FilenameUtils.getExtension(fileItem.getName());
					// 判断文件类型
					String[] type = UPLOAD_IMAGES_TYPE.split(",");
					// 当前上传的文件不支持
					if (!Arrays.asList(type).contains(ext)) {
						throw new LogicException("图片类型不支持");
					}
					// 上传表单控件 把二进制数据写到哪一个文件中
					String dir = req.getServletContext().getRealPath("/headImagesUploadedByUsers");
					String uuidName = UUID.randomUUID().toString() + "."
							+ FilenameUtils.getExtension(fileItem.getName());
					System.out.println("文件上传之前");
					fileItem.write(new File(dir, uuidName));
					System.out.println("文件上传之后");
					System.out.println(fileName + " put 名字");
					System.out.println("/headImagesUploadedByUsers/" + uuidName);
					map.put(fileName, "/headImagesUploadedByUsers/" + uuidName);
				}
			}
		} catch (LogicException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
