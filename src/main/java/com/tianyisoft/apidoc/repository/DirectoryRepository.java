package com.tianyisoft.apidoc.repository;

import com.tianyisoft.apidoc.domain.Directory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectoryRepository extends JpaRepository<Directory, Integer> {
}
