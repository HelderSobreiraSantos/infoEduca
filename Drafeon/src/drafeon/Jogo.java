package drafeon;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.swing.*;

public class Jogo extends JFrame {

    private JPanel painelPrincipal,painelCreditos,painelJogo,painelBatalha;
    private CardLayout cardLayout;
    private JLabel textoCreditos;
    private JLabel textoJogo, textoBatalha;
    private JTextField inputBatalha;
    private JButton botaoJogo,botaoBatalha, botaoPassarOTurno, botaoTutorial;
    private boolean acabou = false;
    // Array de textos para serem mostrados
private ArrayList<String> textos = new ArrayList<String>(); 
// Variável para rastrear o índice do texto atual
private int indiceTextoAtual = 0;
private int fase = 1;
    public Jogo() {
         // Carregar e registrar a fonte personalizada
        carregarFontePersonalizada();
         


        // Configurações da janela
        setTitle("JOGO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(400, 350);
        // Usando CardLayout para gerenciar múltiplas telas
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        // Criando o painel principal
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());

        // Configurações de estilo
        painelPrincipal.setBackground(Color.BLACK);

        // Adicionando o título
        JLabel titulo = new JLabel("Título do Jogo", SwingConstants.CENTER);
        titulo.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 24)); // Aplica a fonte personalizada
        titulo.setForeground(Color.WHITE);
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Criando os botões
        JButton botaoIniciar = criarBotao("Iniciar Jogo");
        JButton botaoCreditos = criarBotao("Créditos");
        JButton botaoSair = criarBotao("Sair");
        this.botaoTutorial = criarBotao("Tutorial");
        this.botaoJogo = criarBotao("Próximo");
        this.botaoBatalha = botaoEnviar("Enviar",40);
        this.botaoPassarOTurno = botaoEnviar(transformarEmHTML("Próximo"),20);
        // Adicionando os botões ao painel em orientação vertical
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 10, 10)); // Grade com 3 linhas e espaçamento de 10px
        painelBotoes.setBackground(Color.BLACK);
        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoCreditos);
        painelBotoes.add(botaoTutorial);
        painelBotoes.add(botaoSair);
        

        // Criando um painel para centralizar os botões horizontalmente
        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelCentral.setBackground(Color.BLACK);
        painelCentral.add(painelBotoes);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Adicionando ações aos botões
        botaoIniciar.addActionListener(e -> iniciarJogo());
        botaoCreditos.addActionListener(e -> iniciarAnimacaoCreditos());
        botaoTutorial.addActionListener( e -> iniciarTutorial());
        botaoSair.addActionListener(e -> System.exit(0));

        
       
        // Criando o painel de créditos
        painelCreditos = new JPanel();
        painelCreditos.setLayout(new BorderLayout());
        painelCreditos.setBackground(Color.BLACK);

        // Adicionando o texto de créditos
        textoCreditos = new JLabel("", SwingConstants.CENTER);
        textoCreditos.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada inicialmente
        textoCreditos.setForeground(Color.WHITE);
        painelCreditos.add(textoCreditos, BorderLayout.CENTER);
        
        
        // ----------------- Painel Inciar Jogo (Historinha)--------------------------------------------------------------------------------------------
        painelJogo = new JPanel();
        painelJogo.setLayout(new BorderLayout());
        painelJogo.setBackground(Color.BLACK);
        
        textoJogo = new JLabel("", SwingConstants.CENTER);
        textoJogo.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada inicialmente
        textoJogo.setForeground(Color.WHITE);
        painelJogo.add(textoJogo, BorderLayout.CENTER);
        
        painelJogo.add(botaoJogo,BorderLayout.SOUTH);
        
        // ------------------------ Painel Iniciar Batalha---------------------------------------------------------------------------------------------
        
        painelBatalha = new JPanel();
        inputBatalha = new JTextField(20);
        inputBatalha.setSize(20, 40);
       
        painelBatalha.setLayout(new BorderLayout());
        painelBatalha.setBackground(Color.BLACK);
        
        textoBatalha = new JLabel("", SwingConstants.CENTER);
        textoBatalha.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada inicialmente
        textoBatalha.setForeground(Color.WHITE);
        painelBatalha.add(textoBatalha, BorderLayout.CENTER);
        
        
      
        JPanel div2 = new JPanel();
        div2.setLayout(new BorderLayout(4, 1));
        div2.setBackground(Color.BLACK);
        div2.setBounds(10, 10, 25, 25);
       
        div2.add(botaoPassarOTurno,BorderLayout.WEST);
        div2.add(inputBatalha,BorderLayout.CENTER);
        div2.add(botaoBatalha,BorderLayout.EAST);
        
        painelBatalha.add(div2,BorderLayout.SOUTH);

        // Adicionando Painéis ao CardLayout
        add(painelPrincipal, "Principal");
        add(painelCreditos, "Creditos");
        add(painelJogo,"Jogo");
        add(painelBatalha,"Batalha");
        // Tornando a janela visível
        setVisible(true);

        // Iniciar a animação de créditos ao iniciar o jogo
        iniciarAnimacaoCreditos();
        
        
        //action listener botões de ação
        
        botaoJogo.addActionListener(e -> {
        // Verifica se ainda há mais textos para mostrar
        if (indiceTextoAtual < textos.size() - 1) {
            indiceTextoAtual+=1; // Avança para o próximo texto

            // Atualiza o texto na tela
            textoJogo.setText(textos.get(this.indiceTextoAtual));
            System.out.println("dentro do if: "+textos.get(this.indiceTextoAtual));
            
        } else {
            if (  this.fase < 1 || this.fase>2) mostrarTelaPrincipal();
            else  iniciaBatalha();
        }
    });
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 40));
        botao.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(70, 70, 70)); // Cor de fundo que se destaca do preto e do branco
        botao.setFocusPainted(false);
        return botao;
    }
    private JButton botaoEnviar(String texto,int tamanho) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(100, tamanho));
        botao.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(70, 70, 70)); // Cor de fundo que se destaca do preto e do branco
        botao.setFocusPainted(false);
        return botao;
    }


    
    private void iniciarTutorial(){
        
        this.fase = 0;
        
        processarTextosDaHistoria();
        
    }
   
