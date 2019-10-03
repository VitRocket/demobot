package com.github.vitrocket.demobot.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Subscribe extends SubscribeAbstract implements Comparable<Subscribe>, Serializable {

	private static final long serialVersionUID = 6182841141049574014L;

	@NonNull
	private Integer id;

	private ScribeType scribeType;

	private LocalTime time;

	private PeriodicityType periodicityType;

	private ScribeAction scribeAction;

	private boolean enable;

	private LocalDateTime lastDateDone;

	private LocalDateTime created;

	@Builder.Default
	private Set<TUser> tUsers = new HashSet<>();

	@Override
	public int compareTo(Subscribe subscribe) {
		return time.compareTo(subscribe.getTime());
	}
}
