package com.mzo.wasl.repository;

import com.mzo.wasl.model.CancelledReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelledReqRepository extends JpaRepository<CancelledReq, Long> {
}
