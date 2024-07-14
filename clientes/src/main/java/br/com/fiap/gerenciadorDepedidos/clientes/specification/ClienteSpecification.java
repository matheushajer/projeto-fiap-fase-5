package br.com.fiap.gerenciadorDepedidos.clientes.specification;

import br.com.fiap.gerenciadorDepedidos.clientes.entities.ClienteEntity;
import br.com.fiap.gerenciadorDepedidos.clientes.records.cliente.ClienteCpfFiltroDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ClienteSpecification implements Specification<ClienteEntity> {

    private ClienteCpfFiltroDTO filtro;

    private final String CPF = "cpf";

    @Override
    public Predicate toPredicate(Root<ClienteEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(filtro.cpf())) {
            predicates.add(criteriaBuilder.like(root.get(CPF), "%" + filtro.cpf() + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
