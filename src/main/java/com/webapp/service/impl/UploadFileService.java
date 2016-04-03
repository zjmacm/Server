package com.webapp.service.impl;

import com.webapp.common.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;

@Service("uploadFileService")
public class UploadFileService {

    public String uploadFile(CommonsMultipartFile multipartFile, String path) throws IOException {
        String fileName = createUri(multipartFile);
        File file = new File(path+fileName);
        try{
            multipartFile.transferTo(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        return fileName;
    }
    /**
     * 为图片产生uri 返回给前端
     * @param file
     * @return
     */

    public static String createUri(MultipartFile file){
        String fileName = file.getOriginalFilename();
        String extName = fileName.substring(fileName.lastIndexOf("."));
        String newName = IdUtil.uuid()+extName;
        return newName;
    }
}
