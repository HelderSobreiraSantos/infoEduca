package drafeon;

import java.util.ArrayList;

public class Interpretador {
    String mensagem = "000";
    // Método que processa a string
    public Interpretador(String input) {
        String palavra1 = "";
        String palavra2 = "";
        String palavra3 = "";
        int quantidadeDePalavras = 0;
        Personagem objPersonagem;

        //verificar quantos espaos vazios tem no input para definir quantidade de palavras através de um if
        // Remove espaços em excesso e divide a string por espaços e vírgulas
        String[] palavras = input.split("[,\\s]+");

//ABAIXO VERIFICAR QUANTIDADE DE PALAVRES
        // Exibir o número de palavras
        quantidadeDePalavras = palavras.length;
        System.out.println("Número de palavras: " + quantidadeDePalavras);

        // Armazena as palavras em variáveis
        if (quantidadeDePalavras == 1) {
            palavra1 = palavras.length > 0 ? palavras[0] : "";
        } else if (quantidadeDePalavras == 2) {
            palavra1 = palavras.length > 0 ? palavras[0] : "";
            palavra2 = palavras.length > 1 ? palavras[1] : "";
        } else if (3 == quantidadeDePalavras) {
            palavra1 = palavras.length > 0 ? palavras[0] : "";
            palavra2 = palavras.length > 1 ? palavras[1] : "";
            palavra3 = palavras.length > 2 ? palavras[2] : "";
        } else {
            //retorna mensagem de erro
        }

        //designa ação baseada no comando passado pelo jogador
        if (quantidadeDePalavras == 1) {
            //chama todos os métodos chamados por uma palavra
            if (palavra1.equalsIgnoreCase("ajuda") || palavra1.equalsIgnoreCase("help")) {
                this.mensagem = ajuda();
            }

        } else if (quantidadeDePalavras == 2) {
if (palavra2.equalsIgnoreCase(Habilidades.BOLADEFOGO)){
    this.mensagem = chamaBoladefogo(palavra1, palavra2);
}
            //chama todos os métodos chamados por três palavras
        } else if (quantidadeDePalavras == 3) {
            if (palavra2.equalsIgnoreCase(Habilidades.CORTELAMINAR)) {
                this.mensagem = chamaCorteLaminar(palavra1, palavra3, palavra2);
            
            }else if (palavra2.equalsIgnoreCase(Habilidades.RECUPERACAO)) {
                this.mensagem = chamaRecuperacao(palavra1, palavra3, palavra2);
            

        } else if (palavra2.equalsIgnoreCase(Habilidades.ATAQUEBRUTAL)){
                this.mensagem = chamaAtaquebrutal(palavra1, palavra3, palavra2);
        } else if (palavra2.equalsIgnoreCase(Habilidades.FORTIFICAR)) {
                this.mensagem = chamaFortificar(palavra1, palavra3, palavra2);
        }
        else if (palavra2.equalsIgnoreCase(Habilidades.DRENARATAQUE)){
                this.mensagem = chamaDrenarAtaque(palavra1, palavra3, palavra2);
        }
         else {
            this.mensagem = "deu ruim";
        }
    }
    }

    public String retornaMensagem(){
        return this.mensagem;
    }

    //Método que verifica se o alvo é válido o e retorna:
    public Object verificarPersonagem(String alvo, ArrayList<Personagem> personagens) {
        //tri catchs que verificam se um alvo é válido
        for (Personagem personagem : personagens) {
            if (personagem.getNome().equalsIgnoreCase(alvo)) {
                System.out.println("personagem: "+personagem.getNome());
                return personagem;//.getNome();
            }

        }
        return "ERROR";
    }
   //tirar essa negócio de estático 
   public static Personagem buscarPersonagemPorNome( String nome) {
        // Verificar nos aliados
        for (Personagem aliado : CampoDeBatalha.getAliados()) {
            if (aliado.getNome().equalsIgnoreCase(nome)) {
                return aliado;
            }
        }
        
        // Verificar nos inimigos
        for (Personagem inimigo : CampoDeBatalha.getInimigos()) {
            if (inimigo.getNome().equalsIgnoreCase(nome)) {
                return inimigo;
            }
        }
        
        // Se não encontrar nenhum personagem com o nome especificado
        return null;
    }

