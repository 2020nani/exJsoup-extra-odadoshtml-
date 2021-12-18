package br.com.nfviasoft.servicoNotaFiscal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@ToString
public class ServicoNotaFiscal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String autorizacao4;
    private String retornoAutorizacao4;
    private String inutilizacao4;
    private String consultaProtocolo4;
    private String statusServico4;
    private String tempoMedio;
    private String consultaCadastro4;
    private String recepcaoEvento4;
    private Integer statusIndisponivel;

    @CreationTimestamp
    private LocalDateTime momentoCriacao;

    public ServicoNotaFiscal(String estado, String autorizacao4, String retornoAutorizacao4, String inutilizacao4, String consultaProtocolo4, String statusServico4, String tempoMedio, String consultaCadastro4, String recepcaoEvento4, Integer statusIndisponivel) {
        this.estado = estado;
        this.autorizacao4 = autorizacao4;
        this.retornoAutorizacao4 = retornoAutorizacao4;
        this.inutilizacao4 = inutilizacao4;
        this.consultaProtocolo4 = consultaProtocolo4;
        this.statusServico4 = statusServico4;
        this.tempoMedio = tempoMedio;
        this.consultaCadastro4 = consultaCadastro4;
        this.recepcaoEvento4 = recepcaoEvento4;
        this.statusIndisponivel = statusIndisponivel;
    }


}
