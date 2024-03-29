/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upskill.porto.service;
import upskill.porto.repo.Dados;
import upskill.porto.dto.*;
import upskill.porto.exception.*;
import upskill.porto.model.*;
import java.util.ArrayList;
/**
 *
 * @author CAD
 */
public class FuncionariosService {
    public static ListaFuncionarioDTO getFuncionarios() {
        Autarquia autarquia = Dados.carregarDados();
        ArrayList<Funcionario> funcionarios = autarquia.getFuncionarios();
        ListaFuncionarioDTO listaFuncionarioaDTO = Mapper.listFuncionario2FuncionarioDTO(funcionarios);
        return listaFuncionarioaDTO;
    }
    
    public static FuncionarioDTO getFuncionario(int nr) {
        Autarquia autarquia = Dados.carregarDados();
        Funcionario funcionario = autarquia.getFuncionario(nr);
        if (funcionario == null) {
            return null;
        }
        FuncionarioDTO funcionarioDTO = Mapper.funcionario2FuncionarioDTO(funcionario);
        if(funcionarioDTO != null){
            return funcionarioDTO;
        }else{
            throw new ConversaoException("FuncionarioDTO");
        }
    }
    
    public static void addFuncionario(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = Mapper.funcionarioDTO2Funcionario(funcionarioDTO);
        if (funcionario != null) {Autarquia autarquia = Dados.carregarDados();
        autarquia.addFuncionario(funcionario);Dados.guardarDados(autarquia);
        }else {
            throw new ConversaoException("FuncionarioDTO");
        }
    }
    
    public static void updateFuncionario(int nr, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = Mapper.funcionarioDTO2Funcionario(funcionarioDTO);
        if (funcionario != null) {
            Autarquia autarquia = Dados.carregarDados();
            autarquia.updateFuncionario(nr, funcionario);
            Dados.guardarDados(autarquia);
        }else {
            throw new ConversaoException("FuncionarioDTO");
        }
    }
    
    public static void removeFuncionario(int nr) {
        Autarquia autarquia = Dados.carregarDados();
        autarquia.removeFuncionario(nr);
        Dados.guardarDados(autarquia);
    }
}
