/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author justcoding
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Leitor {

    /**
     * @param args the command line arguments
     */
    
    
    static int tamanhoPop = 10;

    public static int getTamanhoPop() {
        return tamanhoPop;
    }

    public static void setTamanhoPop(int tamanhoPop) {
        Leitor.tamanhoPop = tamanhoPop;
    }
    static int quantidadeDesafios = 50;
    static int availableStones = 0;

    static Desafio[][] popA = new Desafio[tamanhoPop][quantidadeDesafios];
    static int tempoPopA[] = new int[tamanhoPop];
    static int pontosPopA[] = new int[tamanhoPop];
    static int tamanhoTorre[] = new int[tamanhoPop];
    static int maxValueA = 0;
    static int melhorzinhoA = 0;

    static Desafio[][] popC = new Desafio[tamanhoPop][quantidadeDesafios];
    static int tempoPopC[] = new int[tamanhoPop];
    static int pontosPopC[] = new int[tamanhoPop];
    static int maxValueC = 0;
    static int melhorzinhoC = 0;

    static Desafio[][] popE = new Desafio[tamanhoPop][quantidadeDesafios];
    static int tempoPopE[] = new int[tamanhoPop];
    static int pontosPopE[] = new int[tamanhoPop];
    static int maxValueE = 0;
    static int melhorzinhoE = 0;

    static Desafio[] todosDesafios = new Desafio[quantidadeDesafios];
    static int numDesafios = 0;

    static int maxValueGeral = 0;
    static int melhorzao = 0;

    public static String tudo() {
        Date date = new Date();
        String dateS = new SimpleDateFormat("dd.MM.yyyy_HH'h'mm'm'").format(date);
        System.out.println(dateS);
        String arqNome = "Resultado_"+dateS+".txt";
        criarResultadoArquivo(arqNome);
        FileWriter f = abreResultadoArquivo(arqNome);
        criaPopulacao(f);
        escreverResultadoArquivo(f);
        fechaResultadoArquivo(f);
        
        return arqNome;
    }

    public static void criarResultadoArquivo(String nome) {
        try {
            File F = new File(nome);
            if (!F.exists()) {
                F.createNewFile();
                System.out.println("Criou arquivo");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileWriter abreResultadoArquivo(String nome) {
        //FileWriter FW = new FileWriter("Resultado.txt",true);
        //FW.append("teste");
        FileWriter FW = null;
        try {
            FW = new FileWriter(nome);
            return FW;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FW;
    }

    public static void fechaResultadoArquivo(FileWriter f) {
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void escreverResultadoArquivo(FileWriter FW) {
        try {

            printGeral(FW);
            FW.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void printGeral(FileWriter F) throws IOException {
       
        F.write("\n\n**** CAMPEAO Geral " + melhorzao + " PONTOS " + maxValueGeral + "\n");
        F.write("****  Tamanho torre: " + tamanhoTorre[melhorzao] + "\n");
        F.write("A ============= " + tempoPopA[melhorzao] + " s" + "\n");
        for (int d = 0; d < popA[melhorzao].length; d++) {
            if (!"0a".equals(popA[melhorzao][d].getNum())) {
                popA[melhorzao][d].imprimirArquivo(F);
            }
        }
        F.write("C ============= " + tempoPopC[melhorzao] + " s" + "\n");
        for (int d = 0; d < popC[melhorzao].length; d++) {
            if (!"0a".equals(popC[melhorzao][d].getNum())) {
                popC[melhorzao][d].imprimirArquivo(F);
            }
        }
        F.write("E ============= " + tempoPopE[melhorzao] + " s" + "\n");
        for (int d = 0; d < popE[melhorzao].length; d++) {
            if (!"0a".equals(popE[melhorzao][d].getNum())) {
                popE[melhorzao][d].imprimirArquivo(F);
            }
        }
        F.write("===================");
    }

    public static void printMelhor(Desafio pop, int m, int mV, int tempoPop[], int pontosPop[]) {
        System.out.println("Valores da população: " + Arrays.toString(pontosPop));
        System.out.println("**** CAMPEAO " + tempoPop[m] + "s =" + m + " PONTOS " + mV);
        //for (int d = 0; d < pop[m].length; d++) {
        //    if (!"0e".equals(pop[m][d].getNum())) {
        //        pop[m][d].imprimir();
        //    }
        //}
    }

    public static void criaPopulacao(FileWriter f) {

        try {
            
            /* AUGUSTO MUDAR AQUI:
            FileReader reader = new FileReader("pontosNovo.txt");
            Para: InputStreamReader reader=new InputStreamReader(Leitor.class.getResourceAsStream("/pontosNovo.txt"));
            Criar um diretorio chamado src/main/resources e colocar o arquivo pontosNovo.txt la dentro.
            Com isso, ao gerar o JAR o arquivo deve ser copiado automaticamente para dentro do JAR e lido quando o programa for execudado.*/
            InputStreamReader reader=new InputStreamReader(Leitor.class.getResourceAsStream("/pontosNovo.txt"));
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String[] d = line.split(",");
                String num = d[0];
                char t = d[1].charAt(0);
                String n = d[2];
                float tA = Float.parseFloat(d[3]);
                float tC = Float.parseFloat(d[4]);
                int p = Integer.parseInt(d[5]);
                int m = Integer.parseInt(d[6]);
                int aS = Integer.parseInt(d[7]);
                String de = d[8];

                System.out.println("\t**Criando Desafios...");
                //System.out.println("*DESAFIO: " + num + "," + t + "," + n + "," + tA + "," + tC + "," + p + "," + aS + "," + de + ".");
                todosDesafios[numDesafios] = new Desafio(num, t, n, tA, tC, p, m, aS, de);
                todosDesafios[numDesafios].imprimir();
                numDesafios++;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("* Criando População...\n");
        if (numDesafios > 0) {

            for (int i = 0; i < tamanhoPop; i++) {
                tempoPopA[i] = 0;
                pontosPopA[i] = 0;
                availableStones = 0;
                tamanhoTorre[i] = 0;
                for (int j = 0; j < quantidadeDesafios; j++) {
                    //System.out.println("**** j = " + j);
                    char t = ' ';
                    int e = 0;
                    int pode = 0;
                    while (t != 'a' || pode != 1) {

                        Random escolhido = new Random();
                        e = escolhido.nextInt(numDesafios);

                        t = todosDesafios[e].getTipo();
                        //System.out.println(" Tipo = " + t);
                        pode = 0;
                        //System.out.println("*** " + contaOcorrencias(popA[i], todosDesafios[e], j));
                        if (todosDesafios[e].getMaxVezes() == 0 || todosDesafios[e].getMaxVezes() > contaOcorrencias(popA[i], todosDesafios[e], j)) {
                            if (todosDesafios[e].getNome().equals("Placing") || todosDesafios[e].getNome().equals("Skyscraper")) {
                                if (availableStones > 0) {
                                    if (tamanhoTorre[i] < 8 || todosDesafios[e].getNome().equals("Placing")) {
                                        pode = 1;
                                    }
                                }

                            } else {
                                pode = 1;
                            }

                            //System.out.println("Pode = 1 ::"+todosDesafios[e].getMaxVezes()+" comparado com "+contaOcorrencias(popA[i], todosDesafios[e], j));
                        }
                    }

                    float testeTempo = tempoPopA[i] + todosDesafios[e].getTempoA(tamanhoTorre[i]);
                    if (testeTempo <= 30) {
                        popA[i][j] = todosDesafios[e];
                        tempoPopA[i] += todosDesafios[e].getTempoA(tamanhoTorre[i]);
                        //popA[i][j].imprimir();
                        pontosPopA[i] += todosDesafios[e].calcPontos();
                        if (todosDesafios[e].getNome().equals("Skyscraper")) {
                            tamanhoTorre[i]++;
                            pontosPopA[i] += 2 * tamanhoTorre[i];
                        }
                        availableStones += todosDesafios[e].getAddStones();
                        // System.out.println("**** POP A stones " + availableStones);
                        if (pontosPopA[i] > maxValueA) {
                            maxValueA = pontosPopA[i];
                            melhorzinhoA = i;
                        }
                    } else {
                        popA[i][j] = todosDesafios[0];
                        tempoPopA[i] += todosDesafios[0].getTempoA(tamanhoTorre[i]);
                        //popA[i][j].imprimir();
                        pontosPopA[i] += todosDesafios[0].calcPontos();

                    }
                }

                //System.out.println("* Individuo " + i + " = Tempo: " + tempoPopA[i] + ", Pontos: " + pontosPopA[i]);
                //}
                //for (int i = 0; i < tamanhoPop; i++) {
                //System.out.println("**** i = " + i);
                tempoPopC[i] = 0;
                pontosPopC[i] = 0;
                for (int j = 0; j < quantidadeDesafios; j++) {
                    //System.out.println("**** j = " + j);
                    char t = ' ';
                    int e = 0;
                    int pode = 0;
                    while (t != 'c' || pode != 1) {

                        Random escolhido = new Random();
                        e = escolhido.nextInt(numDesafios);

                        t = todosDesafios[e].getTipo();
                        //System.out.println(" Tipo = " + t);
                        pode = 0;
                        //System.out.println("*** " + contaOcorrencias(popA[i], todosDesafios[e], j));
                        if (todosDesafios[e].getMaxVezes() == 0 || todosDesafios[e].getMaxVezes() > contaOcorrencias(popC[i], todosDesafios[e], j)) {
                            if (todosDesafios[e].getNome().equals("Placing") || todosDesafios[e].getNome().equals("Skyscraper")) {
                                if (availableStones > 0) {
                                    if (tamanhoTorre[i] < 8 || todosDesafios[e].getNome().equals("Placing")) {
                                        pode = 1;
                                    }
                                }

                            } else {
                                pode = 1;
                            }
                            //System.out.println("Pode = 1 ::"+todosDesafios[e].getMaxVezes()+" comparado com "+contaOcorrencias(popA[i], todosDesafios[e], j));
                        }
                    }

                    float testeTempo = tempoPopC[i] + todosDesafios[e].getTempoC(tamanhoTorre[i]);
                    if (testeTempo <= 120) {
                        popC[i][j] = todosDesafios[e];
                        tempoPopC[i] += todosDesafios[e].getTempoC(tamanhoTorre[i]);
                        //popC[i][j].imprimir();
                        pontosPopC[i] += todosDesafios[e].calcPontos();
                        if (todosDesafios[e].getNome().equals("Skyscraper")) {
                            tamanhoTorre[i]++;
                            pontosPopC[i] += 2 * tamanhoTorre[i];
                        }
                        availableStones += todosDesafios[e].getAddStones();
                        //System.out.println("**** POP C stones " + availableStones);

                        if (pontosPopC[i] > maxValueC) {
                            maxValueC = pontosPopC[i];
                            melhorzinhoC = i;
                        }
                    } else {
                        popC[i][j] = todosDesafios[0];
                        tempoPopC[i] += todosDesafios[0].getTempoC(tamanhoTorre[i]);
                        //popC[i][j].imprimir();
                        pontosPopC[i] += todosDesafios[0].calcPontos();

                    }
                }

                //System.out.println("* Individuo " + i + " = Tempo: " + tempoPopC[i] + ", Pontos: " + pontosPopC[i]);
                //}
                //for (int i = 0; i < tamanhoPop; i++) {
                //System.out.println("**** i = " + i);
                tempoPopE[i] = 0;
                pontosPopE[i] = 0;
                for (int j = 0; j < quantidadeDesafios; j++) {
                    //System.out.println("**** j = " + j);
                    char t = ' ';
                    int e = 0;
                    int pode = 0;
                    while (t == 'a' || pode != 1) {

                        Random escolhido = new Random();
                        e = escolhido.nextInt(numDesafios);

                        t = todosDesafios[e].getTipo();
                        //System.out.println(" Tipo = " + t);
                        pode = 0;
                        //System.out.println("*** " + contaOcorrencias(popA[i], todosDesafios[e], j));
                        if (todosDesafios[e].getMaxVezes() == 0 || todosDesafios[e].getMaxVezes() > contaOcorrencias(popE[i], todosDesafios[e], j)) {
                            if (todosDesafios[e].getNome().equals("Placing") || todosDesafios[e].getNome().equals("Skyscraper")) {
                                if (availableStones > 0) {
                                    if (tamanhoTorre[i] < 8 || todosDesafios[e].getNome().equals("Placing")) {
                                        pode = 1;
                                    }
                                }

                            } else {
                                pode = 1;
                            }
                            //System.out.println("Pode = 1 ::"+todosDesafios[e].getMaxVezes()+" comparado com "+contaOcorrencias(popA[i], todosDesafios[e], j));
                        }
                    }

                    float testeTempo = tempoPopE[i] + todosDesafios[e].getTempoC(tamanhoTorre[i]);
                    if (testeTempo <= 30) {
                        popE[i][j] = todosDesafios[e];
                        tempoPopE[i] += todosDesafios[e].getTempoC(tamanhoTorre[i]);
                        //popE[i][j].imprimir();
                        pontosPopE[i] += todosDesafios[e].calcPontos();
                        if (todosDesafios[e].getNome().equals("Skyscraper")) {
                            tamanhoTorre[i]++;
                            pontosPopE[i] += 2 * tamanhoTorre[i];
                        }
                        if (todosDesafios[e].getNome().equals("Capping")) {

                            pontosPopE[i] += tamanhoTorre[i];
                        }
                        availableStones += todosDesafios[e].getAddStones();
                        //System.out.println("**** POP E stones " + availableStones);

                        if (pontosPopE[i] > maxValueE) {
                            maxValueE = pontosPopE[i];
                            melhorzinhoE = i;
                        }
                    } else {
                        popE[i][j] = todosDesafios[0];
                        tempoPopE[i] += todosDesafios[0].getTempoC(tamanhoTorre[i]);
                        //popE[i][j].imprimir();
                        pontosPopE[i] += todosDesafios[0].calcPontos();

                    }
                }
                int pT = pontosPopE[i] + pontosPopA[i] + pontosPopC[i];
                if (pT >= maxValueGeral) {
                    maxValueGeral = pT;
                    melhorzao = i;
                    try {
                        printGeral(f);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

                //System.out.println("* Individuo " + i + " = Tempo: " + tempoPopE[i] + ", Pontos: " + pontosPopE[i]);
            }

        }

    }

    public static int contaOcorrencias(Desafio[] des, Desafio d, int n) {
        int c = 0;
        for (int i = 0; i < n; i++) {
            //System.out.println("** Contando i = " + i + "n =" + n);
            //System.out.println("** d.getNum() = " + d.getNum());
            //System.out.println("** des[i].getNum() = " + des[i].getNum());

            if (d.getNum().compareTo(des[i].getNum()) == 0) {
                c++;
            }
        }

        //System.out.println("** contou = " + c);
        return c;

    }

}
