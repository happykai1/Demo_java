package com.example.demo.app.repository;

import com.example.demo.app.entity.IssueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<IssueModel,String> {
    String TABLE ="ISSUE";

    IssueModel findByIssueId(String issueId);

}
