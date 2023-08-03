package com.simple.basic.controller;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
   
   @Value("${project.upload.path}")
   private String uploadPath;
   
   @GetMapping("/upload")
   public String upload() {
      return "upload/upload";
   }
   //파일데이터는 MultipartFile 객체로 받습니다.
   @PostMapping("/upload_ok")
   public String upload_ok(@RequestParam("file") MultipartFile file) {
      
      //파일이름을 받습니다.
      String originName = file.getOriginalFilename();
      
      //브라우저 별로 파일의 경로가 다를수 있기 때문에 \\ 기준으로 파일명만 잘라서 다시 저장
      String filename = originName.substring(originName.lastIndexOf("\\")+1);
      
      //파일사이즈
      long size = file.getSize();
      
      //바이트코드
//      byte[] = file.getBytes();
      
      //세이브할 경로
      String filepath = uploadPath+"/"+filename;
      
      //동일한 파일을 재업로드시 기존파일을 덮어버리기 때문에, 난수이름으로 파일명을 바꿔서 올림
      
      
      
      System.out.println(originName);
      System.out.println(filename);
      System.out.println(size);
      System.out.println(uploadPath);
      System.out.println("세이브 할 경로:" + filepath);
      File saveFile = new File(filepath);
      
      try {
         file.transferTo(saveFile); //파일 업로드를 진행
      } catch (IllegalStateException | IOException e) {
         System.out.println("파일업로드중 error발생");
         e.printStackTrace();
      }
      
      return "upload/upload_ok";
   }
   
}