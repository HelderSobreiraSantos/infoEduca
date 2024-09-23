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

    private JPanel painelPrincipal;
    private JPanel painelCreditos;
    private JPanel painelJogo;
    private JPanel painelBatalha;
    private CardLayout cardLayout;
    private JLabel textoCreditos;
    private JLabel textoJogo, textoBatalha;
    private JTextField inputBatalha;
    private JButton botaoJogo,botaoBatalha;
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
        this.botaoJogo = criarBotao("Próximo");
        this.botaoBatalha = botaoEnviar("Enviar");
        // Adicionando os botões ao painel em orientação vertical
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 1, 10, 10)); // Grade com 3 linhas e espaçamento de 10px
        painelBotoes.setBackground(Color.BLACK);
        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoCreditos);
        painelBotoes.add(botaoSair);

        // Criando um painel para centralizar os botões horizontalmente
        JPanel painelCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelCentral.setBackground(Color.BLACK);
        painelCentral.add(painelBotoes);

        painelPrincipal.add(painelCentral, BorderLayout.CENTER);

        // Adicionando ações aos botões
        botaoIniciar.addActionListener(e -> iniciarJogo());
        botaoCreditos.addActionListener(e -> iniciarAnimacaoCreditos());
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
        inputBatalha = new JTextField(20);
        painelBatalha = new JPanel();
        painelBatalha.setLayout(new BorderLayout());
        painelBatalha.setBackground(Color.BLACK);
        
        textoBatalha = new JLabel("", SwingConstants.CENTER);
        textoBatalha.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada inicialmente
        textoBatalha.setForeground(Color.WHITE);
        painelBatalha.add(textoBatalha, BorderLayout.CENTER);
        JPanel div2 = new JPanel();
        div2.setLayout(new FlowLayout());
        div2.add(inputBatalha);
        div2.add(botaoBatalha);
        div2.setBackground(Color.BLACK);
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
    private JButton botaoEnviar(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(100, 40));
        botao.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada
        botao.setForeground(Color.WHITE);
        botao.setBackground(new Color(70, 70, 70)); // Cor de fundo que se destaca do preto e do branco
        botao.setFocusPainted(false);
        return botao;
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
    
       
    processarTextosDaHistoria();
    

    
}
     
     private void processarHistoria(){
         cardLayout.show(getContentPane(), "Jogo");
        
         System.out.println(textos.get(this.indiceTextoAtual));
         System.out.println(textos.size());
        // Define o texto inicial
            textoJogo.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada
            textoJogo.setText(textos.get(indiceTextoAtual));
            
            // Adiciona o listener ao botão "Enviar"
            botaoJogo.addActionListener(e -> {
        // Verifica se ainda há mais textos para mostrar
        if (indiceTextoAtual < textos.size() - 1) {
            indiceTextoAtual+=1; // Avança para o próximo texto

            // Atualiza o texto na tela
            textoJogo.setText(textos.get(this.indiceTextoAtual));
            System.out.println(textos.get(this.indiceTextoAtual));
            
        } else {
            if (this.fase>2) mostrarTelaPrincipal();
            else iniciaBatalha();
        }
    });
   }
   
     
   private String transformarEmHTML(String texto){
       
       
      return "<html><div style='margin: 0 auto; display:block;'><p>"+texto+"</p></div></html>";
       
   }
     
   private void processarTextosDaHistoria(){
       this.indiceTextoAtual = 0;
       this.textos.clear();
       String saida;
       if (this.fase==1){
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
    textoBatalha.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16));
    textoBatalha.setText("Que a Batalha Comece!");
    
    boolean ehFase1 = this.fase==1;
    System.out.println("é a fase 1: "+ehFase1);
    
    Batalha batalha = new Batalha(ehFase1);
    Personagem atual = Iniciativa.getAtual();

    Timer timerBatalha = new Timer(1000, null); // Cria o timer sem ação inicial

    botaoBatalha.addActionListener(e -> {
        String input = inputBatalha.getText().toLowerCase(); // Processa o input do jogador
        processarInputBatalha(Iniciativa.getAtual().getNome(), input); // Processa a ação do jogador

        // Após processar o input do jogador, retoma o timer para continuar o loop
        timerBatalha.start();
    });
    
    textoBatalha.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // Retoma o turno após o jogador clicar no texto
            timerBatalha.start();
        }
    });    
    
    timerBatalha.addActionListener(e -> {
        Iniciativa.proximo();
        Personagem p = Iniciativa.getAtual();
        System.out.println("Turno de: " + p.getNome() + " | Eh inimigo? " + p.getEhInimigo());

        Habilidades habilidades = new Habilidades();
        // Verifica se é o turno do jogador
        if (!Iniciativa.getAtual().getEhInimigo()) {
            // Pausa o timer para esperar a ação do jogador
            timerBatalha.stop();  // Espera o input do jogador no listener do botão


            String saida="<p>Turna do(a) "+p.getNome()  + " HP: "+ p.getHP() + " ATK: " + p.getATK() + " DEF: "+p.getDEF() + "<br>Habilidades: </p>";
           
            for (String s : p.getHabilidades()){
                saida+= "<p>" + s + ": " +habilidades.geraDescricao(s)+".</p><hr>";
            }
            
            saida += "<p>  Inimigos: ";
            for (Personagem inimigo : CampoDeBatalha.getInimigos()){
                saida += ""+inimigo.getNome()+" ";
            }
            saida += "</p>";
            
            textoBatalha.setText("<html>"+saida+"</html>");
        } else {
            // IA do inimigo age
            IaDoInimigo agir = new IaDoInimigo(Iniciativa.getAtual());
            textoBatalha.setText("<html><p>" + agir.getMensagem() + "</p></html>");
            timerBatalha.stop();
        }

        // Verifica se a batalha acabou
        if (CampoDeBatalha.getAliados().isEmpty() || CampoDeBatalha.getInimigos().isEmpty()) {
            ((Timer) e.getSource()).stop();  // Para o timer quando a batalha termina
            textoBatalha.setText("A batalha terminou!");
            
            

            this.fase ++;
            processarTextosDaHistoria();
            // Retorna para o fluxo de história
        }
    });

    timerBatalha.start(); // Inicia o timer para processar a batalha
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