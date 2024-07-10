package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	
	public TodoController(TodoService todoservice) {
		super();
		this.todoservice = todoservice;
	}
	private TodoService todoservice;
	
	@RequestMapping("list-todos")
	public String listTodos(ModelMap model){
		String username= getLoggedinUsername(model);

		List<Todo> todos = todoservice.findByUsername(username);
		model.addAttribute("todos", todos);
		return "listTodos";
		
	}

	
	
	@RequestMapping(value ="add-todo", method= RequestMethod.GET)
	public String showNewTodo(ModelMap model){
		String username= getLoggedinUsername(model);
		Todo todo = new Todo(0,username,"", LocalDate.now().plusDays(10), false);
		model.put("todo", todo);
		return "todo";
		
	}
	
	@RequestMapping(value ="add-todo", method= RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo ,BindingResult result){
		
		if(result.hasErrors()) {
			return "todo";
		}
		String username= getLoggedinUsername(model);
		todoservice.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);
		return "redirect:list-todos";
		
	}
	
	@RequestMapping("delete-todo")
	public String deleteTodos(@RequestParam int id){
		
		todoservice.deleteById(id);
		
		return "redirect:list-todos";
		
	}
	
	@RequestMapping(value ="update-todo", method= RequestMethod.GET)
	public String showUpdateTodosPage(@RequestParam int id, ModelMap model){
		
		Todo todo = todoservice.findById(id);
		model.addAttribute("todo", todo);
		
		return "todo";
		
	}
	
	@RequestMapping(value ="update-todo", method= RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo ,BindingResult result){
		
		if(result.hasErrors()) {
			return "todo";
		}
		String username= getLoggedinUsername(model);
		todoservice.updateTodo(todo);
		todo.setUsername(username);
		return "redirect:list-todos";
		
	}
	
	private String getLoggedinUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName(); 	}

}
