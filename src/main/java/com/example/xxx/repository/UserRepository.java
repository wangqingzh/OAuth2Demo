package com.example.xxx.repository;

import com.example.xxx.domain.Role;
import com.example.xxx.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * 简单查询
     * @param userName
     * @return
     */
    User getUserByUsername(String userName);

    /**
     * 多字段查询方法 JPQL 还有一种Map转换的办法即json(查询出来的Obecjt[]转换)->object
     * @param uid
     * @return
     */
    @Query(value = "select new com.example.xxx.domain.Role(r.id, r.name, r.nameZh) " +
            "from com.example.xxx.domain.Role r,com.example.xxx.domain.UserRole ur where r.id=ur.rid and ur.uid= ?1")
    List<Role> getUserRolesByUid(Integer uid);
}
