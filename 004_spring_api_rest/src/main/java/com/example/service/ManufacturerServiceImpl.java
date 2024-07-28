package com.example.service;

import com.example.model.Manufacturer;
import com.example.repository.ManufacturerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    // Inyectamos el repo
    private ManufacturerRepository repository;

    // En este caso inyectamos desde un constructor, muy recomendable, ya que facilita el testing.
    public ManufacturerServiceImpl(ManufacturerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List<Manufacturer> findAllByYear(Integer year) {
        // Lanza una excepción en caso de pasar un year que sea nulo, y evita así conectar con la bbdd
        Objects.requireNonNull(year);
        // debemos de tener el método findAllByYear creado en el repositorio:
        return this.repository.findAllByYear(year);
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        // es necesario implementarlo para que devuelva algo:
        return this.repository.findById(id);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        // Guardamos el manufacturer para poderlo devolver
        this.repository.save(manufacturer);
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAll() {

    }
}
