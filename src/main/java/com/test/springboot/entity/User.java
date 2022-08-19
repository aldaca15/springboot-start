package com.test.springboot.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false, unique = true)
    private Long id; // Better use long for bigApps

    @Column(length = 50)
    private String name;

    @Column(length = 50, unique = true) // If not present unique = true param DDL check will fail on start
    private String email;

    @Column(name = "birthday")
    //@XmlJavaTypeAdapter(value = LocalDateAdapter.class) // Adapter for testing process of marshalling BirthDate on post with xml
    @JsonSerialize(using = ToStringSerializer.class) // Adapter for testing process of marshalling BirthDate on post with JSON
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    //@JsonManagedReference
    private List<Post> posts = new ArrayList<>();

    public User() {
        // Empty default constructor
    }

    public User(String name, String email, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }

    public User(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate.toString() +
                ", posts=" + posts +
                '}';
    }

    public static boolean isUserEmailValid(String email) {
        if (email.split("@").length == 0) {
            System.err.println("User email not valid");
            return false;
        }
        String regex = "^(.+)@(.+)$"; // Email validator REGEX
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isUserEmailValid() {

        if (this.email.split("@").length == 0) {
            System.err.println("User email not valid, not @ symbol");
            return false;
        }
        String regex = "^(.+)@(.+)$"; // Email validator REGEX
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }
}
