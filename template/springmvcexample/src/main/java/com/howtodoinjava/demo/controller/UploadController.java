package com.howtodoinjava.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/file")
public class UploadController {
    
    /**
     * 可以保存使用html5 multiple上传的多个文件
     * */
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/upload") + "/";
        for(int i = 0;i<files.length;i++){
            System.out.println("fileName---------->" + files[i].getOriginalFilename());
        
            if(!files[i].isEmpty()){
                int pre = (int) System.currentTimeMillis();
                try {
                    //拿到输出流，同时重命名上传的文件
                    FileOutputStream os = new FileOutputStream(path + new Date().getTime() + files[i].getOriginalFilename());
                    //拿到上传文件的输入流
                    FileInputStream in = (FileInputStream) files[i].getInputStream();
                    
                    //以写字节的方式写文件
                    int b = 0;
                    while((b=in.read()) != -1){
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    in.close();
                    int finaltime = (int) System.currentTimeMillis();
                    System.out.println(finaltime - pre);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("上传出错");
                }
        }
        }
        return "/";
    }   
    
    /**
     * 使用html5 multiple上传多个文件，只能上传第一个，原因为表单名只有一个，又使用Map保存文件，使用表单名作为主键
     * org.springframework.web.multipart.commons.CommonsFileUploadSupport.parseFileItems
     */
    @RequestMapping("/upload2")
    public String upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request  
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() != ""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "demoUpload" + file.getOriginalFilename();
                        //定义上传路径
                        String path = request.getSession().getServletContext().getRealPath("/upload") + "/" + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }
            
        }
        return "/";
    }
    
    /**
     * 可以保存使用html5 multiple上传的多个文件
     * */
    @RequestMapping("/upload3")
    public String upload3(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/upload") + "/";
        
        for(int i = 0;i<files.length;i++){
            try {
                files[i].transferTo(new File(path, files[i].getOriginalFilename()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return "/";
    }
    
    @RequestMapping("") 
    public String toUpload() {
        
        return "file/upload";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void download(HttpServletResponse response) throws IOException {
 
        File file = new File("D:/links.txt");
        InputStream is = new FileInputStream(file);
 
        // MIME type of the file
        response.setContentType("application/octet-stream");
        // Response header
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + file.getName() + "\"");
        // Read from the file and write into the response
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        os.flush();
        os.close();
        is.close();
    }
    
    @RequestMapping(value = "{file_name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        return new FileSystemResource(new File("D:/", fileName + ".txt")); 
    }
}