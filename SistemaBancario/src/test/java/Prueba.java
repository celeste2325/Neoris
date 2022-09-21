import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class Prueba {
    private Cuenta cuentaAhorro;
    private Cuenta cuentaAhorro2;
    private Cuenta cuentaAhorro3;
    private CuentaCorriente cuentaCorriente;
    private static final double DELTA = 0.5d;

    @Before
    public void init(){
        Titular titularAhorro = new Titular("Celeste Lopez", "95666240", "1151754521");
        Titular titularAhorro2 = new Titular("Ana Gonzalez", "94355698", "42685478965");
        Titular titularCorriente = new Titular("Rosanne Liendo", "95565324", "4248667264");
        this.cuentaAhorro = new CuentaAhorro(4000, "010236544785", titularAhorro);
        this.cuentaAhorro2 = new CuentaAhorro(2000, "2345236544785", titularAhorro2);
        this.cuentaAhorro3= new CuentaAhorro(2000, "2345236544785", titularAhorro);
        this.cuentaCorriente = new CuentaCorriente(10000, "010532547886", titularAhorro2, 5000);
    }
    @Test
    public void test_depositar_dinero(){
        this.cuentaAhorro.realizarDeposito(200);
        Assertions.assertEquals((int)4200, (int)this.cuentaAhorro.getSaldo());
    }
    @Test
    public void test_retirar_dinero_cuenta_ahorro_ok(){
        this.cuentaAhorro.realizarDebito(3600);
        Assertions.assertEquals((int)400, (int)this.cuentaAhorro.getSaldo());
    }
    @Test
    public void test_retirar_dinero_cuenta_corriente_con_descubierto_ok(){
        this.cuentaCorriente.realizarDebito(14000);
        Assertions.assertEquals((int)1000, (int)this.cuentaCorriente.getSaldoDescubierto());
    }

    //TODO MANEJAR LOS ERRORES COMO HACER PARA DEVOLVER EL ERROR EN EL EQUALS
   /* @Test
    public void test_retirar_dinero_saldoInsuficiente(){
        this.cuentaAhorro.retirarDinero(5000);
        Assertions.assertEquals(MENSAJE ERROR, (int)this.cuentaAhorro.getSaldo());
    }*/
    @Test
    public void test_transferencia_mismo_tipo_de_cuenta_y_titular_ok() {
        this.cuentaAhorro.realizarTransferencia(cuentaAhorro3, 3000);
        Assertions.assertEquals(5000, this.cuentaAhorro3.getSaldo());
        Assertions.assertEquals(1000,this.cuentaAhorro.getSaldo());
    }
    @Test
    public void test_transferencia_distinto_tipo_de_cuenta_y_titular_ok() {
        this.cuentaAhorro.realizarTransferencia(cuentaCorriente, 3000);
        Assertions.assertEquals(13000, this.cuentaCorriente.getSaldo());
        Assertions.assertEquals(955,this.cuentaAhorro.getSaldo());
    }

    @Test
    public void test_transferencia_distinto_tipo_de_cuenta_y_mismo_titular_ok() {
        this.cuentaAhorro2.realizarTransferencia(cuentaCorriente, 1000);
        Assertions.assertEquals(11000, this.cuentaCorriente.getSaldo());
        Assertions.assertEquals(1000,this.cuentaAhorro2.getSaldo());
    }
}
