package com.github.vitrocket.demobot.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Telegram User
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TUser implements Serializable {

	private static final long serialVersionUID = 9216028820683817632L;

	private Integer id;

	@NonNull
	private Integer tId;

	@NonNull
	private String firstName;

	private String lastName;

	private String userName;

	@NonNull
	private String languageCode;

	@NonNull
	private Boolean isBot;

	private LocalDateTime created;

	private Integer version;

	private Set<Subscribe> subscribes = new HashSet<>();

}