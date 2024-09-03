package tawjih.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tawjih.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
