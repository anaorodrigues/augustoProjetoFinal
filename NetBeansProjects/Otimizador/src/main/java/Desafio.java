
import java.io.FileWriter;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author justcoding
 */
public class Desafio {

    static int blocosDisponiveis = 56 / 2;


    
    
    private String num;
    private char tipo;
    private String nome;
    private float tempoA;
    private float tempoC;
    private int pontos;
    private int addStones;

    public int getAddStones() {
        return addStones;
    }

    public void setAddStones(int addStones) {
        this.addStones = addStones;
    }
    private String descricao;
    private int maxVezes;

    public int getMaxVezes() {
        return maxVezes;
    }

    public void setMaxVezes(int maxVezes) {
        this.maxVezes = maxVezes;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public static void setBlocosDisponiveis(int blocosDisponiveis) {
        Desafio.blocosDisponiveis = blocosDisponiveis;
    }

    public void setTempoA(float tempoA) {
        this.tempoA = tempoA;
    }

    public void setTempoC(float tempoC) {
        this.tempoC = tempoC;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public static int getBlocosDisponiveis() {
        return blocosDisponiveis;
    }

    public float getTempoA(int tamTorre) {
        if (this.nome.equals("Skyscraper")){
            return tempoA+tamTorre;
        }else
            return tempoA;
    }

    public float getTempoC(int tamTorre) {
        if (this.nome.equals("Skyscraper")){
            return tempoC+tamTorre;
        }else
            return tempoC;
    }

    public int getPontos() {
        return pontos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Desafio(String num, char t, String n, float tA, float tC, int p, int m,int aS,String d) {
        this.num = num;
        this.tipo = t;
        this.nome = n;
        this.tempoA = tA;
        this.tempoC = tC;
        this.pontos = p;
        this.addStones = aS;
        this.descricao = d;
        this.maxVezes = m;
    }
    
    public int calcPontos(){
       
                return this.pontos;                   
        
    }
   
    
    
    public void imprimir() {
        System.out.println("Desafio "+this.num+" = "+this.nome+": " + this.descricao + ". Pontos: " + this.calcPontos() + " MaxVezes = "+this.maxVezes);
    }
    public void imprimirArquivo(FileWriter F) throws IOException {
        F.write("Desafio "+this.num+" = "+this.nome+": " + this.descricao + ". Pontos: " + this.calcPontos() + " MaxVezes = "+this.maxVezes+"\n");
    }

}
