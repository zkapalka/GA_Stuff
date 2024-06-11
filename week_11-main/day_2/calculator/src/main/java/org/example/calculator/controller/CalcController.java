package org.example.calculator.controller;

import org.example.calculator.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalcController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public ResponseEntity<Integer> add(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.add(a, b);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/subtract")
    public ResponseEntity<Integer> subtract(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.subtract(a, b);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/multiply")
    public ResponseEntity<Integer> multiply(@RequestParam int a, @RequestParam int b) {
        int result = calculatorService.multiply(a, b);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/divide")
    public ResponseEntity<Integer> divide(@RequestParam int a, @RequestParam int b) {
        try {
            int result = calculatorService.divide(a, b);
            return ResponseEntity.ok(result);
        } catch (ArithmeticException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}

