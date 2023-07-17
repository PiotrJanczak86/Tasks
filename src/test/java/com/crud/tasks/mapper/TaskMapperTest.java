package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskMapperTest {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    void mapToTaskTest() {
        //Given
        TaskDto taskDto = new TaskDto(1L, "task 1", "content 1");

        //When
        Task resultTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1L, resultTask.getId());
        assertEquals("task 1", resultTask.getTitle());
        assertEquals("content 1", resultTask.getContent());
    }

    @Test
    void mapToTaskDtoTest() {
        //Given
        Task task = new Task(1L, "task 1", "content 1");

        //When
        TaskDto resultTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(1L, resultTaskDto.getId());
        assertEquals("task 1", resultTaskDto.getTitle());
        assertEquals("content 1", resultTaskDto.getContent());
    }

    @Test
    void mapToTaskDtoListTest() {
        //Given
        Task task1 = new Task(1L, "task 1", "content 1");
        Task task2 = new Task(2L, "task 2", "content 2");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);

        //When
        List<TaskDto> resultTasksDto = taskMapper.mapToTaskDtoList(tasks);
        TaskDto resultTaskDto1 = resultTasksDto.get(0);
        TaskDto resultTaskDto2 = resultTasksDto.get(1);

        //Then
        assertEquals(2, resultTasksDto.size());
        assertEquals(1L, resultTaskDto1.getId());
        assertEquals("task 1", resultTaskDto1.getTitle());
        assertEquals("content 1", resultTaskDto1.getContent());
        assertEquals(2L, resultTaskDto2.getId());
        assertEquals("task 2", resultTaskDto2.getTitle());
        assertEquals("content 2", resultTaskDto2.getContent());
    }
}