//---------------Créditos------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private void iniciarAnimacaoCreditos() {

    // Pega o painel   // contexto        //Nome  
    cardLayout.show(getContentPane(), "Creditos");

    // Primeiro texto
    // defenindo a fonte                //nome              //negrito 
    textoCreditos.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16));

    //texto em HTML
    textoCreditos.setText("<html><div style='margin: 0 auto; display:block;'><p style='text-align:center; '>Este jogo foi desenvolvido para a disciplina de Informática na Educação do curso de Sistemas para Internet do IF Sertão Campus Salgueiro.</p></div></html>");

    // Primeira parte da animação com efeito de esmaecimento
    new Thread(() -> {
        try {
            Thread.sleep(100);//100 milisegundo

            // Fade in
            for (int i = 0; i <= 100; i++) {
                final float alpha = i / 100f;
                painelCreditos.setForeground(new Color(0, 0, 0, alpha));
                Thread.sleep(20); // Controla a velocidade do fade
            }

            // Muda para o segundo texto
            textoCreditos.setText(
                "<html><p style='text-align:center; margin: 0 auto;'>Desenvolvido por Helder Manoel Sobreira Dos Santos, Walla Nascimento de Sousa, Daniel Antônio Da Silva e Isaac Antonio Alves Souza</p></html>"
            );

            // Segundo fade in
            for (int i = 0; i <= 100; i++) {
                final float alpha = i / 100f;
                painelCreditos.setForeground(new Color(0, 0, 0, alpha));
                Thread.sleep(20);
            }

            Thread.sleep(100); // Espera mais 3 segundos

            // Terceira parte da animação, exibindo o texto grande
            textoCreditos.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.PLAIN, 5)); // Aplica a fonte personalizada
            textoCreditos.setText(
                "<html><pre>                                                                                     \n" +
                "                                              MMMMMMMMMMMM    mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            MMMMMMMMMMMMMMMM  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            MMMMMMMMMMMMMMMM  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            MMMMMMMMMMMMMMMM  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            MMMMMMMMMMMMMMMM  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            MMMMMMMMMMMMMMMM  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                              MMMMMMMMMMMM    mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                                ..MMMM--                                                                                    \n" +
                "                                                                                                                                            \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            ..------------      --------------                                                              \n" +
                "                                                                                                                                            \n" +
                "                                            mmmmmmmmmmmmmmmm  --mmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmmmm                                          \n" +
                "                                            mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmm    mmmmmmmmmmmmmm--                                          \n" +
                "                                                                                                                                            \n" +
                "                                            mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm  mmmmmmmmmmmmmmmm                                                              \n" +
                "                                            mmmmmmmmmmmmmmmm    mmmmmmmmmmmmmm"
                + "</pre></html>");

            // Terceiro fade in
            for (int i = 0; i <= 100; i++) {
                final float alpha = i / 100f;
                painelCreditos.setForeground(new Color(0, 0, 0, alpha));
                Thread.sleep(20);
            }

            Thread.sleep(100); // Espera mais 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mostrarTelaPrincipal(); // Exibe a tela principal no final
        }
    }).start();
}

    protected void mostrarTelaPrincipal() {
        cardLayout.show(getContentPane(), "Principal");
    }

    // tela da História-----------------------------------------------------------------------------------------------
    
     private void iniciarJogo() {
    cardLayout.show(getContentPane(), "Jogo");
    
    this.fase = 1;
       
    processarTextosDaHistoria();
    

    
}
     
     private void processarHistoria(){
         cardLayout.show(getContentPane(), "Jogo");
        
         System.out.println("pre if: "+textos.get(this.indiceTextoAtual));
         System.out.println(textos.size());
        // Define o texto inicial
            textoJogo.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada
            textoJogo.setText(textos.get(indiceTextoAtual));
            
            // Adiciona o listener ao botão "Enviar"
            
   }
   
     
   private String transformarEmHTML(String texto){
       
       
      return "<html><div style='margin: 0 auto; display:block;'><p>"+texto+"</p></div></html>";
       
   }
     
   private void processarTextosDaHistoria(){
       this.indiceTextoAtual = 0;
       this.textos.clear();
       String saida;
       
       // Morreu
       if (this.fase <0){
           saida = transformarEmHTML("Os nosso bravos aventureiros não conseguiram alcançar o seu objetivo, mas não se desespere e tente novamente. ");
           textos.add(saida);
           processarHistoria();
       }
       // Tutorial
       else if (this.fase == 0){
           saida = transformarEmHTML("Quando no turno de um dos seus personagens, atente-se a seus atributos, habilidades e o custo das habilidades, todas essas informações serão exibidas na tela, junto ao nome dos aliadose e dos inimgos na batalha.");
           textos.add(saida);
           saida = transformarEmHTML("Digite sempre os comando em minúsculo e tudo junto, mesmo que o comando tenha mais de uma palavra. EX: cortalaminar. as habilidades tem custos e geralmente são usadas em um alvo. ");
           textos.add(saida);
           saida = transformarEmHTML("Para usar uma habilidade, certifique-se de que o personagem e questão a tenha e possua os requisitos para ativá-la digite o nome da habilidade em minúsculo, sem espaços, e após um espaço, digite o nome do alvo do mesmo jeito. Clique então em ENVIAR. EX: cortelaminar feiticeiro");
           textos.add(saida);
           saida = transformarEmHTML("APENAS APERTE EM ENVIAR PARA USAR UMA HABILIDADE, quando uma descrtição for exibida na tela, clique em PRÓXIMO.");
           textos.add(saida);
           saida = transformarEmHTML("Digite ajuda e clique em enviar para ver este texto de tutorial mais uma vez, no turno do seu personagem.");
           textos.add(saida);
           processarHistoria();
       }
       else if (this.fase==1){
           saida = transformarEmHTML("Três aventureiros se preparam para adentrar a perigosa masmorra antiga, prontos para buscar conhecimentos ancestrais. O robusto guerreiro, trajando uma armadura, o misterioso mago, envolto em um capuz e a valorosa clériga, vestindo robes.");
           textos.add(saida);
           saida = transformarEmHTML("O grupo entra nas ruínas obscuras, observando os grandes salões esculpidos em pedra com a iluminação fraca de uma tocha. Três espectros surgem, tomando a forma de um cavaleiro negro, um necromante e um feiticeiro, compostos de sombras, se posicionando ameaçadoramente.");
           textos.add(saida);
           saida = transformarEmHTML("Conforme os aventureiros se preparam para o combate, uma voz sussurra em suas mentes: ");
           textos.add(saida);
           saida = transformarEmHTML("Voz: \"O conhecimento ancestral guardado por meu povo... apliquem-no para vencer este  desafio... a decomposição... um pilar deste conhecimento que se baseia em dividir o  problema em diferentes partes... a abstração, por outro lado, se baseia em focar no cerne do  problema... Usem este conhecimento...\"");
           textos.add(saida);
           saida = transformarEmHTML("Com o sessar das palavras, a batalha tem início");
           textos.add(saida);
           processarHistoria();
    
       }
       else if (this.fase == 2){
           saida = transformarEmHTML("Após a dura batalha, os aventureiros trocam olhares. A clériga cura a todos enquanto descansam ao redor de uma fogueira, quando a voz retorna:");
           textos.add(saida);
           saida = transformarEmHTML("voz: Muito bem aventureiros, vocês dominaram os quatros sagrados pilares do que meu povo chamou, em sua era de glória, que contempla o pensamento computacional. A decomposição e abstração, entrentanto, precisam ser aplicadas junto outros dois princípios, sendo eles o do reconhecimento de padrões e o da elaboração de algoritmos.");
           textos.add(saida);
           saida = transformarEmHTML("\"O reconhecimento de padrões consiste em identificar os cursos de ação mais eficientes para lidar com o problema definido. O pilar de algoritmo, por sua vez, consiste na execução de passos bem definidos para atingir o objetivo. Apliquem Estes pilares para dominarem o conhecimento antigo do meu povo... Preparem-se para testar esse novo conhecimento... Siga os melhores passos e estratégias para identificar os inimigos... Analise as ações que resultam em danos ao inimigos...\" ");
           textos.add(saida);
           saida = transformarEmHTML("Conforme a voz sessa, novos inimigos surgem, envoltos em aura de escuridão, sendo eles, um um terrível dragão, uma bruxa e uma feiticeira ");
           
           textos.add(saida);
           System.out.println("Chegou no pos combate "+textos);
           processarHistoria();
       }
       
       else if (this.fase > 2){
           saida = transformarEmHTML("O grupo vence a batalha. Os três caminham com dificuldade, rumo a uma luz fraca que surge na escuridão. O guerreiro toma a frente, protetor, mas ao se aproximar, logo nota a saída desta masmorra obscura. Os três saem, observando, juntos, o alvorecer, conforme as trevas dão espaço ao calor e a luz. A voz surge uma última vez, dizendo:");
           textos.add(saida);
           saida = transformarEmHTML("\"parabéns, aventureiros. Que o conhecimento ancestral do meu povo, o pensamento computacional, guie vocês em suas futuras aventuras. Lembre-se, a força da espada pode moldar um império, mas a força do conhecimento é capaz de mudar o mundo.\"");
           textos.add(saida);
           
           processarHistoria();
           
       }
       
   }
     
