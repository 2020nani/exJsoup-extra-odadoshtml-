package br.com.nfviasoft.application.controller;

import br.com.nfviasoft.servicoNotaFiscal.ServicoNotaFiscal;
import br.com.nfviasoft.servicoNotaFiscal.ServicoNotaFiscalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class ServicoNotaFiscalController {

    @Autowired
    private ServicoNotaFiscalService servicoNotaFiscalService;

    public ServicoNotaFiscalController(ServicoNotaFiscalService servicoNotaFiscalService) {
        this.servicoNotaFiscalService = servicoNotaFiscalService;
    }

    @GetMapping("status")
    public ResponseEntity<List<ServicoNotaFiscal>> buscaStatusServicoNotaFiscal() {
        List<ServicoNotaFiscal> servicos = servicoNotaFiscalService.findAll();

        return ResponseEntity.ok(servicos);

    }

    @GetMapping("status/{estado}")
    public ResponseEntity<ServicoNotaFiscal> buscaStatusServicoNotaFiscalPorEstado(@PathVariable(value = "estado") String estado) {
        ServicoNotaFiscal servico = servicoNotaFiscalService.filtraPorEstado(estado);

        return ResponseEntity.ok(servico);

    }

    @GetMapping("status/data/{data}")
    public ResponseEntity<List<ServicoNotaFiscal>> buscaStatusServicoNotaFiscalPorData(@PathVariable(value = "data") String data) {
        List<ServicoNotaFiscal> servico = servicoNotaFiscalService.filtraPorData(data);

        return ResponseEntity.ok(servico);

    }

    @GetMapping("status/indisponivel")
    public ResponseEntity<List<ServicoNotaFiscal>> buscaServicoMaiorIndisponibilidade() {
        List<ServicoNotaFiscal> servico = servicoNotaFiscalService.filtraPorMaiorIndisponibilidade();

        return ResponseEntity.ok(servico);

    }

}
