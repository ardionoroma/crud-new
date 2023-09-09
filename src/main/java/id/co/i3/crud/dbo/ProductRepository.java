package id.co.i3.crud.dbo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer>
{
    Optional<Product> findById(Integer id);
}
