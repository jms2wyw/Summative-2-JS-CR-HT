package com.company.bookstore.repository;

import com.company.bookstore.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

}
