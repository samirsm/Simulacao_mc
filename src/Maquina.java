
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


  public double getCpuEmUso() {
    return cpuEmUso;
  }
  
  public double getMemoEmUso() {
    return memoEmUso;
  }


  public double getMemoRestante() {
    return memo - memoEmUso;
  }

  public double getCpuRestante() {
    return cpu-cpuEmUso;
  }

  public double getCpu() {
    return cpu;
  }
  

// método para alocação de VM no host
  public boolean alocar(Maquina maquina){
//Caso o tamanho (memória e CPU) da VM seja menor ou igual ao tamanho restante do Host a máquina é alocada
    if(maquina.memo <= getMemoRestante() && maquina.cpu <= getCpuRestante()) {
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
  
  public String toString(){
    return "resta: " + getCpuRestante();
  }
  
}
