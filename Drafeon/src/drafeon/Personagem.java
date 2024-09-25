package drafeon;

import java.util.ArrayList;
import java.util.Scanner;

public class Personagem {
    private String nome;
    // Vida Atual
    private int HP;
    //Defesa Atual
    private int DEF;
    //Ataque Atual
    private int ATK;  
    // Vida Total
    private int totalHP;
    // Defesa Total
    private int totalDEF;
    // Ataque Total
    private int TotalATK;
    // Define se o personagem é ou não jogável
    private boolean ehInimigo;

    //lista de stings com o nome de três habilidadess
    private ArrayList<String> habilidades; 
    
    public Personagem(String nome, int HP, int ATK, int DEF, boolean ehInimigo){
        this.nome = nome;
        this.HP = HP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.totalHP = HP;
        this.TotalATK = ATK;
        this.totalDEF = DEF;
        this.habilidades = new ArrayList<>();
        this.ehInimigo = ehInimigo;
    }

    public void adicionarHabilidade(String habilidade){
        if (this.habilidades.size() < 3){
         this.habilidades.add(habilidade);
        }
    }
   public String retornaHabilidade(int indice){
        return this.habilidades.get(indice);
   }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean getEhInimigo() {
        return ehInimigo;
    }

    public void setEhInimigo(boolean ehInimigo) {
       this.ehInimigo = ehInimigo;
         
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
       this.HP = verificaSetagem(HP);
         
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        
        this.DEF = verificaSetagem(DEF);
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = verificaSetagem(ATK);
    }
    
    public ArrayList<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(ArrayList<String> habilidades) {
        this.habilidades = habilidades;
    }

    public int verificaSetagem(int valor){
        if (this.ehInimigo == true) { //lembrar de corrigir isso pra o inimigo não curar infinitamente
            if (valor < 0){
            valor = 0;
        }
            return valor;
        }
        if (valor > 3){
            valor = 3;
        }else if (valor < 0){
            valor = 0;
        }
        return valor;
    }
    
    //método demonstrativo para ser retirado.
    public void atacar(ArrayList<Personagem> personagens) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Escolha um personagem para atacar:");
        for (int i = 0; i < personagens.size(); i++) {
            System.out.println((i + 1) + ". " + personagens.get(i).getNome());
        }

        int escolha = scanner.nextInt() - 1;
        Personagem alvo = personagens.get(escolha);

        if (alvo.DEF >= 1) {
            alvo.setDEF(alvo.getDEF() - ATK);
            System.out.println(alvo.getNome() + " bloqueia gastando " + ATK + " de defesa \n");
            if (alvo.getDEF() < 0) {
                alvo.setHP(alvo.getHP() - 1);
                alvo.setDEF(0);
                System.out.println(alvo.getNome() + " é acertado, recebendo " + 1 + " de dano \n");
            }
        } else if (alvo.DEF <= 0) {
            alvo.setHP(alvo.getHP() - 1);
            System.out.println(alvo.getNome() + " é acertado, recebendo " + 1 + " de dano \n");
        }
    }
    
    public void mostrarStatus() {
        System.out.println(nome + ": HP - " + HP + ", Defesa - " + DEF + ", Ataque - " + ATK + "\n");
    }
    public void MostrarHabilidades() {
        
    }
    
}
