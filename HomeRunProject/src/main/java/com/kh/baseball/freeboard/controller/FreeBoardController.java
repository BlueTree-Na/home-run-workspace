package com.kh.baseball.freeboard.controller;

import java.util.HashMap;
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
import com.kh.baseball.freeboard.model.service.FreeBoardService;
import com.kh.baseball.freeboard.model.vo.FreeBoard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class FreeBoardController {

	public final FreeBoardService freeBoardService;
	public final ModelAndViewUtil mv;
	
	@GetMapping("freeBoard")
	public ModelAndView selectBoardList(@RequestParam(value="page", defaultValue="1") int Page) {
		
		Map<String, Object> map = freeBoardService.selectBoardList(Page);
		
		
		return mv.setViewNameAndData("freeBoard/freeBoard_list", map);
	}
	
	@GetMapping("insertForm.free")
	public String insertForm() {
		return "freeBoard/freeBoard_insert_form";
	}
	
	@PostMapping("freeBoard")
	public ModelAndView insertBoard(FreeBoard freeBoard, MultipartFile[] upfile, HttpSession session) {
		  log.info("게시글 정보 : {}, 파일 정보 : {}", freeBoard, upfile);
		
		freeBoardService.insertBoard(freeBoard, upfile);
		session.setAttribute("alertMsg", "게시글 등록 성공!");
		
		return mv.setViewNameAndData("redirect:freeBoard", null);
	}
	
	// detail board
	@GetMapping("freeBoard/{boardNo}")
	public ModelAndView selectById(@PathVariable(name="boardNo") Long boardNo) {
		Map<String, Object> responseData = freeBoardService.selectDetailByBoardNo(boardNo);
		return mv.setViewNameAndData("freeBoard/freeBoard_detail", responseData);
	}
	
	@PostMapping("freeBoard/delete.free")
	public ModelAndView deleteFreeBoard(Long boardNo, String file1ChangeName
													, String file2ChangeName
													, String file3ChangeName
													, String file4ChangeName
													, String file5ChangeName) {
		freeBoardService.deleteFreeBoard(boardNo, file1ChangeName
												, file2ChangeName
												, file3ChangeName
												, file4ChangeName
												, file5ChangeName);
		
		return mv.setViewNameAndData("redirect:/freeBoard", null);
	}
	
	@PostMapping("freeBoard/update-form.free")
	public ModelAndView updateForm(Long boardNo) {
		Map<String, Object> responseData = freeBoardService.selectUpdateByBoardNo(boardNo);
		return mv.setViewNameAndData("freeBoard/freeBoard_update", responseData);
	}
	
	@PostMapping("freeBoard/update.free")
	public ModelAndView update(FreeBoard freeBoard, MultipartFile[] upfile) {

		freeBoardService.updateBoard(freeBoard, upfile);
		return mv.setViewNameAndData("redirect:/freeBoard", null);
	}
	
	@GetMapping("searchList.free")
	public ModelAndView searchList(@RequestParam(value="page", defaultValue="1") int page
								   ,@RequestParam("condition") String condition
            					   ,@RequestParam("keyword") String keyword) {
		Map<String, Object> map = new HashMap();
		map.put("condition", condition);
		map.put("keyword", keyword);
		map.put("page", page);
		
		Map<String, Object> searchMap = freeBoardService.searchList(map);
		return mv.setViewNameAndData("freeBoard/freeBoard_list", searchMap);
	}
}
