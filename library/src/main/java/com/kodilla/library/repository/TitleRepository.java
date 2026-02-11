package com.kodilla.library.repository;

import com.kodilla.library.domain.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
