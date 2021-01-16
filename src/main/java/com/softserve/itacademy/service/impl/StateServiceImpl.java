package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.repository.impl.StateRepositoryImpl;
import com.softserve.itacademy.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    StateRepositoryImpl stateRepository = new StateRepositoryImpl();

    @Override
    public State create(State state) {
        if (stateRepository.existsById(state.getId())){
            update(state);
        }
        else {
            stateRepository.save(state);
        }
        return stateRepository.findById(state.getId()).get();
    }

    @Override
    public State readById(long id) {
        if (stateRepository.existsById(id)){
            return stateRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public State update(State state) {
        if (stateRepository.existsById(state.getId())) {
            return stateRepository.save(state);
        }
        return null;
    }

    @Override
    public void delete(long id) {
        if (stateRepository.existsById(id)){
            stateRepository.deleteById(id);
        }
    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        if (stateRepository.findAll().stream().anyMatch(state -> state.getName().equals(name))){
            return stateRepository.findAll().stream().filter(state -> state.getName().equals(name)).findFirst().get();
        }
        return null;
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.getAllStatesSortedByName();
    }
}
