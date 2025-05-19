package com.korea.page.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korea.page.model.PageEntity;

@Repository
public interface PageRepository extends JpaRepository<PageEntity, String>{

}
