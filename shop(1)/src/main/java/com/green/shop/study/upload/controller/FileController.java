package com.green.shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//파일 업로드, 다운로드 학습용 컨트롤러
@Controller
@RequestMapping("/file")
public class FileController {

    //파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        //파일 확장자 추출
        String file1 = "abc.jpg";
        System.out.println(file1.substring(1, 5));

        //처음부터 문자를 검사 해서 중복 되는 문자는 확인 되지 않음
        System.out.println(file1.indexOf("."));
        //뒤에서부터 문자를 검사 함
        System.out.println(file1.lastIndexOf("."));

        System.out.println(file1.lastIndexOf("."));

        System.out.println(file1.substring(file1.lastIndexOf(".")));

        return "test/upload/upload_form";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam(name = "img1") MultipartFile img1, @RequestParam(name = "img2") MultipartFile img2){

        //첨부한 파일명 가져오기
        String originalFileName = img1.getOriginalFilename();
        System.out.println(originalFileName);

        //업로드 경로
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop(1)\\src\\main\\resources\\static\\upload\\";

        // '안녕'
        //특수문자를 문자열로 취급하기 위해
        //특수문자 앞에 \를 삽입하면 됨
        String str = "\"안녕\"";

        //업로드 할 경로 및 파일명을 하나의 문자열로 나열
        String fileName = uploadPath + originalFileName;

        //파일 업로드
        //java.io.File
        try {
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e) {
            System.out.println("!!! 파일 첨부 중 오류 발생 !!!");
            throw new RuntimeException(e);
        }

        //두 번째 파일 업로드
        //원본 파일의 확장자만 추출
        String secondOriginFileName = img2.getOriginalFilename();
        String extension = secondOriginFileName.substring(secondOriginFileName.lastIndexOf("."));

        //서버에 저장 할 파일명 생성
        String uuid = UUID.randomUUID().toString();
        String attachedFileName = uuid + extension;

        try{
            File file1 = new File(uploadPath + attachedFileName);
            img2.transferTo(file1);
        }catch (Exception e){
            System.out.println("파일 첨부 중 오류 발생~");
            e.printStackTrace();
        }




        return "";
    }

    //다중 첨부 페이지로 이동
    @GetMapping("/multiForm")
    public String multiForm(){
        return "test/upload/multi_form";
    }

    //다중 첨부 실행
    @PostMapping("/multi")
    public String multi(@RequestParam(name = "imgs") MultipartFile[] imgs){

        //첨부할 파일 경로
        String uploadPath = "D:\\01-STUDY\\dev\\workspace_sts\\shop(1)\\src\\main\\resources\\static\\upload\\";

        for(MultipartFile img : imgs){
            String img1 = img.getOriginalFilename();
            System.out.println(img1);

            //확장자 추출
            String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));

            //첨부 될 파일명
            String attachedFileName = UUID.randomUUID().toString() + extension;

            //파일 첨부
            try {
                File file = new File(uploadPath + attachedFileName);
                img.transferTo(file);
            }catch (Exception e){
                System.out.println("다중 첨부 중 오류 발생~");
                e.printStackTrace();
            }

        }

        return "";
    }
}
