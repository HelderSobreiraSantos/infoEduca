package drafeon;

public class Batalha {
    private Iniciativa iniciativa;

    public Batalha(boolean ehFase1){
        geraCampoDeBatalha(ehFase1);
        geraIniciativa();

    }

    public void geraCampoDeBatalha(boolean ehFase1){
        //guerreiro: HP - 3, ATK: 2, DEF: 3 (CorteLaminar, AtaqueBrutal)
        //Mago: HP - 1, ATK: 3, DEF: 2 (BolaDeFogo, DrenarAtaque, Fortificar)
        //Cleriga: Hp - 2, ATK: 1, DEF: 3 (Fortificar, Recuperacao, corteLaminar)
        Personagem aliado1 = new Personagem("Guerreiro",3,3,3, false);
        Personagem aliado2 = new Personagem("Mago",2,3,2, false);
        Personagem aliado3 = new Personagem("Cleriga",1,3,2, false);

        aliado1.adicionarHabilidade(Habilidades.CORTELAMINAR);
        aliado1.adicionarHabilidade(Habilidades.ATAQUEBRUTAL);
      
        aliado2.adicionarHabilidade(Habilidades.BOLADEFOGO);
        aliado2.adicionarHabilidade(Habilidades.DRENARATAQUE);
        aliado2.adicionarHabilidade(Habilidades.FORTIFICAR);
        
        aliado3.adicionarHabilidade(Habilidades.FORTIFICAR);
        aliado3.adicionarHabilidade(Habilidades.RECUPERACAO);
        aliado3.adicionarHabilidade(Habilidades.CORTELAMINAR);
        
        CampoDeBatalha.inserirAliado(aliado1);
        CampoDeBatalha.inserirAliado(aliado2);
        CampoDeBatalha.inserirAliado(aliado3);

        geraInimigos(ehFase1);
    }
    
    public void geraInimigos(boolean ehFase1){
        Personagem inimigo1,inimigo2,inimigo3;
        if (ehFase1 ){
        inimigo1 = new Personagem("cavaleironegro",1,5,3,true); // 5,5,5
        inimigo2 = new Personagem("feiticeiro",1,5,1,true); //3,5,2
        inimigo3 = new Personagem("necromante",1,5,0,true); //3,5,4
        
       inimigo1.adicionarHabilidade(Habilidades.DRENARATAQUE);
        inimigo1.adicionarHabilidade(Habilidades.ATAQUEBRUTAL);
        inimigo1.adicionarHabilidade(Habilidades.CORTELAMINAR);

        inimigo2.adicionarHabilidade(Habilidades.BOLADEFOGO);
        inimigo2.adicionarHabilidade(Habilidades.DRENARATAQUE);
        inimigo2.adicionarHabilidade(Habilidades.DRENARATAQUE);
        
        inimigo3.adicionarHabilidade(Habilidades.RECUPERACAO);
        inimigo3.adicionarHabilidade(Habilidades.FORTIFICAR);
        inimigo3.adicionarHabilidade(Habilidades.DRENARATAQUE);
        }
        else {
            inimigo1 = new Personagem("dragao",2,9,2,true);
            inimigo1.adicionarHabilidade(Habilidades.BOLADEFOGODRACONICA);
            inimigo1.adicionarHabilidade(Habilidades.ATAQUEBRUTAL);
            
            inimigo2 = new Personagem("bruxa",1,3,0,true);
            inimigo2.adicionarHabilidade(Habilidades.DRENARATAQUE);
            inimigo2.adicionarHabilidade(Habilidades.FORTIFICAR);

            inimigo3 = new Personagem("feiticera",1,3,0,true);
            inimigo3.adicionarHabilidade(Habilidades.DRENARATAQUE);
            inimigo3.adicionarHabilidade(Habilidades.BOLADEFOGO);
            }
        CampoDeBatalha.inserirInimigo(inimigo1);
        CampoDeBatalha.inserirInimigo(inimigo2);
        CampoDeBatalha.inserirInimigo(inimigo3);
    }



    public Iniciativa geraIniciativa(){
        Iniciativa iniciativa = new Iniciativa();
        return null;
    }
    
}

