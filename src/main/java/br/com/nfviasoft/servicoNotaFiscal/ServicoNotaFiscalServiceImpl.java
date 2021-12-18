package br.com.nfviasoft.servicoNotaFiscal;

import br.com.nfviasoft.application.dto.ServicoNotaFiscalDto.ServicoNotaFiscalRequest;
import br.com.nfviasoft.servicoNotaFiscal.Helper.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ServicoNotaFiscalServiceImpl implements ServicoNotaFiscalService{

    private ServicoNotaFiscalRepository repository;

    private Integer maiorIndisponibilidade = 0;

    private Utils utils;

    @Autowired
    public ServicoNotaFiscalServiceImpl(ServicoNotaFiscalRepository repository, Utils utils) {
        this.repository = repository;
        this.utils = utils;
    }

    @Override
    public ServicoNotaFiscal save(ServicoNotaFiscalRequest request) {
        log.info("Salvando lista de status dos serviços");
        ServicoNotaFiscal servicoNotaFiscal = request.toServiceNotaFical();
        repository.save(servicoNotaFiscal);

        return servicoNotaFiscal;
    }

    @Override
    public List<ServicoNotaFiscal> findAll() {
        List<ServicoNotaFiscal> statusServicoAtual = new ArrayList<>();
        Iterable<ServicoNotaFiscal> lista = repository.findAll();
        lista.forEach(servicoNotaFiscal -> {

            if(utils.filtroStatusAtual(servicoNotaFiscal)) {
                statusServicoAtual.add(servicoNotaFiscal);
            }

        });

        return statusServicoAtual;
    }

    @Override
    public ServicoNotaFiscal filtraPorEstado(String estado) {
        List<ServicoNotaFiscal> lista = repository.findAllByEstado(estado);

        ServicoNotaFiscal servico = lista.get(lista.size()-1);

        return servico;
    }

    @Override
    public List<ServicoNotaFiscal> filtraPorData(String data) {
        List<ServicoNotaFiscal> statusServicoPorData = new ArrayList<>();
        Iterable<ServicoNotaFiscal> listaServiços = repository.findAll();
        listaServiços.forEach(servicoNotaFiscal -> {
            if(utils.filtroPorData(data, servicoNotaFiscal)){
                statusServicoPorData.add(servicoNotaFiscal);
            }
        });
        return statusServicoPorData;
    }

    @Override
    public List<ServicoNotaFiscal> filtraPorMaiorIndisponibilidade() {

        List<ServicoNotaFiscal> lista = new ArrayList<>();
        Iterable<ServicoNotaFiscal> listaServiços = repository.findAll();
        calculaIndisponibilidade(listaServiços);
        listaServiços.forEach(servicoNotaFiscal -> {
            if(servicoNotaFiscal.getStatusIndisponivel() == maiorIndisponibilidade) {
                lista.add(servicoNotaFiscal);
            }
        });

        return lista;
    }

    private void calculaIndisponibilidade(Iterable<ServicoNotaFiscal> listaServiços) {
        listaServiços.forEach(servicoNotaFiscal -> {
            if(maiorIndisponibilidade <= servicoNotaFiscal.getStatusIndisponivel()) {
                maiorIndisponibilidade = servicoNotaFiscal.getStatusIndisponivel();
            }
        });
    }


}
