package com.qinghuo.bdpl.core.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.qinghuo.bdpl.core.domain.Procdef;
import com.qinghuo.bdpl.core.domain.User;
import com.qinghuo.bdpl.core.service.ProcessService;

@RequestMapping("/process")
@Controller
public class MyProcessController {
	@Resource(name = "processService")
	private ProcessService processService;
	@Resource(name = "repositoryService")
	private RepositoryService repositoryService;
	@Resource(name = "runtimeService")
	private RuntimeService runtimeService;
	@Resource(name = "taskService")
	private TaskService taskService; //historyService
	@Resource(name = "historyService")
	private HistoryService historyService;
	
	@RequestMapping("/selectProcdef")
	public ModelAndView tolist() throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Procdef> procList = processService.selectProcdef();
		mv.addObject("procList", procList);
		mv.setViewName("process/process_list");
		return mv;
	}
	@RequestMapping("/startProcdef")
	public ModelAndView startProcdef(@RequestParam("id") String id) throws Exception {
		ModelAndView mv = new ModelAndView();
		ProcessInstance instance = runtimeService.startProcessInstanceById(id);
		String procId = instance.getId();   
		Task task = taskService.createTaskQuery().processInstanceId(procId).singleResult();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
		String currentActivitiId = pi.getActivityId();
		// 2. 获取到流程定义 
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		// 3. 使用流程定义通过currentActivitiId得到活动对象
		ActivityImpl ai = pd.findActivity(currentActivitiId);
		// 5.通过活动对象找当前活动的所有出口
		List<PvmTransition> transitions =  ai.getOutgoingTransitions();
        List<String> transNames = new ArrayList<String>();
        for(PvmTransition trans:transitions){
        	String transname = (String)trans.getProperty("name");
        	if(StringUtils.isNoneEmpty(transname)){
        		transNames.add(transname);
        	}
        }
        if(transNames.size()==0){
        	transNames.add("提交");
        }
        mv.addObject("transList",transNames);
		mv.addObject("task",task);
		mv.setViewName("process/process_handler");
		return mv;
	}
	@RequestMapping(value="/completeProcdef")
	public ModelAndView completeProcdef(@RequestParam("id") String taskId,@RequestParam("outcome") String outcome) throws Exception {
		ModelAndView mv = new ModelAndView();
		Map<String,Object> vars = new HashMap<String,Object>();
		System.out.println(".................................."+outcome);
		vars.put("outcome", outcome);
		taskService.complete(taskId,vars);
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("yuanzhao").list();
		mv.addObject("taskList", tasks);
		mv.setViewName("process/task_list");
		return mv;
	}
	@RequestMapping("/toTask")
	public ModelAndView toTask(@RequestParam("id") String taskId) throws Exception {
		ModelAndView mv = new ModelAndView();  
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
		String currentActivitiId = pi.getActivityId();
		// 2. 获取到流程定义 
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		// 3. 使用流程定义通过currentActivitiId得到活动对象
		ActivityImpl ai = pd.findActivity(currentActivitiId);
		// 5.通过活动对象找当前活动的所有出口
		List<PvmTransition> transitions =  ai.getOutgoingTransitions();
        List<String> transNames = new ArrayList<String>();
        for(PvmTransition trans:transitions){
        	String transname = (String)trans.getProperty("name");
        	if(StringUtils.isNoneEmpty(transname)){
        		transNames.add(transname);
        	}
        }
        if(transNames.size()==0){
        	transNames.add("提交");
        }
        mv.addObject("transList",transNames);
		mv.addObject("task",task);
		mv.setViewName("process/process_handler");
		return mv;
	}
	@RequestMapping("/showTask")
	public ModelAndView showTask() throws Exception {
		ModelAndView mv = new ModelAndView();  
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("yuanzhao").list();
		mv.addObject("taskList", tasks);
		mv.setViewName("process/task_list");
		return mv;
	}
	@RequestMapping("/showHisProcess")
	public ModelAndView showHisProcess() throws Exception {
		ModelAndView mv = new ModelAndView();  
		List<Task> tasks = taskService.createTaskQuery().taskUnassigned().list();
		mv.addObject("taskList", tasks);
		mv.setViewName("process/task_list");
		return mv;
	}
	@RequestMapping("/toDeploy")
	public ModelAndView toDeploy(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();  
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		MultipartFile file = multipartRequest.getFile("processFile");
		if(!file.isEmpty()){
		InputStream in = file.getInputStream();
		repositoryService.createDeployment().addZipInputStream(new ZipInputStream(in)).deploy();
		}
		List<Procdef> procList = processService.selectProcdef();
		mv.addObject("procList", procList);
		mv.setViewName("process/process_list");
		return mv;
	}
	@RequestMapping("/showProcessPng")
	public ModelAndView showProcessPng(@RequestParam("id") String deployId) throws Exception {
		ModelAndView mv = new ModelAndView();  
		
		List<Procdef> procList = processService.selectProcdef();
		mv.addObject("procList", procList);
		mv.setViewName("process/process_list");
		return mv;
	}
	@RequestMapping("/showPng")
	public String showPng(@RequestParam("id") String deployId,@RequestParam("pngName") String pngName,HttpServletResponse resp) throws Exception{
		InputStream in = repositoryService.getResourceAsStream(deployId,pngName);
		try{
			OutputStream out = resp.getOutputStream();
			byte[] b = new byte[1024];
			for (int len = -1; (len= in.read(b))!=-1; )
			{  
				out.write(b, 0, len);   
			}
			out.close();
			in.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/showCurrentPng")
	public ModelAndView showCurrentPng(@RequestParam("taskId") String taskId) throws Exception{
		ModelAndView mv = new ModelAndView();
		//Map<String, Object> coordinates = new HashMap<String, Object>();
		// 1. 获取到当前活动
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult(); 
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionId(task.getProcessDefinitionId()).singleResult();
		String currentActivitiId = pi.getActivityId();
		// 2. 获取到流程定义 
		ProcessDefinitionEntity pd = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		// 3. 使用流程定义通过currentActivitiId得到活动对象
		ActivityImpl ai = pd.findActivity(currentActivitiId);
		
		List<String> names = repositoryService.getDeploymentResourceNames(pi.getDeploymentId());  
		String imageName = null;  
		for (String name : names) {   
			if(name.indexOf(".png")>=0){   
				imageName = name;   
			}  
		} 
		mv.addObject("pngName", imageName);
		mv.addObject("deployId", pi.getDeploymentId());
		mv.addObject("x", ai.getX());
		mv.addObject("y", ai.getY());
		mv.addObject("width", ai.getWidth());
		mv.addObject("height", ai.getHeight());
		mv.setViewName("process/task_showPng");
		return mv;
	}
	
}
