package org.example.testing_api_server.enties.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "account_profiles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountProfiles {
    @Id
    @MapsId
    @Column(name = "account_id", nullable = false)
    private Integer account_id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Size(max = 50)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Size(max = 20)
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 10)
    @NotNull
    @ColumnDefault("'en-US'")
    @Column(name = "locale", nullable = false, length = 10)
    private String locale;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("'UTC'")
    @Column(name = "timezone", nullable = false, length = 50)
    private String timezone;

    @Column(name = "avatar_url", length = Integer.MAX_VALUE)
    private String avatarUrl;

}
