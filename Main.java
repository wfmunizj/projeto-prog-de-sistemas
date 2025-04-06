import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] vetor = null;
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            menu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            while (vetor == null && opcao != 1 && opcao != 0) {
                System.out.println("O vetor ainda não foi inicializado, selecione a opção 1.");
                menu();
                System.out.print("Escolha uma opção: ");
                opcao = scanner.nextInt();
            }

            switch (opcao) {
                case 1:
                    vetor = inicializaVetor();
                    System.out.println("Vetor inicializado com números aleatórios.");
                    System.out.println();
                    break;
                case 2:
                    imprimirVetor(vetor);
                    break;
                case 3:
                    System.out.print("Digite o número a ser verificado: ");
                    int numero = scanner.nextInt();
                    int encontrado = verificarNumero(vetor, numero);
                    if (encontrado != -1) {
                        System.out.println("Número encontrado no vetor na posição: " + encontrado);
                    } else {
                        System.out.println("Número não encontrado no vetor.");

                    }

                    break;
                case 4:

                    int maiorNumero = buscarMaiorNumero(vetor);
                    System.out.println("Maior número no vetor: " + maiorNumero);

                    break;
                case 5:

                    int mediaPares = calcularMediaPares(vetor);
                    System.out.println("Média dos números pares: " + mediaPares);

                    break;
                case 6:
                    if (vetor == null) {
                        System.out.println("O vetor ainda não foi inicializado.");
                    } else {
                        double percentualImpares = calcularPercentualImpares(vetor);
                        System.out.println(percentualImpares);
                        // System.out.println("Percentual de números ímpares: " + percentualImpares +
                        // "%");
                    }
                    break;
                case 7:

                    if (vetor == null) {
                        System.out.println("O vetor ainda não foi inicializado.");
                    } else if (vetor.length <= 2) {
                        System.out.println("Não é possível realizar essa opção com o vetor atual.");
                    } else {
                        double mediaCentralizada = (calcularMediaCentralizada(vetor));
                        System.out.println("Média centralizada dos números: " + mediaCentralizada);
                    }
                    break;
                case 8:
                    System.out.print("Digite o valor a ser verificado: ");
                    int valor = scanner.nextInt();
                    boolean existeSoma = verificarSomaDoisNumeros(vetor, valor);
                    System.out.println(existeSoma ? "Existem dois números que somados resultam no valor informado."
                            : "Não existem dois números que somados resultam no valor informado.");
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void menu() {
        System.out.println("\nMenu:");
        System.out.println("1. Inicializar o vetor com números aleatórios");
        System.out.println("2. Imprimir o vetor");
        System.out.println("3. Verificar se um determinado número está contido no vetor");
        System.out.println("4. Buscar o maior número armazenado no vetor");
        System.out.println("5. Calcular a média dos números pares armazenados no vetor");
        System.out.println("6. Calcular o percentual dos números ímpares armazenados no vetor");
        System.out.println("7. Calcular a média centralizada dos números armazenados no vetor");
        System.out.println(
                "8. Verificar se dado um valor existe dois números em posições distintas que somados são iguais ao valor informado");
        System.out.println("0. Sair");
    }

    public static int[] inicializaVetor() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int tamVetor = scanner.nextInt();
        int maiorNum = scanner.nextInt();
        int[] vetor = new int[tamVetor];
        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(maiorNum + 1);
        }
        return vetor;
    }

    public static void imprimirVetor(int[] vetor) {
        System.out.print("Vetor: ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println();
    }

    public static int verificarNumero(int[] vetor, int numero) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] == numero) {
                return i;
            }
        }
        return -1;
    }

    public static int buscarMaiorNumero(int[] vetor) {
        int maiorNumero = vetor[0];

        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] > maiorNumero) {
                maiorNumero = vetor[i];
            }
        }
        return maiorNumero;
    }

    public static int calcularMediaPares(int[] vetor) {
        int contador = 0;
        int somatoria = 0;
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i] % 2 == 0) {
                contador++;
                somatoria += vetor[i];
            }

        }
        return somatoria / contador;
    }

    public static int calcularPercentualImpares(int[] vetor) {
        int contadorImpares = 0;
        int percentualImpares = 0;
        for (int i = 0; i < vetor.length; i++) {

            if (vetor[i] % 2 != 0) {
                contadorImpares++;
            }
            int tamanhoVetor = vetor.length;
            percentualImpares = (contadorImpares * 100) / tamanhoVetor;
        }
        return percentualImpares;
    }

    public static double calcularMediaCentralizada(int[] vetor) {

        int menorNumero = vetor[0];
        int maiorNumero = vetor[0];
        int soma;

        for (int i : vetor) {
            if (i < menorNumero)
                menorNumero = i;
            if (i > maiorNumero)
                maiorNumero = i;
        }

        soma = 0;
        boolean ignoraMenor = false;
        boolean ignoraMaior = false;

        for (int i : vetor) {
            if (i == menorNumero && !ignoraMenor) {
                ignoraMenor = true;
                continue;
            }
            if (i == maiorNumero && !ignoraMaior) {
                ignoraMaior = true;
                continue;
            }
            soma += i;
        }

        int qntElementos = vetor.length - 2;
        return (double) soma / qntElementos;

    }

    public static boolean verificarSomaDoisNumeros(int[] vetor, int valor) {
        for (int i = 0; i < vetor.length; i++) {
            for (int j = i + 1; j < vetor.length; j++) {
                if (vetor[i] + vetor[j] == valor) {
                    return true;
                }
            }
        }
        return false;
    }
}