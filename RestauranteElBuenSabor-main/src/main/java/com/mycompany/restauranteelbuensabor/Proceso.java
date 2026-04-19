package com.mycompany.restauranteelbuensabor;

public class Proceso {

    public static Factura generarFactura(Pedido pedido, int numeroFactura) {
        return new Factura(pedido, numeroFactura);
    }
}