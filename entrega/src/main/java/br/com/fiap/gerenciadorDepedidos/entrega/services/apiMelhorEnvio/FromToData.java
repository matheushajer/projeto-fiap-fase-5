package br.com.fiap.gerenciadorDepedidos.entrega.services.apiMelhorEnvio;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class FromToData {

    @SerializedName("postal_code")
    private String postalCode;

}
