
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
    public String escolheAcao(Personagem agente){
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
    
    //versão anterior sem filtro:
    //public String escolheAcao(Personagem agente){
        //ArrayList<String> habilidades;
        //habilidades = agente.getHabilidades();
        //int i= r.nextInt(habilidades.size());
        
        //return habilidades.get(i);
    //}
    
    public String getMensagem(){
        return this.mensagem;
    }
}