private void iniciaBatalha() {
    cardLayout.show(getContentPane(), "Batalha");
    textoBatalha.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 15));
    textoBatalha.setText("Que a Batalha Comece!");

    boolean ehFase1 = this.fase == 1;
    this.acabou = false;
    System.out.println("é a fase 1: " + ehFase1);

    Batalha batalha = new Batalha(ehFase1);
    Personagem atual = Iniciativa.getAtual();

    Timer timerBatalha = new Timer(1000, null); // Cria o timer sem ação inicial

    // Listener para o botão "botaoBatalha" (adicionado apenas uma vez)
    if (botaoBatalha.getActionListeners().length == 0) {
        botaoBatalha.addActionListener(e -> {
            if (acabou) {
                processarTextosDaHistoria();
            } else {
                String input = inputBatalha.getText().toLowerCase(); // Processa o input do jogador
                processarInputBatalha(Iniciativa.getAtual().getNome(), input); // Processa a ação do jogador
                //timerBatalha.start(); // Retoma o timer para continuar o loop
            }
        });
    }

    // Listener para o botão "botaoPassarOTurno" (adicionado apenas uma vez)
    if (botaoPassarOTurno.getActionListeners().length == 0) {
        botaoPassarOTurno.addActionListener(e -> {
            if (acabou) {
                processarTextosDaHistoria();
            } else {
                timerBatalha.start(); // Retoma o timer
            }
        });
    }

    // Ação do Timer da batalha
    timerBatalha.addActionListener(e -> {
        Iniciativa.proximo();
        Personagem p = Iniciativa.getAtual();
        System.out.println("Turno de: " + p.getNome() + " | É inimigo? " + p.getEhInimigo());

        Habilidades habilidades = new Habilidades();

        if (!p.getEhInimigo()) {
            // Pausa o timer para esperar a ação do jogador
            timerBatalha.stop(); // Espera o input do jogador no listener do botão

            // Atualiza o texto da batalha com as informações do personagem
            String saida = "<p>Turno do(a) " + p.getNome() + " HP: " + p.getHP() + " ATK: " + p.getATK() + " DEF: " + p.getDEF() + "<br>Habilidades: </p>";
            for (String s : p.getHabilidades()) {
                saida += "<p>" + s + ": " + habilidades.geraDescricao(s) + ".</p><hr>";
            }

            // Mostra os inimigos
            saida += "<p>  Inimigos: ";
            for (Personagem inimigo : CampoDeBatalha.getInimigos()) {
                saida += inimigo.getNome() + " ";
            }
            saida += "</p><br><p>  Aliados: ";
            for (Personagem aliado : CampoDeBatalha.getAliados()) {
                saida += aliado.getNome().toLowerCase() + " ";
            }
            saida += "</p>";

            textoBatalha.setText("<html>" + saida + "</html>");

        } else {
            // IA do inimigo age
            IaDoInimigo agir = new IaDoInimigo(p);
            textoBatalha.setText(transformarEmHTML(agir.getMensagem()));
            timerBatalha.stop(); // Aguarda próximo turno
        }

        // Verifica se a batalha terminou
        if (CampoDeBatalha.getInimigos().isEmpty()) {
            timerBatalha.stop();
            textoBatalha.setText("A batalha terminou!");
            CampoDeBatalha.getAliados().clear();
            Iniciativa.getOrdem().clear();
            this.fase++;
            this.acabou = true;
            processarTextosDaHistoria(); // Avança para o próximo fluxo da história
        } else if (CampoDeBatalha.getAliados().isEmpty()) {
            timerBatalha.stop();
            textoBatalha.setText("A batalha terminou!");
            this.fase = -1;
            this.acabou = true;
            processarTextosDaHistoria(); // Reinicia história ao perder
        }
    });

    // Inicia o timer da batalha
    timerBatalha.start();
}

