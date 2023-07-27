package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
   
   @Autowired
   @Qualifier("memoService") 
   private MemoService memoSerivce;
   
   @GetMapping("/memoList")
   public String memoList(Model model) {
      System.out.println("도착..........?");
      List<MemoVO> memoList = memoSerivce.getList();
      model.addAttribute("memoList", memoList);
      
      return "memo/memoList";
   }
   @GetMapping("/memoWrite")
   public String memoWrite() {
      return "memo/memoWrite";
   }
   
   //글등록
   @PostMapping("/memoForm")
   public String memoForm(@Valid @ModelAttribute("memvo") MemoVO vo ,Errors err,Model model ) {
      
      if(err.hasErrors()) {
         List<FieldError> list = err.getFieldErrors();
         
         for(FieldError err2 : list) {
            if(err2.isBindingFailure()) {
               model.addAttribute("valid_"+err2.getField(), "메모 입력해주세요");
            }else {
               model.addAttribute("valid_"+err2.getField(), err2.getDefaultMessage());            }
         }
      
         return "memo/memoWrite";
            
      }else {
         memoSerivce.registMemo(vo);
        
         //목록으로
         return "redirect:memoList";
      }
      
      
   }
}