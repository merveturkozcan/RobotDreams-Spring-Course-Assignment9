package com.robotdreams.assignment9.repository;

import com.robotdreams.assignment9.entity.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ListCrudRepository<User,Long> {

}
