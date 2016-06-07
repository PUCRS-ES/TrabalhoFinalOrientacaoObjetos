package trabalhoorientacaoobjetos;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import static java.time.temporal.ChronoUnit.MONTHS;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DataManager {

    private List<Revisao> reviews = new ArrayList<Revisao>();
    private HashMap<String, Produto> products = new HashMap<String, Produto>();
    private HashMap<String, Usuario> users = new HashMap<String, Usuario>();
    private List<Produto> produtos = new ArrayList<Produto>();
    
    public DataManager() {
        try {
            Leitor leitor = new Leitor("Arts.txt");
            while(reviews.size() < 27980) {
                String[] reviewAtual = leitor.leProximoReview();

                String produtoId = reviewAtual[0].substring(reviewAtual[0].indexOf(':') + 2);
                String titulo = reviewAtual[1].substring(reviewAtual[1].indexOf(':') + 2);
                String precoAux = reviewAtual[2].substring(reviewAtual[2].indexOf(':') + 2);
                double preco;
                if(precoAux.equals("unknown"))
                    preco = Produto.PRECO_NAO_IDENTIFICADO;
                else
                    preco = Double.parseDouble(precoAux);
                Produto produtoAtual;
                if(!products.containsKey(produtoId)) {
                    produtoAtual = new Produto(produtoId, titulo, preco);
                    products.put(produtoId, produtoAtual);
                }
                else
                    produtoAtual = products.get(produtoId);

                String userId = reviewAtual[3].substring(reviewAtual[3].indexOf(':') + 2);
                String profileName = reviewAtual[4].substring(reviewAtual[4].indexOf(':') + 2);
                Usuario usuarioAtual;
                if(!users.containsKey(userId)) {
                    usuarioAtual = new Usuario(userId, profileName);
                    users.put(userId, usuarioAtual);
                }
                else
                    usuarioAtual = users.get(userId);

                String[] utilidade = reviewAtual[5].substring(reviewAtual[5].indexOf(':') + 2).split("/");
                int utilidadePositiva = Integer.parseInt(utilidade[0]);
                int utilidadeTotal = Integer.parseInt(utilidade[1]);
                double pontuacao = Double.parseDouble(reviewAtual[6].substring(reviewAtual[6].indexOf(':') + 2));
                long time = Long.parseLong(reviewAtual[7].substring(reviewAtual[7].indexOf(':') + 2));
                String sumario = reviewAtual[8].substring(reviewAtual[8].indexOf(':') + 2);
                String texto = reviewAtual[9].substring(reviewAtual[9].indexOf(':') + 2);
                Revisao review = new Revisao(pontuacao, time, sumario, texto, produtoId, userId, utilidadePositiva, utilidadeTotal);
                reviews.add(review);
                
                produtoAtual.addRevisao(review);
                usuarioAtual.addRevisao(review);
            }
//            JOptionPane.showMessageDialog(null, 
//                "Reviews: " + reviews.size() + "\n" +
//                "Users: " + users.size() + "\n" +
//                "Products: " + products.size());
//            calculaQuestao7();
//            calculaQuestao6();
//            calculaQuestao4();
//            calculaQuestao5();
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void calculaQuestao5() {
        List<Usuario> usuariosComAvaliacoesMaisUteis = new ArrayList<Usuario>();
        for (String codUsuario : users.keySet()) {
            Usuario userAux = users.get(codUsuario);
            usuariosComAvaliacoesMaisUteis.add(userAux);
            userAux.calculaMediaDasAvaliacoesDoUsuario();
        }
        usuariosComAvaliacoesMaisUteis.sort(
            Comparator.comparing((Usuario u1) -> u1.getMediaDasAvaliacoesDoUsuario()).reversed()
        );
        usuariosComAvaliacoesMaisUteis.subList(0, 20);
    }
    
    public void calculaQuestao4() {
        List<Produto> produtosMaisBemAvaliados = new ArrayList<Produto>();
        for (Produto produto : products.values()) {
            if(produto.getQuantidadeRevisoes() > 10) {
                produto.calculaMediaDasAvaliacoes();
                produtosMaisBemAvaliados.add(produto);
            }
        }
        produtosMaisBemAvaliados.sort(
            Comparator.comparing((Produto p1) -> p1.getMediaDasAvaliacoes()).reversed()
        );
        produtosMaisBemAvaliados.subList(0, 20);
    }
    
    public void calculaQuestao6() {
        HashMap<Long, Integer> histogramaQuestao6 = new HashMap<Long, Integer>();
        
        for (Revisao rev : reviews) {
            long valor = rev.getTime();
            LocalDateTime timestamp = LocalDateTime.ofInstant(Instant.ofEpochSecond(valor), ZoneOffset.UTC);
            
            //String key = timestamp.getYear() + "/" + String.format("%02d", timestamp.getMonthValue());
            LocalDate primeiroDiaDoMes = LocalDate.of(timestamp.getYear(), timestamp.getMonthValue(), 1);
            Long key = primeiroDiaDoMes.atStartOfDay().atZone(ZoneOffset.UTC).toEpochSecond();
            
            Integer quantAux = histogramaQuestao6.get(key);
            if (quantAux == null)
                histogramaQuestao6.put(key, 1);
            else
                histogramaQuestao6.put(key, quantAux + 1);
        }
        List<Long> minhaList = new ArrayList(histogramaQuestao6.keySet());
        Collections.sort(minhaList);
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Distribuição do número de avaliaçoes por mês");

        for (int i = 0; i < minhaList.size(); i++)
            series1.add(i, histogramaQuestao6.get(minhaList.get(i)));
        dataset.addSeries(series1);
        final JFreeChart chart = ChartFactory.createXYBarChart(
            "",
            "Meses",
            false,
            "Quantidade de reviews",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDomainZoomable(true);
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(chartPanel);

        JFrame frame = new JFrame();
        frame.add(jPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void calculaQuestao7() {
        //usuario de teste. Sabemos que ele tem 14 reviews
        //Usuario testUser = users.get("AJGU56YG8G1DQ");
        //testUser = null;
        HashMap<Integer, Integer> histogramaQuestao7 = new HashMap<Integer, Integer>();
        
        for(Usuario usuarioAtual: users.values()) {
            int quantidadeRevisoesDoUsuario = usuarioAtual.getQuantidadeRevisoes();
            
            //verifica se ja temos algum contador para essa quantidade de reviews
            //Se ja existir, apenas incrementamos ele.
            Integer quantAux = histogramaQuestao7.get(quantidadeRevisoesDoUsuario);
            if(quantAux == null)
                histogramaQuestao7.put(quantidadeRevisoesDoUsuario, 1);
            else
                histogramaQuestao7.put(quantidadeRevisoesDoUsuario, quantAux + 1);
        }
        
        List<Integer> minhaList = new ArrayList(histogramaQuestao7.keySet());
        Collections.sort(minhaList);
        
        //temporariamente estou alterando o histograma para ele ficar melhor
        //minhaList.remove(minhaList.size() - 1);
        //histogramaQuestao7.put(1, 500);
        //histogramaQuestao7.put(2, 300);
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Distribuição do número de usuários pela quantidade de avaliações produzidas");

        for (int i = 0; i < minhaList.size(); i++)
            series1.add(minhaList.get(i), histogramaQuestao7.get(minhaList.get(i)));
        dataset.addSeries(series1);
        final JFreeChart chart = ChartFactory.createXYBarChart(
            "",
            "Quantidade de reviews",
            false,
            "Quantidade de usuários",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setDomainZoomable(true);
        
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.add(chartPanel);

        JFrame frame = new JFrame();
        frame.add(jPanel);
        frame.pack();
        frame.setVisible(true);
    } 
    
    public Produto consultaProdutoPorId(String id){
        
        if(products.containsKey(id)){
            return products.get(id);
        }
        else {
            System.out.print("ID inesistente");
            return null;
        }        
        
    }   
    
    public List<Produto> consultaProdutoPorNome(String nome){
        
        for (String codigoProduto : products.keySet()){
            Produto produtoAtual = products.get(codigoProduto);
            String linha = produtoAtual.getTitulo();
            if(linha.toLowerCase().contains(nome.toLowerCase())){
                produtos.add(produtoAtual);
            }
        }
        return produtos;
    }
    
    
    public Usuario consultaUsuarioPorId(String id)
    {
        for(String x: users.keySet()){
            Usuario usu = users.get(x);
            if(usu != null)return usu;
        }
        System.out.println("Id de usuário inexistente");
        return null;
    }
    
    public Usuario consultaUsuarioPorNome(String nome){
        for(String x: users.keySet()){
            Usuario usu = users.get(x);
            if(usu.getNomeUsuario().indexOf(nome) != -1)return usu;
        }
        System.out.println("Nome de usuário inexistente");
        return null;
    }
    
    public List<Revisao> calculaQuestao3(String avaliacao){
        List<Revisao> listaAtual = new ArrayList<>();
        for (Revisao rev : reviews){            
            String linha = rev.getTexto();
            if(linha.contains(avaliacao)){
                listaAtual.add(rev);
            }
        }
        return listaAtual;
    }
    
}
