package com.example.schedule.repository;

import com.example.schedule.dto.request.ScheduleSearchCondition;
import com.example.schedule.entity.QCategory;
import com.example.schedule.entity.QSchedule;
import com.example.schedule.entity.QTag;
import com.example.schedule.entity.QUser;
import com.example.schedule.entity.Schedule;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> search(ScheduleSearchCondition condition) {
        QSchedule schedule = QSchedule.schedule;
        QUser user = QUser.user;
        QCategory category = QCategory.category;
        QTag tag = QTag.tag;

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

        if (condition.getCategoryId() != null) {
            builder.and(schedule.category.id.eq(condition.getCategoryId()));
        }

        if (!CollectionUtils.isEmpty(condition.getTagIds())) {
            builder.and(schedule.tags.any().id.in(condition.getTagIds()));
        }

        return queryFactory
                .selectFrom(schedule)
                .distinct()
                .leftJoin(schedule.user, user).fetchJoin()
                .leftJoin(schedule.category, category).fetchJoin()
                .leftJoin(schedule.tags, tag).fetchJoin()
                .where(builder)
                .fetch();
    }
}