import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Titular {
    private final int cantidadCaracteresHackeo = 15;
    private String nombreYapellido;
    private String documento;
    private String telefono;

    public boolean isMismoTitular(Titular titular) {
        return this.getDocumento().equals(titular.getDocumento());
    }

    @Override
    public String toString() {
        return "Titular{" +
                "nombreYapellido='" + nombreYapellido + '\'' +
                ", documento='" + documento + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public boolean tieneCantidadCaracteresParaHackeo() {
        return this.getNombreYapellido().length() > this.cantidadCaracteresHackeo;
    }
}
