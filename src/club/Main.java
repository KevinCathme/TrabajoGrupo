package club;

import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        int op;
        Club c = new Club();

        do{
            System.out.println("1. Afiliar un socio al club.");
            System.out.println("2. Registrar una persona autorizada por un socio.");
            System.out.println("3. Pagar una factura.");
            System.out.println("4. Registrar un consumo en la cuenta de un socio");
            System.out.println("5. Aumentar fondos de la cuenta de un socio");
            System.out.println("6. Eliminar un socio del club");
            System.out.println("7. Buscar consumos socios");
            System.out.println("8. Salir");
            System.out.print("Ingrese una opcion: ");
            op = Integer.parseInt(sc.next());
            switch (op){
                case 1:{
                    System.out.print("Ingrese la cedula del socio: ");
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
                    System.out.println("Socio afiliado exitosamente.");
                }break;
                case 2:{

                }break;
                case 3:{

                }break;
                case 4:{

                }break;
                case 5:{

                }break;
                case 6:{
                    System.out.print("Ingrese la cedula del socio a eliminar: ");
                    String cedula = sc.next();

                    // Primero verificar si se puede eliminar
                    if( !c.sePuedeEliminarSocio( cedula ) )
                    {
                        Socio socio = c.buscarSocio( cedula );
                        if( socio == null )
                        {
                            System.out.println("No existe un socio con esa cedula.");
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
                            System.out.println("El socio tiene mas de un autorizado.");
                        }
                    }
                    else
                    {
                        // Si se puede eliminar, proceder con la eliminación
                        if( c.sePuedeEliminarSocio( cedula ) )
                        {
                            System.out.println("Socio eliminado exitosamente.");
                        }
                    }
                }break;
                case 7:{
                    System.out.print("Ingrese la cedula del socio a eliminar: ");
                    String cedula = sc.next();
                    Club.buscarConsumo(cedula);
                }break;
                case 8:{
                    System.out.println("Gracias!");
                }break;
                default:
                    System.out.println("opcion invalida");
            }

        }while(op!=7);


    }
}