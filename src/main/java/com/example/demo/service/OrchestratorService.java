package com.example.demo.service;

import com.example.demo.client.ServerlessClient;
import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class OrchestratorService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrchestratorService.class);
    
    @Autowired
    private ServerlessClient serverlessClient;
    
    @Autowired
    private LocalServerlessService localService;
    
    public ProductoDTO crearProducto(ProductoDTO producto) {
        try {
            logger.info("Intentando crear producto via serverless function");
            return serverlessClient.crearProducto(producto);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.crearProducto(producto);
        }
    }
    
    public ProductoDTO actualizarProducto(Long id, ProductoDTO producto) {
        try {
            logger.info("Intentando actualizar producto via serverless function");
            return serverlessClient.actualizarProducto(id, producto);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.actualizarProducto(id, producto);
        }
    }
    
    public void eliminarProducto(Long id) {
        try {
            logger.info("Intentando eliminar producto via serverless function");
            serverlessClient.eliminarProducto(id);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            localService.eliminarProducto(id);
        }
    }
    
    public ProductoDTO obtenerProducto(Long id) {
        try {
            logger.info("Intentando obtener producto via serverless function");
            return serverlessClient.obtenerProducto(id);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.obtenerProducto(id);
        }
    }
    
    public List<ProductoDTO> listarProductos() {
        try {
            logger.info("Intentando listar productos via serverless function");
            return serverlessClient.listarProductos();
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.listarProductos();
        }
    }
    
    public BodegaDTO crearBodega(BodegaDTO bodega) {
        try {
            logger.info("Intentando crear bodega via serverless function");
            return serverlessClient.crearBodega(bodega);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.crearBodega(bodega);
        }
    }
    
    public BodegaDTO actualizarBodega(Long id, BodegaDTO bodega) {
        try {
            logger.info("Intentando actualizar bodega via serverless function");
            return serverlessClient.actualizarBodega(id, bodega);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.actualizarBodega(id, bodega);
        }
    }
    
    public void eliminarBodega(Long id) {
        try {
            logger.info("Intentando eliminar bodega via serverless function");
            serverlessClient.eliminarBodega(id);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            localService.eliminarBodega(id);
        }
    }
    
    public BodegaDTO obtenerBodega(Long id) {
        try {
            logger.info("Intentando obtener bodega via serverless function");
            return serverlessClient.obtenerBodega(id);
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.obtenerBodega(id);
        }
    }
    
    public List<BodegaDTO> listarBodegas() {
        try {
            logger.info("Intentando listar bodegas via serverless function");
            return serverlessClient.listarBodegas();
        } catch (RestClientException e) {
            logger.warn("Serverless function no disponible, usando servicio local. Error: {}", e.getMessage());
            return localService.listarBodegas();
        }
    }
}