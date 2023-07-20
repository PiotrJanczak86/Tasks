package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldGetAllTasksTest() {
        //Given
        List<Task> tasks = Arrays.asList(new Task(1L, "test title1", "test content1"), new Task(2L, "test title2", "test content2"));
        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> foundTasks = dbService.getAllTasks();

        //Then
        assertEquals(foundTasks.size(), 2);
    }

    @Test
    public void shouldFindTaskByIdTaskTest() throws TaskNotFoundException {
        //Given
        Task task = new Task(1L, "test title", "test content");
        when(repository.findById(task.getId())).thenReturn(Optional.of(task));

        //When
        Task foundTask = dbService.getTask(task.getId());

        //Then
        assertEquals(1L, foundTask.getId());
        assertEquals("test title", foundTask.getTitle());
        assertEquals("test content", foundTask.getContent());
    }

    @Test
    public void shouldSaveTaskTest() {
        //Given
        Task task = new Task(1L, "test title", "test content");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(1L, savedTask.getId());
        assertEquals("test title", savedTask.getTitle());
        assertEquals("test content", savedTask.getContent());
    }

    @Test
    public void shouldThrowExceptionTest() {
        //Given & When
        when(repository.existsById(1L)).thenReturn(false);

        //Then
        assertThrows(TaskNotFoundException.class, () -> dbService.deleteTask(1L));
    }
}