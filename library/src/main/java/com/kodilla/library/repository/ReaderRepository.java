package com.kodilla.library.repository;

import com.kodilla.library.domain.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    @Query("SELECT r FROM Reader r WHERE r.id = :id")
    Optional<Reader> findByIdIncludingDeleted(@Param("id") Long id);

    List<Reader> findAllByDeletedFalse();
    Optional<Reader> findByIdAndDeletedFalse(Long id);
}
