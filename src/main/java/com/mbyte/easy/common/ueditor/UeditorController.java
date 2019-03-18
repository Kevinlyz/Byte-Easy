package com.mbyte.easy.common.ueditor;

import com.mbyte.easy.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("/ueditor")
@Controller
public class UeditorController {

	@Autowired
	private HttpServletRequest request;
	@Value("${easy.server.path}")
	private String serverpath;
	@RequestMapping("/index")
	public String ueditor() {
		return "ueditor";
	}


	/**
	 * 获取ueditor配置参数
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/ueditorConfig")
    @ResponseBody
    public String ueditor(HttpServletRequest request) {
        return PublicMsg.UEDITOR_CONFIG;
    }
	
	/**
	 * 文件上传
	 * @param upfile
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/imgUpload")
    @ResponseBody
    public Ueditor imgUpload(MultipartFile upfile){
		Ueditor ueditor = new Ueditor();
        //上传文件
		String filePath = serverpath+ FileUtil.uploadSuffixPath + FileUtil.uploadFile(upfile);
		if(StringUtils.isNoneBlank(filePath)){
			ueditor.setUrl(filePath);
			ueditor.setState(PublicMsg.UeditorMsg.SUCCESS.get());
			ueditor.setTitle(upfile.getName());
			ueditor.setOriginal(upfile.getOriginalFilename());
		}
        return ueditor;
    }


}
