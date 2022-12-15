import java.util.*;

public class BatalhaNaval {
    public static int numeroLinhas = 10;
    public static int numeroColunas = 10;
    public static int naviosJogador;
    public static int naviosComp;
    public static String[][] tabuleiro = new String[numeroLinhas][numeroColunas];
    public static int[][] tiroAgua = new int[numeroLinhas][numeroColunas];

    public static void main(String[] args){
        System.out.println("---------------------");
        System.out.println("JOGO DE BATALHA NAVAL");
        System.out.println("---------------------\n");
        criaTabuleiroJogador();
        criaTabuleiroComp();
        lancarNaviosJogador();
        lancarNaviosComp();

        //inicio
        do {
            Batalha();
        }while(BatalhaNaval.naviosJogador != 0 && BatalhaNaval.naviosComp != 0);

        // fim
        fim();
    }

    public static void criaTabuleiroJogador(){
        System.out.println("------------");
        System.out.println("Tabuleiro do");
        System.out.println("  JOGADOR   ");
        System.out.println("------------");
        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();

        for(int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = " ";
                if (j == 0)
                    System.out.print(i + tabuleiro[i][j]);
                else if (j == tabuleiro[i].length - 1)
                    System.out.print(tabuleiro[i][j] + i);
                else
                    System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void criaTabuleiroComp(){
        System.out.println("------------");
        System.out.println("Tabuleiro do");
        System.out.println(" COMPUTADOR ");
        System.out.println("------------");
        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();

        for(int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro[i].length; j++) {
                tabuleiro[i][j] = " ";
                if (j == 0)
                    System.out.print(i + tabuleiro[i][j]);
                else if (j == tabuleiro[i].length - 1)
                    System.out.print(tabuleiro[i][j] + i);
                else
                    System.out.print(tabuleiro[i][j]);
            }
            System.out.println();
        }

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void lancarNaviosJogador(){
        Scanner entrada = new Scanner(System.in);

        System.out.println("\nLance seus navios:");
        BatalhaNaval.naviosJogador = 10;
        for (int i = 1; i <= BatalhaNaval.naviosJogador; ) {
            System.out.print("Digita a LINHA do seu " + i + " navio: ");
            int x = entrada.nextInt();
            System.out.print("Digite a COLUNA do seu " + i + " navio: ");
            int y = entrada.nextInt();

            if((x >= 0 && x < numeroLinhas) && (y >= 0 && y < numeroColunas) && (tabuleiro[x][y] == " "))
            {
                tabuleiro[x][y] = "N";
                i++;
            }
            else if((x >= 0 && x < numeroLinhas) && (y >= 0 && y < numeroColunas) && tabuleiro[x][y] == "N")
                System.out.println("Você não pode colocar dois navios na mesma posição! Tente de novo: ");
            else if((x < 0 || x >= numeroLinhas) || (y < 0 || y >= numeroColunas))
                System.out.println("Você não pode colocar navios fora do tabuleiro! Use as coordenadas:  "
                        + numeroLinhas + " por " + numeroColunas + ". Tente de novo!");
        }
        imprimeTabuleiroJogador();
    }

    public static void lancarNaviosComp(){
        System.out.println("\nO computador está colocando seus navios...");
        BatalhaNaval.naviosComp = 10;
        for (int i = 1; i <= BatalhaNaval.naviosComp; ) {
            int x = (int)(Math.random() * 10);
            int y = (int)(Math.random() * 10);

            if((x >= 0 && x < numeroLinhas) && (y >= 0 && y < numeroColunas) && (tabuleiro[x][y] == " "))
            {
                tabuleiro[x][y] =   "C";
                System.out.println(i + " navio do computador lançado");
                i++;
            }
        }
        imprimeTabuleiroJogador();
    }

    public static void Batalha(){
        vezJogador();
        vezComp();

        imprimeTabuleiroJogador();

        System.out.println();
        System.out.println("Your ships: " + BatalhaNaval.naviosJogador + " | Computer ships: " + BatalhaNaval.naviosComp);
        System.out.println();
    }

    public static void vezJogador(){
        System.out.println("\nSua vez: ");
        int x = -1, y = -1;
        do {
            Scanner input = new Scanner(System.in);
            System.out.print("Digite a LINHA: ");
            x = input.nextInt();
            System.out.print("Digite a COLUNA: ");
            y = input.nextInt();

            if ((x >= 0 && x < numeroLinhas) && (y >= 0 && y < numeroColunas))
            {
                if (tabuleiro[x][y] == "C")
                {
                    System.out.println("Opa, você afundou um navio inimigo!");
                    tabuleiro[x][y] = "*";
                    --BatalhaNaval.naviosComp;
                }
                else if (tabuleiro[x][y] == "N") {
                    System.out.println("Você afundou seu próprio navio!");
                    tabuleiro[x][y] = "N";
                    --BatalhaNaval.naviosJogador;
                    ++BatalhaNaval.naviosComp;
                }
                else if (tabuleiro[x][y] == " ") {
                    System.out.println("Você errou!");
                    tabuleiro[x][y] = "-";
                }
            }
            else if ((x < 0 || x >= numeroLinhas) || (y < 0 || y >= numeroColunas))
                System.out.println("Você não pode atirar fora do tabuleiro. Tente de novo: ");
        }while((x < 0 || x >= numeroLinhas) || (y < 0 || y >= numeroColunas));
    }

    public static void vezComp(){
        System.out.println("\nÉ a vez do computador: ");

        int x = -1, y = -1;

        do {
            x = (int)(Math.random() * 10);
            y = (int)(Math.random() * 10);

            if ((x >= 0 && x < numeroLinhas) && (y >= 0 && y < numeroColunas))
            {
                if (tabuleiro[x][y] == "N")
                {
                    System.out.println("Seu navio foi afundado!");
                    tabuleiro[x][y] = "X";
                    --BatalhaNaval.naviosJogador;
                    ++BatalhaNaval.naviosComp;
                }
                else if (tabuleiro[x][y] == "C") {
                    System.out.println("O computador afundou um de seus próprios navios!");
                    tabuleiro[x][y] = "X";
                }
                else if (tabuleiro[x][y] == " ") {
                    System.out.println("O computador errou!");
                    tabuleiro[x][y] = "n";

                    if(tiroAgua[x][y] != 1)
                        tiroAgua[x][y] = 1;
                }
            }
        }while((x < 0 || x >= numeroLinhas) || (y < 0 || y >= numeroColunas));
    }

    public static void imprimeTabuleiroJogador(){
        System.out.println();

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();

        for(int x = 0; x < tabuleiro.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < tabuleiro[x].length; y++){
                System.out.print(tabuleiro[x][y]);
            }

            System.out.println("|" + x);
        }

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void imprimeTabuleiroComp(){
        System.out.println();

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();

        for(int x = 0; x < tabuleiro.length; x++) {
            System.out.print(x + "|");

            for (int y = 0; y < tabuleiro[x].length; y++){
                System.out.print(tabuleiro[x][y]);
            }

            System.out.println("|" + x);
        }

        System.out.print(" ");
        for(int i = 0; i < numeroColunas; i++)
            System.out.print(i);
        System.out.println();
    }

    public static void fim(){
        System.out.println("Jogador: " + BatalhaNaval.naviosJogador + "        Computador: " + BatalhaNaval.naviosComp);
        if(BatalhaNaval.naviosJogador > 0 && BatalhaNaval.naviosComp <= 0)
            System.out.println("VOCÊ VENCEU!");
        else
            System.out.println("O computador venceu! Melhor sorte na próxima...");
        System.out.println();

        imprimeTabuleiroJogador();
        imprimeTabuleiroComp();
    }
}