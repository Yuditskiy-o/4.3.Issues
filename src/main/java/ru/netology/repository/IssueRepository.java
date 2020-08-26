package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.*;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public boolean addAll(List<Issue> issues) {
        return this.issues.addAll(issues);
    }

    public List<Issue> findAll() {
        return issues;
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public Issue findById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public void removeById(int id) {
        issues.removeIf(issue -> issue.getId() == id);
    }

    public boolean removeAll(List<Issue> issues) {
        return this.issues.removeAll(issues);
    }
}
