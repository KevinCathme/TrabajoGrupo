package club;
import java.util.ArrayList;
import club.Socio.Tipo;
/**
 * Clase que modela un club.
 */
public class Club
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad máxima de socios VIP que acepta el club.
     */
    public final static int MAXIMO_VIP = 3;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Lista de socios del club.
     */
    private ArrayList<Socio> socios;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Constructor de la clase. <br>
     * <b>post: </b> Se inicializó la lista de socios.
     */
    public Club( )
    {
        socios = new ArrayList<Socio>( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna los socios afiliados al club.
     * @return Lista de socios.
     */
    public ArrayList<Socio> darSocios( )
    {
        return socios;
    }

    /**
     * Afilia un nuevo socio al club. <br>
     * <b>pre: </b> La lista de socios está inicializada. <br>
     * <b>post: </b> Se ha afiliado un nuevo socio en el club con los datos dados.
     * @param pCedula Cédula del socio a afiliar. pCedula != null && pCedula != "".
     * @param pNombre Nombre del socio a afiliar. pNombre != null && pNombre != "".
     * @param pTipo Es el tipo de subscripción del socio. pTipo != null.
     *
     */
    public void afiliarSocio( String pCedula, String pNombre, Tipo pTipo )
    {
        // Revisar que no haya ya un socio con la misma cédula
        Socio s = buscarSocio( pCedula );

        if( s != null )
        {
            System.out.println( "El socio ya existe" );
            return;
        }

        // Revisar que no se haya alcanzado el límite de subscripciones VIP
        if( pTipo == Tipo.VIP && contarSociosVIP( ) >= MAXIMO_VIP )
        {
            System.out.println("El club en el momento no acepta más socios VIP" );
            return;
        }

        // Se crea el objeto del nuevo socio
        Socio nuevoSocio = new Socio( pCedula, pNombre, pTipo );
        // Se agrega el nuevo socio al club
        socios.add( nuevoSocio );
    }

    /**
     * Retorna el socio con la cédula dada. <br>
     * <b> pre:<b> La lista de socios está inicializada.<br>
     * @param pCedulaSocio Cédula del socio buscado. pCedulaSocio != null && pCedulaSocio != "".
     * @return El socio buscado, null si el socio buscado no existe.
     */
    public Socio buscarSocio( String pCedulaSocio )
    {
        Socio elSocio = null;

        boolean encontre = false;
        int numSocios = socios.size( );
        for( int i = 0; i < numSocios && !encontre; i++ )
        {
            Socio s = socios.get( i );
            if( s.darCedula( ).equals( pCedulaSocio ) )
            {
                elSocio = s;
                encontre = true;
            }
        }

        return elSocio;
    }

    /**
     * Retorna la cantidad de socios VIP que tiene el club.<br>
     * <b> pre: </b> La lista de socios está inicializada.
     * @return Número de socios VIP.
     */
    public int contarSociosVIP( )
    {
        int conteo = 0;
        for( Socio socio : socios )
        {
            if( socio.darTipo( ) == Tipo.VIP )
            {
                conteo++;
            }
        }
        return conteo;
    }

    /**
     * Retorna la lista de autorizados del socio con la cédula dada.<br>
     * <b> pre: </b> La lista de socios está inicializada.<br>
     * El socio buscado existe.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @return La lista de autorizados del socio.
     */
    public ArrayList<String> darAutorizadosSocio( String pCedulaSocio )
    {
        Socio s = buscarSocio( pCedulaSocio );
        ArrayList<String> autorizados = new ArrayList<String>( );

        if( s != null )
        {
            autorizados.add( s.darNombre( ) );
            autorizados.addAll( s.darAutorizados( ) );
        }

        return autorizados;
    }

    /**
     * Agrega una nueva persona autorizada por el socio con la cédula dada. <br>
     * <b>pre:<b/> El socio con la cédula dada existe. <b>post: </b> Se agregó el nuevo autorizado..
     * @param pCedulaSocio La cédula del socio al cual se va a agregar el autorizado. pCedulaSocio != null && pCedulaSocio != "".
     * @param pNombreAutorizado El nombre de la persona a autorizar. pNombreAutorizado != null && pNombre != "".
     *
     */
    public void agregarAutorizadoSocio( String pCedulaSocio, String pNombreAutorizado )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            s.agregarAutorizado( pNombreAutorizado );
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    /**
     * Elimina la persona autorizada por el socio con la cédula dada.
     * @param pCedulaSocio La cédula del socio que autorizó a la persona a eliminar.pCedulaSocio!= null && pCedulaSocio! ""
     * @param pNombreAutorizado El nombre del autorizado a eliminar. pNombreAutorizado!= null && pNombreAutorizado!=""
     *
     */
    public void eliminarAutorizadoSocio( String pCedulaSocio, String pNombreAutorizado )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            s.eliminarAutorizado( pNombreAutorizado );
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    /**
     * Registra un consumo a un socio o a su autorizado. <br>
     * <b>post: </b> Se agregó una nueva factura al vector del socio.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pNombreCliente El nombre la persona que realizó en consumo. pNombreCliente != null && pNombreCliente != "".
     * @param pConcepto El concepto del consumo. pConcepto != null && pConcepto != "".
     * @param pValor El valor del consumo. pValor >= 0.
     *
     */
    public void registrarConsumo( String pCedulaSocio, String pNombreCliente, String pConcepto, double pValor )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            // Verificar que el nombre pertenece al socio o sus autorizados
            ArrayList<String> autorizados = darAutorizadosSocio( pCedulaSocio );
            boolean autorizado = false;

            for( String nombre : autorizados )
            {
                if( nombre.equals( pNombreCliente ) )
                {
                    autorizado = true;
                    break;
                }
            }

            if( autorizado )
            {
                s.registrarConsumo( pNombreCliente, pConcepto, pValor );
            }
            else
            {
                System.out.println("La persona no está autorizada para este socio.");
            }
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    /**
     * Retorna la lista de facturas de un socio. <br>
     * <b>pre:<b> Existe el socio con la cédula dada.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @return La lista de facturas del socio.
     */
    public ArrayList<Factura> darFacturasSocio( String pCedulaSocio )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            return s.darFacturas( );
        }
        return new ArrayList<Factura>();
    }

    /**
     * Realiza el pago de la factura de un socio. <br>
     * <b>post: </b> Se borró la factura del vector del socio. <br>
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pFacturaIndice El índice de la factura a pagar. pFacturaIndice >= 0.
     *
     */
    public void pagarFacturaSocio( String pCedulaSocio, int pFacturaIndice )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            ArrayList<Factura> facturas = s.darFacturas();
            if( pFacturaIndice >= 0 && pFacturaIndice < facturas.size() )
            {
                s.pagarFactura( pFacturaIndice );
            }
            else
            {
                System.out.println("Índice de factura inválido.");
            }
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    /**
     * Aumenta los fondos de un socio en la cantidad dada. <br>
     * <b>post: </b> Los fondos del socio aumentaron en el valor especificado.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     * @param pValor Valor por el cual se desean aumentar los fondos. pValor >= 0.
     */
    public void aumentarFondosSocio( String pCedulaSocio, double pValor )
    {
        Socio s = buscarSocio( pCedulaSocio );
        if( s != null )
        {
            s.aumentarFondos( pValor );
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    /**
     * Verifica si un socio puede ser eliminado del club.
     * @param pCedulaSocio La cédula del socio a verificar. pCedulaSocio != null && pCedulaSocio != "".
     * @return true si el socio puede ser eliminado, false en caso contrario.
     */
    public boolean sePuedeEliminarSocio( String pCedulaSocio )
    {
        // Buscar el socio con la cédula dada
        Socio socio = buscarSocio( pCedulaSocio );

        // Si el socio no existe, no se puede eliminar
        if( socio == null )
        {
            return false;
        }

        // Si el socio es VIP, no se puede eliminar
        if( socio.darTipo() == Tipo.VIP )
        {
            return false;
        }

        // Si el socio tiene facturas pendientes, no se puede eliminar
        if( socio.darFacturas().size() > 0 )
        {
            return false;
        }

        // Si el socio tiene más de un autorizado, no se puede eliminar
        if( socio.darAutorizados().size() > 1 )
        {
            return false;
        }

        // Si pasa todas las validaciones, se puede eliminar
        return true;
    }

    /**
     * Elimina un socio del club si cumple con las condiciones necesarias.
     * <b>pre: </b> La lista de socios está inicializada.
     * <b>post: </b> Si el socio cumple las condiciones, se elimina de la lista de socios.
     * @param pCedulaSocio La cédula del socio a eliminar. pCedulaSocio != null && pCedulaSocio != "".
     * @return true si el socio fue eliminado exitosamente, false en caso contrario.
     */
    public boolean eliminarSocio( String pCedulaSocio )
    {
        // Verificar si el socio puede ser eliminado
        if( !sePuedeEliminarSocio( pCedulaSocio ) )
        {
            return false;
        }

        // Buscar y eliminar el socio
        Socio socioAEliminar = buscarSocio( pCedulaSocio );
        socios.remove( socioAEliminar );
        return true;
    }

    /**
     * Busca y muestra el consumo total de un socio.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     */

    /**
     * Busca y muestra el consumo total de un socio.
     * @param pCedulaSocio La cédula del socio. pCedulaSocio != null && pCedulaSocio != "".
     */
    public void buscarConsumo( String pCedulaSocio )
    {
        Socio socio = buscarSocio( pCedulaSocio );

        if( socio != null )
        {
            double totalConsumo = 0.0;
            ArrayList<Factura> facturas = socio.darFacturas();

            for( Factura factura : facturas )
            {
                totalConsumo += factura.darValor();
            }

            System.out.println("\n=== INFORMACIÓN DEL SOCIO ===");
            System.out.println("Socio: " + socio.darNombre());
            System.out.println("Cédula: " + socio.darCedula());
            System.out.println("Tipo: " + socio.darTipo());
            System.out.println("Fondos disponibles: $" + socio.darFondos());
            System.out.println("\n=== CONSUMOS REALIZADOS ===");
            System.out.println("Total de consumos realizados: $" + totalConsumo);

            if( facturas.size() > 0 )
            {
                System.out.println("\nDetalle de consumos:");
                for( int i = 0; i < facturas.size(); i++ )
                {
                    Factura f = facturas.get(i);
                    System.out.println((i+1) + ". " + f.darConcepto() + " - $" + f.darValor() + " (" + f.darNombre() + ")");
                }
            }
            else
            {
                System.out.println("No hay consumos registrados.");
            }
        }
        else
        {
            System.out.println("No existe un socio con esa cédula.");
        }
    }

    // -----------------------------------------------------------------
    // Métodos de Extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1.
     * @return Resultado extensión 1.
     */
    public String metodo1( )
    {
        return "respuesta1";
    }

    /**
     * Extensión 2.
     * @return Resultado extensión 2.
     */
    public String metodo2( )
    {
        return "respuesta2";
    }
}