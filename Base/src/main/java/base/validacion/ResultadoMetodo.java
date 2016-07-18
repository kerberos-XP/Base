package base.validacion;

/**
 *
 * @author Omar Paché
 */
public interface ResultadoMetodo {

    public boolean isError();

    public int getCodigo();

    public String getMensaje();
}
