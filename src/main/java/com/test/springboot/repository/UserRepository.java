package com.test.springboot.repository;

import com.test.springboot.dto.UserDto;
import com.test.springboot.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u from User u WHERE u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    //Below with query Methods
    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);
    //List<User> findByNameLikeOrderByIdDesc(String name); // Same than below

    List<User> findByNameContainingOrderByIdDesc(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate l1, LocalDate l2);

    @Query("SELECT new com.test.springboot.dto.UserDto(u.id, u.name, u.birthDate) FROM User u WHERE u.birthDate=:parametroFecha AND u.email=:parametroEmail ")
    Optional<UserDto> getAllByBirthDateAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email); // When param on query not present fails

    void deleteByEmail(String email);
}
