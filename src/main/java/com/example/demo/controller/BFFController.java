package com.example.demo.controller;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import com.example.demo.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class BFFController {
    
    @Autowired
    private OrchestratorService orchestratorService;
    
    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO producto) {
        ProductoDTO resultado = orchestratorService.crearProducto(producto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
    
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productos = orchestratorService.listarProductos();
        return ResponseEntity.ok(productos);
    }
    
    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> obtenerProducto(@PathVariable Long id) {
        ProductoDTO producto = orchestratorService.obtenerProducto(id);
        return ResponseEntity.ok(producto);
    }
    
    @PutMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO producto) {
        ProductoDTO resultado = orchestratorService.actualizarProducto(id, producto);
        return ResponseEntity.ok(resultado);
    }
    
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        orchestratorService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/bodegas")
    public ResponseEntity<BodegaDTO> crearBodega(@RequestBody BodegaDTO bodega) {
        BodegaDTO resultado = orchestratorService.crearBodega(bodega);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }
    
    @GetMapping("/bodegas")
    public ResponseEntity<List<BodegaDTO>> listarBodegas() {
        List<BodegaDTO> bodegas = orchestratorService.listarBodegas();
        return ResponseEntity.ok(bodegas);
    }
    
    @GetMapping("/bodegas/{id}")
    public ResponseEntity<BodegaDTO> obtenerBodega(@PathVariable Long id) {
        BodegaDTO bodega = orchestratorService.obtenerBodega(id);
        return ResponseEntity.ok(bodega);
    }
    
    @PutMapping("/bodegas/{id}")
    public ResponseEntity<BodegaDTO> actualizarBodega(@PathVariable Long id, @RequestBody BodegaDTO bodega) {
        BodegaDTO resultado = orchestratorService.actualizarBodega(id, bodega);
        return ResponseEntity.ok(resultado);
    }
    
    @DeleteMapping("/bodegas/{id}")
    public ResponseEntity<Void> eliminarBodega(@PathVariable Long id) {
        orchestratorService.eliminarBodega(id);
        return ResponseEntity.noContent().build();
    }
}