import Exceptions.CuentaInhabilitadaException;
import Exceptions.SaldoInsuficienteException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Prueba {
    private Cuenta cuentaAhorro;
    private Cuenta cuentaAhorro2;
    private Cuenta cuentaAhorro3;
    private Cuenta cuentaAhorro4;
    private Cuenta cuentaHackeable;
    private CuentaCorriente cuentaCorrienteCuentaInhabilitada;
    private CuentaCorriente cuentaCorriente;
    private List<Cuenta> cuentas;

    @Before
    public void init() {
        this.cuentas = new ArrayList<>();
        Titular titularAhorro = new Titular("CELESTE LOPEZ", "95666240", "1151754521");
        Titular titularAhorro2 = new Titular("ANA GONZALEZ", "94355698", "42685478965");
        Titular titularAhorro4 = new Titular("ROSANNE LIENDO", "95565324", "4248667264");
        Titular titularHackeable = new Titular("ROSANNE LIENDO LOPEZ", "95565324", "4248667264");
        this.cuentaAhorro = new CuentaAhorro(4000, "010236544785", titularAhorro);
        this.cuentaAhorro2 = new CuentaAhorro(2000, "2345236544785", titularAhorro2);
        this.cuentaAhorro3 = new CuentaAhorro(2000, "2345236544785", titularAhorro);
        this.cuentaAhorro4 = new CuentaAhorro(20000, "123456", titularAhorro4);
        this.cuentaHackeable = new CuentaAhorro(60000, "250", titularHackeable);
        this.cuentaCorriente = new CuentaCorriente(10000, "010532547886", titularAhorro2, 5000);
        this.cuentaCorrienteCuentaInhabilitada = new CuentaCorriente(100, "454521", titularAhorro2, 500);
        this.cuentaCorrienteCuentaInhabilitada.setHabilitada(false);
        this.addCuentas(cuentaAhorro, cuentaAhorro2, cuentaAhorro3, cuentaCorriente, cuentaAhorro4);
    }

    @Test
    public void test_depositar_dinero() throws CuentaInhabilitadaException {
        this.cuentaAhorro.realizarDeposito(200);
        Assertions.assertEquals((int) 4200, (int) this.cuentaAhorro.getSaldo());
    }

    @Test
    public void test_retirar_dinero_cuenta_ahorro_ok() throws SaldoInsuficienteException {
        this.cuentaAhorro.realizarDebito(3600);
        Assertions.assertEquals((int) 400, (int) this.cuentaAhorro.getSaldo());
    }

    @Test
    public void test_retirar_dinero_cuenta_corriente_con_descubierto_ok() throws SaldoInsuficienteException {
        this.cuentaCorriente.realizarDebito(14000);
        Assertions.assertEquals((int) 1000, (int) this.cuentaCorriente.getSaldoDescubierto());
    }

    @Test(expected = SaldoInsuficienteException.class)
    public void test_retirar_dinero_saldoInsuficiente() throws SaldoInsuficienteException {
        this.cuentaAhorro.realizarDebito(5000);
    }

    @Test(expected = CuentaInhabilitadaException.class)
    public void test_cuenta_inhabilitada() throws CuentaInhabilitadaException {
        this.cuentaCorrienteCuentaInhabilitada.realizarDeposito(5000);
    }

    @Test
    public void test_transferencia_mismo_tipo_de_cuenta_y_titular_ok() throws SaldoInsuficienteException, CuentaInhabilitadaException {
        this.cuentaAhorro.realizarTransferencia(cuentaAhorro3, 3000);
        Assertions.assertEquals(5000, this.cuentaAhorro3.getSaldo());
        Assertions.assertEquals(1000, this.cuentaAhorro.getSaldo());
    }

    @Test
    public void test_transferencia_distinto_tipo_de_cuenta_y_titular_ok() throws SaldoInsuficienteException, CuentaInhabilitadaException {
        this.cuentaAhorro.realizarTransferencia(cuentaCorriente, 3000);
        Assertions.assertEquals(13000, this.cuentaCorriente.getSaldo());
        Assertions.assertEquals(955, this.cuentaAhorro.getSaldo());
    }

    @Test
    public void test_transferencia_distinto_tipo_de_cuenta_y_mismo_titular_ok() throws SaldoInsuficienteException, CuentaInhabilitadaException {
        this.cuentaAhorro2.realizarTransferencia(cuentaCorriente, 1000);
        Assertions.assertEquals(11000, this.cuentaCorriente.getSaldo());
        Assertions.assertEquals(1000, this.cuentaAhorro2.getSaldo());
    }

    @Test
    public void test_cumple_con_requisito_para_otorgar_prestamo_ok() {
        Assertions.assertEquals("ANA GONZALEZ,ROSANNE LIENDO", this.obtenerTitularesAptosParaPrestamo(this.cuentas));
    }

    @Test
    public void test_alguna_cuenta_puede_ser_hackeada_true() {
        this.addCuentas(cuentaHackeable);
        Assertions.assertTrue(this.algunaCuentaPuedeSerHackeada(this.cuentas));
    }

    public void addCuentas(Cuenta... cuentas) {
        Collections.addAll(this.cuentas, cuentas);
    }

    public String obtenerTitularesAptosParaPrestamo(List<Cuenta> cuentas) {
        return cuentas.stream().filter(cuenta -> cuenta.optaPorElPrestamo()).
                map(cuenta -> cuenta.getTitular().getNombreYapellido()).collect(Collectors.joining(","));
    }

    public boolean algunaCuentaPuedeSerHackeada(List<Cuenta> cuentas) {
        return cuentas.stream().anyMatch(Cuenta::esPropensaAHackeo);
    }
}
