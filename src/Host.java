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

/*Inicializa m�quinas do Host com a configura��o (mem�ria e CPU) padr�o passada no construtor */
  private Maquina[] inicializaMaquinas(Maquina mqPadrao) {
    Maquina[] maquinas = new Maquina[numeroDeMaquinas];
    for (int i = 0; i < maquinas.length; i++) {
      maquinas[i] = new Maquina(mqPadrao.getCpu(), mqPadrao.getMemo());
    }
    return maquinas;
  }

/* M�todo para aloca��o de m�quina utilizando o algoritmo First Fit, onde o c�digo percorre toda a lista 
de m�quinas de host at� que encontre a primeira m�quina de host que caiba (m�moria e CPU) a VM solicitada */
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


/* M�todo para aloca��o de m�quina utilizando o algoritmo Best Fit, onde o c�digo percorre toda a lista de m�quinas 
de host para encontrar a m�quina de host que caiba (m�moria e CPU) a VM solicitada com menor fragmenta��o*/

  public boolean aloqueBF(Maquina maquina) {

    final int MEMO = 0;
    final int CPU = 1;

	/* A melhor aloca��o ser� na m�quinde host onde se pode minimizar a fragmenta��o */
    double[] melhorAlocacao = new double[2];
    melhorAlocacao[MEMO] = Integer.MAX_VALUE;
    melhorAlocacao[CPU] = Integer.MAX_VALUE;
    int bestFitIndex = -1;

    for (int i = 0; i < numeroDeMaquinas; i++) {
	/*Verifica se a VM solicitada cabe a m�quina atual do host que est� sendo visitada */
      if ((maquinas[i].getMemoRestante() >= maquina.getMemo())
          && (maquinas[i].getCpuRestante() >= maquina.getCpu())) {
	
	/* Se a fragmenta��o da m�quina de host atual for menor que a fragmenta��o da m�quina 
	com melhor aloca��o at� o momento, atualiza-se os valores de melhor aloca��o e �ndice */
        if (((maquinas[i].getMemoRestante() - maquina.getMemo()) < melhorAlocacao[MEMO])
            && ((maquinas[i].getCpuRestante() - maquina.getCpu()) < melhorAlocacao[CPU])) {
          melhorAlocacao[MEMO] = maquinas[i].getMemoRestante() - maquina.getMemo();
          melhorAlocacao[CPU] = maquinas[i].getCpuRestante() - maquina.getCpu();
          bestFitIndex = i;
        }
      }
    }

    /* Caso o �ndice da melhor aloca��o n�o seja diferente de -1 n�o houve aloca��o */
    if (bestFitIndex != -1) {
      return maquinas[bestFitIndex].alocar(maquina);
    } else {
      return false;
    }
  }

/* M�todo para aloca��o de m�quina utilizando o algoritmo Random Fit, 
onde o c�digo acessa aleatoriamente uma m�quina de host e verifica se a VM pode ser alocada nela*/

  public boolean aloqueRF(Maquina maquina) {
    Random rn = new Random();
    int randomIndex = rn.nextInt(numeroDeMaquinas) + 0;
    if (maquinas[randomIndex].alocar(maquina)) {
      return true;
    }

    return false;
  }

/* Calcular a fragmenta��o total do host */
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
