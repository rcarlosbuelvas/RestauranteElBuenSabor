package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

public class Carta {

    private static final List<Producto> PRODUCTOS = new ArrayList<>();

    static {
        PRODUCTOS.add(new Producto("Bandeja Paisa",       32000));
        PRODUCTOS.add(new Producto("Sancocho de Gallina", 28000));
        PRODUCTOS.add(new Producto("Arepa con Huevo",      8000));
        PRODUCTOS.add(new Producto("Jugo Natural",          7000));
        PRODUCTOS.add(new Producto("Gaseosa",               4500));
        PRODUCTOS.add(new Producto("Cerveza Poker",         6000));
        PRODUCTOS.add(new Producto("Agua Panela",           3500));
        PRODUCTOS.add(new Producto("Arroz con Pollo",      25000));
    }

    public static Producto getProducto(int indice) {
        return PRODUCTOS.get(indice);
    }

    public static int getCantidadProductos() {
        return PRODUCTOS.size();
    }

    public static List<Producto> getTodos() {
        return PRODUCTOS;
    }
}