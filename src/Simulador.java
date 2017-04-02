import java.util.Random;

import javax.swing.SingleSelectionModel;

public class Simulador {

  public static void main(String[] args) {
    
    
    //configurações do host pequeno
    int numeroDemaquinas_hostP = 3;
    double cpuHost_hostP = 0.9;
    double memoHost_hostP = 0.9;
    
  //configurações do host grande
    int numeroDemaquinas_hostG = 3;
    double cpuHost_hostG = 0.9;
    double memoHost_hostG = 0.9;
    
    
    //configurações das VMs
    double confMinima = 0.1;
    double confMaxima = 0.9;
    
    int requisicoes = 10000;
    
    // Hosts pequenos
    int reqAceitasFF_hostP = 0;
    int reqNegadasFF_hostP = 0;

    int reqAceitasBF_hostP = 0;
    int reqNegadasBF_hostP = 0;

    int reqAceitasRF_hostP = 0;
    int reqNegadasRF_hostP = 0;
    
    // Hosts grandes
    int reqAceitasFF_hostG = 0;
    int reqNegadasFF_hostG = 0;

    int reqAceitasBF_hostG = 0;
    int reqNegadasBF_hostG = 0;

    int reqAceitasRF_hostG = 0;
    int reqNegadasRF_hostG = 0;

    
    Host hostFF_hostP = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
    Host hostBF_hostP = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
    Host hostRF_hostP = new Host(new Maquina(cpuHost_hostP, memoHost_hostP), numeroDemaquinas_hostP);
    
    Host hostFF_hostG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
    Host hostBF_hostG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
    Host hostRF_hostG = new Host(new Maquina(cpuHost_hostG, memoHost_hostG), numeroDemaquinas_hostG);
    
    
	//Requisições
    for (int i = 0; i < requisicoes; i++) {

	/* Para cada requisição é gerada uma configuração (memória e CPU) de VM randômica entre os intervalos da menor configuração possível
  	e a maior configuração possível, a mesma VM será alocada nos diferentes hosts com os diferentes algoritmos de alocação */
      Random r = new Random();
      double randomValue = confMinima + (confMaxima - confMinima) * r.nextDouble();

      Maquina maquina = new Maquina(randomValue,randomValue);
      // Maquinas com mesma configuração para poder avaliar os diferentes hosts e algoritmos
     
      
      //Hosts pequenos      
      if(hostFF_hostP.aloqueFF(maquina)){
        reqAceitasFF_hostP ++;
      }else{
        reqNegadasFF_hostP ++;
      }

      if(hostBF_hostP.aloqueBF(maquina)){
        reqAceitasBF_hostP ++;
      }else{
        reqNegadasBF_hostP ++;
      }

      if(hostRF_hostP.aloqueRF(maquina)){
        reqAceitasRF_hostP ++;
      }else{
        reqNegadasRF_hostP ++;
      }
      
      // Hosts grandes
      if(hostFF_hostG.aloqueFF(maquina)){
          reqAceitasFF_hostG ++;
        }else{
          reqNegadasFF_hostG ++;
        }

        if(hostBF_hostG.aloqueBF(maquina)){
          reqAceitasBF_hostG ++;
        }else{
          reqNegadasBF_hostG ++;
        }

        if(hostRF_hostG.aloqueRF(maquina)){
          reqAceitasRF_hostG ++;
        }else{
          reqNegadasRF_hostG ++;
        }
      
    }
    
    // Hosts pequenos
    
    System.out.println("Host pequeno");
    System.out.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostP);
    System.out.println("Fragmentacao do código First Fit: " + hostFF_hostP.calcularFrag());

    System.out.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostP);
    System.out.println("Fragmentacao do código Best Fit: " + hostBF_hostP.calcularFrag());

    System.out.println("requisicoes negadas pelo código Random Fit: " + reqNegadasRF_hostP);
    System.out.println("Fragmentacao do código Random Fit: " + hostRF_hostP.calcularFrag());

    System.out.println("-------------------------------------------------------");

    // Hosts grandes
    System.out.println("Host grande");
    System.out.println("requisicoes negadas pelo código Fist Fit: " + reqNegadasFF_hostG);
    System.out.println("Fragmentacao do código First Fit: " + hostFF_hostG.calcularFrag());

    System.out.println("requisicoes negadas pelo código Best Fit: " + reqNegadasBF_hostG);
    System.out.println("Fragmentacao do código Best Fit: " + hostBF_hostG.calcularFrag());

    System.out.println("requisicoes negadas pelo código Random Fit: " + reqNegadasRF_hostG);
    System.out.println("Fragmentacao do código Random Fit: " + hostRF_hostG.calcularFrag());
  
  }
  
  

}
