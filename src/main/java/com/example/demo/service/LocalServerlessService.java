package com.example.demo.service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Bodega;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalServerlessService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private BodegaRepository bodegaRepository;
    
    public ProductoDTO crearProducto(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());
        
        producto = productoRepository.save(producto);
        return convertirProductoADTO(producto);
    }
    
    public ProductoDTO actualizarProducto(Long id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        producto.setCategoria(dto.getCategoria());
        producto.setActivo(dto.getActivo());
        
        producto = productoRepository.save(producto);
        return convertirProductoADTO(producto);
    }
    
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    
    public ProductoDTO obtenerProducto(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return convertirProductoADTO(producto);
    }
    
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findAll().stream()
            .map(this::convertirProductoADTO)
            .collect(Collectors.toList());
    }
    
    public BodegaDTO crearBodega(BodegaDTO dto) {
        Bodega bodega = new Bodega();
        bodega.setCodigo(dto.getCodigo());
        bodega.setNombre(dto.getNombre());
        bodega.setDireccion(dto.getDireccion());
        bodega.setTelefono(dto.getTelefono());
        bodega.setCapacidadMaxima(dto.getCapacidadMaxima());
        bodega.setEspacioUtilizado(dto.getEspacioUtilizado());
        bodega.setActivo(dto.getActivo());
        
        bodega = bodegaRepository.save(bodega);
        return convertirBodegaADTO(bodega);
    }
    
    public BodegaDTO actualizarBodega(Long id, BodegaDTO dto) {
        Bodega bodega = bodegaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));
        
        bodega.setCodigo(dto.getCodigo());
        bodega.setNombre(dto.getNombre());
        bodega.setDireccion(dto.getDireccion());
        bodega.setTelefono(dto.getTelefono());
        bodega.setCapacidadMaxima(dto.getCapacidadMaxima());
        bodega.setEspacioUtilizado(dto.getEspacioUtilizado());
        bodega.setActivo(dto.getActivo());
        
        bodega = bodegaRepository.save(bodega);
        return convertirBodegaADTO(bodega);
    }
    
    public void eliminarBodega(Long id) {
        bodegaRepository.deleteById(id);
    }
    
    public BodegaDTO obtenerBodega(Long id) {
        Bodega bodega = bodegaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));
        return convertirBodegaADTO(bodega);
    }
    
    public List<BodegaDTO> listarBodegas() {
        return bodegaRepository.findAll().stream()
            .map(this::convertirBodegaADTO)
            .collect(Collectors.toList());
    }
    
    private ProductoDTO convertirProductoADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setCodigo(producto.getCodigo());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setStockMinimo(producto.getStockMinimo());
        dto.setCategoria(producto.getCategoria());
        dto.setActivo(producto.getActivo());
        return dto;
    }
    
    private BodegaDTO convertirBodegaADTO(Bodega bodega) {
        BodegaDTO dto = new BodegaDTO();
        dto.setId(bodega.getId());
        dto.setCodigo(bodega.getCodigo());
        dto.setNombre(bodega.getNombre());
        dto.setDireccion(bodega.getDireccion());
        dto.setTelefono(bodega.getTelefono());
        dto.setCapacidadMaxima(bodega.getCapacidadMaxima());
        dto.setEspacioUtilizado(bodega.getEspacioUtilizado());
        dto.setActivo(bodega.getActivo());
        return dto;
    }
}