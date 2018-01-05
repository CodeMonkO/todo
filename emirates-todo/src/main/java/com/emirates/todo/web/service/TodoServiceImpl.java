package com.emirates.todo.web.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.stereotype.Service;

import com.emirates.todo.web.model.Todo;

@Service
@CacheConfig(cacheNames = "todo")
public class TodoServiceImpl {

	@Autowired
	private CacheManager cacheManager;

	private static int todoCount = 0;

	@CacheEvict(allEntries = true)
	public void clearCache() {
		todoCount = 0;
	}

	public List<Todo> retrieveAllTodos() {
		final Cache cache = cacheManager.getCache("todo");
		SimpleValueWrapper filteredTodos =  (SimpleValueWrapper) cache.get("listOfTodo");
		List<Todo> list = new LinkedList<Todo>();
		if(filteredTodos!=null)
		 list = (List<Todo>) filteredTodos.get();	
		return list;
	}
	
	public void updateTodo(int id) {
		List<Todo> listOfTodo = retrieveAllTodos(); 
		final Cache cache = cacheManager.getCache("todo");
		for(Todo todo : listOfTodo){
			if(todo.getId() == id){
				todo.setDone(true);
			}
		}
		cache.put("listOfTodo", listOfTodo);
	}

	@Cacheable
	public void addTodo(String desc, Date targetDate, boolean isDone) {
		Todo todo = new Todo(++todoCount, desc, targetDate, isDone);
		List<Todo> listOfTodo = retrieveAllTodos();
		listOfTodo.add(todo);
		final Cache cache = cacheManager.getCache("todo");
		cache.put("listOfTodo", listOfTodo);
	}

	public void deleteTodo(int id) {
		List<Todo> listOfTodo = retrieveAllTodos();
		List<Todo> newlistOfTodo = new LinkedList<Todo>();
		final Cache cache = cacheManager.getCache("todo");
		for(Todo todo : listOfTodo){
			if(todo.getId() != id){
				newlistOfTodo.add(todo);
			}
		}
		cache.put("listOfTodo", newlistOfTodo);
	}
}