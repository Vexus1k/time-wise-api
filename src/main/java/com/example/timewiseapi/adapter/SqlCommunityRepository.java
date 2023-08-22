package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Community;
import com.example.timewiseapi.model.CommunityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface SqlCommunityRepository extends CommunityRepository, JpaRepository<Community, Integer> {
}
