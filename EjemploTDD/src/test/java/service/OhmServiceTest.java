package service;

import org.junit.Assert;
import org.junit.Test;

public class OhmServiceTest {

    private final OhmService ohmService = new OhmService();

    @Test
    public void testCalcularVoltaje() {
        String resultado = ohmService.calcularParametro(null, 10.0, 2.0);
        Assert.assertEquals("20.00", resultado);
    }

    @Test
    public void testCalcularCorriente() {
        String resultado = ohmService.calcularParametro(20.0, 10.0, null);
        Assert.assertEquals("2.00", resultado);
    }

    @Test
    public void testCalcularResistencia() {
        String resultado = ohmService.calcularParametro(20.0, null, 2.0);
        Assert.assertEquals("10.00", resultado);
    }

    // Edge case: cero en corriente al calcular resistencia
    @Test
    public void testCorrienteCero() {
        String resultado = ohmService.calcularParametro(10.0, null, 0.0);
        Assert.assertEquals("Invalid values", resultado);
    }

    // Edge case: cero en resistencia al calcular corriente
    @Test
    public void testResistenciaCero() {
        String resultado = ohmService.calcularParametro(10.0, 0.0, null);
        Assert.assertEquals("Invalid values", resultado);
    }

    // Rounding behavior: valores con más de 2 decimales (multiplicación)
    @Test
    public void testRoundingUpMultiplication() {
        // 2.345 * 3.333 = 7.81685 -> 7.82
        String resultado = ohmService.calcularParametro(null, 2.345, 3.333);
        Assert.assertEquals("7.82", resultado);
    }

    // Rounding behavior: valores con más de 2 decimales (división para resistencia)
    @Test
    public void testRoundingUpDivisionResistencia() {
        // 7.7777 / 2.0 = 3.88885 -> 3.89
        String resultado = ohmService.calcularParametro(7.7777, null, 2.0);
        Assert.assertEquals("3.89", resultado);
    }

    // Rounding behavior: valores con más de 2 decimales (división para corriente)
    @Test
    public void testRoundingUpDivisionCorriente() {
        // 5.0 / 3.0 = 1.6666667 -> 1.67
        String resultado = ohmService.calcularParametro(5.0, 3.0, null);
        Assert.assertEquals("1.67", resultado);
    }

    // Negative values handling (multiplicación)
    @Test
    public void testNegativeValueMultiplication() {
        String resultado = ohmService.calcularParametro(null, -5.0, 2.0);
        Assert.assertEquals("-10.00", resultado);
    }

    // Negative values handling (división para resistencia)
    @Test
    public void testNegativeValueDivisionResistencia() {
        String resultado = ohmService.calcularParametro(-10.0, null, 2.0);
        Assert.assertEquals("-5.00", resultado);
    }

    // Validación de combinaciones inválidas
    @Test
    public void testParametrosInvalidos() {
        Assert.assertEquals("Invalid values", ohmService.calcularParametro(null, null, null));
        Assert.assertEquals("Invalid values", ohmService.calcularParametro(10.0, null, null));
        Assert.assertEquals("Invalid values", ohmService.calcularParametro(null, 10.0, null));
        Assert.assertEquals("Invalid values", ohmService.calcularParametro(null, null, 2.0));
        Assert.assertEquals("Invalid values", ohmService.calcularParametro(10.0, 5.0, 2.0));
    }
}
