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
        Personagem aliado2 = new Personagem("Mago",1,3,2, false);
        Personagem aliado3 = new Personagem("Cleriga",2,1,3, false);

        aliado1.adicionarHabilidade("cortelaminar");
        aliado1.adicionarHabilidade("ataquebrutal");
      
        aliado2.adicionarHabilidade("boladefogo");
        aliado2.adicionarHabilidade("drenarataque");
        aliado2.adicionarHabilidade("fortificar");
        
        aliado3.adicionarHabilidade("fortificar");
        aliado3.adicionarHabilidade("recuperacao");
        aliado3.adicionarHabilidade("cortelaminar");
        
        CampoDeBatalha.inserirAliado(aliado1);
        CampoDeBatalha.inserirAliado(aliado2);
        CampoDeBatalha.inserirAliado(aliado3);

        geraInimigos(ehFase1);
    }
    
    public void geraInimigos(boolean ehFase1){
        Personagem inimigo1,inimigo2,inimigo3;
        if (ehFase1 ){
        inimigo1 = new Personagem("cavaleironegro",1,5,2,true); // 5,5,5
        inimigo2 = new Personagem("feiticeiro",1,5,0,true); //3,5,2
        inimigo3 = new Personagem("necromante",1,5,0,true); //3,5,4
        
        inimigo1.adicionarHabilidade("drenarataque");
        inimigo1.adicionarHabilidade("ataquebrutal");
        inimigo1.adicionarHabilidade("cortelaminar");

        inimigo2.adicionarHabilidade("boladefogo");
        inimigo2.adicionarHabilidade("drenarataque");
        inimigo2.adicionarHabilidade("drenarataque");
        
        inimigo3.adicionarHabilidade("recuperacao");
        inimigo3.adicionarHabilidade("fortificar");
        inimigo3.adicionarHabilidade("drenarataque");
        }
        else {
            inimigo1 = new Personagem("dragao",2,9,2,true);
            inimigo1.adicionarHabilidade("boladefogodraconica");
            inimigo1.adicionarHabilidade("ataquebrutal");
            
            inimigo2 = new Personagem("bruxa",1,3,0,true);
            inimigo2.adicionarHabilidade("drenarataque");
            inimigo2.adicionarHabilidade("fortificar");

            inimigo3 = new Personagem("feiticera",1,3,0,true);
            inimigo3.adicionarHabilidade("drenarataque");
            inimigo3.adicionarHabilidade("boladefogo");
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

