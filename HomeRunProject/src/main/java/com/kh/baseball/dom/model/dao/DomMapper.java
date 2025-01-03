package com.kh.baseball.dom.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.web.multipart.MultipartFile;

import com.kh.baseball.dom.model.vo.Dom;
import com.kh.baseball.dom.model.vo.DomAttachment;

@Mapper
public interface DomMapper {
	
	// 현재 등록된 구장의 총 개수
	int findTotalCount();
	// 등록된 구장 조회
	List<Dom> findAll(RowBounds rowBounds);
	List<DomAttachment> findAttachment();
	// 구장 세부 조회
	Dom findById(Long domNo);
	// 구장 등록
	int save(Dom dom);
	// 구장 파일 등록
	int saveDomFile(DomAttachment domAtt);

	
	

}
