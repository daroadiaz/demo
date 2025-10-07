package com.example.demo.service;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import com.example.demo.event.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class OrchestratorService {

    private static final Logger logger = LoggerFactory.getLogger(OrchestratorService.class);

    @Autowired
    private EventGridPublisher eventGridPublisher;

    @Autowired
    private QueryService queryService;
    
    public ProductoDTO crearProducto(ProductoDTO producto) {
        logger.info("Publicando evento ProductoCreado para: {}", producto.getCodigo());
        eventGridPublisher.publishProductoEvent(EventType.PRODUCTO_CREADO, producto);
        return producto;
    }
    
    public ProductoDTO actualizarProducto(Long id, ProductoDTO producto) {
        logger.info("Publicando evento ProductoActualizado para ID: {}", id);
        producto.setId(id);
        eventGridPublisher.publishProductoEvent(EventType.PRODUCTO_ACTUALIZADO, producto);
        return producto;
    }
    
    public void eliminarProducto(Long id) {
        logger.info("Publicando evento ProductoEliminado para ID: {}", id);
        ProductoDTO producto = new ProductoDTO();
        producto.setId(id);
        eventGridPublisher.publishProductoEvent(EventType.PRODUCTO_ELIMINADO, producto);
    }
    
    public ProductoDTO obtenerProducto(Long id) {
        logger.info("Consultando producto con ID: {}", id);
        return queryService.obtenerProducto(id);
    }

    public List<ProductoDTO> listarProductos() {
        logger.info("Listando todos los productos");
        return queryService.listarProductos();
    }
    
    public BodegaDTO crearBodega(BodegaDTO bodega) {
        logger.info("Publicando evento BodegaCreada para: {}", bodega.getCodigo());
        eventGridPublisher.publishBodegaEvent(EventType.BODEGA_CREADA, bodega);
        return bodega;
    }

    public BodegaDTO actualizarBodega(Long id, BodegaDTO bodega) {
        logger.info("Publicando evento BodegaActualizada para ID: {}", id);
        bodega.setId(id);
        eventGridPublisher.publishBodegaEvent(EventType.BODEGA_ACTUALIZADA, bodega);
        return bodega;
    }

    public void eliminarBodega(Long id) {
        logger.info("Publicando evento BodegaEliminada para ID: {}", id);
        BodegaDTO bodega = new BodegaDTO();
        bodega.setId(id);
        eventGridPublisher.publishBodegaEvent(EventType.BODEGA_ELIMINADA, bodega);
    }

    public BodegaDTO obtenerBodega(Long id) {
        logger.info("Consultando bodega con ID: {}", id);
        return queryService.obtenerBodega(id);
    }

    public List<BodegaDTO> listarBodegas() {
        logger.info("Listando todas las bodegas");
        return queryService.listarBodegas();
    }
}