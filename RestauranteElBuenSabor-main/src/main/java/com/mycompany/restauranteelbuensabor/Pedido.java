package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private final List<ItemPedido> items = new ArrayList<>();

    public void agregarProducto(Producto producto, int cantidad) {
        ItemPedido itemExistente = buscarItem(producto);
        if (itemExistente != null) {
            itemExistente.agregarCantidad(cantidad);
        } else {
            items.add(new ItemPedido(producto, cantidad));
        }
    }

    private ItemPedido buscarItem(Producto producto) {
        for (ItemPedido item : items) {
            if (item.getProducto().getNombre().equals(producto.getNombre())) {
                return item;
            }
        }
        return null;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : items) {
            subtotal = subtotal + item.calcularSubtotal();
        }
        return subtotal;
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public void limpiar() {
        items.clear();
    }

    public List<ItemPedido> getItems() {
        return items;
    }
}