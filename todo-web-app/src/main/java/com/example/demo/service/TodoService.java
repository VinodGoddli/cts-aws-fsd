package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.TodoDao;
import com.example.demo.model.Todo;

@Service
public class TodoService {
	
	private TodoDao todoDao;
	
    public TodoService(TodoDao todoDao) {
		super();
		this.todoDao = todoDao;
	}
	private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "dummy", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "dummy", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "dummy", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
		/*
		 * for (Todo todo : todos) { if (todo.getUser().equals(user)) {
		 * filteredTodos.add(todo); } }
		 */
        filteredTodos=todoDao.findAll();
        
        return filteredTodos;
    }
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }
    public void addTodo(String name, String desc, Date targetDate,
            boolean isDone) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
    public void updateTodo(Todo todo){
		todos.remove(todo);
		todos.add(todo);
}
}