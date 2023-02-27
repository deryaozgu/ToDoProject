package com.example.ToDoList;

import com.example.ToDoList.models.ToDo;
import com.example.ToDoList.repositories.ToDoRepository;
import com.example.ToDoList.services.ToDoServices;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ToDoServicesTest {


	@Mock
	private ToDoRepository toDoRepository;

	@InjectMocks
	private ToDoServices toDoServices;

	public ToDoServicesTest() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllToDo() {
		List<ToDo> expectedToDos = new ArrayList<>();
		expectedToDos.add(new ToDo("Todo3","Aciklama3", LocalDate.of(2023, 3, 12),true));
		expectedToDos.add(new ToDo("Todo2","Aciklama2", LocalDate.of(2022, 4, 10),true));
		when(toDoRepository.findAll()).thenReturn(expectedToDos);

		List<ToDo> actualToDos = toDoServices.getAllToDo();

		assertNotNull(actualToDos);
		assertEquals(expectedToDos.size(), actualToDos.size());
	}

	@Test
	public void testGetByIdToDo() {
		ToDo expectedToDo = new ToDo("Todo1","Aciklama1", LocalDate.of(2022, 3, 12),true);
		when(toDoRepository.findById(1)).thenReturn(Optional.of(expectedToDo));

		ToDo actualToDo = toDoServices.getByIdToDo(1);

		assertNotNull(actualToDo);
		assertEquals(expectedToDo.getId(), actualToDo.getId());
		assertEquals(expectedToDo.getDescription(), actualToDo.getDescription());
	}

	@Test
	public void testDeleteToDo() {
		ToDo expectedToDo = new ToDo("Todo1","Aciklama1", LocalDate.of(2022, 3, 12),true);
		when(toDoRepository.findById(1)).thenReturn(Optional.of(expectedToDo));
		doNothing().when(toDoRepository).delete(any());

		Optional<ToDo> actualToDo = toDoServices.deleteToDo(1);

		assertNotNull(actualToDo);
		assertEquals(expectedToDo.getId(), actualToDo.get().getId());
		assertEquals(expectedToDo.getDescription(), actualToDo.get().getDescription());
		verify(toDoRepository, times(1)).delete(any());
	}
	@Test
	public void testUpdateToDo() {
		ToDo expectedToDo = new ToDo( "Todo3", "Aciklama3", LocalDate.of(2022, 5, 12), true);
		expectedToDo.setId(1);
		when(toDoRepository.findById(1)).thenReturn(Optional.of(expectedToDo));
		when(toDoRepository.save(any())).thenReturn(expectedToDo);

		ToDo actualToDo = toDoServices.updateToDo(expectedToDo);

		assertNotNull(actualToDo);
		assertEquals(expectedToDo.getId(), actualToDo.getId());
		assertEquals(expectedToDo.getDescription(), actualToDo.getDescription());
		verify(toDoRepository, times(1)).save(any());
	}

	@Test
	public void testSaveToDo() {
		ToDo expectedToDo = new ToDo("Todo1","Aciklama1", LocalDate.of(2022, 3, 12),true);
		when(toDoRepository.save(any())).thenReturn(expectedToDo);

		ToDo actualToDo = toDoServices.saveToDo(expectedToDo);

		assertNotNull(actualToDo);
		assertEquals(expectedToDo.getId(), actualToDo.getId());
		assertEquals(expectedToDo.getDescription(), actualToDo.getDescription());
		verify(toDoRepository, times(1)).save(any());
	}
}
