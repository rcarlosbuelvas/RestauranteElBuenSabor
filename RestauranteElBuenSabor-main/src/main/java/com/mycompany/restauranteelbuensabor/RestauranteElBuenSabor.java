package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    private static final String SEPARADOR      = "========================================";
    private static final String NOMBRE         = "    RESTAURANTE EL BUEN SABOR";
    private static final String DIRECCION      = "    Calle 151 #8-32, Valledupar - cesar";
    private static final String NIT            = "    NIT: 900.123.456-7";

    private static Pedido pedido               = new Pedido();
    private static Mesa mesa                   = new Mesa();
    private static int numeroFactura           = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean ejecutando = true;
        int intentosInvalidos = 0;

        imprimirBienvenida();

        while (ejecutando) {
            imprimirMenu();
            int opcionMenu = sc.nextInt();

            if (opcionMenu == 1) {
                Imprimir.mostrarCarta();
                System.out.println();

            } else if (opcionMenu == 2) {
                procesarAgregarProducto(sc);

            } else if (opcionMenu == 3) {
                System.out.println();
                if (pedido.tieneProductos()) {
                    Imprimir.mostrarPedido(pedido);
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();

            } else if (opcionMenu == 4) {
                procesarGenerarFactura();

            } else if (opcionMenu == 5) {
                System.out.println();
                Utilidades.reiniciarPedido(pedido, mesa);
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();

            } else if (opcionMenu == 0) {
                ejecutando = false;
                System.out.println("Hasta luego!");

            } else {
                intentosInvalidos = intentosInvalidos + 1;
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                if (intentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    intentosInvalidos = 0;
                }
            }
        }

        sc.close();
    }

    private static void imprimirBienvenida() {
        System.out.println(SEPARADOR);
        System.out.println(NOMBRE);
        System.out.println(DIRECCION);
        System.out.println(NIT);
        System.out.println(SEPARADOR);
    }

    private static void imprimirMenu() {
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println(SEPARADOR);
        System.out.print("Seleccione una opcion: ");
    }

    private static void procesarAgregarProducto(Scanner sc) {
        System.out.println("--- AGREGAR PRODUCTO ---");
        System.out.print("Numero de producto (1-" + Carta.getCantidadProductos() + "): ");
        int numeroProducto = sc.nextInt();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();

        if (numeroProducto <= 0 || numeroProducto > Carta.getCantidadProductos()) {
            System.out.println("Producto no existe. La carta tiene " + Carta.getCantidadProductos() + " productos.");
            System.out.println();
            return;
        }

        if (cantidad <= 0) {
            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
            System.out.println();
            return;
        }

        if (!mesa.estaActiva()) {
            System.out.print("Ingrese numero de mesa: ");
            int numeroMesa = sc.nextInt();
            mesa.activar(numeroMesa > 0 ? numeroMesa : 1);
        }

        Producto productoSeleccionado = Carta.getProducto(numeroProducto - 1);
        pedido.agregarProducto(productoSeleccionado, cantidad);
        System.out.println("Producto agregado al pedido.");
        System.out.println("  -> " + productoSeleccionado.getNombre() + " x" + cantidad);
        System.out.println();
    }

    private static void procesarGenerarFactura() {
        System.out.println();
        if (!pedido.tieneProductos()) {
            System.out.println("No se puede generar factura.");
            System.out.println("No hay productos en el pedido.");
            System.out.println("Use la opcion 2 para agregar productos primero.");
            return;
        }

        Factura factura = Proceso.generarFactura(pedido, numeroFactura);
        Imprimir.imprimirFacturaCompleta(factura);
        numeroFactura = numeroFactura + 1;
        mesa.desactivar();
        System.out.println();
    }
}