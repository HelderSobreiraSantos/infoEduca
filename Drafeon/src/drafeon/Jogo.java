package drafeon;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;

public class Jogo extends JFrame {

    private JPanel painelPrincipal;
    private JPanel painelCreditos;
    private CardLayout cardLayout;
    private JLabel textoCreditos;

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
        botaoIniciar.addActionListener(e -> JOptionPane.showMessageDialog(null, "Jogo Iniciado!"));
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

        // Adicionando Painéis ao CardLayout
        add(painelPrincipal, "Principal");
        add(painelCreditos, "Creditos");

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

    private void iniciarAnimacaoCreditos() {
        cardLayout.show(getContentPane(), "Creditos");

        // Primeiro texto
        textoCreditos.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.BOLD, 16)); // Aplica a fonte personalizada inicialmente
       
        textoCreditos.setText("<html><div style='margin: 0 auto; display:block;'><p style='text-align:center; '>Este jogo foi desenvolvido para a disciplina de Informática na Educação do curso de Sistemas para Internet do IF Sertão Campus Salgueiro.</p></div></html>");

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(3000); // Espera 3 segundos
                for (int i = 0; i <= 100; i++) {
                    final float alpha = i / 100f;
                    SwingUtilities.invokeLater(() -> painelCreditos.setForeground(new Color(0, 0, 0, alpha)));
                    Thread.sleep(20);
                }
                textoCreditos.setText("<html><p style='text-align:center; margin: 0 auto;'>Desenvolvido por Helder Manoel Sobreira Dos Santos, Walla Nascimento de Sousa, Daniel Antônio Da Silva e Isaac Antonio Alves Souza</p></html>");
                for (int i = 0; i <= 100; i++) {
                    final float alpha = i / 100f;
                    SwingUtilities.invokeLater(() -> painelCreditos.setForeground(new Color(0, 0, 0, alpha)));
                    Thread.sleep(20);
                }
                Thread.sleep(3000); // Espera mais 3 segundos
                for (int i = 0; i <= 100; i++) {
                    final float alpha = i / 100f;
                    SwingUtilities.invokeLater(() -> painelCreditos.setForeground(new Color(0, 0, 0, alpha)));
                    Thread.sleep(20);
                }
                textoCreditos.setFont(obterFonte("VCR_OSD_MONO_1.001", Font.PLAIN, 5)); // Aplica a fonte personalizada inicialmente
       
                textoCreditos.setText("<html><pre>                                                                                     \n" +
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
                for (int i = 0; i <= 100; i++) {
                    final float alpha = i / 100f;
                    SwingUtilities.invokeLater(() -> painelCreditos.setForeground(new Color(0, 0, 0, alpha)));
                    Thread.sleep(20);
                }
                Thread.sleep(1500); // Espera mais 3 segundos
                return null;
            }

            @Override
            protected void done() {
                mostrarTelaPrincipal();
            }
        }.execute();
    }

    private void mostrarTelaPrincipal() {
        cardLayout.show(getContentPane(), "Principal");
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
        new Jogo();
    }
}
