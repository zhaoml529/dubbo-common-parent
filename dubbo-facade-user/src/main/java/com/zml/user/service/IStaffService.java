package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Staff;


public interface IStaffService {

	public Long addStaff(Staff staff);
	
	public Long updateStaff(Staff staff);
	
	public List<Staff> findAll(Map<String, Object> map);
}
