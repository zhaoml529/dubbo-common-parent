package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Post;
import com.zml.user.exceptions.PostServiceException;

public interface IPostService {

	public Long addPost(Post post) throws PostServiceException;
	
	public Long updatePost(Post post) throws PostServiceException;
	
	public List<Post> findAll(Map<String, Object> map) throws PostServiceException;
}
