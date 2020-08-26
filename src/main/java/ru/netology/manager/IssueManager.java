package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue))
                result.add(issue);
        }
        return result;
    }

    public List<Issue> findOpened() {
        return filterBy(issue -> issue.isOpen());
    }

    public List<Issue> findClosed() {
        return filterBy(issue -> !issue.isOpen());
    }




}
