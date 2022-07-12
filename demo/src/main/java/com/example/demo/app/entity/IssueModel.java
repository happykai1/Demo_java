package com.example.demo.app.entity;

import com.example.demo.app.repository.IssueRepository;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = IssueRepository.TABLE)
public class IssueModel {
    @Id
    @Column(nullable = false)
    private String issueId;

    private String title;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    @Column(name="create_by")
    private String createBy;
    @Column(name="update_by")
    private String updateBy;
}
