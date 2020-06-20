package com.bootstudy.study.data;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

@Table(name = "users")
@Entity
@Getter @Setter
public class User extends DaoDtoImpl {
    private static final long serialVersionUID = 23942934790L;

    @OriginalStates
    User originalStates;

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String password;
    String firstName;
    String lastName;
    String email;
    String tel;
    String zip;
    String address;
    Long uploadFileId;

    @Transient
    UploadFile uploadFile;
}
