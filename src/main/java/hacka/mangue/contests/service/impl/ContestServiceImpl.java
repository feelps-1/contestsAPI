package hacka.mangue.contests.service.impl;

import hacka.mangue.contests.controller.exceptions.BusinessException;
import hacka.mangue.contests.domain.models.Contest;
import hacka.mangue.contests.domain.repository.ContestRepository;
import hacka.mangue.contests.service.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Optional.ofNullable;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;

    public ContestServiceImpl(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Contest> findAll() {
        return contestRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Contest findById(Long id) {
        return contestRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public Contest create(Contest contestToCreate) {
        ofNullable(contestToCreate).orElseThrow(() -> new BusinessException("Contest to create must not be null."));
        ofNullable(contestToCreate.getName()).orElseThrow(() -> new BusinessException("Contest name must not be null."));

        if (contestRepository.existsByName(contestToCreate.getName())) {
            throw new BusinessException("A contest with this name already exists.");
        }
        return contestRepository.save(contestToCreate);
    }

    @Transactional
    @Override
    public Contest update(Long id, Contest contestToUpdate) {
        Contest dbContest = this.findById(id);
        if (!dbContest.getId().equals(contestToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbContest.setName(contestToUpdate.getName());
        dbContest.setDescription(contestToUpdate.getDescription());
        dbContest.setParticipants(contestToUpdate.getParticipants());

        return contestRepository.save(dbContest);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Contest dbContest = this.findById(id);
        contestRepository.delete(dbContest);
    }
}
