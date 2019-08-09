package cn.e3mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.e3mall.common.utils.FastDFSClient;
import cn.e3mall.common.utils.JsonUtils;

/**
 * 图片上传处理Controller
 * @author admin
 *
 */
@Controller
public class PictureController {

	@Value("${IMAGE_SERVER_UIL}")
	private String IMAGE_SERVER_UIL;
	
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uplpoad(MultipartFile uploadFile){
		try {
			//把图片上传到图片服务器
			FastDFSClient fastDFSClient =new FastDFSClient("classpath:conf/client.conf");
			//取文件扩展名
			String originalFilename = uploadFile.getOriginalFilename();
			String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			//得到图片地址和文件名
			String url = fastDFSClient.uploadFile(uploadFile.getBytes(),extName );
			//补充为完整的url
			url = IMAGE_SERVER_UIL+url;
			//封装到map中返回
			Map result = new HashMap<>();
			result.put("error", 0);
			result.put("url", url);
			return JsonUtils.objectToJson(result);
			
		} catch (Exception e) {
			e.printStackTrace();
			Map result = new HashMap<>();
			result.put("error", 1);
			result.put("url", "图片上传失败");
			return JsonUtils.objectToJson(result);
		}
	}
}
