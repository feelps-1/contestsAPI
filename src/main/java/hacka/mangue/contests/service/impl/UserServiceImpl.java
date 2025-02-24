package hacka.mangue.contests.service.impl;

import hacka.mangue.contests.domain.models.user.User;
import hacka.mangue.contests.domain.repository.UserRepository;
import hacka.mangue.contests.service.UserService;
import hacka.mangue.contests.service.exceptions.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    @Override
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("User to create must not be null."));
        ofNullable(userToCreate.getUsername()).orElseThrow(() -> new BusinessException("Username must not be null."));
        ofNullable(userToCreate.getPassword()).orElseThrow(() -> new BusinessException("Password must not be null."));

        if (userRepository.existsByUsername(userToCreate.getUsername())) {
            throw new BusinessException("A user with this username already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Transactional
    @Override
    public User update(Long id, User userToUpdate) {
        User dbUser = this.findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setUsername(userToUpdate.getUsername());
        dbUser.setPassword(userToUpdate.getPassword());
        dbUser.setContests(userToUpdate.getContests());

        return userRepository.save(dbUser);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User dbUser = this.findById(id);
        userRepository.delete(dbUser);
    }
}
