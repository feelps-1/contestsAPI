package hacka.mangue.contests.service.impl;

import hacka.mangue.contests.domain.models.Contest;
import hacka.mangue.contests.domain.models.Scholarity;
import hacka.mangue.contests.domain.models.Tematic;
import hacka.mangue.contests.domain.repository.ContestRepository;
import hacka.mangue.contests.service.ContestService;
import hacka.mangue.contests.service.exceptions.BusinessException;
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

        if (contestToCreate.getTematic() != null) {
            for (Tematic tematic : contestToCreate.getTematic()) {
                System.out.println(tematic);
                try {
                    Tematic.valueOf(tematic.name());
                } catch (IllegalArgumentException e) {
                    throw new BusinessException("Invalid tematic: " + tematic);
                }
            }
        }

        if (contestToCreate.getScholarity() != null) {
            for (Scholarity scholarity : contestToCreate.getScholarity()) {
                try {
                    Scholarity.valueOf(scholarity.name());
                } catch (IllegalArgumentException e) {
                    throw new BusinessException("Invalid scholarity: " + scholarity);
                }
            }
        }


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