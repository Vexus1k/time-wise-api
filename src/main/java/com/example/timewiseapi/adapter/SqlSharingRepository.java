package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Sharing;
import com.example.timewiseapi.model.SharingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlSharingRepository  extends SharingRepository, JpaRepository<Sharing, Integer> {
}
