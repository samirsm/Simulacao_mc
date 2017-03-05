
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
