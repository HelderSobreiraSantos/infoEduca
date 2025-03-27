
package drafeon;

import java.util.ArrayList;
import java.util.Random;


public class IaDoInimigo {
    private Random r=new Random();
    private String mensagem;
    
    public IaDoInimigo(Personagem agente){
        String acao = escolheAcao(agente);
        
        mensagem = switch (acao) {
            case Habilidades.RECUPERACAO -> Habilidades.recuperacao(agente,escolheAlvo(CampoDeBatalha.getInimigos()));
            case Habilidades.CORTELAMINAR -> Habilidades.corteLaminar(agente,escolheAlvo(CampoDeBatalha.getAliados()));
            case Habilidades.BOLADEFOGO -> Habilidades.bolaDeFogo(agente,CampoDeBatalha.getAliados());
            case Habilidades.FORTIFICAR -> Habilidades.fortificar(agente,escolheAlvo(CampoDeBatalha.getInimigos()));
            case Habilidades.ATAQUEBRUTAL -> Habilidades.ataqueBrutal(agente,escolheAlvo(CampoDeBatalha.getAliados()));
            case Habilidades.DRENARATAQUE -> Habilidades.drenarAtaque(agente,escolheAlvo(CampoDeBatalha.getAliados()));
            case Habilidades.BOLADEFOGODRACONICA -> Habilidades.bolaDeFogoDraconica(agente,CampoDeBatalha.getAliados());
            default -> "habilidade incorreta";
        };

        
   }    
    //recebe a lista de oponentes e escolhe um alvo
    public Personagem escolheAlvo(ArrayList<Personagem> alvos){
        int i=r.nextInt(alvos.size());
        return  alvos.get(i); 
    }

    //método que recebe o arraylist de habilidades da classe personagem e retorna uma de suas habilidades aleatória:
    public String escolheAcao(Personagem agente, int nada){ //basta remover o parametro nada para que o método seja usado
        ArrayList<String> habilidades, habilidadesFiltro;
        habilidades = agente.getHabilidades();
        habilidadesFiltro = new ArrayList<String>();
        int escolha;
        int atk = agente.getATK();
        int def = agente.getDEF();
        
        //filtrando habilidades pelo custo de recursos:
        for (int i = 0; i < habilidades.size(); i++) {
            String habilidade = habilidades.get(i);

            // Condições de uso para cada habilidade
            if (habilidade.equals("recuperacao") && def < 1) {
                continue;  // Habilidade não pode ser usada, então pula para a próxima
            }
            if (habilidade.equals("fortificar") && (def < 1 || atk < 1)) {
                continue;
            }
            if (habilidade.equals("drenarAtaque") && def < 2) {
                continue;
            }
            if (habilidade.equals("bolaDeFogo") && atk < 2) {
                continue;
            }
            if (habilidade.equals("bolaDeFogoDraconica") && def < 2) {
                continue;
            }
            if (habilidade.equals("corteLaminar") && atk < 1) {
                continue;
            }
            if (habilidade.equals("ataqueBrutal") && (atk < 2 || def < 1)) {
                continue;
            }

            // Se passou por todas as condições, a habilidade é válida
            habilidadesFiltro.add(habilidade);
        }
            //retornando habilidade que pode ser usada:
        if (habilidadesFiltro.size() > 0){
            escolha = r.nextInt(habilidadesFiltro.size());
            return habilidadesFiltro.get(escolha);    
        }
            
        //retornando habilidade que não pode ser usada
        escolha = r.nextInt(habilidades.size());
        return habilidades.get(escolha);
    }
    
