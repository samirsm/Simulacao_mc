
public class Maquina {

  private double memo;
  private double cpu;
  private double cpuEmUso;
  private double memoEmUso;
  

  public Maquina(double memo, double cpu) {
    this.memo = memo;
    this.cpu = cpu;
    cpuEmUso = 0;
    memoEmUso = 0;
  }

  public double getMemo() {
    return memo;
  }


  public double getCpu() {
    return cpu;
  }
  
  public double[2] alocar_BF(Maquina maquina){
    double capacidadeMemo = memo - memoEmUso;
    double capacidadeCpu = cpu - cpuEmUso;
    double[] capacidadeRestante = new double[2];

    capacidadeRestante[0]= capacidadeMemo;
    capacidadeRestante[1] = capacidadeCpu;

    if(maquina.memo < capacidadeMemo && maquina.cpu < capacidadeCpu) {
      memoEmUso += maquina.memo;
      cpuEmUso += maquina.cpu;
    }
    return capacidadeRestante;

  }

  public boolean alocar(Maquina maquina){
    double capacidadeMemo = memo - memoEmUso;
    double capacidadeCpu = cpu - cpuEmUso;
    if(maquina.memo < capacidadeMemo && maquina.cpu < capacidadeCpu) {
      memoEmUso += maquina.memo;
      cpuEmUso += maquina.cpu;
      return true;
    }
    else return false;
  }
  
  public double getFragMemo(){
    return memo - memoEmUso;
  }
  
  public double getFragCpu(){
    return cpu - cpuEmUso;
  }

}
