package base.tipoDato;

import base.excepciones.RutException;
import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Cristián Alarcón de la Maza
 * @author Omar Paché
 */
public class RUTTest {

    private static final Logger LOG = Logger.getLogger(RUT.class);
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public RUTTest() {
    }

    /**
     * Comprueba reemplazando la 'k' en dintintas cadenas de texto.
     */
    @Test
    public void testReemplazarK_Combinaciones() {
        LOG.info("probando reemplazarK con diferentes cadenas de texto");
        RUT rut = new RUT();
        String reemplazado1 = rut.reemplazarK("345345345");
        String esperado1 = "345345345";
        String reemplazado2 = rut.reemplazarK("345345345K");
        String esperado2 = "345345345K";
        String reemplazado3 = rut.reemplazarK("34k34");
        String esperado3 = "34K34";
        String reemplazado4 = rut.reemplazarK("3453k45k345");
        String esperado4 = "3453K45K345";
        String reemplazado5 = rut.reemplazarK("");
        String esperado5 = "";
        assertEquals(reemplazado1, esperado1);
        assertEquals(reemplazado2, esperado2);
        assertEquals(reemplazado3, esperado3);
        assertEquals(reemplazado4, esperado4);
        assertEquals(reemplazado5, esperado5);
    }

    /**
     * Comprueba reemplazando la 'k' de un texto nulo, debe lanzar excepción.
     */
    @Test(expected = NullPointerException.class)
    public void testReemplazarK_Null() {
        LOG.info("probando reemplazarK con null");
        RUT rut = new RUT();
        rut.reemplazarK(null);
    }

