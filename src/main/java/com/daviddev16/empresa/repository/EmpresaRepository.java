package com.daviddev16.empresa.repository;

import com.daviddev16.empresa.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> { }
