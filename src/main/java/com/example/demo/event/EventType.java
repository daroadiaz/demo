package com.example.demo.event;

public enum EventType {
    PRODUCTO_CREADO("ProductoCreado"),
    PRODUCTO_ACTUALIZADO("ProductoActualizado"),
    PRODUCTO_ELIMINADO("ProductoEliminado"),
    BODEGA_CREADA("BodegaCreada"),
    BODEGA_ACTUALIZADA("BodegaActualizada"),
    BODEGA_ELIMINADA("BodegaEliminada");

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
