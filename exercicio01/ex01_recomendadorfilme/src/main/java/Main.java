import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pergunta 1: Que ambientação você prefere?");
        System.out.println("1. Sci-fi");
        System.out.println("2. Medieval");
        int resposta1 = scanner.nextInt();

        if (resposta1 != 1 && resposta1 != 2) {
            System.out.println("Resposta inválida. O programa será encerrado.");
            return;
        }

        System.out.println("Pergunta 2: Que gênero você prefere?");
        System.out.println("1. Comédia");
        System.out.println("2. Drama");
        int resposta2 = scanner.nextInt();

        if (resposta2 != 1 && resposta2 != 2) {
            System.out.println("Resposta inválida. O programa será encerrado.");
            return;
        }

        String recomendacao = "";

        if (resposta1 == 1 && resposta2 == 1) {
            recomendacao = "Homens de Preto";
        } else if (resposta1 == 1 && resposta2 == 2) {
            recomendacao = "Arrival";
        } else if (resposta1 == 2 && resposta2 == 1) {
            recomendacao = "Shrek";
        } else if (resposta1 == 2 && resposta2 == 2) {
            recomendacao = "Gladiador";
        }

        System.out.println("Recomendação: " + recomendacao);
    }
}
