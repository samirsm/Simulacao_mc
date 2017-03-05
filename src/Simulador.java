import java.util.Random;

import javax.swing.SingleSelectionModel;

public class Simulador {

  public static void main(String[] args) {
    
    
    Host hostFF = new Host(new Maquina(0.45, 0.45), 13000);
    int requisicoes = 40000;
    int reqAceitas = 0;
    int reqNegadas = 0;
    
    for (int i = 0; i < requisicoes; i++) {
      Random r = new Random();
      double randomValue = 0.1 + (0.5 - 0.1) * r.nextDouble();
      if(hostFF.aloqueFF(new Maquina(randomValue,randomValue ))){
        reqAceitas ++;
      }else{
        reqNegadas ++;
      }
      
    }
    
    System.out.println("requisicoes negadas:" + reqNegadas);
    System.out.println("Fragmentacao: " + hostFF.calcularFrag());

  }
  
  

}
