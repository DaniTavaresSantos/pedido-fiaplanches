package br.com.fiaplanchesorder.application.ports.out;

import br.com.fiaplanchesorder.application.dtos.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductRestPortOut {

    Optional<List<ProductDto>> findByIds(List<Long> productsIds);
}
