import lombok.Getter;
import lombok.Setter;


public class CuentaCorriente extends Cuenta {
    private final double porcentajeCargoAdicional = 3;
    @Setter
    @Getter
    private double saldoDescubierto;
    private boolean usoDescubierto;
    private double saldoDescubiertoUsado;

    public CuentaCorriente(double saldo, String nroCuenta, Titular titular, double saldoDescubierto) {
        super(saldo, nroCuenta, titular);
        this.saldoDescubierto = saldoDescubierto;
    }

    @Override
    boolean elSaldoEsSuficiente(double monto) {
        return (this.getSaldo() + this.getSaldoDescubierto()) > monto;
    }

    @Override
    void debitar(double monto) {
        if ((monto > this.getSaldo())) {
            monto -= this.getSaldo();
            this.setSaldo(0);
            this.setSaldoDescubierto(this.getSaldoDescubierto() - monto);
            this.saldoDescubiertoUsado = monto;
            this.usoDescubierto = true;
        } else {
            this.setSaldo(this.getSaldo() - monto);
        }
    }

    @Override
    String muestraSaldoAnterior() {
        return "Saldo Anterior = " + this.getSaldo() + "\n" +
                "Descubierto anterior = " + this.getSaldoDescubierto();
    }

    @Override
    String muestraSaldoActual() {
        return this.usoDescubierto ? "Saldo actual = " + this.getSaldo() + "\n" +
                "Uso la siguiente cantidad del saldo descubierto = " + this.saldoDescubiertoUsado + "\n" +
                "Saldo descubierto disponible = " + this.getSaldoDescubierto()
                : "Saldo Actual = " + this.getSaldo();
    }


    @Override
    double cargoPorTransferencia(double monto) {
        return (monto * this.porcentajeCargoAdicional) / 100;
    }

    @Override
    boolean optaPorElPrestamo() {
        return this.isHabilitada() && (this.getSaldo() + this.getSaldoDescubierto()) >= 10000;
    }


}
