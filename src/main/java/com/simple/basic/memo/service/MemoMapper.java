package com.simple.basic.memo.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.simple.basic.command.MemoVO;

@Mapper
public interface MemoMapper {
   public void registMemo(MemoVO vo);
   public List<MemoVO> getList();
}
