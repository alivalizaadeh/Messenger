package com.av.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode
@ToString
@Scope(scopeName = BeanDefinition.SCOPE_PROTOTYPE)
public class User implements Serializable {
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

    @Column(unique = true)
    private String username;

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
    @Column(name = "MESSAGE_ID")
    private List<String> messages;

    @ElementCollection
    @CollectionTable(
            name = "TYPE_MESSAGES",
            joinColumns = @JoinColumn(
                    name = "MESSAGE_ID"
            )
    )
    @Column(name = "MESSAGE_TYPE")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<String> messageTypes;

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
