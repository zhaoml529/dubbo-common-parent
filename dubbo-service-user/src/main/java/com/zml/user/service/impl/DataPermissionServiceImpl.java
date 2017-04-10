package com.zml.user.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IDataPermissionDao;
import com.zml.user.entity.DataPermissions;
import com.zml.user.exceptions.DataPermissionServiceException;
import com.zml.user.service.IDataPermissionService;

@Service("dataPermissionService")
public class DataPermissionServiceImpl implements IDataPermissionService {

	@Autowired
	private IDataPermissionDao dataPermissionDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long addDataPermission(DataPermissions dataPermission)
			throws DataPermissionServiceException {
		if(isExist(dataPermission.getName())) {
			throw DataPermissionServiceException.DATA_PERMISSION_IS_EXIST;
		}
		return this.dataPermissionDao.insert(dataPermission);
	}

	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long updateDataPermission(DataPermissions dataPermission)
			throws DataPermissionServiceException {
		if(isExist(dataPermission.getName())) {
			throw DataPermissionServiceException.DATA_PERMISSION_IS_EXIST;
		}
		return this.dataPermissionDao.update(dataPermission);
	}

	private boolean isExist(String name) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		DataPermissions dp = this.dataPermissionDao.getBy(paramMap);
		if(dp != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long deleteDataPermission(Long id)
			throws DataPermissionServiceException {
		DataPermissions dp = this.dataPermissionDao.getById(id);
		if(dp == null) {
			throw DataPermissionServiceException.DELETE_DATA_PERMISSION_ERR;
		}
		return this.dataPermissionDao.deleteById(id);
	}

	@Override
	public List<DataPermissions> getDataPermissionList()
			throws DataPermissionServiceException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<DataPermissions> list = this.dataPermissionDao.getList(paramMap);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
