package edu.syslocacar.model.services;

import edu.syslocacar.model.entity.Veiculo;
import edu.syslocacar.persistence.VeiculoDAO;

import java.util.List;

public class VeiculoServices {

    private VeiculoDAO veiculoDAO = new VeiculoDAO();

    public void addVeiculo(Veiculo veiculo) {
        veiculoDAO.save(veiculo);
    }

    public List<Veiculo> getAllVeiculos() {
        return veiculoDAO.findAll();
    }

    public Veiculo getVeiculoById(Long id) {
        return veiculoDAO.findById(id);
    }

    public void updateVeiculo(Veiculo veiculo) {
        veiculoDAO.update(veiculo);
    }

    public void removeVeiculo(Long id) {
        veiculoDAO.delete(id);
    }
}
