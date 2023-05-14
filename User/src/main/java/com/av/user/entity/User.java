package com.av.user.entity;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@ToString
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence_generator")
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "firstname must be not null.")
    @Column(length = 15, nullable = false)
    private String firstname;

    @Column(length = 15)
    private String lastname;

    @Column(length = 100)
    private String bio;

    @UniqueElements(message = "username must be unique.")
    @Column(unique = true)
    private String username;

    @UniqueElements(message = "phoneNumber must be unique.")
    @Column(unique = true)
    private String phoneNumber;

    @Column
    private byte [] profilePicture;

    @ElementCollection
    @CollectionTable(
            name = "MESSAGES",
            joinColumns = @JoinColumn(
                    name = "USER_ID"
            )
    )
    @Column(name = "MESSAGES_ID")
    private List<String> messages;

    @ElementCollection
    @CollectionTable(
            name = "TYPE_MESSAGES" ,
            joinColumns = @JoinColumn(
                    name = "MESSAGE_ID"
            )
    )
    private List<MessageTypes> messageTypes;

    @ElementCollection
    @CollectionTable(
            name = "CONTACTS",
            joinColumns = @JoinColumn(
                    name = "USER_ID"
            )
    )
    @Column(name = "CONTACTS_ID")
    private List<String> contacts;
}
