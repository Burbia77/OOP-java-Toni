package es.netmind.simuladorcoches;

import es.netmind.simuladorcoches.modelos.coches.*;

public class GestorCoches {

    static int DISTANCIA = 200;

    public static void main(String[] args) {
        System.out.println("SIMULADOR DE COCHES");

        try {
            DISTANCIA = new Integer(args[0]);
            System.out.println("Distania correcta: " + DISTANCIA);
        } catch (NumberFormatException ex) {
            System.out.println("Distancia incorecta, continuamos con el:" + DISTANCIA);
        } catch (Exception ex) {
            System.out.println("Ha habido un error");
            return ;
        }

        System.out.println("Cargando ....");
        ICoche c1 = new Deportivo("Porsche", "Deportivo", 120, false);
        c1.arrancar();
        ICoche c2 = new Familiar("Renault", "Familiar", 130, 5);
        c2.arrancar();
        ICoche c3 = new SUV("Seat", "SUV", 100, 1);
        c3.arrancar();

//        c1.nRuedas = 5;
//        c2.nRuedas = 3;
        Coche.nRuedas = 4;
        System.out.println(c1);
        System.out.println(c2);

        System.out.println("Simulando ....");
        double t1 = c1.avanzar(DISTANCIA);
        double t2 = c2.avanzar(DISTANCIA);
        double t3 = c3.avanzar(DISTANCIA);

        System.out.println("Calculando ....");
        ICoche masRapido = null;
        if (t1 < t2 && t1 < t3) masRapido = c1;
        else if (t2 < t1 && t2 < t3) masRapido = c2;
        else if (t3 < t1 && t3 < t2) masRapido = c3;

        c1.parar();
        c2.parar();
        c3.parar();

        System.out.println("El coche más rápido es:");
        System.out.println(masRapido);
    }
}
