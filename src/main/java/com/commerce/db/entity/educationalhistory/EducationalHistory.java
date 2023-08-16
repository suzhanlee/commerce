package com.commerce.db.entity.educationalhistory;

import com.commerce.db.entity.BaseCreateTimeEntity;
import com.commerce.db.entity.BaseTimeEntity;
import com.commerce.db.entity.applicant.Applicant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EducationalHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EduHistoryType eduHistoryType;

    @Column(nullable = false)
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GraduateType graduateType;

    @Column(nullable = false)
    private LocalDate admissionDate;

    private LocalDate graduateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Applicant applicant;

}
