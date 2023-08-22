package com.example.timewiseapi.adapter;

import com.example.timewiseapi.model.Calendar;
import com.example.timewiseapi.model.CalendarRepository;
import com.example.timewiseapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlCalendarRepository extends CalendarRepository, JpaRepository<Calendar, Integer> {
}
