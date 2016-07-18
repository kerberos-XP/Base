package base.tipoDato;

import org.apache.log4j.Logger;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Omar Paché
 */
public class TextoTest {

    private static final Logger LOG = Logger.getLogger(TextoTest.class);

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public TextoTest() {
    }

    /**
     * Comprobar el cambio de las primeras letras de cada palabra por mayusculas (estilo titulo).
     */
    @Test
    public void test_comprobarTextoTradicional_aTitulo_ok() {
        LOG.info("test_comprobarTextoMayuscula_aTitulo_ok");
        String esperado = "Esto Es Una Prueba";
        Texto texto = new Texto("Esto es una prueba");
        String resultadoado = texto.aTitulo();
        assertEquals(esperado, resultadoado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra instanciada en mayuscula.
     */
    @Test
    public void test_comprobarTextoMayuscula_aTitulo_ok() {
        LOG.info("test_comprobarTextoMayuscula_aTitulo_ok");
        String esperado = "Ivan";
        Texto texto = new Texto("IVAN");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra instanciada en minuscula.
     */
    @Test
    public void test_comprobarTextoMinuscula_aTitulo_ok() {
        LOG.info("test_comprobarTextoMinuscula_aTitulo_ok");
        String esperado = "Ivan";
        Texto texto = new Texto("ivan");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra instanciada con minusculas y mayusculas.
     */
    @Test
    public void test_comprobarTextoMayusculaMinuscula_aTitulo_ok() {
        LOG.info("test_comprobarTextoMayusculaMinuscula_aTitulo_ok");
        String esperado = "Sorprendente";
        Texto texto = new Texto("sOrPreNdenTE");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra instanciada con puntuaciones (punto y coma).
     */
    @Test
    public void test_comprobarTextoConPuntuaciones_aTitulo_ok() {
        LOG.info("test_comprobarTextoConPuntuaciones_aTitulo_ok");
        String esperado = "Hola Mundo, Java.";
        Texto texto = new Texto("Hola mundo, java.");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra instanciada con puntuaciones y números.
     */
    @Test
    public void test_comprobarTextoConPuntuacionesNumeros_aTitulo_ok() {
        LOG.info("test_comprobarTextoConPuntuacionesNumeros_aTitulo_ok");
        String esperado = "Lautaro 886, Concepción, Chile.";
        Texto texto = new Texto("lautaro 886, concepción, chile.");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra vacia devuelve vacio.
     */
    @Test
    public void test_comprobarTexto_Vacio() {
        LOG.info("test_comprobarTexto_Vacio");
        String esperado = "";
        Texto texto = new Texto("");
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra nula lanza una exepcion nulo.
     */
    @Test
    public void test_comprobarTexto_Nulo() {
        LOG.info("test_comprobarTexto_Nulo");
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("Texto nulo.");
        String esperado = "Ivan";
        Texto texto = new Texto(null);
        String resultado = texto.aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar el cambio de la primera letra en una palabra nula lanza una exepcion nulo.
     */
//    @Test
//    public void test_comprobarTextoConcatenar_OK() {
//        LOG.info("test_comprobarTextoConcatenar_OK");
//        Texto esperado = new Texto("pedro fernandez");
//        Texto texto = new Texto("pedro");
//        Texto texto2 = new Texto("fernandez");
//        Texto resultado = texto.concatenar(texto2);
//        assertEquals(esperado, resultado);
//    }
    /**
     * Comprobar el cambio de la primera letra en una palabra nula lanza una exepcion nulo.
     */
    @Test
    public void test_comprobarTextoConcatenar_aTitulo_OK() {
        LOG.info("test_comprobarTextoConcatenar_OK");
        String esperado = ("Pedro Fernandez");
        Texto texto = new Texto("pedro");
        Texto texto2 = new Texto("fernandez");
        String resultado = texto.concatenar(texto2).aTitulo();
        assertEquals(esperado, resultado);
    }

    /**
     * Comprobar si un texto es numerico.
     */
    @Test
    public void test_comprobarTexto_esNumero_OK() {
        LOG.info("test_comprobarTextoConcatenar_OK");
        Texto textoNumerico = new Texto("123456");
        Texto texto1 = new Texto("asdfx");
        Texto texto2 = new Texto("ABFDk23l");
        boolean resultadoNumerico = textoNumerico.esNumero();
        boolean resultado1 = texto1.esNumero();
        boolean resultado2 = texto2.esNumero();
        assertTrue(resultadoNumerico);
        assertFalse(resultado1);
        assertFalse(resultado2);
    }
}