    //a escolha da ação será baseada numa rolagem de D6 e este método aribui bônus a cada opção de ação baseado no estado do campo de batalha
    public String escolheAcao(Personagem agente){
        ArrayList<String> habilidades;
        ArrayList<String> habilidadesFiltro = new ArrayList<String>();
        habilidades = agente.getHabilidades();
        Random dado = new Random();
        
        //bonus para as rolagens de decisão:
        int recuperacao = 0;
        int ataqueBrutal = 0;
        int bolaDeFogo = 0;
        int bolaDeFogoDraconica = 0;
        int corteLaminar = 0;
        int fortificar = 0;
        int drenarAtaque = 0;
        
        int atk = agente.getATK();
        int def = agente.getDEF();
        
        //filtrando habilidades pelo custo de recursos:
        for (int i = 0; i < habilidades.size(); i++) {
            String habilidade = habilidades.get(i);

            // Condições de uso para cada habilidade
            if (habilidade.equals("recuperacao") && def < 1) {
                continue;  // Habilidade não pode ser usada, então pula para a próxima
            }
            if (habilidade.equals("fortificar") && (def < 1 || atk < 1)) {
                continue;
            }
            if (habilidade.equals("drenarAtaque") && def < 2) {
                continue;
            }
            if (habilidade.equals("bolaDeFogo") && atk < 2) {
                continue;
            }
            if (habilidade.equals("bolaDeFogoDraconica") && def < 2) {
                continue;
            }
            if (habilidade.equals("corteLaminar") && atk < 1) {
                continue;
            }
            if (habilidade.equals("ataqueBrutal") && (atk < 2 || def < 1)) {
                continue;
            }

            // Se passou por todas as condições, a habilidade é válida
            habilidadesFiltro.add(habilidade);
        }
        
        //análise por estado do agente:
        if (agente.getHP() < 2){ //HP baixo
            if (agente.getDEF() < 1){ //indefeso - vai fazer o que pode ou tentar um ataque suicida
                if (agente.getATK() >= 2){
                    //suicida - vai atacar com tudo
                    ataqueBrutal = ataqueBrutal + 1;
                    bolaDeFogo = bolaDeFogo + 1;
                    bolaDeFogoDraconica = bolaDeFogoDraconica + 1;
                    corteLaminar = corteLaminar + 1;
                }
            } else if (agente.getDEF() < 2){
                //em risco - vai priorizar a cura
                recuperacao = recuperacao + 3;
            }
        } 
        if (agente.getATK() <= 1){ //ATK baixo 
            if (agente.getDEF() >= 1){ 
                if(agente.getHP() >= 2){ //capaz de se defender - se fortificar
                    drenarAtaque = drenarAtaque + 3;
                } 
                //vulnerável - vai evitar o gasto de recursos
            }
        }
        if (agente.getDEF() <= 1){ //DEF baixa
            if (agente.getHP() >= 2){ //DEF baixa - vai evitar gastar 
                recuperacao--; fortificar--; drenarAtaque--;
            }
        }
        if (agente.getATK() >= 2 && agente.getDEF() >= 2){//tudo no talo - usar os ataques mais poderosos
            recuperacao--; fortificar--; drenarAtaque--;
            corteLaminar++; bolaDeFogo++; bolaDeFogoDraconica++;
            if(agente.getATK() >= 3){
                ataqueBrutal = ataqueBrutal + 2;
            }
        }
        
        int resultado = 0;
        int indice = -1;
        for (int i = 0; i < habilidadesFiltro.size(); i++) {
            int bonus = 0;
            int rolagem;
            
            if (habilidadesFiltro.get(i).equals("recuperacao")){
                bonus = recuperacao;
            }else if (habilidadesFiltro.get(i).equals("ataqueBrutal")){
                bonus = ataqueBrutal;
            }else if (habilidadesFiltro.get(i).equals("bolaDeFogo")){
                bonus = bolaDeFogo;
            }else if (habilidadesFiltro.get(i).equals("bolaDeFogoDraconica")){
                bonus = bolaDeFogoDraconica;
            }else if (habilidadesFiltro.get(i).equals("corteLaminar")){
                bonus = corteLaminar;
            }else if (habilidadesFiltro.get(i).equals("fortificar")){
                bonus = fortificar;
            }else if (habilidadesFiltro.get(i).equals("drenarAtaque")){
                bonus = drenarAtaque;
            }
            rolagem = dado.nextInt(1, 6) + bonus;
            if (rolagem > resultado){
                resultado = rolagem;
                indice = i;
            }
        }
        if (indice <= -1){
            indice = dado.nextInt(1, habilidades.size());
            return habilidades.get(indice);
        }
        //int escolha = dado.nextInt(habilidades.size());
        return habilidadesFiltro.get(indice);
        
    }
    
    public String getMensagem(){
        return this.mensagem;
    }
}
