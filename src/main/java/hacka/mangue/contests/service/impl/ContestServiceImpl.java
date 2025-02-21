package hacka.mangue.contests.service.impl;

import hacka.mangue.contests.controller.exceptions.BusinessException;
import hacka.mangue.contests.domain.models.Contest;
import hacka.mangue.contests.domain.models.Scholarity;
import hacka.mangue.contests.domain.models.Tematic;
import hacka.mangue.contests.domain.repository.ContestRepository;
import hacka.mangue.contests.domain.repository.ScholarityRepository;
import hacka.mangue.contests.domain.repository.TematicRepository;
import hacka.mangue.contests.service.ContestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Optional.ofNullable;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    private final ScholarityRepository scholarityRepository;
    private final TematicRepository tematicRepository;

    public ContestServiceImpl(ContestRepository contestRepository,
                              ScholarityRepository scholarityRepository,
                              TematicRepository tematicRepository) {
        this.contestRepository = contestRepository;
        this.scholarityRepository = scholarityRepository;
        this.tematicRepository = tematicRepository;
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
        // Validate contestToCreate
        ofNullable(contestToCreate).orElseThrow(() -> new BusinessException("Contest to create must not be null."));
        ofNullable(contestToCreate.getName()).orElseThrow(() -> new BusinessException("Contest name must not be null."));

        // Check if a contest with the same name already exists
        if (contestRepository.existsByName(contestToCreate.getName())) {
            throw new BusinessException("A contest with this name already exists.");
        }

        // Validate Scholarity entities
        if (contestToCreate.getScholarity() != null) {
            for (Scholarity scholarity : contestToCreate.getScholarity()) {
                if (scholarity.getId() == null || !scholarityRepository.existsById(scholarity.getId())) {
                    throw new BusinessException("Scholarity with ID " + scholarity.getId() + " does not exist.");
                }
            }
        }

        // Validate Tematic entities
        if (contestToCreate.getTematic() != null) {
            for (Tematic tematic : contestToCreate.getTematic()) {
                if (tematic.getId() == null || !tematicRepository.existsById(tematic.getId())) {
                    throw new BusinessException("Tematic with ID " + tematic.getId() + " does not exist.");
                }
            }
        }

        // Save the Contest
        return contestRepository.save(contestToCreate);
    }

    @Transactional
    @Override
    public Contest update(Long id, Contest contestToUpdate) {
        // Fetch the existing contest from the database
        Contest dbContest = this.findById(id);

        // Validate contestToUpdate
        if (!dbContest.getId().equals(contestToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        // Validate Scholarity entities
        if (contestToUpdate.getScholarity() != null) {
            for (Scholarity scholarity : contestToUpdate.getScholarity()) {
                if (scholarity.getId() == null || !scholarityRepository.existsById(scholarity.getId())) {
                    throw new BusinessException("Scholarity with ID " + scholarity.getId() + " does not exist.");
                }
            }
        }

        // Validate Tematic entities
        if (contestToUpdate.getTematic() != null) {
            for (Tematic tematic : contestToUpdate.getTematic()) {
                if (tematic.getId() == null || !tematicRepository.existsById(tematic.getId())) {
                    throw new BusinessException("Tematic with ID " + tematic.getId() + " does not exist.");
                }
            }
        }

        // Update the contest fields
        dbContest.setName(contestToUpdate.getName());
        dbContest.setDescription(contestToUpdate.getDescription());
        dbContest.setParticipants(contestToUpdate.getParticipants());
        dbContest.setScholarity(contestToUpdate.getScholarity());
        dbContest.setTematic(contestToUpdate.getTematic());
        dbContest.setIcon(contestToUpdate.getIcon());
        dbContest.setUrl(contestToUpdate.getUrl());

        // Save the updated contest
        return contestRepository.save(dbContest);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Contest dbContest = this.findById(id);
        contestRepository.delete(dbContest);
    }
}