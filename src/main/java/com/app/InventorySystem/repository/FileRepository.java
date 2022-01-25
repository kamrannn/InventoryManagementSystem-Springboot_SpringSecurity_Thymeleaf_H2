package com.app.InventorySystem.repository;

import com.app.InventorySystem.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, String> {
    List<File> findAllByNameContaining(String fileName);
}
