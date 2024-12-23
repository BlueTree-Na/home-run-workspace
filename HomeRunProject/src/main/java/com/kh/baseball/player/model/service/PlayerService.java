package com.kh.baseball.player.model.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.baseball.player.model.vo.Player;

public interface PlayerService {
	
	
	// 선수 등록 신청
	int savePlayer(Player player);

	// 선수 정보 일람
	List<Player> selectPlayerBoard();			

	// 선수 정보 상세조회
	Player selectPlayer(int userNo);
	
	// 선수 정보 수정
	int updatePlayer(Player player);
	
	// 선수 정보 삭제
	int deletePlayer(int userNo);

	
	
	

}