    public String listarHabilidades(Personagem agente) {
        String mensagem;
        mensagem = "habilidade 1: " + agente.retornaHabilidade(0) + "\nhabilidade 2: " + agente.retornaHabilidade(1) + "\nhabilidade 3: " + agente.retornaHabilidade(2);

        return mensagem;
    }

//Método para acionar o corte laminar
    public String chamaCorteLaminar(String agent, String target, String skill) {
        Object agente;
        Object alvo;
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        alvo = verificarPersonagem(target, CampoDeBatalha.getInimigos()); 
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                    String ataque = Habilidades.corteLaminar((Personagem)agente, (Personagem)alvo); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return "deu erro tente novamente";
        }
    }

//Método para acionar recupera ação
public String chamaRecuperacao(String agent, String target, String skill) {
        Object agente;
        Object alvo;
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        alvo = verificarPersonagem(target,  CampoDeBatalha.getAliados());
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                    String ataque = Habilidades.recuperacao((Personagem)agente, (Personagem)alvo); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return "deu erro tente novamente";
        }
    }

    public String chamaBoladefogo(String agent, String skill) {
        Object agente;
        
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                    String ataque = Habilidades.bolaDeFogo((Personagem)agente, CampoDeBatalha.getInimigos()); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return "deu erro tente novamente";
        }
    }

//Método para acionar ataque brutal
public String chamaAtaquebrutal(String agent, String target, String skill) {
        Object agente;
        Object alvo;
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        alvo = verificarPersonagem(target, CampoDeBatalha.getInimigos()); 
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                   
                    String ataque = Habilidades.ataqueBrutal((Personagem)agente, (Personagem)alvo); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return "deu erro tente novamente";
        }
    }

//Método para acionar fortificar
public String chamaFortificar(String agent, String target, String skill) {
        Object agente;
        Object alvo;
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        alvo = verificarPersonagem(target,  CampoDeBatalha.getAliados());
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                    System.out.println("verificado");
                    String ataque = Habilidades.fortificar((Personagem)agente, (Personagem)alvo); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            System.out.println(e.toString());
            return "deu erro tente novamente ";
        }
    }
 public String chamaDrenarAtaque(String agent, String target, String skill) {
        Object agente;
        Object alvo;
        agente = verificarPersonagem(agent, CampoDeBatalha.getAliados());
        alvo = verificarPersonagem(target, CampoDeBatalha.getInimigos()); 
        try {
            boolean verificador = verificaHabilidade((Personagem)agente, skill);
            if(verificador == true){
                   
                    String ataque = Habilidades.drenarAtaque((Personagem)agente, (Personagem)alvo); 
                     
                return ataque;
            }else{
                return "comando inválido, tente novamente";
            }
        }catch(Exception e){
            return "deu erro tente novamente".concat(e.toString());
        }
    }


    public String verificarAcao(String acao) {
        if (true) {

        }
        return "ERROR!";
    }

    public boolean verificaHabilidade(Personagem agente, String habilidadeDesejada) {
    ArrayList<String> habilidades = agente.getHabilidades();

    for (String habilidade : habilidades) {
        if (habilidade.equalsIgnoreCase(habilidadeDesejada)) {
            return true;
        }
    }
        return false; // Se chegou até aqui, a habilidade não foi encontrada
    }


    //a seguir, método chamados por uma só palavra:
    public String ajuda() {
        return "Digite sempre tudo em minúsculo e sem acentos \n usar habilidade - (agente nomedahabilidade alvo)";
    
    }

    public String sair() {
        //cria janela de confirmação na tela
        //alterna para a tela do menu inicial
        return "Saindo";
    }

    public String verStatus() {
        return "adicionar retorno";
    }

}