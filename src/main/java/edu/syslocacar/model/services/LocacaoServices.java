package edu.syslocacar.model.services;

import edu.syslocacar.model.entity.Locacao;
import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.persistence.LocacaoDAO;

import java.util.List;

public class LocacaoServices {
    private LocacaoDAO locacaoDAO = new LocacaoDAO();

    public void addLocacao(Locacao locacao) { locacaoDAO.save(locacao); }

    public List<Locacao> getAllLocacoes() {
        return locacaoDAO.findAll();
    }

    public Locacao getLocacaoById(Long id) {
        return locacaoDAO.findById(id);
    }

    public void updateLocacao(Locacao locacao) {
        locacaoDAO.update(locacao);
    }

    public void removeLocacao(Long id) {
        locacaoDAO.delete(id);
    }
}
