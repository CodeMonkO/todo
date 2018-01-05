package com.emirates.todo.web.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emirates.todo.web.model.Todo;
import com.emirates.todo.web.service.TodoServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@EnableCaching
public class TodoController {

	@Autowired
	TodoServiceImpl service;

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody void add(@RequestBody String str) throws JsonProcessingException {
		service.addTodo(str, new Date(), false);
	}

	@RequestMapping(value = "/readall", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody Object read(@RequestBody Boolean isDone) throws JsonProcessingException {
		List<Todo> listOfTodo = service.retrieveAllTodos();
		List<Todo> filteredListOfTodo = new LinkedList<Todo>();
		for(Todo todo : listOfTodo){
			if(todo.isDone() == isDone){
				filteredListOfTodo.add(todo);
			}
		}
		System.out.println(service.retrieveAllTodos());
		ObjectMapper o = new ObjectMapper();
		return o.writeValueAsString(filteredListOfTodo);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody void update(@RequestBody String id) throws JsonProcessingException {
		service.updateTodo(Integer.parseInt(id));
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.GET, produces = { "application/json" })
	public @ResponseBody void reset() throws JsonProcessingException {
		service.clearCache();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody void delete(@RequestBody String id) throws JsonProcessingException {
		service.deleteTodo(Integer.parseInt(id));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "index";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String showTodos() {
		return "welcome";
	}
}
