package com.example.demo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.bean.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
