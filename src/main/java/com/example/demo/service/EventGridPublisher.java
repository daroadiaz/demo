package com.example.demo.service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import com.example.demo.event.EventGridEvent;
import com.example.demo.event.EventType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class EventGridPublisher {

    private static final Logger logger = LoggerFactory.getLogger(EventGridPublisher.class);

    @Value("${eventgrid.productos.topic.endpoint:http://localhost:8090/productos-events}")
    private String productosTopic;

    @Value("${eventgrid.bodegas.topic.endpoint:http://localhost:8090/bodegas-events}")
    private String bodegasTopic;

    @Value("${eventgrid.topic.key:mock-key}")
    private String topicKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public EventGridPublisher() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public void publishProductoEvent(EventType eventType, ProductoDTO producto) {
        try {
            String subject = String.format("/productos/%s", producto.getId() != null ? producto.getId() : "new");
            EventGridEvent<ProductoDTO> event = new EventGridEvent<>(eventType.getType(), subject, producto);

            publishEvent(productosTopic, Collections.singletonList(event));
            logger.info("Evento {} publicado exitosamente para producto: {}", eventType, producto.getCodigo());
        } catch (Exception e) {
            logger.error("Error al publicar evento de producto: {}", e.getMessage(), e);
            throw new RuntimeException("Error al publicar evento a Event Grid", e);
        }
    }

    public void publishBodegaEvent(EventType eventType, BodegaDTO bodega) {
        try {
            String subject = String.format("/bodegas/%s", bodega.getId() != null ? bodega.getId() : "new");
            EventGridEvent<BodegaDTO> event = new EventGridEvent<>(eventType.getType(), subject, bodega);

            publishEvent(bodegasTopic, Collections.singletonList(event));
            logger.info("Evento {} publicado exitosamente para bodega: {}", eventType, bodega.getCodigo());
        } catch (Exception e) {
            logger.error("Error al publicar evento de bodega: {}", e.getMessage(), e);
            throw new RuntimeException("Error al publicar evento a Event Grid", e);
        }
    }

    private void publishEvent(String topicEndpoint, List<?> events) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("aeg-sas-key", topicKey);

        HttpEntity<List<?>> request = new HttpEntity<>(events, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            topicEndpoint,
            HttpMethod.POST,
            request,
            String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to publish event: " + response.getStatusCode());
        }
    }
}
