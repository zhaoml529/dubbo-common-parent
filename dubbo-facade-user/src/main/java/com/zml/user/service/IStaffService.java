package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Staff;
import com.zml.user.exceptions.StaffServiceException;


public interface IStaffService {

	public Long addStaff(Staff staff) throws StaffServiceException;
	
	public Long updateStaff(Staff staff) throws StaffServiceException;
	
	public List<Staff> findAll(Map<String, Object> map) throws StaffServiceException;
}
