package service;

public record ServicioAImpl() implements ServicioA {
    @Override
    public int sumar(int a, int b) {
        return a+b;
    }

}