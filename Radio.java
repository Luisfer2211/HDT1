import java.util.Scanner;

public class Radio implements IRadio {
    private boolean on;
    private boolean am;
    private double currentStation;
    private double[] savedStations;

    public Radio() {
        this.on = false;
        this.am = true;
        this.currentStation = 530.0; // Frecuencia AM inicial
        this.savedStations = new double[12];
    }

    @Override
    public void saveStation(int buttonId, double station) {
        if (on) {
            if (buttonId >= 1 && buttonId <= 12) {
                savedStations[buttonId - 1] = currentStation;
                System.out.println("Emisora actual guardada en el botón " + buttonId + ".");
            } else {
                System.out.println("Número de botón inválido. Debe estar entre 1 y 12.");
            }
        } else {
            System.out.println("Enciende la radio antes de guardar una emisora.");
        }
    }
}
