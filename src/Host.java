import java.util.Random;

public class Host {

  private Maquina mqPadrao;
  private int numeroDeMaquinas;
  private Maquina[] maquinas;



  public Host(Maquina mqPadrao, int numeroDeMaquinas) {
    super();
    this.mqPadrao = mqPadrao;
    this.numeroDeMaquinas = numeroDeMaquinas;
    maquinas = inicializaMaquinas(mqPadrao);
  }

/*Inicializa máquinas do Host com a configuração (memória e CPU) padrão passada no construtor */
  private Maquina[] inicializaMaquinas(Maquina mqPadrao) {
    Maquina[] maquinas = new Maquina[numeroDeMaquinas];
    for (int i = 0; i < maquinas.length; i++) {
      maquinas[i] = new Maquina(mqPadrao.getCpu(), mqPadrao.getMemo());
    }
    return maquinas;
  }

/* Método para alocação de máquina utilizando o algoritmo First Fit, onde o código percorre toda a lista 
de máquinas de host até que encontre a primeira máquina de host que caiba (mémoria e CPU) a VM solicitada */
  public boolean aloqueFF(Maquina maquina) {
    int mqIndex = 0;
    Maquina mqAtaul;
    while (mqIndex < numeroDeMaquinas) {
      mqAtaul = maquinas[mqIndex];
      if (mqAtaul.alocar(maquina)) {
        return true;
      } else {
        mqIndex++;
      }
    }
    return false;
  }


/* Método para alocação de máquina utilizando o algoritmo Best Fit, onde o código percorre toda a lista de máquinas 
de host para encontrar a máquina de host que caiba (mémoria e CPU) a VM solicitada com menor fragmentação*/

  public boolean aloqueBF(Maquina maquina) {

    final int MEMO = 0;
    final int CPU = 1;

	/* A melhor alocação será na máquinde host onde se pode minimizar a fragmentação */
    double[] melhorAlocacao = new double[2];
    melhorAlocacao[MEMO] = Integer.MAX_VALUE;
    melhorAlocacao[CPU] = Integer.MAX_VALUE;
    int bestFitIndex = -1;

    for (int i = 0; i < numeroDeMaquinas; i++) {
	/*Verifica se a VM solicitada cabe a máquina atual do host que está sendo visitada */
      if ((maquinas[i].getMemoRestante() >= maquina.getMemo())
          && (maquinas[i].getCpuRestante() >= maquina.getCpu())) {
	
	/* Se a fragmentação da máquina de host atual for menor que a fragmentação da máquina 
	com melhor alocação até o momento, atualiza-se os valores de melhor alocação e índice */
        if (((maquinas[i].getMemoRestante() - maquina.getMemo()) < melhorAlocacao[MEMO])
            && ((maquinas[i].getCpuRestante() - maquina.getCpu()) < melhorAlocacao[CPU])) {
          melhorAlocacao[MEMO] = maquinas[i].getMemoRestante() - maquina.getMemo();
          melhorAlocacao[CPU] = maquinas[i].getCpuRestante() - maquina.getCpu();
          bestFitIndex = i;
        }
      }
    }

    /* Caso o índice da melhor alocação não seja diferente de -1 não houve alocação */
    if (bestFitIndex != -1) {
      return maquinas[bestFitIndex].alocar(maquina);
    } else {
      return false;
    }
  }

/* Método para alocação de máquina utilizando o algoritmo Random Fit, 
onde o código acessa aleatoriamente uma máquina de host e verifica se a VM pode ser alocada nela*/

  public boolean aloqueRF(Maquina maquina) {
    Random rn = new Random();
    int randomIndex = rn.nextInt(numeroDeMaquinas) + 0;
    if (maquinas[randomIndex].alocar(maquina)) {
      return true;
    }

    return false;
  }

/* Calcular a fragmentação total do host */
  public double calcularFrag() {
    double frag = 0;
    for (int i = 0; i < maquinas.length; i++) {
      frag += maquinas[i].getFragCpu();
    }
    return frag;
  }

  public Maquina[] getMaquinas() {
    return maquinas;
  }

}
