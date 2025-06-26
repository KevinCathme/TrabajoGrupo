package club;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int op = 0;
        Club c = new Club();

        do{
            try {
                System.out.println("\n==== MENÚ PRINCIPAL ====");
                System.out.println("1. Afiliar un socio al club");
                System.out.println("2. Registrar una persona autorizada por un socio");
                System.out.println("3. Pagar una factura");
                System.out.println("4. Registrar un consumo en la cuenta de un socio");
                System.out.println("5. Aumentar fondos de la cuenta de un socio");
                System.out.println("6. Eliminar un socio del club");
                System.out.println("7. Buscar consumos de un socio");
                System.out.println("8. Salir");
                System.out.print("Ingrese una opción: ");
                op = Integer.parseInt(sc.next());

                switch (op){
                    case 1:{
                        System.out.println("\n--- AFILIAR SOCIO ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();
                        sc.nextLine(); // Limpiar el buffer
                        System.out.print("Ingrese el nombre del socio: ");
                        String nombre = sc.nextLine();
                        System.out.print("Ingrese el tipo de socio (1. VIP, 2. REGULAR): ");
                        int tipo = Integer.parseInt(sc.next());

                        Socio.Tipo tipoSocio;
                        if( tipo == 1 )
                        {
                            tipoSocio = Socio.Tipo.VIP;
                        }
                        else
                        {
                            tipoSocio = Socio.Tipo.REGULAR;
                        }

                        c.afiliarSocio( cedula, nombre, tipoSocio );
                    }break;

                    case 2:{
                        System.out.println("\n--- REGISTRAR PERSONA AUTORIZADA ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();
                        sc.nextLine(); // Limpiar buffer

                        Socio socio = c.buscarSocio(cedula);
                        if( socio != null )
                        {
                            System.out.print("Ingrese el nombre de la persona a autorizar: ");
                            String nombreAutorizado = sc.nextLine();
                            c.agregarAutorizadoSocio( cedula, nombreAutorizado );
                        }
                        else
                        {
                            System.out.println("No existe un socio con esa cédula.");
                        }
                    }break;

                    case 3:{
                        System.out.println("\n--- PAGAR FACTURA ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();

                        ArrayList<Factura> facturas = c.darFacturasSocio( cedula );
                        if( facturas != null && facturas.size() > 0 )
                        {
                            System.out.println("\nFacturas pendientes:");
                            for( int i = 0; i < facturas.size(); i++ )
                            {
                                System.out.println(i + ". " + facturas.get(i).toString());
                            }

                            System.out.print("Ingrese el índice de la factura a pagar: ");
                            int indice = Integer.parseInt(sc.next());
                            c.pagarFacturaSocio( cedula, indice );
                        }
                        else
                        {
                            System.out.println("El socio no tiene facturas pendientes o no existe.");
                        }
                    }break;

                    case 4:{
                        System.out.println("\n--- REGISTRAR CONSUMO ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();

                        Socio socio = c.buscarSocio(cedula);
                        if( socio != null )
                        {
                            // Mostrar autorizados
                            ArrayList<String> autorizados = c.darAutorizadosSocio(cedula);
                            System.out.println("\nPersonas autorizadas:");
                            for( String autorizado : autorizados )
                            {
                                System.out.println("- " + autorizado);
                            }

                            sc.nextLine(); // Limpiar buffer
                            System.out.print("Ingrese el nombre de quien realizó el consumo: ");
                            String nombreCliente = sc.nextLine();
                            System.out.print("Ingrese el concepto del consumo: ");
                            String concepto = sc.nextLine();
                            System.out.print("Ingrese el valor del consumo: ");
                            double valor = Double.parseDouble(sc.next());

                            c.registrarConsumo( cedula, nombreCliente, concepto, valor );
                        }
                        else
                        {
                            System.out.println("No existe un socio con esa cédula.");
                        }
                    }break;

                    case 5:{
                        System.out.println("\n--- AUMENTAR FONDOS ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();

                        Socio socio = c.buscarSocio(cedula);
                        if( socio != null )
                        {
                            System.out.println("Fondos actuales: $" + socio.darFondos());
                            System.out.print("Ingrese el valor a aumentar: ");
                            double valor = Double.parseDouble(sc.next());

                            if( valor > 0 )
                            {
                                c.aumentarFondosSocio( cedula, valor );
                            }
                            else
                            {
                                System.out.println("El valor debe ser mayor a 0.");
                            }
                        }
                        else
                        {
                            System.out.println("No existe un socio con esa cédula.");
                        }
                    }break;

                    case 6:{
                        System.out.println("\n--- ELIMINAR SOCIO ---");
                        System.out.print("Ingrese la cédula del socio a eliminar: ");
                        String cedula = sc.next();

                        // Primero verificar si se puede eliminar
                        if( !c.sePuedeEliminarSocio( cedula ) )
                        {
                            Socio socio = c.buscarSocio( cedula );
                            if( socio == null )
                            {
                                System.out.println("No existe un socio con esa cédula.");
                            }
                            else if( socio.darTipo() == Socio.Tipo.VIP )
                            {
                                System.out.println("No se puede eliminar un socio VIP.");
                            }
                            else if( socio.darFacturas().size() > 0 )
                            {
                                System.out.println("El socio tiene facturas pendientes de pago.");
                            }
                            else if( socio.darAutorizados().size() > 1 )
                            {
                                System.out.println("El socio tiene más de un autorizado.");
                            }
                        }
                        else
                        {
                            // Si se puede eliminar, proceder con la eliminación
                            if( c.eliminarSocio( cedula ) )
                            {
                                System.out.println("Socio eliminado exitosamente.");
                            }
                        }
                    }break;

                    case 7:{
                        System.out.println("\n--- BUSCAR CONSUMOS DE SOCIO ---");
                        System.out.print("Ingrese la cédula del socio: ");
                        String cedula = sc.next();
                        c.buscarConsumo(cedula);
                    }break;

                    case 8:{
                        System.out.println("\n¡Gracias por usar el sistema del club!");
                    }break;

                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo");
                }
            }
            catch(NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                sc.nextLine(); // Limpiar buffer
            }
            catch(Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
                sc.nextLine(); // Limpiar buffer
            }

        }while(op != 8);

        sc.close();
    }
}