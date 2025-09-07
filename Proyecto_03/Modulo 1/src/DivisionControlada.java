import java.util.Scanner;

public class DivisionControlada {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un número para dividir 100 entre él: ");
        int numero = sc.nextInt();

        try {
            int resultado = 100 / numero;
            System.out.println("Resultado: 100 / " + numero + " = " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Error: No se puede dividir entre cero." + e.getMessage() + e.hashCode());
        }

        sc.close();
    }
}

