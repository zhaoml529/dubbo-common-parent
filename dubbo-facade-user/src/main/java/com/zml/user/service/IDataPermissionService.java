package com.zml.user.service;

import java.util.List;

import com.zml.user.entity.DataPermissions;
import com.zml.user.exceptions.DataPermissionServiceException;

public interface IDataPermissionService {

	public Long addDataPermission(DataPermissions dataPermission) throws DataPermissionServiceException;
	
	public Long updateDataPermission(DataPermissions dataPermission) throws DataPermissionServiceException;
	
	public Long deleteDataPermission(Long id) throws DataPermissionServiceException;
	
	public List<DataPermissions> getDataPermissionList() throws DataPermissionServiceException;
}
