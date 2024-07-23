package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    // podemos también agregar nuestras propias queries:
    // @Query("SELECT E from employee"...
    Optional<Employee> findByFullName(String fullName);
    Optional<Employee> findByFullNameAndBirthDate(String fullName, LocalDate birthDate);
    List<Employee> findAllByBirthDateBetween(LocalDate min, LocalDate max);
    List<Employee> findAllByMarriedTrue();
    List<Employee> findAllByMarriedFalse();
}
