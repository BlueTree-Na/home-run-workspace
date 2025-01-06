package com.kh.baseball.small.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baseball.common.ModelAndViewUtil;
import com.kh.baseball.member.model.vo.Member;
import com.kh.baseball.small.model.service.SmallBoardService;
import com.kh.baseball.small.model.vo.SmallBoard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SmallBoardController {

	public final SmallBoardService smallBoardService;
	public final ModelAndViewUtil mv;
	
	@GetMapping("small")
	public ModelAndView selectBoardList(@RequestParam(value="page", defaultValue="1") int Page,
										@RequestParam(value="boardLimit", defaultValue="5") int boardLimit) {
		
		Map<String, Object> map = smallBoardService.selectBoardList(Page, boardLimit);
		
		return mv.setViewNameAndData("small/smallBoard_list", map);
	}
	
	@GetMapping("insertForm.small")
	public ModelAndView insertForm() {
		
		List<String> teamList = smallBoardService.selectTeamList();
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("small/smallBoard_insert_form");
		if(teamList != null) {
			mv.addObject("teamList", teamList);
		}
		
		return mv;
	}
	
	@PostMapping("small")
	public ModelAndView insert(SmallBoard smallBoard, MultipartFile upfile, HttpSession session) {
		
		smallBoardService.insertBoard(smallBoard, upfile);
		session.setAttribute("alertMsg", "게시글 등록 성공!");
		
		return mv.setViewNameAndData("redirect:small", null);
	}
	
	@GetMapping("adminList.small")
	public ModelAndView adminList(@RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> map = smallBoardService.selectAdminList(page);
		
		return mv.setViewNameAndData("small/smallBoard_adminList", map);
	}
	
	@GetMapping("adminListDetail.small/{boardNo}")
	public ModelAndView adminListDetail(@PathVariable(name="boardNo") Long boardNo){
		// log.info("{}", boardNo);
		
		Map<String, Object> adminDetail = smallBoardService.adminListDetail(boardNo);
		// log.info("{}", adminDetail.get("file"));
		return mv.setViewNameAndData("small/smallBoard_adminDetail", adminDetail);
	}
	
	@PostMapping("permit.small")
	public ModelAndView adminPermit(Long boardNo) {
		// log.info("{}", boardNo);
		smallBoardService.adminPermit(boardNo);
		
		return mv.setViewNameAndData("redirect:/adminList.small", null);
	}
	
	@GetMapping("myList.small")
	public ModelAndView mySmallBoardList(@RequestParam(value="page", defaultValue="1") int page, HttpSession session) {
		Member member = (Member)(session.getAttribute("loginUser"));
		int loginUserNo = member.getUserNo();
		
		Map<String, Object> map = smallBoardService.selectMyBoardList(page, loginUserNo);
		
		return mv.setViewNameAndData("small/smallBoard_myList", map);
	}
	
	@GetMapping("small/{boardNo}")
	public ModelAndView boardListDetail(@PathVariable(name="boardNo") Long boardNo) {
		Map<String, Object> responseData = smallBoardService.selectDetailByBoardNo(boardNo);
		return mv.setViewNameAndData("small/smallBoard_detail", responseData);
	}
	
	@PostMapping("delete.small")
	public ModelAndView delete(Long boardNo) {
		smallBoardService.deleteBoard(boardNo);
		
		return mv.setViewNameAndData("redirect:/small", null);
	}
	
	@PostMapping("update-form.small")
	public ModelAndView updateForm(Long boardNo) {
		Map<String, Object> responseData = smallBoardService.selectUpdateByBoardNo(boardNo);
		log.info("{}",responseData);
		return mv.setViewNameAndData("small/smallBoard_update", responseData);
	}
	
}