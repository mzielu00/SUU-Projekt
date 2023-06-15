package suu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import suu.model.TrafficCongestion;
import suu.repository.TrafficRepository;

import java.util.List;

@RestController
@RequestMapping("/srodmiescie")
public class Edge1Controller {
    private final TrafficRepository repository;

    public Edge1Controller(TrafficRepository repository) {
        this.repository = repository;
    }

    @GetMapping // Get request that returns all students
    public ResponseEntity<List<TrafficCongestion>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}") // Get request that returns a specific student with the provided Id
    public ResponseEntity<TrafficCongestion> getStudentById(@PathVariable String id) {
        return new ResponseEntity<>(repository.findById(Long.valueOf(id))
                .orElseThrow(() -> new IllegalStateException("Edge1: traffic not found")), HttpStatus.OK);
    }
}
