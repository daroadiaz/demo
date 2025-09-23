package com.example.demo.client;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.*;
import java.util.List;
import java.util.Arrays;

@Component
public class ServerlessClient {
    
    private final RestTemplate restTemplate = new RestTemplate();
    private String serverlessUrl = "http://localhost:7072/api";
    
    public ProductoDTO crearProducto(ProductoDTO producto) throws RestClientException {
        String url = serverlessUrl + "/productos";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDTO> request = new HttpEntity<>(producto, headers);
        ResponseEntity<ProductoDTO> response = restTemplate.postForEntity(url, request, ProductoDTO.class);
        return response.getBody();
    }
    
    public ProductoDTO actualizarProducto(Long id, ProductoDTO producto) throws RestClientException {
        String url = serverlessUrl + "/productos/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ProductoDTO> request = new HttpEntity<>(producto, headers);
        ResponseEntity<ProductoDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, ProductoDTO.class);
        return response.getBody();
    }
    
    public void eliminarProducto(Long id) throws RestClientException {
        String url = serverlessUrl + "/productos/" + id;
        restTemplate.delete(url);
    }
    
    public ProductoDTO obtenerProducto(Long id) throws RestClientException {
        String url = serverlessUrl + "/productos/" + id;
        ResponseEntity<ProductoDTO> response = restTemplate.getForEntity(url, ProductoDTO.class);
        return response.getBody();
    }
    
    public List<ProductoDTO> listarProductos() throws RestClientException {
        String url = serverlessUrl + "/productos";
        ResponseEntity<ProductoDTO[]> response = restTemplate.getForEntity(url, ProductoDTO[].class);
        return Arrays.asList(response.getBody());
    }
    
    public BodegaDTO crearBodega(BodegaDTO bodega) throws RestClientException {
        String url = serverlessUrl + "/bodegas";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BodegaDTO> request = new HttpEntity<>(bodega, headers);
        ResponseEntity<BodegaDTO> response = restTemplate.postForEntity(url, request, BodegaDTO.class);
        return response.getBody();
    }
    
    public BodegaDTO actualizarBodega(Long id, BodegaDTO bodega) throws RestClientException {
        String url = serverlessUrl + "/bodegas/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<BodegaDTO> request = new HttpEntity<>(bodega, headers);
        ResponseEntity<BodegaDTO> response = restTemplate.exchange(url, HttpMethod.PUT, request, BodegaDTO.class);
        return response.getBody();
    }
    
    public void eliminarBodega(Long id) throws RestClientException {
        String url = serverlessUrl + "/bodegas/" + id;
        restTemplate.delete(url);
    }
    
    public BodegaDTO obtenerBodega(Long id) throws RestClientException {
        String url = serverlessUrl + "/bodegas/" + id;
        ResponseEntity<BodegaDTO> response = restTemplate.getForEntity(url, BodegaDTO.class);
        return response.getBody();
    }
    
    public List<BodegaDTO> listarBodegas() throws RestClientException {
        String url = serverlessUrl + "/bodegas";
        ResponseEntity<BodegaDTO[]> response = restTemplate.getForEntity(url, BodegaDTO[].class);
        return Arrays.asList(response.getBody());
    }
}