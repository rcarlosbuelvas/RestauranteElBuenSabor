package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    private static final String NOMBRE_RESTAURANTE = "RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION          = "Calle 15 #8-32, Valledupar";
    private static final String NIT                = "NIT: 900.123.456-7";
    private static final String SEPARADOR_LARGO    = "========================================";
    private static final String SEPARADOR_CORTO    = "----------------------------------------";
    private static final String FORMATO_ITEM       = "%-20s x%-6d $%,.0f%n";
    private static final String FORMATO_MONTO      = "%-27s $%,.0f%n";

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR_LARGO);
        System.out.println("    " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    " + NIT);
        System.out.println(SEPARADOR_LARGO);
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_LARGO);
        for (int i = 0; i < Carta.getCantidadProductos(); i++) {
            Producto p = Carta.getProducto(i);
            System.out.printf("%d. %-22s $%,.0f%n", (i + 1), p.getNombre(), p.getPrecio());
        }
        System.out.println(SEPARADOR_LARGO);
    }

    public static void mostrarPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
        System.out.println("--------------------");
        System.out.printf(FORMATO_MONTO, "Subtotal:", pedido.calcularSubtotal());
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_CORTO);

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        System.out.println(SEPARADOR_CORTO);
        imprimirMontos(factura);
        System.out.println(SEPARADOR_LARGO);
        System.out.println("Gracias por su visita!");
        System.out.println(SEPARADOR_LARGO);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println(SEPARADOR_CORTO);
        imprimirMontos(factura);
        System.out.println(SEPARADOR_LARGO);
    }

    private static void imprimirMontos(Factura factura) {
        System.out.printf(FORMATO_MONTO, "Subtotal:", factura.calcularSubtotalConDescuento());
        System.out.printf(FORMATO_MONTO, "IVA (19%):", factura.calcularIVA());
        if (factura.calcularPropina() > 0) {
            System.out.printf(FORMATO_MONTO, "Propina (10%):", factura.calcularPropina());
        }
        System.out.println(SEPARADOR_CORTO);
        System.out.printf(FORMATO_MONTO, "TOTAL:", factura.calcularTotal());
    }
}