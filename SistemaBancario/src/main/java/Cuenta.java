import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Cuenta {
    private double saldo;
    private String nroCuenta;
    private Titular titular;
    private boolean isHabilitada;
    private String saldoAnterior;


    public Cuenta(double saldo, String nroCuenta, Titular titular) {
        this.saldo = saldo;
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.isHabilitada = true;
    }
    abstract boolean elSaldoEsSuficiente(double montoSolicitado);
    abstract void debitar(double monto);
    abstract String muestraSaldoAnterior();
    abstract String muestraSaldoActual();
    abstract double cargoPorTransferencia(double monto);
    public void realizarDeposito(double monto) {
        this.saldoAnterior = this.muestraSaldoAnterior();
        if (this.isHabilitada) {
            this.setSaldo(this.getSaldo() + monto);
            mostrarDetalle("Deposito",this.saldoAnterior ,monto);
        }else System.out.println("La cuenta esta inhabilitada");
    }
    public void realizarDebito(double monto) {
        this.saldoAnterior = this.muestraSaldoAnterior();
        if (this.isHabilitada() && elSaldoEsSuficiente(monto)) {
            this.debitar(monto);
            this.mostrarDetalle("retiro", saldoAnterior, monto);
        } else {
            System.out.println("El saldo actual es insuficiente");
        }
    }
    public void realizarTransferencia(Cuenta cuentaDestino, double monto){
        this.saldoAnterior = this.muestraSaldoAnterior();
        String saldoAnteriorCuentaDestino = cuentaDestino.muestraSaldoAnterior();
        if (this.isHabilitada && cuentaDestino.isHabilitada) {
            if (debeCobrarComision(cuentaDestino)){
                //TODO se tiene demaciados iF aqui se puede refactorizar en otro metodo apartate 
                if (this.elSaldoEsSuficiente(monto + this.cargoPorTransferencia(monto))) {
                    this.debitar(this.cargoPorTransferencia(monto));
                    this.realizarOperacion(cuentaDestino, monto, this.saldoAnterior, saldoAnteriorCuentaDestino);
                }
                else System.out.println("El saldo no es suficiente para realizar la transferencia");

            }else if (this.elSaldoEsSuficiente(monto)) {
                this.realizarOperacion(cuentaDestino, monto, this.saldoAnterior, saldoAnteriorCuentaDestino);
            }else System.out.println("El saldo no es suficiente para realizar la transferencia");
            
        } else System.out.println("No se puede realizar la transferencia porque una de las cuentas esta inhabilitada");
    }
    public boolean debeCobrarComision(Cuenta cuentaDestino) {
        return !this.getTitular().isMismoTitular(cuentaDestino.getTitular()) &&
                !this.getClass().getName().equals(cuentaDestino.getClass().getName());
    }
    public void realizarOperacion(Cuenta cuentaDestino, double monto, String saldoInicialOrigen, String saldoInicialDestino){
        this.debitar(monto);
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
        System.out.println("Detalle cuenta Origen");
        this.mostrarDetalle("Transferir", saldoInicialOrigen, monto);
        System.out.println("Detalle cuenta Destino");
        cuentaDestino.mostrarDetalle("Transferir", saldoInicialDestino, monto);
    }
    
    public void mostrarDetalle(String operacion, String saldoAnterior, double monto) {
        System.out.println("Operación exitosa - Detalle " + operacion + "\n" +
                "Nro de cuenta: " + this.nroCuenta + "\n" +
                "Titular = " + this.getTitular().getNombreYapellido()  + "\n" +
                "Tipo de cuenta = " + this.getClass().getName() + "\n" +
                saldoAnterior + "\n" +
                operacion + " = " + monto  + "\n" +
                this.muestraSaldoActual() + "\n" +
                "----------------------------------");
    }

}
