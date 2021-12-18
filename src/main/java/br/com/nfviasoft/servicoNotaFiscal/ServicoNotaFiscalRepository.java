package br.com.nfviasoft.servicoNotaFiscal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoNotaFiscalRepository extends CrudRepository<ServicoNotaFiscal, Long> {
    List<ServicoNotaFiscal> findAllByEstado(String estado);
}
