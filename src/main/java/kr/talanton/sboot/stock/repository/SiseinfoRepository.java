package kr.talanton.sboot.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import kr.talanton.sboot.stock.entity.SiseInfo;

public interface SiseinfoRepository extends JpaRepository<SiseInfo, Long>, QuerydslPredicateExecutor<SiseInfo> {

}