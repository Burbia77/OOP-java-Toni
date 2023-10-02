package es.netmind.simuladorcoches.modelos.coches;

import es.netmind.simuladorcoches.excepciones.CocheException;
import es.netmind.simuladorcoches.modelos.motores.Motor;

public abstract class Coche {

    public static int nRuedas = 4;
    private String marca;
    private String tipoCoche;
    protected int velocidad;
    protected int posicion = 0;
    protected boolean arrancado = false;
    protected Motor motor;

    public Coche(String marca, String tipoCoche, int velocidad) {
        this.marca = marca;
        this.tipoCoche = tipoCoche;
        this.velocidad = velocidad;
    }

    public boolean arrancar() {
        /* Necesita un motor con valor */
        motor.arrancar();
        this.arrancado = true;
        return true;
    }

    /*public double avanzar(int distancia) {
        if (this.arrancado) {
            double tiempo = (double) distancia / (double) this.velocidad;
            this.posicion += distancia;
            return tiempo;
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }*/

    public boolean parar() {
        motor.parar();
        this.arrancado = false;
        return true;
    }

    @Override
    public String toString() {
        return "Coche{" +
                "marca='" + marca + '\'' +
                ", tipoCoche='" + tipoCoche + '\'' +
                ", velocidad=" + velocidad +
                ", posicion=" + posicion +
                ", arrancado=" + arrancado +
                ", nRuedas=" + nRuedas +
                '}';
    }

    public String getMarca() {
        return marca;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public abstract void ralenti();

    public boolean validar() throws CocheException {
        if (marca != null && tipoCoche != null && velocidad > 0 && marca.length() > 3 && tipoCoche.length() > 2) {
            return true;
        } else {
            throw new CocheException("Coche no valido:"+ this.marca);
        }
    }
}