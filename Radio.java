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

    @Override
    public boolean isAM() {
        return am;
    }

    @Override
    public boolean isON() {
        return on;
    }

    @Override
    public double selectStation(int buttonId) {
        if (on) {
            if (buttonId >= 1 && buttonId <= 12) {
                double selectedStation = savedStations[buttonId - 1];
                System.out.println("Seleccionada emisora del botón " + buttonId + ": " + selectedStation);
                return selectedStation;
            } else {
                System.out.println("Número de botón inválido. Debe estar entre 1 y 12.");
                return -1.0; 
            }
        } else {
            System.out.println("Enciende el radio antes de seleccionar una emisora.");
            return -1.0;
        }
    }

    @Override
    public void switchOnOff() {
        on = !on;
        if (!on) {
            System.out.println("Radio apagada.");
        } else {
            System.out.println("Radio encendida.");
        }
    }

    @Override
    public void switchAMFM() {
        if (on) {
            am = !am;
            currentStation = am ? 530.0 : 87.9;
            System.out.println("Cambiando a " + (am ? "AM" : "FM") + ".");
        } else {
            System.out.println("Enciende la radio antes de cambiar la frecuencia.");
        }
    }

    @Override
    public double nextStation() {
        if (on) {
            if (am) {
                currentStation += 10.0;
                if (currentStation > 1610.0) {
                    currentStation = 530.0;
                }
            } else {
                currentStation += 0.2;
                if (currentStation > 107.9) {
                    currentStation = 87.9;
                }
            }
            System.out.println("Avanzando a la siguiente emisora: " + currentStation);
            return currentStation;
        } else {
            System.out.println("Enciende la radio antes de cambiar la emisora.");
            return -1.0; 
        }
    }

    public static void main(String[] args) {
        Radio radio = new Radio();  
        Scanner scanner = new Scanner(System.in);
    
        int option;
        do {
            displayMenu();
            System.out.print("Seleccione una opción: ");
            String userInput = scanner.next();
    
            if (isNumeric(userInput)) {
                option = Integer.parseInt(userInput);
    
                switch (option) {
                    case 1:
                        radio.switchOnOff();
                        break;
                    case 2:
                        radio.switchAMFM();
                        break;
                    case 3:
                        radio.nextStation();
                        break;
                    case 4:
                        System.out.print("Ingrese el número de botón (1-12): ");
                        String buttonInput = scanner.next();
                        if (isNumeric(buttonInput)) {
                            int buttonId = Integer.parseInt(buttonInput);
                            radio.saveStation(buttonId, 0);  
                        } else {
                            System.out.println("Entrada no válida. Debe ser un número.");
                        }
                        break;
                    case 5:
                        System.out.print("Ingrese el número de botón (1-12) a seleccionar: ");
                        String selectedButtonInput = scanner.next();
                        if (isNumeric(selectedButtonInput)) {
                            int selectedButtonId = Integer.parseInt(selectedButtonInput);
                            radio.selectStation(selectedButtonId);
                        } else {
                            System.out.println("Entrada no válida. Debe ser un número.");
                        }
                        break;
                    case 6:
                        radio.switchOnOff();
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Debe ser un número.");
                option = 0; 
            }
    
        } while (option != 6);
    
        System.out.println("Programa finalizado.");
    }
    
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  
    }

    private static void displayMenu() {
        System.out.println("------ Menú ------");
        System.out.println("1. Prende la radio");
        System.out.println("2. Cambia de AM a FM a AM");
        System.out.println("3. Avanzar en el dial de las emisoras");
        System.out.println("4. Guardar esta emisora en uno de los 12 botones");
        System.out.println("5. Seleccionar la emisora puesta en un botón");
        System.out.println("6. Apagar el radio");
    }
}
