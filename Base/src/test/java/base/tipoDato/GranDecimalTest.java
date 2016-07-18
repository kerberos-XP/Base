package base.tipoDato;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Cristián Alarcón de la Maza
 */
public class GranDecimalTest {

    private static final Logger LOG = Logger.getLogger(GranDecimal.class);

    public GranDecimalTest() {
    }

    /**
     * Test of contarDigitos method, of class GranDecimalImpl.
     */
    @Test
    public void testContarDigitos() {
        LOG.info("contar digitos de grandecimal");
        GranDecimal granDecimal1 = new GranDecimal("0");
        GranDecimal granDecimal2 = new GranDecimal("00");
        GranDecimal granDecimal3 = new GranDecimal("202.3234200");
        GranDecimal granDecimal4 = new GranDecimal("0.0");
        assertEquals(1, granDecimal1.contarDigitos());
        assertEquals(1, granDecimal2.contarDigitos());
        assertEquals(8, granDecimal3.contarDigitos());
        assertEquals(1, granDecimal4.contarDigitos());
    }

    /**
     * Test of contarDecimales method, of class GranDecimalImpl.
     */
    @Test
    public void testContarDecimales() {
        LOG.info("contar digitos decimales de grandecimal");
        GranDecimal granDecimal1 = new GranDecimal("0");
        GranDecimal granDecimal2 = new GranDecimal("00");
        GranDecimal granDecimal3 = new GranDecimal("202.3234200");
        GranDecimal granDecimal4 = new GranDecimal("0.0");
        assertEquals(0, granDecimal1.contarDecimales());
        assertEquals(0, granDecimal2.contarDecimales());
        assertEquals(5, granDecimal3.contarDecimales());
        assertEquals(0, granDecimal4.contarDecimales());
    }

    /**
     * Test of validarDigitos method, of class GranDecimalImpl.
     */
    @Test
    public void testValidarDigitos() {
        LOG.info("probar validarDigitos de grandecimal, con uno largo y uno corto");
        GranDecimal granDecimal = new GranDecimal("0");
        assertEquals(false, granDecimal.validarDigitos(true).isError());
        assertEquals(false, granDecimal.validarDigitos(false).isError());
    }

    /**
     * Probar el redondeo de un gran decimal a un valor.
     */
    @Test
    public void test_probarRedondeoA() {
        LOG.info("Probando el redondeo de un gran decimal");

        GranDecimal valor = new GranDecimal("14537.55");
        LOG.info("Valor normal:" + valor);
        valor = valor.redondearA(10);
        LOG.info("Valor redondeado:" + valor);
        assertEquals(new GranDecimal("14540"), valor);
    }

    /**
     * Comprobar que un texto numérico se represente como porcentaje.
     */
    @Test
    public void test_comprobarTextoAporcentaje_ok() {
        LOG.info("Comprobando que un texto numérico se represente como porcentaje");

        assertEquals("6.00%", new GranDecimal("0.06").toPorcentajeString());
    }
}
