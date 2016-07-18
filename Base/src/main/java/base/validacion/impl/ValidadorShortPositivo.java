package base.validacion.impl;

import base.validacion.ResultadoMetodo;
import base.validacion.Validador;

/**
 *
 * @author Omar Paché
 */
public class ValidadorShortPositivo implements Validador {

    private ValidadorShortPositivo() {
    }

    public static ResultadoMetodo aplicar(short numero) {
        return new ValidadorShortPositivo().validar(numero);
    }

    @Override
    public ResultadoMetodo validar(Object object) {
        Short numero = (short) object;
        if (numero <= 0) {
            return ResultadoMetodoImpl.setError("Número menor a mínimo permitido");
        } else {
            return ResultadoMetodoImpl.setSinError();
        }
    }

}
