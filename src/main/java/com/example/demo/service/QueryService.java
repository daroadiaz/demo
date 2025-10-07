package com.example.demo.service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class QueryService {

    private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

    @Value("${azure.functions.producto.query.url:http://localhost:7071/api/productos}")
    private String productoQueryUrl;

    @Value("${azure.functions.bodega.query.url:http://localhost:7072/api/bodegas}")
    private String bodegaQueryUrl;

    private final RestTemplate restTemplate;

    public QueryService() {
        this.restTemplate = new RestTemplate();
    }

    public ProductoDTO obtenerProducto(Long id) {
        try {
            String url = productoQueryUrl + "/" + id;
            ResponseEntity<ProductoDTO> response = restTemplate.getForEntity(url, ProductoDTO.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error al consultar producto con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al consultar producto", e);
        }
    }

    public List<ProductoDTO> listarProductos() {
        try {
            ResponseEntity<List<ProductoDTO>> response = restTemplate.exchange(
                productoQueryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductoDTO>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error al listar productos: {}", e.getMessage());
            throw new RuntimeException("Error al listar productos", e);
        }
    }

    public BodegaDTO obtenerBodega(Long id) {
        try {
            String url = bodegaQueryUrl + "/" + id;
            ResponseEntity<BodegaDTO> response = restTemplate.getForEntity(url, BodegaDTO.class);
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error al consultar bodega con ID {}: {}", id, e.getMessage());
            throw new RuntimeException("Error al consultar bodega", e);
        }
    }

    public List<BodegaDTO> listarBodegas() {
        try {
            ResponseEntity<List<BodegaDTO>> response = restTemplate.exchange(
                bodegaQueryUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BodegaDTO>>() {}
            );
            return response.getBody();
        } catch (Exception e) {
            logger.error("Error al listar bodegas: {}", e.getMessage());
            throw new RuntimeException("Error al listar bodegas", e);
        }
    }
}
