package com.av.message.repository;

import com.av.message.entity.Message;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

@Repository
@Scope(scopeName = BeanDefinition.SCOPE_SINGLETON)
public interface MessageRepository extends JpaRepository<Message , String> {
}
