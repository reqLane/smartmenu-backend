package com.naukma.smartmenubackend.table;

import com.naukma.smartmenubackend.table.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepo extends JpaRepository<Table, Long> {

}
