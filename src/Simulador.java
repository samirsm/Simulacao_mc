import java.util.Random;

import javax.swing.SingleSelectionModel;

public class Simulador {

  public static void main(String[] args) {
    
    
    //configurações do host
    int numeroDemaquinas = 20;
    double cpuHost = 0.9;
    double memoHost = 0.9;
    
    //configurações das VMs
    double confMinima = 0.1;
    double confMaxima = 0.9;
    
    int requisicoes = 100000;
    
    int reqAceitasFF = 0;
    int reqNegadasFF = 0;

    int reqAceitasBF = 0;
    int reqNegadasBF = 0;

    int reqAceitasRF = 0;
    int reqNegadasRF = 0;
    
    
    Host hostFF = new Host(new Maquina(cpuHost, memoHost), numeroDemaquinas);
    Host hostBF = new Host(new Maquina(cpuHost, memoHost), numeroDemaquinas);
    Host hostRF = new Host(new Maquina(cpuHost, memoHost), numeroDemaquinas);

    

    

    for (int i = 0; i < requisicoes; i++) {
      Random r = new Random();
      double randomValue = confMinima + (confMaxima - confMinima) * r.nextDouble();
      if(hostFF.aloqueFF(new Maquina(i, randomValue,randomValue ))){
        reqAceitasFF ++;
      }else{
        reqNegadasFF ++;
      }

      if(hostBF.aloqueBF(new Maquina(i, randomValue,randomValue ))){
        reqAceitasBF ++;
      }else{
        reqNegadasBF ++;
      }

      if(hostRF.aloqueRF(new Maquina(i, randomValue,randomValue ))){
        reqAceitasRF ++;
      }else{
        reqNegadasRF ++;
      }
      
    }
    
    System.out.println("requisicoes negadas pelo código Fist Fit:" + reqNegadasFF);
    System.out.println("Fragmentacao do código First Fit: " + hostFF.calcularFrag());
    for (Maquina mq : hostFF.getMaquinas()) {
      String show = "";
          for (Maquina mv : mq.getMaquinas()) {
            show += mv.getRequisicao() + "-" + mv.getCpu() + ", ";
          }
      System.out.println(show);
    }

    System.out.println("********************************************************************");
    System.out.println("requisicoes negadas pelo código Best Fit:" + reqNegadasBF);
    System.out.println("Fragmentacao do código Best Fit: " + hostBF.calcularFrag());
    for (Maquina mq : hostBF.getMaquinas()) {
      String show = "";
      for (Maquina mv : mq.getMaquinas()) {
        show += mv.getRequisicao() + "-" + mv.getCpu() + ", ";
      }
      System.out.println(show);
    }

    System.out.println("********************************************************************");

    System.out.println("requisicoes negadas pelo código Random Fit:" + reqNegadasRF);
    System.out.println("Fragmentacao do código Random Fit: " + hostRF.calcularFrag());
    
    for (Maquina mq : hostRF.getMaquinas()) {
      String show = "";
      for (Maquina mv : mq.getMaquinas()) {
        show += mv.getRequisicao() + "-" + mv.getCpu() + ", ";
      }
      System.out.println(show);
    }

    System.out.println("********************************************************************");
    

  }
  
  

}
