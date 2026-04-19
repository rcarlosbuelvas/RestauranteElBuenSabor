package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    public static void reiniciarPedido(Pedido pedido, Mesa mesa) {
        pedido.limpiar();
        mesa.desactivar();
    }
}