package com.zml.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.common.page.Page;
import com.zml.common.page.Parameter;
import com.zml.user.dao.IPostDao;
import com.zml.user.entity.Post;
import com.zml.user.exceptions.PostServiceException;
import com.zml.user.service.IPostService;


@Service("postService")
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDao postDao;
	
	@Override
	public Post getById(Long id) throws PostServiceException {
		return postDao.getById(id);
	}
	
	@Override
	public Page getListPage(Parameter<Post> param) throws PostServiceException {
		param.initPage();	// 初始化分页参数
		Page page = this.postDao.listPage(param.getPageParam(), param.getParamMap());
		return page;
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public Long save(Post post) throws PostServiceException {
		return this.postDao.insert(post);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void update(Post post) throws PostServiceException {
		this.postDao.update(post);
	}
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	@Override
	public void delete(Long id) throws PostServiceException {
		this.postDao.deleteById(id);
	}
	
}
