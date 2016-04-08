package com.qinghuo.bdpl.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinghuo.bdpl.core.dao.ProcessMapper;
import com.qinghuo.bdpl.core.domain.Procdef;
import com.qinghuo.bdpl.core.service.ProcessService;
@Service("processService")
public class ProcessServiceImpl implements ProcessService {
	
	private ProcessMapper processMapper;
	@Autowired
	public void setPrcessMapper(ProcessMapper processMapper) {
		this.processMapper = processMapper;
	}
	@Override
	public List<Procdef> selectProcdef() {
		// TODO Auto-generated method stub
		return processMapper.selectProcdef();
	}

}
