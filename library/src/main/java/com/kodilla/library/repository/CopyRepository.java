package com.kodilla.library.repository;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CopyRepository extends JpaRepository<Copy, Long> {

    List<Copy> findByTitleIdAndStatus(Long titleId, CopyStatus status);
}
