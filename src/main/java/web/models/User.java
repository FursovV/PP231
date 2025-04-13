package web.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Длина должна быть не менее 2 и не более 30 символов")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Длина должна быть не менее 2 и не более 30 символов")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Email(message = "Введите email")
    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}