    /**
     * Compara la extracción de dígitos de distintas cadenas de texto.
     */
    @Test
    public void testExtraerDigitos_combinaciones() {
        LOG.info("probando extraerDigitos con distintas cadenas de texto");
        RUT rut = new RUT();
        String expResult1 = "9834791298374918";
        String result1 = rut.extraerDigitos("983479...1298374918");
        String expResult2 = "983479129837491K";
        String result2 = rut.extraerDigitos("983479...129K8k3-74-91K");
        String expResult3 = "9834791298374918";
        String result3 = rut.extraerDigitos("--983479...1298374*918-");
        String expResult4 = "";
        String result4 = rut.extraerDigitos("");
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResult4, result4);
    }

    /**
     * Comprueba extraer dígitos de un texto nulo, debe lanzar excepción.
     */
    @Test(expected = NullPointerException.class)
    public void testExtraerDigitos_Null() {
        LOG.info("probando extraerDigitos con excepcion null");
        RUT rut = new RUT();
        rut.extraerDigitos(null);
    }

    /**
     * Prueba formateando distintos textos como RUT.
     */
    @Test
    public void testFormatear_combinaciones() {

        LOG.info("prubando formatear con diferentes cadenas de texto");
        RUT rut = new RUT();

        String texto1 = "140683337";
        String rutEsperado1 = "14.068.333-7";
        String rutResultante1 = rut.formatear(texto1);
        assertEquals(rutEsperado1, rutResultante1);

        String texto2 = "14068333-7";
        String rutEsperado2 = "14.068.333-7";
        String rutResultante2 = rut.formatear(texto2);
        assertEquals(rutEsperado2, rutResultante2);

        String texto3 = "14068.3337";
        String rutEsperado3 = "14.068.333-7";
        String rutResultante3 = rut.formatear(texto3);
        assertEquals(rutEsperado3, rutResultante3);

        String texto4 = "38638319";
        String rutEsperado4 = "3.863.831-9";
        String rutResultante4 = rut.formatear(texto4);
        assertEquals(rutEsperado4, rutResultante4);

        String texto5 = "386";
        String rutEsperado5 = "386";
        String rutResultante5 = rut.formatear(texto5);
        assertEquals(rutEsperado5, rutResultante5);

        String texto6 = "dfgfdgdfgdf";
        String rutEsperado6 = "dfgfdgdfgdf";
        String rutResultante6 = rut.formatear(texto6);
        assertEquals(rutEsperado6, rutResultante6);

    }

    /**
     * Formatear texto nulo debe devolver excepción.
     */
    @Test(expected = NullPointerException.class)
    public void testFormatear_null() {
        LOG.info("probando formatear con excepcion null");
        RUT rut = new RUT();
        rut.formatear(null);
    }

    /**
     * Prueba si distintos textos estan o no formateados como RUT.
     */
    @Test
    public void testEstaFormateado_combinaciones() {
        LOG.info("probando estaFormateado con distintos textos");

        RUT rut = new RUT();
        assertTrue(rut.estaFormateado("14.068.333-7"));
        assertFalse(rut.estaFormateado("3.333.154.7"));
        assertFalse(rut.estaFormateado("d.dddddd-d"));
        assertFalse(rut.estaFormateado("14-068.333-7"));
        assertFalse(rut.estaFormateado("14068333-7"));
        assertTrue(rut.estaFormateado("500.000-0"));
        assertFalse(rut.estaFormateado("500@000-0"));
    }

    /**
     * Prueba si texto nulo esta formateado. Debe arrojar null
     */
    @Test(expected = NullPointerException.class)
    public void testEstaFormateado_null() {
        LOG.info("probando estaFormateado con distintos textos");

        RUT rut = new RUT();
        rut.estaFormateado(null);
    }

    /**
     * Prueba extraer entero de distintas combinaciones de textos formateados como rut.
     */
    @Test
    public void testExtraerEntero_combinaciones() {
        LOG.info("probando extraerEntero con distintas combinaciones en texto de entrada");

        try {
            assertEquals(15222325, new RUT().extraerEntero("15.222.325-0"));
            assertEquals(55222325, new RUT().extraerEntero("55.222.325-0"));
            assertEquals(222325, new RUT().extraerEntero("222.325-0"));
        } catch (RutException ex) {
            // nada
        }
    }

    /**
     * Prueba extraer entero de un texto no nulo ni formateado como rut.
     *
     * @throws RutException
     */
    @Test(expected = RutException.class)
    public void testExtraerEntero_noformateado() throws RutException {
        LOG.info("probando extraerEntero con texto no formateado como rut, debe mandar excepcion ilegal");
        new RUT().extraerEntero("140683337");
    }

    /**
     * Prueba extraer entero de un texto nulo. Debe lanzar excepción
     */
    @Test(expected = NullPointerException.class)
    public void testExtraerEntero_null() {
        LOG.info("probando extraerEntero con texto nulo");
        try {
            new RUT().extraerEntero(null);
        } catch (RutException ex) {
            //nada
        }
    }

    /**
     * Prueba extraer verificador de diferentes textos formateados como rut.
     */
    @Test
    public void testExtraerVerificador_combinaciones() {
        LOG.info("prueba extraerVerificador con textos formateados como rut");
        try {
            assertEquals("0", new RUT().extraerVerificador("150.222.325-0"));
            assertEquals("K", new RUT().extraerVerificador("222.325-K"));
            assertEquals("3", new RUT().extraerVerificador("5.222.325-3"));
            assertEquals("0", new RUT().extraerVerificador("15.222.325-0"));
        } catch (RutException ex) {
            //nada
        }
    }

    /**
     * Prueba extraer verificador de texto no formateado como rut. Debe lanzar excepción ilegal.
     */
    public void testExtraerVerificador_ilegal() {
        LOG.info("prueba extraerVerificador con texto no formateado como rut");
        try {
            new RUT().extraerVerificador("15222.325-0");
        } catch (RutException ex) {
            assertEquals("Texto no formateado como RUT (Ej.:22.222.222-2)", ex.getMessage());
        }
    }

    /**
     * Prueba extraer verificador de texto nulo. Debe lanzar nullpointerexception
     */
    @Test(expected = NullPointerException.class)
    public void testExtraerVerificador_null() {
        LOG.info("prueba extraerVerificador con texto nulo");
        try {
            new RUT().extraerVerificador(null);
        } catch (RutException ex) {
            //nada
        }
    }

    /**
     * Comprueba cálculo de dígito verificador comparando datos de ruts preverificados manualmente con los calculados.
     */
    @Test
    public void testCalcularVerificador_enteroOK() {
        LOG.info("probando calcularVerificador con rut valido");
        RUT rut = new RUT();
        int enteroRut1 = 14068333;
        String verificadorEsperado1 = "7";
        String verificadorResultante1 = rut.calcularVerificador(enteroRut1);
        assertEquals(verificadorEsperado1, verificadorResultante1);
        int enteroRut2 = 16599735;
        String verificadorEsperado2 = "2";
        String verificadorResultante2 = rut.calcularVerificador(enteroRut2);
        assertEquals(verificadorEsperado2, verificadorResultante2);
        int enteroRut3 = 3863831;
        String verificadorEsperado3 = "9";
        String verificadorResultante3 = rut.calcularVerificador(enteroRut3);
        assertEquals(verificadorEsperado3, verificadorResultante3);
    }

    /**
     * Probando calculo digito verificador con entero negativo.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularVerificador_enteroNegativo() {
        LOG.info("probando calcularVerificador con entero negativo");
        RUT rut = new RUT();
        rut.calcularVerificador(-999);
    }

    /**
     * Probando calculo digito verificador con entero menor al aceptado.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularVerificador_enteroMenorAceptado() {
        LOG.info("probando calcularVerificador con entero menor al aceptado");
        RUT rut = new RUT();
        rut.calcularVerificador(10);
    }

    /**
     * Probando calculo digito verificador con entero mayor al aceptado.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCalcularVerificador_enteroMayorAceptado() {
        LOG.info("probando calcularVerificador con entero mayor al aceptado");
        RUT rut = new RUT();
        rut.calcularVerificador(1000000000);
    }

    /**
     * Valida distintos textos formateados como RUT de acuerdo a su verificador
     */
    @Test
    public void testValidar_combinaciones() {
        LOG.info("valida distintos textos formateados como RUT de acuerdo a su verificador");
        try {
            assertTrue(new RUT().validar("14.068.333-7"));
            assertFalse(new RUT().validar("14.068.333-9"));
            assertFalse(new RUT().validar("14.068.334-7"));
            assertTrue(new RUT().validar("14.360.009-2"));
            assertFalse(new RUT().validar("14.360.009-1"));
            assertTrue(new RUT().validar("77.394.420-2"));
            assertFalse(new RUT().validar("77.394.420-3"));
            assertFalse(new RUT().validar("77.394.421-2"));
            assertTrue(new RUT().validar("4.278.090-1"));
            assertFalse(new RUT().validar("4.278.091-1"));
            assertFalse(new RUT().validar("4.278.090-0"));
        } catch (RutException ex) {
            //nada
        }
    }

    /**
     * Valida digito verificador corresponda al texto ingresado como RUT.
     */
    @Test
    public void testValidar_digitoVerificador_Exepcion() {
        LOG.info("valida distintos textos formateados como RUT de acuerdo a su verificador");
        try {
            assertTrue(new RUT().validar("14.068.333-A"));
        } catch (RutException ex) {
            assertEquals("Texto no formateado como RUT (Ej.:22.222.222-2)", ex.getMessage());
        }
    }

    /**
     * Valida que el texto corresponda a formato tipo un RUT.
     */
    @Test
    public void testValidar_digitoVerificador_Exepcion2() {
        LOG.info("valida distintos textos formateados como RUT de acuerdo a su verificador");
        try {
            assertTrue(new RUT().validar("14068333-7"));
        } catch (RutException ex) {
            assertEquals("Texto no formateado como RUT (Ej.:22.222.222-2)", ex.getMessage());
        }
    }

    /**
     * Prueba validar texto nulo. Debe lanzar nullpointerexception
     */
    @Test(expected = NullPointerException.class)
    public void testValidar_null() {
        LOG.info("prueba validar con texto nulo");
        try {
            new RUT().validar(null);
        } catch (RutException ex) {
            //nada
        }
    }

    /**
     * Prueba formatear un texto como rut
     */
    public void test_formatearRut() {
        LOG.info("probar formatear un texto como rut");

        String rut1 = "165997352";
        String esperado1 = "16.599.735-2";
        assertEquals(esperado1, RUT.formatearRut(rut1));

        String rut2 = "7.79030.62";
        String esperado2 = "7.790.306-2";
        assertEquals(esperado2, RUT.formatearRut(rut2));

        String rut3 = "78723456k";
        String esperado3 = "78.723.456-K";
        assertEquals(esperado3, RUT.formatearRut(rut3));

        String rut4 = null;
        String esperado4 = "";
        assertEquals(esperado4, RUT.formatearRut(rut4));

        String rut5 = "";
        String esperado5 = "";
        assertEquals(esperado5, RUT.formatearRut(rut5));
    }

}
