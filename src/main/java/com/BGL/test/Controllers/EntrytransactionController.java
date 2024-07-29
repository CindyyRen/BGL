package com.BGL.test.Controllers;

import com.BGL.test.entity.EntryTransaction;
import com.BGL.test.exception.EntryNotFoundException;
import com.BGL.test.exception.EntrytransactionNotFoundException;
import com.BGL.test.service.EntrytransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/entrytransaction")
public class EntrytransactionController {
    @Autowired
    EntrytransactionService entrytransactionService;
    @GetMapping("/{id}")
    public ResponseEntity<EntryTransaction> findEntrytransactionById(@PathVariable("id") Long id) {
        EntryTransaction entrytransaction = entrytransactionService.getEntrytransaction(id);
        return ResponseEntity.ok(entrytransaction);
    }
    @GetMapping()
    public ResponseEntity<Page<EntryTransaction>> getAllEntrytransaction(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EntryTransaction> allEntrytransaction = entrytransactionService.getAllEntrytransaction(pageable);
        return ResponseEntity.ok(allEntrytransaction);
    }

    @PostMapping
    public ResponseEntity<EntryTransaction> saveEntrytransaction(@RequestBody EntryTransaction entrytransaction) {
        EntryTransaction created = entrytransactionService.createEntrytransaction(entrytransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntryTransaction> updateEntrytransaction(@PathVariable Long id, @RequestBody EntryTransaction entrytransaction) {
        EntryTransaction updated = entrytransactionService.updateEntrytransaction(id, entrytransaction);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrytransaction(@PathVariable("id") Long id) {
        entrytransactionService.deleteEntrytransaction(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler({EntrytransactionNotFoundException.class, EntryNotFoundException.class})
    public ResponseEntity<String> handleUserNotFound(EntrytransactionNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }

}
