public class CuentaAhorro extends Cuenta {
    private final double porcentajeCargoAdicional = 1.5;

    public CuentaAhorro(double saldo, String nroCuenta, Titular titular) {
        super(saldo, nroCuenta, titular);
    }

    @Override
    boolean elSaldoEsSuficiente(double monto) {
        return this.getSaldo() > monto;
    }

    @Override
    void debitar(double monto) {
        this.setSaldo(this.getSaldo() - monto);
    }

    @Override
    String muestraSaldoAnterior() {
        return "Saldo anterior = " + this.getSaldo();
    }

    @Override
    String muestraSaldoActual() {
        return "Saldo Actual = " + this.getSaldo();
    }

    //TODO
    @Override
    double cargoPorTransferencia(double monto) {
        return (monto * this.porcentajeCargoAdicional) / 100;
    }

    @Override
    boolean optaPorElPrestamo() {
        return this.isHabilitada() && this.getSaldo() >= 10000;
    }
}
