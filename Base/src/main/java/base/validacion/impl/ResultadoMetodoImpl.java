package base.validacion.impl;

import base.validacion.ResultadoMetodo;

/**
 *
 * @author Omar Paché
 */
public class ResultadoMetodoImpl implements ResultadoMetodo {

    private boolean error;
    private int codigo = 0;
    private String mensaje = "";

    private ResultadoMetodoImpl() {
    }

    public static ResultadoMetodo setSinError() {
        ResultadoMetodoImpl resultadoMetodoImpl = new ResultadoMetodoImpl();
        resultadoMetodoImpl.error = false;
        return resultadoMetodoImpl;
    }

    /**
     * Setea resultado exitoso y da la posibilidad de retornar un entero que codifique el tipo de resultado.
     *
     * @param codigo
     * @param mensaje
     * @return
     */
    public static ResultadoMetodo setSinError(int codigo, String mensaje) {
        ResultadoMetodoImpl resultadoMetodoImpl = new ResultadoMetodoImpl();
        resultadoMetodoImpl.codigo = codigo;
        resultadoMetodoImpl.mensaje = mensaje;
        return resultadoMetodoImpl;
    }

    /**
     *
     * @param mensaje
     * @return
     */
    public static ResultadoMetodo setError(String mensaje) {
        ResultadoMetodoImpl resultadoMetodoImpl = new ResultadoMetodoImpl();
        resultadoMetodoImpl.error = true;
        resultadoMetodoImpl.mensaje = mensaje;
        return resultadoMetodoImpl;
    }

    /**
     *
     * @param codigo
     * @param mensaje
     * @return
     */
    public static ResultadoMetodo setError(int codigo, String mensaje) {
        if (codigo == 0) {
            throw new IllegalArgumentException("Código error no puede ser 0");
        } else if (mensaje == null) {
            throw new IllegalArgumentException("Mensaje no puede ser nulo");
        } else {
            ResultadoMetodoImpl resultadoMetodoImpl = new ResultadoMetodoImpl();
            resultadoMetodoImpl.error = true;
            resultadoMetodoImpl.codigo = codigo;
            resultadoMetodoImpl.mensaje = mensaje;
            return resultadoMetodoImpl;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isError() {
        return error;
    }

    /**
     *
     * @return
     */
    @Override
    public int getCodigo() {
        return codigo;
    }

    /**
     *
     * @return
     */
    @Override
    public String getMensaje() {
        return mensaje;
    }

}
