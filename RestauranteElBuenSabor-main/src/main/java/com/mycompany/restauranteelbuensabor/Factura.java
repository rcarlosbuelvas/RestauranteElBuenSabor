package com.mycompany.restauranteelbuensabor;

public class Factura {

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double calcularDescuento() {
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return calcularSubtotal() * TASA_DESCUENTO;
        }
        return 0;
    }

    public double calcularSubtotalConDescuento() {
        return calcularSubtotal() - calcularDescuento();
    }

    public double calcularIVA() {
        // El IVA se calcula sobre el subtotal ya descontado, según normativa DIAN
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    public double calcularPropina() {
    // La propina aplica sobre el total con IVA incluido, según política del restaurante
    double baseConIva = calcularSubtotalConDescuento() + calcularIVA();
    if (calcularSubtotalConDescuento() > UMBRAL_PROPINA) {
        return baseConIva * TASA_PROPINA;
    }
    return 0;
}

public double calcularTotal() {
    return calcularSubtotalConDescuento() + calcularIVA() + calcularPropina();
}

    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }
}