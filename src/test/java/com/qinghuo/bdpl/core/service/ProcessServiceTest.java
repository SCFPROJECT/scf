package com.qinghuo.bdpl.core.service;

import java.io.FileInputStream;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class ProcessServiceTest {
	private static String realPath="D:\\bdpl-core\\src\\main\\java\\com\\qinghuo\\bdpl\\core\\process";
	private RepositoryService repositoryService;
	private RuntimeService runtimeService;
	private TaskService taskService;
	private HistoryService historyService;
	private ManagementService managementService;
	@Autowired
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	@Autowired
	public void setRuntimeService(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	@Autowired
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@Autowired
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	@Autowired
	public void setManagementService(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	@Test
	public void processService() throws Exception{
		repositoryService.createDeployment().addInputStream("MyProcess1.bpmn", new FileInputStream
				(realPath + "\\MyProcess1.bpmn"))
				.addInputStream("MyProcess1.png", new FileInputStream
						(realPath + "\\MyProcess1.png")) 
						.deploy();
		
		 ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess");
		 String procId = instance.getId();  
		 System.out.println(".....................................procId:"+ procId);  
		 
		 List<Task> tasks=taskService.createTaskQuery().taskDefinitionKey("firstTask").list();
		 
		 for(Task task:tasks){
			 System.out.println("当前流程ID："+task.getId()+"...当前流程名称："+task.getName());
			 taskService.claim(task.getId(), "testUser");
		 }
		 
		 tasks = taskService.createTaskQuery().taskAssignee("testUser").list();
		 for(Task task:tasks){
			 System.out.println("当前用户的任务："+task.getName());
			 taskService.complete(task.getId());
		 }
		 
		 System.out.println("Number of tasks for testUser: "  
				                 + taskService.createTaskQuery().taskAssignee("testUser").count());  
		 
		 
		 tasks=taskService.createTaskQuery().taskDefinitionKey("secondTask").list();
		 
		 for(Task task:tasks){
			 System.out.println("当前流程ID："+task.getId()+"...当前流程名称："+task.getName());
			 taskService.claim(task.getId(), "testUser");
		 }
		 
		 tasks = taskService.createTaskQuery().taskAssignee("testUser").list();
		 for(Task task:tasks){
			 taskService.complete(task.getId());
		 }
		 
		 HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();  
		 System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());  

	}
	
}
