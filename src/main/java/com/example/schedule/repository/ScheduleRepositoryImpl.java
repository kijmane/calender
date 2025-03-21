package com.example.schedule.repository;

import com.example.schedule.entity.QSchedule;
import com.example.schedule.entity.QUser;
import com.example.schedule.entity.Schedule;
import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> search(ScheduleSearchCondition condition) {
        QSchedule schedule = QSchedule.schedule;
        QUser user = QUser.user;

        BooleanBuilder builder = new BooleanBuilder();

        if (condition.getTitle() != null) {
            builder.and(schedule.title.containsIgnoreCase(condition.getTitle()));
        }

        if (condition.getEmail() != null) {
            builder.and(schedule.user.email.eq(condition.getEmail()));
        }

        if (condition.getFrom() != null && condition.getTo() != null) {
            builder.and(schedule.createdAt.between(condition.getFrom(), condition.getTo()));
        }

        return queryFactory
                .selectFrom(schedule)
                .join(schedule.user, user).fetchJoin()
                .where(builder)
                .fetch();
    }
}