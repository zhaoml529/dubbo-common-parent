package com.zml.user.service;

import java.util.List;
import java.util.Map;

import com.zml.user.entity.Post;

public interface IPostService {

	public Long addPost(Post post);
	
	public Long updatePost(Post post);
	
	public List<Post> findAll(Map<String, Object> map);
}
