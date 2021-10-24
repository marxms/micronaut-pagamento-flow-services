package micronaut.atom.cliente.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.core.annotation.Introspected;
import lombok.*;
import org.bson.types.ObjectId;

@Getter
@Setter
@Builder
@Data
@Introspected
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class Endereco {

    private ObjectId id;

    private String UF;

    private String cidade;

    private String bairro;

    private Long numero;

    private String complemento;

    private TipoEnderecoEnum tipoEndereco;

    private CoordenadasGeograficas coordenadasGeograficas;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public CoordenadasGeograficas getCoordenadasGeograficas() {
        return coordenadasGeograficas;
    }

    public void setCoordenadasGeograficas(CoordenadasGeograficas coordenadasGeograficas) {
        this.coordenadasGeograficas = coordenadasGeograficas;
    }

    public TipoEnderecoEnum getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEnderecoEnum tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }
}
