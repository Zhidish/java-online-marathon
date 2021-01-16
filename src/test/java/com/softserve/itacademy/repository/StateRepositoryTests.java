package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.State;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class StateRepositoryTests {

    @Autowired
    StateRepository stateRepository;

    @Test
    public void checkCreateState() {
        State state = new State();
        state.setName("StateCreate");
        long test_id = new Double(Math.random()*100).longValue();
        state.setId(test_id);
        state = stateRepository.save(state);
        assertEquals(test_id, state.getId());
    }
    @Test
    public void checkDeleteStateByState(){
        State state = new State();
        long test_id = new Double(Math.random()*100).longValue();
        state.setName("StateDelete");
        state.setId(test_id);
        state = stateRepository.save(state);
        stateRepository.delete(state);
        assertFalse(stateRepository.existsById(state.getId()));
    }
    @Test
    public void checkDeleteStateById(){
        State state = new State();
        long test_id = new Double(Math.random()*100).longValue();
        state.setName("StateDeleteByID");
        state.setId(test_id);
        state = stateRepository.save(state);
        assertEquals(test_id, state.getId());
        stateRepository.deleteById(state.getId());
        assertFalse(stateRepository.existsById(state.getId()));
    }
    @Test
    public void checkDeleteAllStates(){
        for (int i = 0; i < 10; i++) {
            State state = new State();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            state.setName("StateDelete" + i);
            state.setId(test_id);
            stateRepository.save(state);
        }
        assertEquals(stateRepository.count(), 10);
        assertEquals(stateRepository.findAll().size(), 10);
        stateRepository.deleteAll();
        assertEquals(stateRepository.count(), 0);
        assertEquals(stateRepository.findAll().size(), 0);
    }
    @Test
    public void checkExistsState(){
        State state = new State();
        long test_id = new Double(Math.random()*100).longValue();
        state.setName("StateExists");
        state.setId(test_id);
        stateRepository.save(state);
        assertTrue(stateRepository.findAll().stream().anyMatch(state1 -> state1.getId() == state.getId()));
        assertTrue(stateRepository.existsById(state.getId()));
    }
    @Test
    public void checkFindState(){
        State state = new State();
        long test_id = new Double(Math.random()*100).longValue();
        state.setName("StateFind");
        state.setId(test_id);
        stateRepository.save(state);
        assertTrue(stateRepository.findAll().stream().anyMatch(state1 -> state1.getId() == state.getId()));
        assertEquals(state, stateRepository.findById(state.getId()).get());
    }
    @Test
    public void checkReturnAll(){
        List<State> expectedStates = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            State state = new State();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            state.setName("State" + i);
            state.setId(test_id);
            stateRepository.save(state);
            expectedStates.add(state);
        }
        assertEquals(stateRepository.count(), 10);
        assertEquals(stateRepository.findAll().size(), 10);
        assertEquals(expectedStates, stateRepository.findAll());
    }
    @Test
    public void checkCounter(){
        Set<State> expectedStates = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            State state = new State();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            state.setName("State" + i);
            state.setId(test_id);
            stateRepository.save(state);
            expectedStates.add(state);
        }
        assertEquals(stateRepository.count(), stateRepository.findAll().size(), expectedStates.size());
        assertEquals(stateRepository.count(), expectedStates.size());
        assertEquals(stateRepository.findAll().size(), expectedStates.size());
        for (int i = 0; i < 10; i++) {
            State state = new State();
            long test_id = new Double(Math.random()*100*(i+1)).longValue();
            state.setName("State" + i*10);
            state.setId(test_id);
            stateRepository.save(state);
            expectedStates.add(state);
        }
        assertEquals(stateRepository.count(), stateRepository.findAll().size(), expectedStates.size());
        assertEquals(stateRepository.count(), expectedStates.size());
        assertEquals(stateRepository.findAll().size(), expectedStates.size());
    }
}
