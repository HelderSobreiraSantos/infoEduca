package drafeon;

import java.util.ArrayList;

public class Habilidades {
 public static final String RECUPERACAO = "recuperacao", 
         FORTIFICAR = "fortificar",
         BOLADEFOGO = "boladefogo",
         BOLADEFOGODRACONICA="boladefogodraconica",
         CORTELAMINAR="cortelaminar",
         ATAQUEBRUTAL="ataquebrutal",
         DRENARATAQUE="drenarataque";
    
    private static String templateAtk(String nome, boolean direto, int valor, Personagem alvo, Personagem agente){
            
            if (direto) return alvo.getNome() + " é acertado, por " +nome+ " de " + agente.getNome() +" recebendo " + valor + " de dano ao seu HP<br>";
            return alvo.getNome() + " bloqueia " + nome + " de " + agente.getNome() +" gastando " + valor + " de defesa<br>";
        }
     
         
    public static String recuperacao(Personagem agente, Personagem alvo){
        if (agente.getDEF() < 1) {
            return agente.getNome() + " não tem defesa o suficiente pode ativar a recuperação";
        }
        agente.setDEF(agente.getDEF() - 1);
        String mensagem = "algo deu errado";
        alvo.setHP(alvo.getHP() + 2);
        if(alvo.getHP()>3) {alvo.setHP(3);}
        mensagem = "O HP de: "+ alvo.getNome() + " é recuperado em 2 ao custo de 1 de defesa por "+agente.getNome();
        return mensagem;
    }

    public static String fortificar(Personagem agente, Personagem alvo){

        if (agente.getDEF() < 1 || agente.getATK() < 1) {
            return agente.getNome() + " não tem a defesa ou o ataque necessario para fortificar "+alvo.getNome();
        }
        agente.setDEF(agente.getDEF() - 1);
        agente.setATK(agente.getATK() -1);
        String mensagem = "algo deu errado";
        alvo.setHP(alvo.getATK() + 2);
        if(alvo.getATK()>3) {alvo.setATK(3);}
        mensagem = "O ataque de: "+alvo.getNome()+ " foi recuperada em 2 ao custo de 1 de defesa e 1 de ataque por "+agente.getNome();
        return mensagem;
    }
    
      
        public static String drenarAtaque(Personagem agente, Personagem alvo){
            if (agente.getDEF() < 2){
                return agente.getNome() + " não tem defesa o suficiente para drenar o ataque de: "+alvo.getNome();
            }
            
            agente.setDEF(agente.getDEF() - 2);
            alvo.setATK(alvo.getATK() - 1);
            if (alvo.getATK() < 0) alvo.setATK(0);
            agente.setATK(agente.getATK() + 1);
            if (alvo.getATK() > 3) alvo.setATK(3);

            return alvo.getNome() + " foi drenado, diminuindo 1 de ataque e "+ agente.getNome() +" ataque aumentou em 1";
        }
    
    public static ArrayList<Personagem> acumularPersonagensMortos(ArrayList<Personagem> alvo) {
    ArrayList<Personagem> paraRemover = new ArrayList<>();
    for (Personagem p : alvo) {
        if (p.getHP() <= 0) { // Verifica se o HP é 0 ou menor
            paraRemover.add(p);
        }
    }
    return paraRemover;
}

public static String bolaDeFogo(Personagem agente, ArrayList<Personagem> alvo) {

    if (agente.getATK() < 2) {
        return agente.getNome() + " não tem ataque o suficiente para ativar a bola de fogo";
    }
    agente.setATK(agente.getATK() - 2);
    String mensagem = "";
    String nome = "bola de fogo";
    for (Personagem p : alvo) {
        if (p.getDEF() >= 1) {
            p.setDEF(p.getDEF() - 1);
            mensagem += Habilidades.templateAtk(nome, false, 1, p, agente);
        } else if (p.getDEF() <= 0) {
            p.setHP(p.getHP() - 1);
            mensagem += Habilidades.templateAtk(nome, true, 1, p, agente);
        }
    }
    
    // Acumular e remover apenas personagens cujo HP seja 0
    ArrayList<Personagem> paraRemover = acumularPersonagensMortos(alvo);
    for (Personagem p : paraRemover) {
        mensagem += matarPersonagem(p); // Chama matarPersonagem para remover da batalha
        CampoDeBatalha.removePersonagem(p);
        Iniciativa.removePersonagem(p);
    }

    return mensagem;
}

public static String bolaDeFogoDraconica(Personagem agente, ArrayList<Personagem> alvo) {

    if (agente.getATK() < 2) {
        return agente.getNome() + " não tem ataque o suficiente para ativar a poderosa bola de fogo draconica";
    }
    agente.setATK(agente.getATK() - 2);
    String mensagem = "";
    String nome = "bola de fogo draconica";
    for (Personagem p : alvo) {
        if (p.getDEF() >= 2) {
            p.setDEF(p.getDEF() - 2);
            mensagem += Habilidades.templateAtk(nome, false, 2, p, agente);
        } else if (p.getDEF() == 1) {
            p.setDEF(p.getDEF() - 1);
            mensagem += Habilidades.templateAtk(nome, false, 1, p, agente);
            p.setHP(p.getHP() - 1);
            mensagem += Habilidades.templateAtk(nome, true, 1, p, agente);
        } else if (p.getDEF() <= 0) {
            p.setHP(p.getHP() - 2);
            mensagem += Habilidades.templateAtk(nome, true, 2, p, agente);
        }
    }
    
    // Acumular e remover apenas personagens cujo HP seja 0
    ArrayList<Personagem> paraRemover = acumularPersonagensMortos(alvo);
    for (Personagem p : paraRemover) {
        mensagem += matarPersonagem(p); // Chama matarPersonagem para remover da batalha
        CampoDeBatalha.removePersonagem(p);
        Iniciativa.removePersonagem(p);
    }

    return mensagem;
}

