package com.example.DigioProgrammingTask.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DigioProgrammingTask.ModelClasses.LogResultsStorage;

public interface LogResultsRepository extends JpaRepository<LogResultsStorage, Long> {

}
