package trabalhoorientacaoobjetos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataManager {

    List<Revisao> reviews = new ArrayList<Revisao>();
    HashMap<String, Produto> products = new HashMap<String, Produto>();
    HashMap<String, Usuario> users = new HashMap<String, Usuario>();
    
    public DataManager() {
        try {
            Leitor leitor = new Leitor("Arts.txt");
            String[] reviewAtual = leitor.leProximoReview();
            
            String produtoId = reviewAtual[0].substring(reviewAtual[0].indexOf(':') + 2);
            String titulo = reviewAtual[1].substring(reviewAtual[1].indexOf(':') + 2);
            String precoAux = reviewAtual[2].substring(reviewAtual[2].indexOf(':') + 2);
            double preco;
            if(precoAux.equals("unknown"))
                preco = Produto.PRECO_NAO_IDENTIFICADO;
            else
                preco = Double.parseDouble(precoAux);
            Produto produtoAtual = new Produto(produtoId, titulo, preco);
            products.put(produtoId, produtoAtual);
            
            String userId = reviewAtual[3].substring(reviewAtual[3].indexOf(':') + 2);
            String profileName = reviewAtual[4].substring(reviewAtual[4].indexOf(':') + 2);
            Usuario usuarioAtual = new Usuario(userId, profileName);
            users.put(userId, usuarioAtual);
            
            double pontuacao = Double.parseDouble(reviewAtual[6].substring(reviewAtual[6].indexOf(':') + 2));
            long time = Long.parseLong(reviewAtual[7].substring(reviewAtual[7].indexOf(':') + 2));
            String sumario = reviewAtual[8].substring(reviewAtual[8].indexOf(':') + 2);
            String texto = reviewAtual[9].substring(reviewAtual[9].indexOf(':') + 2);
            Revisao review = new Revisao(pontuacao, time, sumario, texto, produtoId, userId);
            reviews.add(review);
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
