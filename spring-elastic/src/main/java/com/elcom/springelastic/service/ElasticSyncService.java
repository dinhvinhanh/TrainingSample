package com.elcom.springelastic.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface ElasticSyncService {
    void sync();
    void syncBooks();
    
}
