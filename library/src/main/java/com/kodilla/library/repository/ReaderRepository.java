package com.kodilla.library.repository;

import com.kodilla.library.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
