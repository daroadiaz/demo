package com.example.demo.controller;

import com.example.demo.dto.ProductoDTO;
import com.example.demo.dto.BodegaDTO;
import com.example.demo.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GraphQLController {

    @Autowired
    private OrchestratorService orchestratorService;

    @QueryMapping
    public List<ProductoDTO> productos() {
        return orchestratorService.listarProductos();
    }

    @QueryMapping
    public ProductoDTO producto(@Argument Long id) {
        return orchestratorService.obtenerProducto(id);
    }

    @QueryMapping
    public List<BodegaDTO> bodegas() {
        return orchestratorService.listarBodegas();
    }

    @QueryMapping
    public BodegaDTO bodega(@Argument Long id) {
        return orchestratorService.obtenerBodega(id);
    }

    @MutationMapping
    public ProductoDTO crearProducto(@Argument ProductoDTO input) {
        return orchestratorService.crearProducto(input);
    }

    @MutationMapping
    public ProductoDTO actualizarProducto(@Argument Long id, @Argument ProductoDTO input) {
        return orchestratorService.actualizarProducto(id, input);
    }

    @MutationMapping
    public Boolean eliminarProducto(@Argument Long id) {
        orchestratorService.eliminarProducto(id);
        return true;
    }

    @MutationMapping
    public BodegaDTO crearBodega(@Argument BodegaDTO input) {
        return orchestratorService.crearBodega(input);
    }

    @MutationMapping
    public BodegaDTO actualizarBodega(@Argument Long id, @Argument BodegaDTO input) {
        return orchestratorService.actualizarBodega(id, input);
    }

    @MutationMapping
    public Boolean eliminarBodega(@Argument Long id) {
        orchestratorService.eliminarBodega(id);
        return true;
    }
}
