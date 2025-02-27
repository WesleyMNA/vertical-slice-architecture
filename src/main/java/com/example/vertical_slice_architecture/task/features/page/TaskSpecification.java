package com.example.vertical_slice_architecture.task.features.page;

import com.example.vertical_slice_architecture.task.entities.Task;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

record TaskSpecification(
        UUID userId,
        String title,
        LocalDate day
) implements Specification<Task> {

    @Override
    public Predicate toPredicate(
            @NotNull
            Root<Task> root,
            CriteriaQuery<?> query,
            @NotNull
            CriteriaBuilder builder
    ) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("userId"), userId));

        if (title != null)
            predicates.add(builder.like(root.get("title"), "%" + title + "%"));

        if (day != null)
            predicates.add(builder.equal(root.get("day"), day));

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
