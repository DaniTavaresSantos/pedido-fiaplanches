package br.com.fiaplanchesorder.infra.repository;

import br.com.fiaplanchesorder.domain.enums.OrderStatus;
import br.com.fiaplanchesorder.infra.repository.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostGresOrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("select o from OrderEntity o where o.cpf = :cpf")
    Page<OrderEntity> findAllByCpf(String cpf, Pageable pageable);

    @Query("select o from OrderEntity o where o.orderStatus = :status")
    List<OrderEntity> findAllByStatus(OrderStatus status);
}
