package com.simple.basic.memo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simple.basic.command.MemoVO;


public interface MemoService {
   
   public void registMemo(MemoVO vo);
   public List<MemoVO> getList();
}