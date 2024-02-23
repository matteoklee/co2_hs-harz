package de.kleemann.co2_hsharz.persistence.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This Interface is a {@link JpaRepository} for the {@link GroupEntity}
 *
 * @author Matteo Kleemann
 * @version 1.0
 * @since 06.12.2023
 */
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
	
	/**
	 * Tries to find a {@link GroupEntity} by its nickname, passphrase and size
	 * @param groupNickName {@link String} nickname
	 * @param groupPassPhrase {@link String} passphrase
	 * @param groupSize {@link Integer} size
	 * @return {@link GroupEntity} with this attributes or <code>null</code> if it couldn't be found
	 */
    GroupEntity findByGroupNickNameAndGroupPassPhraseAndGroupSize(String groupNickName, String groupPassPhrase, int groupSize);

}
