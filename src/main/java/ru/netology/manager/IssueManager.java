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
            if (predicate.test(issue)) result.add(issue);
        }
        return result;
    }

    public List<Issue> findOpened() {
        return filterBy(issue -> issue.isOpen());
    }

    public List<Issue> findClosed() {
        return filterBy(issue -> !issue.isOpen());
    }

    public List<Issue> filterByAuthors(Collection<? extends String> authors) {
        return filterBy(issue -> issue.getAuthor().equals(authors));
    }

    public List<Issue> filterByLabels(Collection<? extends String> labels) {
        return filterBy(issue -> issue.getIssueLabels().containsAll(labels));
    }

    public List<Issue> filterByAssignees(Collection<? extends String> assignees) {
        return filterBy(issue -> issue.getIssueAssignees().contains(assignees));
    }

    public List<Issue> sortByNewest() {
        Comparator byNewest = Comparator.naturalOrder();
        List<Issue> issues = new ArrayList<>(repository.findAll());
        issues.sort(byNewest);
        return issues;
    }

    public List<Issue> sortByOldest() {
        Comparator byOldest = Comparator.reverseOrder();
        List<Issue> issues = new ArrayList<>(repository.findAll());
        issues.sort(byOldest);
        return issues;
    }

    public void openIssueById(int id, boolean isOpen) {
        repository.findById(id).setOpen(isOpen);
    }

    public void closeIssueById(int id, boolean isOpen) {
        repository.findById(id).setOpen(!isOpen);
    }
}
