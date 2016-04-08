package com.qinghuo.bdpl.core.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qinghuo.bdpl.core.test.domain.UserModel;

@RequestMapping("/test")
@Controller
public class MyTestController {
	
	@RequestMapping("/toform")
	public String toform() {
		return "test/to_form";
	}
	@RequestMapping("/mytest")
	public ModelAndView mytest(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("user")UserModel user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("modelName", "userModel");
		mv.setViewName("test/to_list");
		return mv;
	}
	@RequestMapping("/mytest/{modelName}/{totalNum}")
	public ModelAndView tolist(@PathVariable("modelName")String modelName,@PathVariable("totalNum")String totalNum,@ModelAttribute("user")UserModel user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("modelName", modelName);
		mv.addObject("totalNum", totalNum);
		mv.setViewName("test/to_list");
		return mv;
	}
}
 	