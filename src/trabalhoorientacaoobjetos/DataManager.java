package trabalhoorientacaoobjetos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.AbstractIntervalXYDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class DataManager {

    private List<Revisao> reviews = new ArrayList<Revisao>();
    private HashMap<String, Produto> products = new HashMap<String, Produto>();
    private HashMap<String, Usuario> users = new HashMap<String, Usuario>();
    private HashMap<Integer, Integer> histogramaQuestao7 = new HashMap<Integer, Integer>();
    
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

                double pontuacao = Double.parseDouble(reviewAtual[6].substring(reviewAtual[6].indexOf(':') + 2));
                long time = Long.parseLong(reviewAtual[7].substring(reviewAtual[7].indexOf(':') + 2));
                String sumario = reviewAtual[8].substring(reviewAtual[8].indexOf(':') + 2);
                String texto = reviewAtual[9].substring(reviewAtual[9].indexOf(':') + 2);
                Revisao review = new Revisao(pontuacao, time, sumario, texto, produtoId, userId);
                reviews.add(review);
                
                produtoAtual.addRevisao(review);
                usuarioAtual.addRevisao(review);
            }
            JOptionPane.showMessageDialog(null, 
                "Reviews: " + reviews.size() + "\n" +
                "Users: " + users.size() + "\n" +
                "Products: " + products.size());
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Integer> calculaQuestao7() throws IOException {
        //usuario de teste. Sabemos que ele tem 14 reviews
        //Usuario testUser = users.get("AJGU56YG8G1DQ");
        //testUser = null;
        
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
        
        List minhaList = new ArrayList(histogramaQuestao7.keySet());
        Collections.sort(minhaList);
        //A partir daqui ja temos os dados prontos para montar o histograma
        //So entender a biblioteca e passar os dados para ela.
        
        
        double[] value = new double[100];
        Random generator = new Random();
        for (int i=1; i < 100; i++) {
            value[i] = generator.nextDouble();
            int number = 10;
            HistogramDataset dataset = new HistogramDataset();
            dataset.setType(HistogramType.RELATIVE_FREQUENCY);
            dataset.addSeries("Histogram",value,number);
            String plotTitle = "Histogram";
            String xaxis = "number";
            String yaxis = "value";
            PlotOrientation orientation = PlotOrientation.VERTICAL;
            boolean show = false;
            boolean toolTips = false;
            boolean urls = false;
            JFreeChart chart = ChartFactory.createHistogram( plotTitle, xaxis, yaxis,
            dataset, orientation, show, toolTips, urls);
            int width = 500;
            int height = 300;
            try {
                ChartUtilities.saveChartAsPNG(new File("histogram.PNG"), chart, width, height);
            } 
            catch (IOException e) {
            }
        }
        
        return null;
    } 
}
