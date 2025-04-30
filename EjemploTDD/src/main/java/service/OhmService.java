package service;

public class OhmService {
    private static final String INVALID_VALUES_MESSAGE = "Invalid values";
    private static final double ROUNDING_FACTOR = 100.0;

    public String calcularParametro(Double voltaje, Double resistencia, Double corriente) {
        int count = 0;
        if (voltaje != null) count++;
        if (resistencia != null) count++;
        if (corriente != null) count++;

        if (count != 2) {
            return INVALID_VALUES_MESSAGE;
        }

        // Falta voltaje
        if (voltaje == null) {
            return formatResult(corriente * resistencia);
        }

        // Falta resistencia
        if (resistencia == null) {
            if (corriente == 0) {
                return INVALID_VALUES_MESSAGE;
            }
            return formatResult(voltaje / corriente);
        }

        // Falta corriente
        // En este punto, corriente necesariamente es null
        if (resistencia == 0) {
            return INVALID_VALUES_MESSAGE;
        }
        return formatResult(voltaje / resistencia);
    }

    private String formatResult(double result) {
        return String.format("%.2f", Math.round(result * ROUNDING_FACTOR) / ROUNDING_FACTOR);
    }
}