// Processa o input do jogador
private void processarInputBatalha(String nome,String input) {
    System.out.println("Nome do agente: "+nome);
    
    switch(input){
        case "sair" : System.exit(0); break;
        default: try {
            String saida = nome.toLowerCase() + " "+input;
            System.out.println("entrada para o interpretador: "+saida);
        Interpretador inter = new Interpretador(saida);
        System.out.println("saida do interpretador: "+inter.retornaMensagem());
        textoBatalha.setText(transformarEmHTML(inter.retornaMensagem()));
    } catch (Exception e) {
        System.out.println("Erro ao processar input: " + e.getMessage());
    }
    }
   
}
    

               
    private Font obterFonte(String nomeFonte, int estilo, int tamanho) {
        try {
            // Tenta carregar a fonte personalizada
            InputStream fonteStream = getClass().getClassLoader().getResourceAsStream("resources/" + nomeFonte + ".ttf");
            if (fonteStream == null) {
                throw new IOException("Fonte não encontrada");
            }
            Font fonte = Font.createFont(Font.TRUETYPE_FONT, fonteStream);
            return fonte.deriveFont(estilo, tamanho);
        } catch (Exception e) {
            e.printStackTrace();
            // Caso a fonte não possa ser carregada, usa a fonte padrão
            return new Font("Sans Serif", estilo, tamanho);
        }
    }

    private void carregarFontePersonalizada() {
        try {
            InputStream fonteStream = getClass().getClassLoader().getResourceAsStream("resources/VCR_OSD_MONO_1.001.ttf");
            if (fonteStream == null) {
                throw new IOException("Fonte não encontrada");
            }
            Font fonte = Font.createFont(Font.TRUETYPE_FONT, fonteStream);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(fonte.deriveFont(16f)); // Registra a fonte com um tamanho padrão
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Jogo::new);
        
    }
}
