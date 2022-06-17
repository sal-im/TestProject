package tn.esprit.auditing;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import tn.esprit.entites.Command;

/**
 * Custom AuditorAware implementation for Spring-Auditing of '@lastModifidBy'
 * and '@lastCreatedBy' {@link Command}. Auditing logic for Modifier and Creator
 * are implemented here
 *
 */

public class AuditorAwareImpl implements AuditorAware<String> {

	private static final String NOMEN_NESCIO = "N.N.";

	@Override
	public Optional<String> getCurrentAuditor() {
		// String actorName = ActorContextHolder.getActor().getName();
		String actorName = System.getProperty("user.name");
		return actorName != null ? Optional.of(actorName) : Optional.of(NOMEN_NESCIO);
	}
}