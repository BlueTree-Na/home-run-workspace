package com.kh.baseball.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.baseball.member.model.vo.Member;

@Mapper
public interface MemberMapper {

	Member login(Member member);
	
	
	

}