    public static String corteLaminar(Personagem agente, Personagem alvo) {
        //reduzir o atk do agente em 1 como custo.
        if (agente.getATK() < 1) {
            return agente.getNome()+" não tem ataque o suficiente para ativar o corte laminar";
        }
        agente.setATK(agente.getATK() - 1);
        String mensagem = "algo deu errado";
        String nome = "corte laminar";
        if (alvo.getDEF() >= 1) {
            alvo.setDEF(alvo.getDEF() - 2);
            mensagem = Habilidades.templateAtk(nome, false, 2, alvo, agente);
            if (alvo.getDEF() < 0) {
                alvo.setHP(alvo.getHP() - 1);
                alvo.setDEF(0);
                
                mensagem = Habilidades.templateAtk(nome, true, 1, alvo, agente);
                    }
        } else if (alvo.getDEF() <= 0) {
            alvo.setHP(alvo.getHP() - 2);
            
        mensagem = Habilidades.templateAtk(nome, true, 2, alvo, agente);        }
        
        if (verificaMorte(alvo)) mensagem += matarPersonagem(alvo);
        
        
        return mensagem;
    }

        public static String ataqueBrutal(Personagem agente, Personagem alvo) {
        //reduzir o atk do agente em 1 como custo.
        
        
        if (agente.getATK() < 2 || agente.getDEF() < 1) {
            return agente.getNome() + "não tem o ataque ou a defesa o suficiente para ativar o ataque brutal";
        }
        agente.setATK(agente.getATK() - 2);
        agente.setDEF(agente.getDEF() - 1);
        String mensagem = "algo deu errado";
        String nome = "ataque brutal";
        int diferenca = 3 - alvo.getDEF();
     
        if (diferenca >= 1) {

            if (diferenca == 3) {
                alvo.setHP(alvo.getHP() - 3);
               
                mensagem = Habilidades.templateAtk(nome, true, diferenca, alvo, agente);
            
  
            }
            else {
            alvo.setDEF(alvo.getDEF() - (3 - diferenca));
            mensagem = Habilidades.templateAtk(nome, false, 3 - diferenca, alvo, agente);
            alvo.setHP(alvo.getHP() - diferenca);
          
            mensagem += Habilidades.templateAtk(nome, true, diferenca, alvo, agente);
            }
        } else {
            alvo.setDEF(alvo.getDEF() - 3);
            mensagem = Habilidades.templateAtk(nome, false, 3, alvo, agente);
        }
        
        if (verificaMorte(alvo)) mensagem += matarPersonagem(alvo);
        return mensagem;
    }
     


    

    //verifica se o personagem morreu
    public static boolean verificaMorte(Personagem personagem){
        if(personagem.getHP() <= 0){
            return true;
        }
        return false;
    }
    //remove personagem de time
    public static String matarPersonagem(Personagem personagem){
        CampoDeBatalha.removePersonagem(personagem);
        Iniciativa.removePersonagem(personagem);
        return personagem.getNome() + " cai a 0 de HP, saindo do combate.<br>";
    }

    public String geraDescricao(String habilidade){
        
        String descricao = switch (habilidade) {
            case Habilidades.RECUPERACAO -> recuperarDescricao();
            case Habilidades.CORTELAMINAR -> corteLaminarDesc();
            case Habilidades.BOLADEFOGO -> boladefogoDesc();
            case Habilidades.FORTIFICAR -> fortificarDesc();
            case Habilidades.ATAQUEBRUTAL -> ataqueBrutalDesc();
            case Habilidades.DRENARATAQUE -> drenarAtaqueDesc();
            case Habilidades.BOLADEFOGODRACONICA -> bolaDefogoDraconicaDesc();
            default -> "habilidade incorreta";
        };
        return descricao;
    }

    public String recuperarDescricao(){
        return "Cura um aliado, recuperando 2 de vida. Custo: 1 de defesa";
    
    }
    public String corteLaminarDesc() {
        return "Desfere um poderoso ataque cortante em um alvo, causando 2 de dano. Custo: 1 de ataque";
    
    }
    public String boladefogoDesc(){
        return "Conjura uma enorme bola flamejante, causando 1 de dano em todos os inimigos. Custo: 2 de ataque";
    }

    public String fortificarDesc(){
        return "Aumenta o atk de um aliado em 2. Custo: 1 de ataque e 1 de defesa";
    
    }
    
    public String ataqueBrutalDesc(){
        return "Desfere um poderoso ataque, causando 3 de dano em um alvo. Custo: 2 de ataque e 1 de defesa";
    
    }
    
    public String drenarAtaqueDesc(){
        return "Drena a habilidade o poder de um alvo, removendo 1 ATK dele e recuperando 1 de ATK para si. Custo: 2 de defesa";
    
    }
    public String bolaDefogoDraconicaDesc(){
        return "Conjura uma enorme bola flamejante, causando 2 de dano em todos os inimigos. Custo: 2 de ataque";
    }
    
    //public String reviverDesc(){
    //    return "Levanta um aliado caído no campo de batalha, aumentando seu HP em 1. Custo: 2 de ataque";
    
    //}
    
}