package base.validacion.impl;

import base.validacion.ResultadoMetodo;
import base.validacion.Validador;

/**
 *
 * @author Omar Paché
 */
public class ValidadorStringLargoMax implements Validador {

    int largoMax;

    private ValidadorStringLargoMax(int largoMax) {
        this.largoMax = largoMax;
    }

    public static ResultadoMetodo aplicar(String texto, int largoMax) {
        return new ValidadorStringLargoMax(largoMax).validar(texto);
    }

    @Override
    public ResultadoMetodo validar(Object object) {
        String texto = (String) object;
        if (ValidadorStringVacio.aplicar(texto).isError()) {
            return ValidadorStringVacio.aplicar(texto);
        } else if (largoMax < texto.length()) {
            return ResultadoMetodoImpl.setError("Largo de texto excede al máximo permitido.");
        } else {
            return ResultadoMetodoImpl.setSinError();
        }
    }
}
