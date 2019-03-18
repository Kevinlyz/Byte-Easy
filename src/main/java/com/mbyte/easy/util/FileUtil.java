package com.mbyte.easy.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName: FileUtil
 * @Description: 文件上传工具类
 * @Version 1.0
 **/
public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 文件上传路径前缀
     */
    public static String uploadSuffixPath;
    /**
     * 本地磁盘目录
     */
    public static String uploadLocalPath;
    /**
     * @Title: uploadFile
     * @Description: 单文件上传到本地磁盘
     * @param: multipartFile
     * @return: java.lang.String
     * @throws:
     */
    public static String uploadFile(MultipartFile multipartFile){
        if(multipartFile == null){
            return null;
        }
        //获取文件相对路径
        String filePath = getUploadFilePath(multipartFile.getOriginalFilename());
        logger.info("filePath："+filePath);
        File destFile = new File(uploadLocalPath + File.separator + filePath);
        if(!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(destFile);
            logger.info("文件【"+multipartFile.getOriginalFilename()+"】上传成功");
            return filePath;
        } catch (IOException e) {
            logger.error("文件上传异常："+multipartFile.getOriginalFilename(),e);
            return null;
        }
    }
    /**
     * @Title: getUploadFilePath
     * @Description: 获取上传后的文件相对路径  --数据库存储该路径
     * @param: fileName
     * @return: java.lang.String
     * @throws:
     */
    public static String getUploadFilePath(String fileName){
        return new StringBuilder(DateUtil.format(null, DateUtil.PATTERN_yyyyMMdd))
                .append( File.separator)
                .append(DateUtil.format(null, DateUtil.PATTERN_yyyyMMddHHmmssSSS))
                .append("_").append(Utility.getRandomStrByNum(6))
                .append(".").append(FilenameUtils.getExtension(fileName))
                .toString();
    }

    /**
     * @Title: isFileBySuffix
     * @Description: 通过后缀名判断是否是某种文件
     * @param: fileName 文件名称
     * @param: suffix 后缀名
     * @return: boolean
     * @throws:
     */
    public static boolean isFileBySuffix(String fileName,String suffix){
        if(StringUtils.isNoneBlank(fileName) && StringUtils.isNoneBlank(suffix)){
            return fileName.endsWith(suffix.toLowerCase()) || fileName.endsWith(suffix.toUpperCase());
        }
        return false;
    }

}
