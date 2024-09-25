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

    public Personagem escolheAlvo(ArrayList<Personagem> alvos){
        int i=r.nextInt(alvos.size());
        return  alvos.get(i); 
    }

    //método que recebe o arraylist de habilidades da classe personagem() 
    //e retorna uma de suas habilidades aleatória:
    public String escolheAcao(Personagem agente){
        ArrayList<String> habilidades;
        habilidades = agente.getHabilidades();
        int i= r.nextInt(habilidades.size());
        
        return habilidades.get(i);
    }
    public String getMensagem(){
        return this.mensagem;
    }
}