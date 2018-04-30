package br.com.senior.model.repository.document;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.senior.model.domain.document.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	@Query("select sum(total) from Document where confirmed = true")
	BigDecimal totalSoldSum();

}
