package com.zml.user.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zml.user.dao.IPostDao;
import com.zml.user.entity.Post;
import com.zml.user.exceptions.PostServiceException;
import com.zml.user.service.IPostService;

@Service("postService")
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostDao postDao;
	
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Long addPost(Post post) throws PostServiceException {
		return this.postDao.insert(post);
	}

	public Long updatePost(Post post) throws PostServiceException {
		return this.postDao.update(post);
	}

	public List<Post> findAll(Map<String, Object> map)
			throws PostServiceException {
		List<Post> list = this.postDao.getList(map);
		if(list != null) {
			return list;
		} else {
			return Collections.emptyList();
		}
	}

}
